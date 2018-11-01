package com.exc.service;

import com.exc.service.dto.QuoteDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuoteServiceTest extends AbstractServiceTest {
    @Autowired
    QuoteService quoteService;
    String b = "eth", s = "btc";

    @Test
    public void shouldGetByDateRange() {
        QuoteDTO quoteDTO = new QuoteDTO();
        quoteDTO.setBuy(b);
        quoteDTO.setSell(s);
        quoteDTO.setCreated(ZonedDateTime.now().minusDays(1));
        quoteDTO.setValue(BigDecimal.TEN);

        quoteService.save(quoteDTO);
        quoteDTO.setCreated(ZonedDateTime.now().minusDays(20));
        quoteService.save(quoteDTO);
        List<QuoteDTO> rs = quoteService.getByPeriod(b, s, ZonedDateTime.now().minusDays(2), ZonedDateTime.now());

        assertThat(rs.size()).isEqualTo(1);

    }
}
