package com.yate.catalog.controller;

import com.yate.catalog.dto.YachtCreateDTO;
import com.yate.catalog.dto.YachtResponseDTO;
import com.yate.catalog.service.YachtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/catalog/yachts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class YachtController {
    
    private final YachtService yachtService;
    
    @GetMapping
    public ResponseEntity<List<YachtResponseDTO>> getAllYachts(
            @RequestParam(required = false, defaultValue = "false") boolean onlyAvailable) {
        List<YachtResponseDTO> yachts = onlyAvailable ? 
            yachtService.getAvailableYachts() : 
            yachtService.getAllYachts();
        return ResponseEntity.ok(yachts);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<YachtResponseDTO> getYachtById(@PathVariable Long id) {
        return yachtService.getYachtById(id)
                .map(yacht -> ResponseEntity.ok(yacht))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<YachtResponseDTO> createYacht(@Valid @RequestBody YachtCreateDTO createDTO) {
        YachtResponseDTO createdYacht = yachtService.createYacht(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdYacht);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<YachtResponseDTO> updateYacht(
            @PathVariable Long id, 
            @Valid @RequestBody YachtCreateDTO updateDTO) {
        return yachtService.updateYacht(id, updateDTO)
                .map(yacht -> ResponseEntity.ok(yacht))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteYacht(@PathVariable Long id) {
        boolean deleted = yachtService.deleteYacht(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    @PatchMapping("/{id}/availability")
    public ResponseEntity<YachtResponseDTO> setYachtAvailability(
            @PathVariable Long id, 
            @RequestParam boolean available) {
        return yachtService.setYachtAvailability(id, available)
                .map(yacht -> ResponseEntity.ok(yacht))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<YachtResponseDTO>> searchYachts(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String yachtType,
            @RequestParam(required = false) Integer minCapacity,
            @RequestParam(required = false) BigDecimal maxPrice) {
        
        List<YachtResponseDTO> yachts = yachtService.searchYachts(location, yachtType, minCapacity, maxPrice);
        return ResponseEntity.ok(yachts);
    }
    
    @GetMapping("/type/{yachtType}")
    public ResponseEntity<List<YachtResponseDTO>> getYachtsByType(@PathVariable String yachtType) {
        List<YachtResponseDTO> yachts = yachtService.getYachtsByType(yachtType);
        return ResponseEntity.ok(yachts);
    }
    
    @GetMapping("/location")
    public ResponseEntity<List<YachtResponseDTO>> getYachtsByLocation(@RequestParam String location) {
        List<YachtResponseDTO> yachts = yachtService.getYachtsByLocation(location);
        return ResponseEntity.ok(yachts);
    }
}
