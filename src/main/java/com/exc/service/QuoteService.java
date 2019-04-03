package com.exc.service;

import com.exc.domain.Quote;
import com.exc.repository.QuoteRepository;
import com.exc.repository.QuoteRepositoryFactory;
import com.exc.service.dto.QuoteDTO;
import com.exc.service.mapper.QuoteEntityMapper;
import com.exc.service.mapper.QuoteMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service Implementation for managing Quote.
 */
@Service
@Transactional
public class QuoteService {

    private final Logger log = LoggerFactory.getLogger(QuoteService.class);


    private final QuoteMapperFactory quoteMapperFactory;

    private final QuoteRepositoryFactory quoteRepositoryFactory;


    public QuoteService(QuoteMapperFactory quoteMapperFactory, QuoteRepositoryFactory quoteRepositoryFactory) {
        this.quoteMapperFactory = quoteMapperFactory;
        this.quoteRepositoryFactory = quoteRepositoryFactory;
    }


    /**
     * Save a quote.
     *
     * @param quoteDTO the entity to save
     * @return the persisted entity
     */
    public QuoteDTO save(QuoteDTO quoteDTO) {
        log.debug("Request to save Quote : {}", quoteDTO);
        quoteDTO.setId(null);
        QuoteEntityMapper mapper = quoteMapperFactory.getBean(quoteDTO.getBuy(), quoteDTO.getSell());
        Quote quote = mapper.toEntity(quoteDTO);
        QuoteRepository repo = quoteRepositoryFactory.getBean(quoteDTO.getBuy(), quoteDTO.getSell());

        quote = repo.save(quote);
        return mapper.toDto(quote);
    }

    /**
     * Get all the quotes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<QuoteDTO> findAll(Pageable pageable, String buy, String sell) {
        log.debug("Request to get all Quotes");
        QuoteEntityMapper mapper = quoteMapperFactory.getBean(buy, sell);
        QuoteRepository repo = quoteRepositoryFactory.getBean(buy, sell);
        return repo.findAll(pageable)
            .map(q -> mapper.toDto((Quote) q));
    }


    /**
     * Get one quote by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public QuoteDTO findOne(Long id, String buy, String sell) {
        log.debug("Request to get Quote : {}", id);
        QuoteEntityMapper mapper = quoteMapperFactory.getBean(buy, sell);
        QuoteRepository repo = quoteRepositoryFactory.getBean(buy, sell);

        Quote quote = (Quote) repo.findById(id).orElse(null);
        return mapper.toDto(quote);
    }

    @Transactional(readOnly = true)
    public List<QuoteDTO> getByPeriod(String buy, String sell, ZonedDateTime startZ, ZonedDateTime endZ) {
        log.debug("Request to get Quote by period {} {}", startZ, endZ);
        QuoteEntityMapper mapper = quoteMapperFactory.getBean(buy, sell);
        QuoteRepository repo = quoteRepositoryFactory.getBean(buy, sell);
        return mapper.toDto(repo.findByCreatedBetween(startZ, endZ));

    }


}
