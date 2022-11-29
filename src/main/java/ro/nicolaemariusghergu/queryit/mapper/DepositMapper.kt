package ro.nicolaemariusghergu.queryit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.nicolaemariusghergu.queryit.dto.DepositDto;
import ro.nicolaemariusghergu.queryit.model.Deposit;

@Mapper
public interface DepositMapper {

    DepositMapper INSTANCE = Mappers.getMapper(DepositMapper.class);

    DepositDto depositToDepositDto(Deposit deposit);

    Deposit depositDtoToDeposit(DepositDto depositDto);

    default String mapEmptyString(String string) {
        return string != null && !string.isEmpty() ? string : null;
    }
}
