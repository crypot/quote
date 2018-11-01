package com.exc.repository;

import com.exc.domain.Quote;
import org.springframework.stereotype.Component;

@Component
public class QuoteRepositoryFactory {

    private final QuoteEthBtcRepository quoteEthBtcRepository;
    private final QuoteEtcBtcRepository quoteEtcBtcRepository;

    public QuoteRepositoryFactory(QuoteEthBtcRepository quoteEthBtcRepository, QuoteEtcBtcRepository quoteEtcBtcRepository) {
        this.quoteEthBtcRepository = quoteEthBtcRepository;
        this.quoteEtcBtcRepository = quoteEtcBtcRepository;
    }


    public QuoteRepository<Quote> getBean(String buy, String sell) {
        QuoteRepository res = null;
        String key = buy + "-" + sell;
        switch (key) {
            case "eth-btc":
                res = quoteEthBtcRepository;
                break;
            case "etc-btc":
                res = quoteEtcBtcRepository;
                break;
        }

        return res;
    }
}
