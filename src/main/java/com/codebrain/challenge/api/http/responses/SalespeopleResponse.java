package com.codebrain.challenge.api.http.responses;

import com.codebrain.challenge.api.domain.entities.Salesperson;

import java.util.ArrayList;
import java.util.List;

public class SalespeopleResponse {

    private List<Salesperson> salespeople;

    public SalespeopleResponse() {
        this.salespeople = new ArrayList<>();
    }

    public SalespeopleResponse(List<Salesperson> salespeople) {
        this.salespeople = salespeople;
    }

    public List<Salesperson> getSalespeople() {
        return salespeople;
    }

    public void setSalespeople(List<Salesperson> salespeople) {
        this.salespeople = salespeople;
    }
}
