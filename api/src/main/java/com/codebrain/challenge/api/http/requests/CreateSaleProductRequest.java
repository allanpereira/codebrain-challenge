package com.codebrain.challenge.api.http.requests;

public class CreateSaleProductRequest {

    private Long id;
    private Integer quantity;

    public CreateSaleProductRequest() {
    }

    public CreateSaleProductRequest(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
