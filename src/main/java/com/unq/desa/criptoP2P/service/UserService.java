package com.unq.desa.criptoP2P.service;


import com.unq.desa.criptoP2P.exeption.DataIntentionNotFound;
import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.dto.ActiveCryptoReportDto;
import com.unq.desa.criptoP2P.model.dto.UserDto;
import com.unq.desa.criptoP2P.model.dto.UserRegisterDto;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.IIntentionRepository;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IIntentionRepository intentionRepository;

    @Autowired
    private MapperComponent modelMapper;

    public List<UserDto> get(){
        return modelMapper.toListUsersDto(this.userRepository.findAll());
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    public UserDto getById(Integer id) {
        return modelMapper.toUserDto(this.userRepository.findById(id).get());
    }

    public void delete(String email) {
        this.userRepository.deleteByEmail(email);
    }

    @Override
    public void registerUser(UserRegisterDto user) {
        var encriptor = new BCryptPasswordEncoder();
        var password = encriptor.encode(user.getPassword());
        var userRegister = User.builder()
                .name(user.getFirstName()+" "+ user.getLastName())
                .address(user.getAddress())
                .cvu(user.getCvu())
                .email(user.getEmail())
                .password(password)
                .walletAddress(user.getWalletAddress()).build();
        userRepository.save(userRegister);
    }

    @Override
    public ActiveCryptoReportDto generateReport(String email, LocalDate initDate, LocalDate endDate) {
        var intentions = intentionRepository.findIntentionByUserCriptoAndQuotationDayAndTimeBetween(email, initDate.atStartOfDay(), endDate.atStartOfDay());
        if(intentions.isEmpty()){
            throw new DataIntentionNotFound("The user did not make any intention to buy or sell");
        }
        var sumValue = intentions.stream().mapToInt(i ->i.getAmountOfOperationInPesos()).sum();
        //var activos = intentions.stream().map(i ->ActivosDto.builder()
                                                                            //.price(i.getQuotation().getCryptoOcurrency().getPrice()).getPrice())
                                                                            //.symbol(i.getQuotation().getCryptocurrency().getSymbol()).build()).collect(Collectors.toList());
        //var dolarSumValue = intentions.stream().mapToDouble(i ->i.getQuotation().getCryptoOcurrency().getPrice()).sum();

        return ActiveCryptoReportDto.builder()
                .argValue(sumValue)
                .date(LocalDateTime.now()).build();
                //.dolarValue(dolarSumValue)
                //.activosDto(activos).build();
    }

    @Override
    public UserDto getByMail(String name) {
        return this.modelMapper.toUserDto(userRepository.findByEmail(name));
    }

    @Override
    public void updateUser(String email, UserDto user){
        var userUpdate = userRepository.findByEmail(email);
        user.setId(userUpdate.getId());
        userRepository.save(userUpdate);
    }


}
