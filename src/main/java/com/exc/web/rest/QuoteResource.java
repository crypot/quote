package com.exc.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.exc.service.QuoteService;
import com.exc.service.dto.QuoteDTO;
import com.exc.web.rest.errors.BadRequestAlertException;
import com.exc.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * REST controller for managing Quote.
 */
@RestController
@RequestMapping("/api")
public class QuoteResource {

    private final Logger log = LoggerFactory.getLogger(QuoteResource.class);

    private static final String ENTITY_NAME = "quoteQuote";

    private QuoteService quoteService;

    public QuoteResource(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    /**
     * POST  /quotes : Create a new quote.
     *
     * @param quoteDTO the quoteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quoteDTO, or with status 400 (Bad Request) if the quote has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotes")
    @Timed
    public ResponseEntity<QuoteDTO> createQuote(@RequestBody QuoteDTO quoteDTO) throws URISyntaxException {
        log.debug("REST request to save Quote : {}", quoteDTO);
        if (quoteDTO.getId() != null) {
            throw new BadRequestAlertException("A new quote cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuoteDTO result = quoteService.save(quoteDTO);
        return ResponseEntity.created(new URI("/api/quotes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotes : Updates an existing quote.
     *
     * @param quoteDTO the quoteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quoteDTO,
     * or with status 400 (Bad Request) if the quoteDTO is not valid,
     * or with status 500 (Internal Server Error) if the quoteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotes")
    @Timed
    public ResponseEntity<QuoteDTO> updateQuote(@RequestBody QuoteDTO quoteDTO) throws URISyntaxException {
        log.debug("REST request to update Quote : {}", quoteDTO);
        if (quoteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuoteDTO result = quoteService.save(quoteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quoteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotes : get all the quotes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quotes in body
     */
   /* @GetMapping("/quotes")
    @Timed
    public ResponseEntity<List<QuoteDTO>> getAllQuotes(Pageable pageable) {
        log.debug("REST request to get a page of Quotes");
        Page<QuoteDTO> page = quoteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    *//**
     * GET  /quotes/:id : get the "id" quote.
     *
     * @param id the id of the quoteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quoteDTO, or with status 404 (Not Found)
     *//*
    @GetMapping("/quotes/{id}")
    @Timed
    public ResponseEntity<QuoteDTO> getQuote(@PathVariable Long id) {
        log.debug("REST request to get Quote : {}", id);
        Optional<QuoteDTO> quoteDTO = quoteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quoteDTO);
    }

    *//**
     * DELETE  /quotes/:id : delete the "id" quote.
     *
     * @param id the id of the quoteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     *//*
    @DeleteMapping("/quotes/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        log.debug("REST request to delete Quote : {}", id);
        quoteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }*/
}
