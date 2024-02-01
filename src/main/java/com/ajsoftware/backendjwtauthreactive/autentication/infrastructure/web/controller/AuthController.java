package com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.web.controller;


import com.ajsoftware.backendjwtauthreactive.autentication.application.port.AuthPort;
import com.ajsoftware.backendjwtauthreactive.autentication.domain.service.UserService;
import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.config.PBKDF2Encoder;
import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.config.jwt.JWTUtil;
import com.ajsoftware.backendjwtauthreactive.util.ResponseRest;
import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.web.dto.request.LoginRequestDto;
import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.web.dto.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${application.request.mappings}/v1/api/auth")
public class AuthController {
    private final AuthPort authPort;
    private JWTUtil jwtUtil;
    private PBKDF2Encoder passwordEncoder;
    private UserService userService;

    @PostMapping("/login")
    public Mono<ResponseEntity<ResponseRest<AuthResponse>>> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("Ingres a login");
        return authPort.login(loginRequestDto);
    }
/*
    @PostMapping("/login")
    public Mono<ResponseEntity<ResponseRest<AuthResponse>>> login(@RequestBody LoginRequestDto ar) {
        return userService.findByUserName(ar.getUserName())
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.getToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }*/
}
