package com.codebrain.challenge.api.domain.repositories;

import com.codebrain.challenge.api.domain.entities.Salesperson;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface SalespersonRepository extends CrudRepository<Salesperson, Long> {

    @Query("FROM Salesperson s WHERE (:registration IS NULL OR s.registration = :registration)")
    List<Salesperson> findByFilters(@Nullable String registration);

    @Override
    @Query("UPDATE Salesperson SET deleted = true WHERE id = :id")
    void deleteById(@NonNull @NotNull Long id);

}