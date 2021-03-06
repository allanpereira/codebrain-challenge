package com.codebrain.challenge.api;

import com.codebrain.challenge.api.domain.entities.Product;
import com.codebrain.challenge.api.http.responses.ProductsResponse;
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

@MicronautTest
class ProductsTest {

    private final String PLACEHOLDER_IMG = "https://via.placeholder.com/150";

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void shouldCreateProduct() {
        Product product = new Product("Apple - 10.2-Inch iPad (Latest Model) with Wi-Fi - 64GB", 32999L, PLACEHOLDER_IMG);

        HttpRequest<Product> request = HttpRequest.POST("/products", product);
        HttpResponse<Product> response = client.toBlocking().exchange(request, Product.class);

        assertResponseStatus(201, response);
        assertProduct(product, response.body());
    }

    @Test
    void shouldUpdateProduct() {
        Product product = new Product("Apple iPhone 13 256GB", 99999L, PLACEHOLDER_IMG);

        HttpRequest<Product> request = HttpRequest.PUT("/products/2", product);
        HttpResponse<Product> response = client.toBlocking().exchange(request, Product.class);

        assertResponseStatus(200, response);
        assertProduct(product, response.body(), 2L);
    }

    @Test
    void shouldRemoveProduct() {
        HttpRequest<?> request = HttpRequest.DELETE("/products/3");
        HttpResponse<?> response = client.toBlocking().exchange(request);

        assertResponseStatus(200, response);

        HttpRequest<Product> findProductRequest = HttpRequest.GET("/products/3");

        final HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class, () -> {
            client.toBlocking().exchange(findProductRequest, Product.class);
        });

        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void shouldFindProducts() {
        HttpRequest<Product> request = HttpRequest.GET("/products");
        HttpResponse<ProductsResponse> response = client.toBlocking().exchange(request, ProductsResponse.class);

        assertResponseStatus(200, response);
        Assertions.assertNotNull(response.body());
        Assertions.assertNotNull(response.body().getProducts());
    }

    @Test
    void shouldFindProductsById() {
        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setName("Apple MacBook Air 8GB RAM 256GB SSD");
        expectedProduct.setImageUrl("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/5721/5721600_sd.jpg;maxHeight=640;maxWidth=550");
        expectedProduct.setPrice(89999L);
        expectedProduct.setDeleted(false);

        HttpRequest<Product> request = HttpRequest.GET("/products?id=1");
        HttpResponse<ProductsResponse> response = client.toBlocking().exchange(request, ProductsResponse.class);

        assertResponseStatus(200, response);
        Assertions.assertNotNull(response.body());
        Assertions.assertNotNull(response.body().getProducts());
        Assertions.assertEquals(1L, response.body().getProducts().size());

        assertProduct(expectedProduct, response.body().getProducts().get(0));
    }

    @Test
    void shouldFindProductsByName() {
        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setName("Apple MacBook Air 8GB RAM 256GB SSD");
        expectedProduct.setImageUrl("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/5721/5721600_sd.jpg;maxHeight=640;maxWidth=550");
        expectedProduct.setPrice(89999L);
        expectedProduct.setDeleted(false);

        HttpRequest<Product> request = HttpRequest.GET("/products?name=apple%20macbook");
        HttpResponse<ProductsResponse> response = client.toBlocking().exchange(request, ProductsResponse.class);

        assertResponseStatus(200, response);
        Assertions.assertNotNull(response.body());
        Assertions.assertNotNull(response.body().getProducts());
        Assertions.assertEquals(1L, response.body().getProducts().size());

        assertProduct(expectedProduct, response.body().getProducts().get(0));
    }

    private void assertResponseStatus(int expectedStatus, HttpResponse<?> response) {
        Assertions.assertEquals(expectedStatus, response.getStatus().getCode());
    }

    private void assertProduct(Product expected, Product actual) {
        this.assertProduct(expected, actual, null);
    }

    private void assertProduct(Product expected, Product actual, Long expectedId) {
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
        Assertions.assertEquals(expected.getImageUrl(), actual.getImageUrl());

        if (expectedId != null) {
            Assertions.assertEquals(expectedId, actual.getId());
        }
    }
}
