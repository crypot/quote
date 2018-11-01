package com.exc.service.mapper;


import com.exc.domain.Quote;
import com.exc.service.dto.QuoteDTO;

import java.util.List;

public interface QuoteEntityMapper<E extends Quote> extends EntityMapper<QuoteDTO, E> {
    E fromId(Long id);

    @Override
    E toEntity(QuoteDTO dto);

    @Override
    QuoteDTO toDto(E entity);

    @Override
    List<E> toEntity(List<QuoteDTO> dtoList);

    @Override
    List<QuoteDTO> toDto(List<E> entityList);
}
