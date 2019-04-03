package com.exc.config.kryo;

import com.exc.config.KryoContext;
import com.exc.service.dto.QuoteDTO;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;


public class KryoRedisQuoteSerializer implements RedisSerializer<QuoteDTO> {
    private KryoContext kryoContext;

    public KryoRedisQuoteSerializer(KryoContext kryoContext) {
        this.kryoContext = kryoContext;
    }

    @Override
    public byte[] serialize(QuoteDTO quoteDTO) throws SerializationException {
        return kryoContext.ser(quoteDTO);
    }

    @Override
    public QuoteDTO deserialize(byte[] bytes) throws SerializationException {
        return (QuoteDTO) kryoContext.des(QuoteDTO.class, bytes);
    }
}
