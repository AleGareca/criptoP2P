package com.unq.desa.criptoP2P.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
public class UserDto {
    private int id;

    @Size(max = 30,min = 3, message = "first_name "+"Cantidad de caracteres invalidos")
    @NotNull(message = "primer nombre requerido")
    private String firstName;
    @Size(max = 30,min = 3, message = "last_name "+"Cantidad de caracteres invalidos")
    @NotNull
    private String lastName;
    //@Email
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "Mail no valido")
    @NotNull
    private String email;
    @Size(max = 30,min = 10, message = "address "+"Cantidad de caracteres invalidos")
    @NotNull
    private String address;

    @NotNull
    @Size(min = 22, message = "No es un CVU valido")
    private String cvu;

    @NotNull
    @Size(min = 8, message = "cantidad de digitos no validos")
    private String walletAddress;
}
