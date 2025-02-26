package com.fundacion_habacuc.sistema_de_gestion.dto;


import com.fundacion_habacuc.sistema_de_gestion.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;
}
