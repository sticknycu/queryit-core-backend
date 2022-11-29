package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.dto.MiniMarketDto;
import ro.nicolaemariusghergu.queryit.service.MiniMarketService;

import java.util.List;

import static ro.nicolaemariusghergu.queryit.BackEndApplication.FRONTEND_CORE_ADDRESS;

@Controller
@RequestMapping("/api/minimarkets")
@CrossOrigin(FRONTEND_CORE_ADDRESS)
public record MiniMarketController(MiniMarketService miniMarketService) {

    @GetMapping("/v1")
    @ResponseBody
    public ResponseEntity<List<MiniMarketDto>> getMiniMarkets() {
        return miniMarketService.getMiniMarkets();
    }

    @GetMapping("/v1/{miniMarketId}")
    @ResponseBody
    public ResponseEntity<MiniMarketDto> getMiniMarketById(@PathVariable Long miniMarketId) {
        return miniMarketService.findMiniMarketById(miniMarketId);
    }

    @GetMapping("/v1/minimarket-name")
    @ResponseBody
    public ResponseEntity<MiniMarketDto> getMiniMarketByName(@RequestBody MiniMarketDto miniMarketDto) {
        return miniMarketService.getMiniMarketByName(miniMarketDto.getName());
    }

    @PostMapping("/v1")
    @ResponseBody
    public ResponseEntity<Long> addMiniMarket(@RequestBody MiniMarketDto miniMarketDto) {
        return miniMarketService.addMiniMarket(miniMarketDto);
    }

    @PatchMapping("/v1")
    @ResponseBody
    public ResponseEntity<MiniMarketDto> updateMiniMarket(@RequestBody MiniMarketDto miniMarketDto) {
        return miniMarketService.updateMiniMarket(miniMarketDto);
    }

    @DeleteMapping("/v1/{miniMarketId}")
    @ResponseBody
    public ResponseEntity<Long> deleteMiniMarket(@PathVariable Long miniMarketId) {
        return miniMarketService.deleteMiniMarketById(miniMarketId);
    }
}
