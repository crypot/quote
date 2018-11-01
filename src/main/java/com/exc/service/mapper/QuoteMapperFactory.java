package com.exc.service.mapper;

import org.springframework.stereotype.Component;

@Component
public class QuoteMapperFactory {
    private final QuoteEtcBtcMapper quoteEtcBtcMapper;
    private final QuoteEthBtcMapper quoteEthBtcMapper;

    public QuoteMapperFactory(QuoteEtcBtcMapper quoteEtcBtcMapper, QuoteEthBtcMapper quoteEthBtcMapper) {
        this.quoteEtcBtcMapper = quoteEtcBtcMapper;
        this.quoteEthBtcMapper = quoteEthBtcMapper;
    }


    public QuoteEntityMapper getBean(String buy, String sell) {
        QuoteEntityMapper res = null;
        String key = buy + "-" + sell;

        switch (key) {
            case "eth-btc":
                res = quoteEthBtcMapper;
                break;
            case "etc-btc":
                res = quoteEtcBtcMapper;
                break;
        }

        return res;
    }
}
