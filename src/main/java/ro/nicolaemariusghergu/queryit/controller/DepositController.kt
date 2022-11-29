package ro.nicolaemariusghergu.queryit.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ro.nicolaemariusghergu.queryit.BackEndApplication
import ro.nicolaemariusghergu.queryit.dto.DepositDto

@Controller
@RequestMapping("/api/deposits")
@CrossOrigin(BackEndApplication.FRONTEND_CORE_ADDRESS)
class DepositController {
    @GetMapping("/v1")
    @ResponseBody
    fun getDeposits(): ResponseEntity<MutableList<DepositDto?>?>? {
        return depositService.getDeposits()
    }

    @GetMapping("/v1/{depositId}")
    @ResponseBody
    fun getDepositById(@PathVariable depositId: Long?): ResponseEntity<DepositDto?>? {
        return depositService.findDepositById(depositId)
    }

    @PostMapping("/v1")
    @ResponseBody
    fun addDeposit(@RequestBody productDto: DepositDto?): ResponseEntity<Long?>? {
        return depositService.addDeposit(productDto)
    }

    @GetMapping("/v1/deposit-name")
    @ResponseBody
    fun getDepositByName(@RequestBody depositDto: DepositDto?): ResponseEntity<DepositDto?>? {
        return depositService.getDepositByName(depositDto.getName())
    }

    @PatchMapping("/v1")
    @ResponseBody
    fun updateDeposit(@RequestBody productDto: DepositDto?): ResponseEntity<DepositDto?>? {
        return depositService.updateDeposit(productDto)
    }

    @DeleteMapping("/v1/{depositId}")
    @ResponseBody
    fun deleteDeposit(@PathVariable depositId: Long?): ResponseEntity<Long?>? {
        return depositService.deleteDepositById(depositId)
    }
}