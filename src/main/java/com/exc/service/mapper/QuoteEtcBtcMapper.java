package com.exc.service.mapper;

import com.exc.domain.QuoteEtcBtc;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Quote and its DTO QuoteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuoteEtcBtcMapper extends QuoteEntityMapper<QuoteEtcBtc> {


    default QuoteEtcBtc fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuoteEtcBtc quote = new QuoteEtcBtc();
        quote.setId(id);
        return quote;
    }
}
