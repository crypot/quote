package com.exc.config.kryo;

import com.exc.config.DefaultKryoContext;
import com.exc.service.dto.QuoteDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KryoConfig {
    @Bean
    public KryoRedisQuoteSerializer kryoRedisQuoteSerializer() {
      return new KryoRedisQuoteSerializer(DefaultKryoContext
          .newKryoContextFactory(kryo -> kryo.register(QuoteDTO.class)));
    }
}

