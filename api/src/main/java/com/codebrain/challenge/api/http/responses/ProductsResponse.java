package com.codebrain.challenge.api.http.responses;

import com.codebrain.challenge.api.domain.entities.Pagination;
import com.codebrain.challenge.api.domain.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsResponse {

    private List<Product> products;
    private Pagination pagination;

    public ProductsResponse() {
        this.products = new ArrayList<>();
    }

    public ProductsResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
