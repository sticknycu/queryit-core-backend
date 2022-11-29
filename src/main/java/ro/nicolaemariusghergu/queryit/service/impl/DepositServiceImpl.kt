package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.dto.DepositDto;
import ro.nicolaemariusghergu.queryit.mapper.DepositMapper;
import ro.nicolaemariusghergu.queryit.repository.DepositRepository;
import ro.nicolaemariusghergu.queryit.service.DepositService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public record DepositServiceImpl(DepositRepository depositRepository) implements DepositService {

    @Override
    public ResponseEntity<DepositDto> findDepositById(Long id) {
        return ResponseEntity.ok(depositRepository.findById(id).stream()
                .map(DepositMapper.INSTANCE::depositToDepositDto)
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException("Deposit does not exist!"))
        );
    }

    @Override
    public ResponseEntity<List<DepositDto>> getDeposits() {
        return ResponseEntity.ok(depositRepository.findAll()
                .stream()
                .map(DepositMapper.INSTANCE::depositToDepositDto)
                .toList());
    }

    @Override
    public ResponseEntity<Long> addDeposit(DepositDto depositDto) {
        depositRepository.save(DepositMapper.INSTANCE.depositDtoToDeposit(depositDto));
        return ResponseEntity.ok(depositDto.getId());
    }

    @Override
    public ResponseEntity<DepositDto> getDepositByName(String name) {
        return ResponseEntity.ok(DepositMapper.INSTANCE.depositToDepositDto(
                depositRepository.findByName(name)
                        .orElseThrow(() ->
                                new NoSuchElementException("Deposit cannot be found"))
        ));
    }

    @Override
    public ResponseEntity<Long> deleteDepositById(Long id) {
        depositRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<DepositDto> updateDeposit(DepositDto depositDto) {
        DepositDto modifiedDeposit = depositRepository.findById(depositDto.getId()).stream()
                .map(DepositMapper.INSTANCE::depositToDepositDto)
                .map(updatedDeposit -> {
                    updatedDeposit.setName(depositDto.getName());
                    return updatedDeposit;
                })
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException("Deposit has not been found")
                );
        depositRepository.save(
                DepositMapper.INSTANCE.depositDtoToDeposit(modifiedDeposit));
        return ResponseEntity.ok(modifiedDeposit);
    }

}
