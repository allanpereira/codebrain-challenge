package com.codebrain.challenge.api;

import com.codebrain.challenge.api.domain.entities.Salesperson;
import com.codebrain.challenge.api.http.responses.SalespeopleResponse;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import java.util.List;

@MicronautTest
class SalespeopleTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void shouldCreateSalesperson() {
        Salesperson salesperson = new Salesperson("Carl Ross", "RG-020520");

        HttpRequest<Salesperson> request = HttpRequest.POST("/salespeople", salesperson);
        HttpResponse<Salesperson> response = client.toBlocking().exchange(request, Salesperson.class);

        assertResponseStatus(201, response);
        assertSalesperson(salesperson, response.body());
    }

    @Test
    void shouldUpdateSalesperson() {
        Salesperson salesperson = new Salesperson("Jane Roe", "RG-050100");

        HttpRequest<Salesperson> request = HttpRequest.PUT("/salespeople/2", salesperson);
        HttpResponse<Salesperson> response = client.toBlocking().exchange(request, Salesperson.class);

        assertResponseStatus(200, response);
        assertSalesperson(salesperson, response.body(), 2L);
    }

    @Test
    void shouldRemoveSalesperson() {
        HttpRequest<?> request = HttpRequest.DELETE("/salespeople/3");
        HttpResponse<?> response = client.toBlocking().exchange(request);

        assertResponseStatus(200, response);

        HttpRequest<Salesperson> findSalespersonRequest = HttpRequest.GET("/salespeople/3");

        final HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class, () -> {
            client.toBlocking().exchange(findSalespersonRequest, Salesperson.class);
        });

        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void shouldFindSalesperson() {
        Salesperson salesperson = new Salesperson(1L, "John Doe", "RG-000001");

        HttpRequest<Salesperson> request = HttpRequest.GET("/salespeople/1");
        HttpResponse<Salesperson> response = client.toBlocking().exchange(request, Salesperson.class);

        assertResponseStatus(200, response);
        assertSalesperson(salesperson, response.body(), salesperson.getId());
    }

    @Test
    void shouldFindSalespersonByRegistration() {
        SalespeopleResponse salespeople = new SalespeopleResponse(List.of(new Salesperson(1L, "John Doe", "RG-000001")));

        HttpRequest<Salesperson> request = HttpRequest.GET("/salespeople?registration=RG-000001");
        HttpResponse<SalespeopleResponse> response = client.toBlocking().exchange(request, SalespeopleResponse.class);

        assertResponseStatus(200, response);

        Assertions.assertNotNull(response.body());

        Assertions.assertEquals(salespeople.getSalespeople(), response.body().getSalespeople());
    }

    private void assertResponseStatus(int expectedStatus, HttpResponse<?> response) {
        Assertions.assertEquals(expectedStatus, response.getStatus().getCode());
    }

    private void assertSalesperson(Salesperson expected, Salesperson actual) {
        this.assertSalesperson(expected, actual, null);
    }

    private void assertSalesperson(Salesperson expected, Salesperson actual, Long expectedId) {
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getRegistration(), actual.getRegistration());

        if (expectedId != null) {
            Assertions.assertEquals(expectedId, actual.getId());
        }
    }
}
