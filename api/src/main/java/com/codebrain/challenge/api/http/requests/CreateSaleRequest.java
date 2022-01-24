package com.codebrain.challenge.api.http.requests;

import java.util.List;

public class CreateSaleRequest {

    private Long salespersonId;
    private List<CreateSaleProductRequest> products;

    public CreateSaleRequest() {
    }

    public CreateSaleRequest(Long salespersonId, List<CreateSaleProductRequest> products) {
        this.salespersonId = salespersonId;
        this.products = products;
    }

    public Long getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(Long salespersonId) {
        this.salespersonId = salespersonId;
    }

    public List<CreateSaleProductRequest> getProducts() {
        return products;
    }

    public void setProducts(List<CreateSaleProductRequest> products) {
        this.products = products;
    }
}
