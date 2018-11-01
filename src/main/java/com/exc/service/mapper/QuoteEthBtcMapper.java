package com.exc.service.mapper;

import com.exc.domain.QuoteEthBtc;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Quote and its DTO QuoteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuoteEthBtcMapper extends QuoteEntityMapper<QuoteEthBtc> {

    default QuoteEthBtc fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuoteEthBtc quote = new QuoteEthBtc();
        quote.setId(id);
        return quote;
    }
}
