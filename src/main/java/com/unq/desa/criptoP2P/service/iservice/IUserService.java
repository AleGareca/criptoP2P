package com.unq.desa.criptoP2P.service.iservice;

import com.unq.desa.criptoP2P.model.dto.ActiveCryptoReportDto;
import com.unq.desa.criptoP2P.model.dto.UserDto;

import java.time.LocalDate;
import java.util.List;

public interface IUserService {
     List<UserDto> get();

    UserDto getById(Integer id);

     void delete(Integer id);

    void updateUser(UserDto user);

    ActiveCryptoReportDto generateReport(Integer id, LocalDate initDate, LocalDate endDate);
}
