package com.codebrain.challenge.api.http.controllers;

import com.codebrain.challenge.api.domain.entities.Salesperson;
import com.codebrain.challenge.api.domain.filters.SalespersonFilter;
import com.codebrain.challenge.api.domain.services.SalespersonService;
import com.codebrain.challenge.api.http.responses.SalespeopleResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

import java.util.Optional;

@ExecuteOn(TaskExecutors.IO)
@Controller("/salespeople")
public class SalespeopleController {

    @Inject
    SalespersonService salespersonService;

    @Post
    public HttpResponse<Salesperson> create(Salesperson salesperson) {
        return HttpResponse.created(this.salespersonService.create(salesperson));
    }

    @Put("/{id}")
    public Salesperson update(Long id, Salesperson salesperson) {
        salesperson.setId(id);
        return this.salespersonService.update(salesperson);
    }

    @Delete("/{id}")
    public void remove(Long id) {
        this.salespersonService.remove(id);
    }

    @Get("/{id}")
    public Optional<Salesperson> get(Long id) {
        return this.salespersonService.get(id);
    }

    @Get
    public SalespeopleResponse findByFilters(@QueryValue Optional<String> registration) {
        SalespersonFilter salespersonFilter = new SalespersonFilter(registration.orElse(null));
        return new SalespeopleResponse(this.salespersonService.findByFilters(salespersonFilter));
    }

}
