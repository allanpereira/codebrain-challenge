package com.codebrain.challenge.api.domain.services;

import com.codebrain.challenge.api.domain.entities.Product;
import com.codebrain.challenge.api.domain.repositories.ProductRepository;
import jakarta.inject.Inject;

import java.util.Optional;

public class ProductService {

    @Inject
    ProductRepository productRepository;

    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    public Product update(Product product) {
        return this.productRepository.update(product);
    }

    public void remove(Long id) {
        this.productRepository.deleteById(id);
    }

    public Optional<Product> get(Long id) {
        return this.productRepository.findById(id);
    }

}
