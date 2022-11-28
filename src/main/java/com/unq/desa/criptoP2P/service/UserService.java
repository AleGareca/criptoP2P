package com.unq.desa.criptoP2P.service;


import com.unq.desa.criptoP2P.exeption.DataIntentionNotFound;
import com.unq.desa.criptoP2P.config.MapperComponent;
import com.unq.desa.criptoP2P.model.dto.ActiveCryptoReportDto;
import com.unq.desa.criptoP2P.model.dto.ActivosDto;
import com.unq.desa.criptoP2P.model.dto.UserDto;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.persistence.IIntentionRepository;
import com.unq.desa.criptoP2P.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IIntentionRepository intentionRepository;

    @Autowired
    private MapperComponent modelMapper;

    public List<UserDto> get(){
        return modelMapper.ToList(this.userRepository.findAll(), UserDto.class);
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    public UserDto getById(Integer id) {
        return modelMapper.To(this.userRepository.findById(id).get(),UserDto.class);
    }

    public void delete(String email) {
        this.userRepository.deleteByEmail(email);
    }

    @Override
    public void registerUser(UserDto user) {
        userRepository.save(this.modelMapper.To(user, User.class));
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
        return this.modelMapper.To(userRepository.findByEmail(name), UserDto.class);
    }

    @Override
    public void updateUser(String email, UserDto user){
        var userUpdate = userRepository.findByEmail(email);
        user.setId(userUpdate.getId());
        userRepository.save(userUpdate);
    }


}
