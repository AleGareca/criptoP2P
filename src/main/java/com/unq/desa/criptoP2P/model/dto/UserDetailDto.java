package com.unq.desa.criptoP2P.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class UserDetailDto {
    private String firstName;
    private String lastName;
    @NotNull
    private String email;

}
