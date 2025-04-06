package com.fundacion_habacuc.sistema_de_gestion.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fundacion_habacuc.sistema_de_gestion.entity.User;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String role;
    private String email;
    private String password;
    private User User;
    private String profilePicture;
    private List<User> UsersList;

}