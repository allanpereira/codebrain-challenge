package com.codebrain.challenge.api.http.controllers;

import com.codebrain.challenge.api.domain.entities.Product;
import com.codebrain.challenge.api.domain.entities.Sale;
import com.codebrain.challenge.api.domain.entities.SaleItem;
import com.codebrain.challenge.api.domain.entities.Salesperson;
import com.codebrain.challenge.api.domain.filters.SalespersonFilter;
import com.codebrain.challenge.api.domain.services.SalesService;
import com.codebrain.challenge.api.domain.services.SalespersonService;
import com.codebrain.challenge.api.http.requests.CreateSaleRequest;
import com.codebrain.challenge.api.http.responses.SalespeopleResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.stream.Collectors;

@ExecuteOn(TaskExecutors.IO)
@Controller("/sales")
public class SalesController {

    @Inject
    SalesService salesService;

    @Post
    public HttpResponse<Sale> create(CreateSaleRequest createSaleRequest) {
        Sale sale = new Sale();
        sale.setSalesperson(new Salesperson(createSaleRequest.getSalespersonId()));
        sale.setItems(createSaleRequest.getProducts().stream().map(i -> {
            SaleItem saleItem = new SaleItem();
            saleItem.setProduct(new Product(i.getId()));
            saleItem.setQuantity(i.getQuantity());
            return saleItem;
        }).collect(Collectors.toList()));

        return HttpResponse.created(this.salesService.create(sale));
    }

}
