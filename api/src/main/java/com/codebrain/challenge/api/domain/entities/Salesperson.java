package com.codebrain.challenge.api.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "salespeople")

@Where(clause = "deleted = false")
public class Salesperson {

    public Salesperson() {
    }

    public Salesperson(@NotNull Long id, @NotNull String name, @NotNull String registration) {
        this(name, registration);
        this.id = id;
    }

    public Salesperson(@NotNull String name, @NotNull String registration) {
        this.name = name;
        this.registration = registration;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "registration", nullable = false, unique = true)
    private String registration;

    @NotNull
    @Column(name = "deleted", nullable = false, columnDefinition = "BIT(1) DEFAULT false")
    @JsonIgnore
    private boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salesperson that = (Salesperson) o;

        return deleted == that.deleted &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(registration, that.registration);
    }
}