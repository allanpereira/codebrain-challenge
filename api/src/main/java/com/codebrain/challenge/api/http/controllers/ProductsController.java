package com.codebrain.challenge.api.http.controllers;

import com.codebrain.challenge.api.domain.entities.Product;
import com.codebrain.challenge.api.domain.services.ProductService;
import com.codebrain.challenge.api.http.responses.ProductsResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

import java.util.Optional;

@ExecuteOn(TaskExecutors.IO)
@Controller("/products")
public class ProductsController {

    @Inject
    ProductService productService;

    @Post
    public HttpResponse<Product> create(Product product) {
        return HttpResponse.created(this.productService.create(product));
    }

    @Put("/{id}")
    public Product update(Long id, Product product) {
        product.setId(id);
        return this.productService.update(product);
    }

    @Delete("/{id}")
    public void remove(Long id) {
        this.productService.remove(id);
    }

    @Get("/{id}")
    public Optional<Product> get(Long id) {
        return this.productService.get(id);
    }

    @Get
    public ProductsResponse find() {
        return new ProductsResponse(this.productService.find());
    }

}
