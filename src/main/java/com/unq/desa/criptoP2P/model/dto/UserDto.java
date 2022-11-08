package com.unq.desa.criptoP2P.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
public class UserDto {
    private int id;

    @Size(max = 30,min = 3, message = "first_name "+"La cantidad de caracteres debe ser 3 como minimo y como maximo 30.")
    @NotNull(message = "primer nombre requerido")
    private String fullName;
    //@Email
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "Mail invalido")
    @NotNull
    private String email;
    @Size(max = 30,min = 10, message = "address: "+"La cantidad de caracteres debe ser 10 como minimo y como maximo 30.")
    @NotNull
    private String address;
    @Size(min = 6, message = "password: "+"La cantidad de caracteres debe ser 6 como minimo.")
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "al menos 1 minuscula, 1 mayuscula, 1 caracter especia")
    private String password;
    @NotNull
    @Size(min = 22, message = "La cantidad de caracteres debe ser 22 como minimo")
    private String cvu;
    @NotNull
    @Size(min = 8, message = "La cantidad de caracteres debe ser 8 como minimo")
    private String walletAddress;
}
