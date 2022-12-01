package com.unq.desa.criptoP2P.config;

import com.unq.desa.criptoP2P.model.dto.IntentionDto;
import com.unq.desa.criptoP2P.model.dto.QuotationDto;
import com.unq.desa.criptoP2P.model.dto.UserDto;
import com.unq.desa.criptoP2P.model.intencion.Intention;
import com.unq.desa.criptoP2P.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class MapperComponent {
    @Autowired
    private ModelMapper modelMapper;

    public <S, T> List<T> ToList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public <S, T> T To(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public UserDto toUserDto(User user) {
        var name = user.getName().split(" ");
        var result = UserDto.builder()
                .id(user.getId())
                .address(user.getAddress())
                .cvu(user.getCvu())
                .email(user.getEmail())
                .walletAddress(user.getWalletAddress())
                .firstName(name[0])
                .lastName(name[1])
                .build();
        return result;
    }

    public List<UserDto> toListUsersDto(List<User> all) {
        return all.stream().map(user -> toUserDto(user)).collect(Collectors.toList());
    }

    public List<IntentionDto> toListIntentions(List<Intention> intentions) {
        return intentions.stream().map(i -> toIntentionDto(i)).collect(Collectors.toList());
    }

    public IntentionDto toIntentionDto(Intention intention) {
    var result =  IntentionDto.builder().id(intention.getId()).isActive(intention.isActive())
            .quotation(QuotationDto.builder().symbolCryptocurrency(intention.getQuotation().getSymbol())
                    .dayAndTime(intention.getQuotation().getDayAndTime()).build()).operacion(intention.getOperacion())
            .amountOfOperationInPesos(intention.getAmountOfOperationInPesos())
            .build();
    return result;
    }
}
