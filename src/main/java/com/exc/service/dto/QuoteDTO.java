package com.exc.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Quote entity.
 */
public class QuoteDTO implements Serializable {

    private Long id;

    private ZonedDateTime created;

    private BigDecimal value;

    private String buy;
    private String sell;

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuoteDTO quoteDTO = (QuoteDTO) o;
        if (quoteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quoteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuoteDTO{" +
            "id=" + getId() +
            ", created='" + getCreated() + "'" +
            ", value=" + getValue() +
            "}";
    }
}
