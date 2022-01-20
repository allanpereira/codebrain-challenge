package com.codebrain.challenge.api.domain.services;

import com.codebrain.challenge.api.domain.entities.Salesperson;
import com.codebrain.challenge.api.domain.filters.SalespersonFilter;
import com.codebrain.challenge.api.domain.repositories.SalespersonRepository;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class SalespersonService {

    @Inject
    SalespersonRepository salespersonRepository;

    public Salesperson create(Salesperson salesperson) {
        return this.salespersonRepository.save(salesperson);
    }

    public Salesperson update(Salesperson salesperson) {
        return this.salespersonRepository.update(salesperson);
    }

    public void remove(Long id) {
        this.salespersonRepository.deleteById(id);
    }

    public Optional<Salesperson> get(Long id) {
        return this.salespersonRepository.findById(id);
    }

    public List<Salesperson> findByFilters(SalespersonFilter filter) {
        return this.salespersonRepository.findByFilters(filter.getRegistration());
    }

}
