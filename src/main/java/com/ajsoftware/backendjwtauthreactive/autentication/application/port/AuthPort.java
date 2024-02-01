package com.ajsoftware.backendjwtauthreactive.autentication.application.port;

import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.web.dto.request.LoginRequestDto;
import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.web.dto.response.AuthResponse;
import com.ajsoftware.backendjwtauthreactive.util.ResponseRest;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface AuthPort {
    Mono<ResponseEntity<ResponseRest<AuthResponse>>> login(LoginRequestDto loginRequestDto);
}
