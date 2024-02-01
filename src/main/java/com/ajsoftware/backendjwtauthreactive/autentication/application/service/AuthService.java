package com.ajsoftware.backendjwtauthreactive.autentication.application.service;


import com.ajsoftware.backendjwtauthreactive.autentication.application.port.AuthPort;
import com.ajsoftware.backendjwtauthreactive.autentication.domain.service.UserService;
import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.config.PBKDF2Encoder;
import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.config.jwt.JWTUtil;
import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.repository.UserRepository;
import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.web.dto.request.LoginRequestDto;
import com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.web.dto.response.AuthResponse;
import com.ajsoftware.backendjwtauthreactive.util.CustomErrorCode;
import com.ajsoftware.backendjwtauthreactive.util.ResponseRest;
import com.ajsoftware.backendjwtauthreactive.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class AuthService implements AuthPort {
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final ResponseUtil responseUtil = new ResponseUtil();
    private PBKDF2Encoder passwordEncoder;


    @Autowired
    public AuthService(UserService userService, JWTUtil jwtUtil, PBKDF2Encoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<ResponseEntity<ResponseRest<AuthResponse>>> login(LoginRequestDto loginRequestDto) {

        return userService.findByUserName(loginRequestDto.getUserName())
                 .flatMap(userEntity -> {
                     if (passwordEncoder.encode(loginRequestDto.getPassword()).equals(userEntity.getPassword())) {
                         AuthResponse authResponse = AuthResponse.builder().token(jwtUtil.getToken(userEntity)).build();
                         return responseUtil.createResponse(authResponse);
                     } else {
                         return Mono.error(new BadCredentialsException("Password does not match"));
                     }
                })
                .switchIfEmpty( responseUtil.handleErrorResponseGeneric(CustomErrorCode.USER_NOTFOUND.getMessage(), CustomErrorCode.USER_NOTFOUND.getCode(),  CustomErrorCode.USER_NOTFOUND.getHttpCode()))
                .onErrorResume(UsernameNotFoundException.class, e ->
                        responseUtil.handleErrorResponseGeneric(CustomErrorCode.USER_NOTFOUND.getMessage(), CustomErrorCode.USER_NOTFOUND.getCode(),  CustomErrorCode.USER_NOTFOUND.getHttpCode())
                )
                .onErrorResume(BadCredentialsException.class, e ->
                        responseUtil.handleErrorResponseGeneric(CustomErrorCode.PASS_INCORRECT.getMessage(), CustomErrorCode.PASS_INCORRECT.getCode(),CustomErrorCode.PASS_INCORRECT.getHttpCode())
                )
                .onErrorResume(DisabledException.class, e ->
                        responseUtil.handleErrorResponseGeneric(CustomErrorCode.USER_BLOCKED.getMessage(),  CustomErrorCode.USER_BLOCKED.getCode(),CustomErrorCode.USER_BLOCKED.getHttpCode())
                );
    }
}
