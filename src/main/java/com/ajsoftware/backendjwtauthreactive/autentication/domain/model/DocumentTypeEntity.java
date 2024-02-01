package com.ajsoftware.backendjwtauthreactive.autentication.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("ajscore.documenttype")
public class DocumentTypeEntity{
    @Id
    private Long id;
    private String documentName;
    private String description;
    private String status;
    private String abbreviation;

}
