package com.ajsoftware.backendjwtauthreactive.autentication.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("ajscore.role")
public class RoleEntity{

    @Id
    private Long id;

    private String roleName;

    private String description;

    private String status;

}
