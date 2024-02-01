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
@Table("ajscore.person")
public class PersonEntity {


    @Id
    private Long id;
    private Long documentTypeId;
    private String documentId;
    private String firstName;
    private String secondName;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private Long phoneNumber;
    private String address;

}
