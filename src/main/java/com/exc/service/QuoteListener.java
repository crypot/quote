package com.exc.service;

import com.exc.service.dto.QuoteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Main quotes consumption class
 */
@Component
public class QuoteListener {
    private final Logger log = LoggerFactory.getLogger(QuoteListener.class);
    private final QuoteService quoteService;

    public QuoteListener(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    public void handleMessage(QuoteDTO quoteDTO) {
        log.info("New incoming quote with pair {}/{}", quoteDTO.getBuy(), quoteDTO.getSell());
        quoteService.save(quoteDTO);
    }
}
