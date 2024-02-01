package com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.repository;

import com.ajsoftware.backendjwtauthreactive.autentication.domain.model.RoleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ReactiveCrudRepository<RoleEntity,Long> {
}
