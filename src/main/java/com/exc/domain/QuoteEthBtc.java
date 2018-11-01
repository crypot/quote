package com.exc.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "quote_eth_btc")
@ApiModel(description = "quotes history, could be migrated to the mongoDB")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QuoteEthBtc extends Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eth_btc_seq")
    @SequenceGenerator(name = "eth_btc_seq", sequenceName = "eth_btc_seq", allocationSize = 1)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
