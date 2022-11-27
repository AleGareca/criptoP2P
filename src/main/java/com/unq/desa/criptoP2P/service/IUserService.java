package com.unq.desa.criptoP2P.service;

import com.unq.desa.criptoP2P.model.dto.ActiveCryptoReportDto;
import com.unq.desa.criptoP2P.model.dto.UserDto;

import java.time.LocalDate;
import java.util.List;

public interface IUserService {
     List<UserDto> get();

    UserDto getById(Integer id);

     void delete(String id);

    void updateUser(String name, UserDto user);

    ActiveCryptoReportDto generateReport(String email, LocalDate initDate, LocalDate endDate);

    UserDto getByMail(String name);

    void registerUser(UserDto user);
}
