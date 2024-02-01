package com.ajsoftware.backendjwtauthreactive.autentication.domain.service;

import com.ajsoftware.backendjwtauthreactive.autentication.domain.model.UserEntity;
import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.repository.PersonRepository;
import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.repository.RoleRepository;
import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.personRepository = personRepository;
    }

    public Mono<UserEntity> findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .flatMap(this::processUserEntity);
    }

    private Mono<UserEntity> processUserEntity(UserEntity userEntity) {
        Mono<UserEntity> resultMono = Mono.just(userEntity);

        if (userEntity.getIdRole() != null) {
            resultMono = resultMono.flatMap(this::getUserWithRole);
        }

        if (userEntity.getIdPerson() != null) {
            resultMono = resultMono.flatMap(this::getUserWithPerson);
        }

        return resultMono;
    }

    private Mono<UserEntity> getUserWithRole(UserEntity userEntity) {
        return roleRepository.findById(userEntity.getIdRole())
                .map(roleEntity -> {
                    userEntity.setRole(roleEntity);
                    return userEntity;
                });
    }

    private Mono<UserEntity> getUserWithPerson(UserEntity userEntity) {
        return personRepository.findById(userEntity.getIdPerson())
                .map(personEntity -> {
                    userEntity.setPerson(personEntity);
                    return userEntity;
                });
    }


}
