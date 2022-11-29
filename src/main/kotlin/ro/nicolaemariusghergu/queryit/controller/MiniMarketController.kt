package ro.nicolaemariusghergu.queryit.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ro.nicolaemariusghergu.queryit.BackEndApplication
import ro.nicolaemariusghergu.queryit.dto.MiniMarketDto
import ro.nicolaemariusghergu.queryit.service.MiniMarketService

@Controller
@RequestMapping("/api/minimarkets")
class MiniMarketController(private val miniMarketService: MiniMarketService) {
    @GetMapping("/v1")
    @ResponseBody
    fun getMiniMarkets(): ResponseEntity<List<MiniMarketDto>> {
        return miniMarketService.getMiniMarkets()
    }

    @GetMapping("/v1/{miniMarketId}")
    @ResponseBody
    fun getMiniMarketById(@PathVariable miniMarketId: Long): ResponseEntity<MiniMarketDto> {
        return miniMarketService.findMiniMarketById(miniMarketId)
    }

    @GetMapping("/v1/minimarket-name")
    @ResponseBody
    fun getMiniMarketByName(@RequestBody miniMarketDto: MiniMarketDto): ResponseEntity<MiniMarketDto> {
        return miniMarketService.getMiniMarketByName(miniMarketDto.name)
    }

    @PostMapping("/v1")
    @ResponseBody
    fun addMiniMarket(@RequestBody miniMarketDto: MiniMarketDto): ResponseEntity<Long> {
        return miniMarketService.addMiniMarket(miniMarketDto)
    }

    @PatchMapping("/v1")
    @ResponseBody
    fun updateMiniMarket(@RequestBody miniMarketDto: MiniMarketDto): ResponseEntity<MiniMarketDto> {
        return miniMarketService.updateMiniMarket(miniMarketDto)
    }

    @DeleteMapping("/v1/{miniMarketId}")
    @ResponseBody
    fun deleteMiniMarket(@PathVariable miniMarketId: Long): ResponseEntity<Long> {
        return miniMarketService.deleteMiniMarketById(miniMarketId)
    }
}