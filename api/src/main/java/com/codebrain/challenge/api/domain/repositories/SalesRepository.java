package com.codebrain.challenge.api.domain.repositories;

import com.codebrain.challenge.api.domain.entities.Sale;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface SalesRepository extends CrudRepository<Sale, Long> {
}
