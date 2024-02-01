package com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.repository;

import com.ajsoftware.backendjwtauthreactive.autentication.domain.model.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {
    Mono<UserEntity> findByUserName(String userName);

}
