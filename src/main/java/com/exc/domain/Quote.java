package com.exc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.Objects;


/**
 * quotes history, could be migrated to the mongoDB
 */

@MappedSuperclass
public abstract class Quote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "created")
    private ZonedDateTime created;

    @Column(name = "jhi_value", precision=10, scale=2)
    private BigInteger value;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public Quote created(ZonedDateTime created) {
        this.created = created;
        return this;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public BigInteger getValue() {
        return value;
    }

    public Quote value(BigInteger value) {
        this.value = value;
        return this;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Quote quote = (Quote) o;
        if (quote.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quote.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Quote{" +
            "id=" + getId() +
            ", created='" + getCreated() + "'" +
            ", value=" + getValue() +
            "}";
    }
}
