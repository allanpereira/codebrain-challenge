package com.codebrain.challenge.api.domain.services;

import com.codebrain.challenge.api.domain.entities.Product;
import com.codebrain.challenge.api.domain.entities.Sale;
import com.codebrain.challenge.api.domain.entities.SaleItem;
import com.codebrain.challenge.api.domain.repositories.ProductRepository;
import com.codebrain.challenge.api.domain.repositories.SalesRepository;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SalesService {

    @Inject
    ProductRepository productRepository;

    @Inject
    SalesRepository salesRepository;

    public Sale create(Sale sale) {
        List<Long> productIds = sale.getItems().stream().map(i -> i.getProduct().getId()).collect(Collectors.toList());
        List<Product> products = productRepository.findByIds(productIds);

        sale.getItems().forEach((saleItem -> {
            Optional<Product> foundProduct = products.stream()
                    .filter(product -> saleItem.getProduct().getId().equals(product.getId()))
                    .findFirst();

            saleItem.setPrice(foundProduct.map(product -> product.getPrice() * saleItem.getQuantity()).orElse(0L));
        }));

        Long totalPrice = sale.getItems().stream()
                .map(SaleItem::getPrice)
                .reduce(0L, Long::sum);

        sale.setTotalPrice(totalPrice);

        return this.salesRepository.save(sale);
    }

}
