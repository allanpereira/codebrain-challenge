package com.codebrain.challenge.api.domain.repositories;

import com.codebrain.challenge.api.domain.entities.Product;
import io.micronaut.core.annotation.NonNull;
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

    List<Product> find(Pageable pageable);

}