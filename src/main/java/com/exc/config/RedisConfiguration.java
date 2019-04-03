package com.exc.config;

import com.exc.config.kryo.KryoRedisQuoteSerializer;
import com.exc.service.QuoteListener;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.Executors;

@Configuration
public class RedisConfiguration {
    private final Logger log = LoggerFactory.getLogger(RedisConfiguration.class);

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(Runtime.getRuntime().availableProcessors() * 2);
        genericObjectPoolConfig.setMaxIdle(8);
        genericObjectPoolConfig.setMinIdle(4);
        LettuceClientConfiguration cfg = LettucePoolingClientConfiguration
            .builder()
            .poolConfig(genericObjectPoolConfig)
            .build();
        RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
        return new LettuceConnectionFactory(redisConf, cfg);
    }


/*    @Bean
    public RedisTemplate getObjectRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }*/


    @Bean
    public MessageListenerAdapter messageListenerAdapter(QuoteListener quoteListener, KryoRedisQuoteSerializer serializer) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(quoteListener);
        messageListenerAdapter.setSerializer(serializer);

        return messageListenerAdapter;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer res = new RedisMessageListenerContainer();
        res.setConnectionFactory(redisConnectionFactory);
        res.setErrorHandler(t -> log.error(t.getMessage(), t));
        //todo: add pairs
        res.addMessageListener(messageListenerAdapter, new ChannelTopic("quotes"));
        int cores = Runtime.getRuntime().availableProcessors();


        res.setTaskExecutor(Executors.newFixedThreadPool(cores, target -> {
            final Thread thread = new Thread(target);
            thread.setName("red-task-");
            thread.setUncaughtExceptionHandler((t, e) -> log.error("Subscribe exception", e));
            return thread;
        }));
        return res;
    }
}
