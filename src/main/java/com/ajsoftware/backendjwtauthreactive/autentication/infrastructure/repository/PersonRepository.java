package com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.repository;

import com.ajsoftware.backendjwtauthreactive.autentication.domain.model.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<PersonEntity,Long> {
}
