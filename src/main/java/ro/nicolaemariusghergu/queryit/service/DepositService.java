package ro.nicolaemariusghergu.queryit.service;

import org.springframework.http.ResponseEntity;
import ro.nicolaemariusghergu.queryit.dto.DepositDto;

import java.util.List;

public interface DepositService {
    ResponseEntity<DepositDto> findDepositById(Long id);

    ResponseEntity<List<DepositDto>> getDeposits();

    ResponseEntity<Long> addDeposit(DepositDto depositDto);

    ResponseEntity<DepositDto> getDepositByName(String name);

    ResponseEntity<Long> deleteDepositById(Long id);

    ResponseEntity<DepositDto> updateDeposit(DepositDto depositDto);
}
