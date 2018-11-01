package com.exc.repository;

import com.exc.domain.Quote;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data JPA repository for the Quote entity.
 */
@SuppressWarnings("unused")
//@Repository
@NoRepositoryBean
public interface QuoteRepository<T extends Quote> extends JpaRepository<T, Long> {
    List<T> findByCreatedBetween(
        ZonedDateTime start,
        ZonedDateTime end);

    @Override
    <S extends T> S save(S s);
}
