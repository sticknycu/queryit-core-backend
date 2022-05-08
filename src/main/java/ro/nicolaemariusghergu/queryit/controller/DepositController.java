package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.dto.DepositDto;
import ro.nicolaemariusghergu.queryit.service.DepositService;

import java.util.List;

import static ro.nicolaemariusghergu.queryit.BackEndApplication.FRONTEND_CORE_ADDRESS;

@Controller
@RequestMapping("/api/deposits")
@CrossOrigin(FRONTEND_CORE_ADDRESS)
public record DepositController(DepositService depositService) {

    @GetMapping("/v1")
    @ResponseBody
    public ResponseEntity<List<DepositDto>> getDeposits() {
        return depositService.getDeposits();
    }

    @GetMapping("/v1/{depositId}")
    @ResponseBody
    public ResponseEntity<DepositDto> getDepositById(@PathVariable Long depositId) {
        return depositService.findDepositById(depositId);
    }

    @PostMapping("/v1")
    @ResponseBody
    public ResponseEntity<Long> addDeposit(@RequestBody DepositDto productDto) {
        return depositService.addDeposit(productDto);
    }

    @GetMapping("/v1")
    @ResponseBody
    public ResponseEntity<DepositDto> getDepositByName(@RequestBody DepositDto depositDto) {
        return depositService.getDepositByName(depositDto.getName());
    }

    @PatchMapping("/v1")
    @ResponseBody
    public ResponseEntity<DepositDto> updateDeposit(@RequestBody DepositDto productDto) {
        return depositService.updateDeposit(productDto);
    }

    @DeleteMapping("/v1/{depositId}")
    @ResponseBody
    public ResponseEntity<Long> deleteDeposit(@PathVariable Long depositId) {
        return depositService.deleteDepositById(depositId);
    }
}




