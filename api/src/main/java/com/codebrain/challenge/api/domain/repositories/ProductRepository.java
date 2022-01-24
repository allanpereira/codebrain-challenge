package com.codebrain.challenge.api.domain.repositories;

import com.codebrain.challenge.api.domain.entities.Product;
import com.codebrain.challenge.api.domain.entities.Salesperson;
import com.codebrain.challenge.api.domain.filters.ProductFilter;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    @Query("UPDATE Product SET deleted = true WHERE id = :id")
    void deleteById(@NonNull @NotNull Long id);

    List<Product> findByIds(List<Long> ids);

    @Query("FROM Product p WHERE (:id IS NULL OR p.id = :id) AND (:name IS NULL OR p.name like :name)")
    List<Product> find(@Nullable Long id, @Nullable String name, Pageable pageable);
}