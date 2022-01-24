package com.codebrain.challenge.api;

import com.codebrain.challenge.api.domain.entities.Sale;
import com.codebrain.challenge.api.domain.entities.SaleItem;
import com.codebrain.challenge.api.domain.entities.Salesperson;
import com.codebrain.challenge.api.http.requests.CreateSaleProductRequest;
import com.codebrain.challenge.api.http.requests.CreateSaleRequest;
import com.codebrain.challenge.api.http.responses.SalespeopleResponse;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@MicronautTest
class SalesTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void shouldCreateSale() {
        CreateSaleRequest createSaleRequest = new CreateSaleRequest(1L, List.of(
                new CreateSaleProductRequest(1L, 1),
                new CreateSaleProductRequest(2L, 1),
                new CreateSaleProductRequest(3L, 4)
        ));

        HttpRequest<CreateSaleRequest> request = HttpRequest.POST("/sales", createSaleRequest);
        HttpResponse<Sale> response = client.toBlocking().exchange(request, Sale.class);

        Assertions.assertEquals(201, response.getStatus().getCode());

        Sale sale = response.body();
        Assertions.assertNotNull(sale);
        Assertions.assertNotNull(sale.getId());
        Assertions.assertEquals(359994L, sale.getTotalPrice());
        Assertions.assertEquals(3, sale.getItems().size());
    }
}
