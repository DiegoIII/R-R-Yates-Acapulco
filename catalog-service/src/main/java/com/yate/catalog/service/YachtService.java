package com.yate.catalog.service;

import com.yate.catalog.dto.YachtCreateDTO;
import com.yate.catalog.dto.YachtResponseDTO;
import com.yate.catalog.model.Yacht;
import com.yate.catalog.repository.YachtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class YachtService {
    
    private final YachtRepository yachtRepository;
    
    public List<YachtResponseDTO> getAllYachts() {
        return yachtRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public List<YachtResponseDTO> getAvailableYachts() {
        return yachtRepository.findByAvailableTrue()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<YachtResponseDTO> getYachtById(Long id) {
        return yachtRepository.findById(id)
                .map(this::convertToResponseDTO);
    }
    
    public YachtResponseDTO createYacht(YachtCreateDTO createDTO) {
        Yacht yacht = convertToEntity(createDTO);
        yacht.setAvailable(true);
        Yacht savedYacht = yachtRepository.save(yacht);
        return convertToResponseDTO(savedYacht);
    }
    
    public Optional<YachtResponseDTO> updateYacht(Long id, YachtCreateDTO updateDTO) {
        return yachtRepository.findById(id)
                .map(yacht -> {
                    updateYachtFromDTO(yacht, updateDTO);
                    Yacht savedYacht = yachtRepository.save(yacht);
                    return convertToResponseDTO(savedYacht);
                });
    }
    
    public boolean deleteYacht(Long id) {
        if (yachtRepository.existsById(id)) {
            yachtRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public Optional<YachtResponseDTO> setYachtAvailability(Long id, boolean available) {
        return yachtRepository.findById(id)
                .map(yacht -> {
                    yacht.setAvailable(available);
                    Yacht savedYacht = yachtRepository.save(yacht);
                    return convertToResponseDTO(savedYacht);
                });
    }
    
    public List<YachtResponseDTO> searchYachts(String location, String yachtType, 
                                               Integer minCapacity, BigDecimal maxPrice) {
        return yachtRepository.findYachtsWithFilters(location, yachtType, minCapacity, maxPrice)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public List<YachtResponseDTO> getYachtsByType(String yachtType) {
        return yachtRepository.findByYachtTypeAndAvailableTrue(yachtType)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public List<YachtResponseDTO> getYachtsByLocation(String location) {
        return yachtRepository.findByLocationContainingIgnoreCaseAndAvailableTrue(location)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    private YachtResponseDTO convertToResponseDTO(Yacht yacht) {
        YachtResponseDTO dto = new YachtResponseDTO();
        dto.setId(yacht.getId());
        dto.setName(yacht.getName());
        dto.setDescription(yacht.getDescription());
        dto.setPricePerDay(yacht.getPricePerDay());
        dto.setCapacity(yacht.getCapacity());
        dto.setLocation(yacht.getLocation());
        dto.setYachtType(yacht.getYachtType());
        dto.setLength(yacht.getLength());
        dto.setCabins(yacht.getCabins());
        dto.setBathrooms(yacht.getBathrooms());
        dto.setCrew(yacht.getCrew());
        dto.setAmenities(yacht.getAmenities());
        dto.setImageUrls(yacht.getImageUrls());
        dto.setAvailable(yacht.getAvailable());
        dto.setCreatedAt(yacht.getCreatedAt());
        dto.setUpdatedAt(yacht.getUpdatedAt());
        return dto;
    }
    
    private Yacht convertToEntity(YachtCreateDTO createDTO) {
        Yacht yacht = new Yacht();
        updateYachtFromDTO(yacht, createDTO);
        return yacht;
    }
    
    private void updateYachtFromDTO(Yacht yacht, YachtCreateDTO dto) {
        yacht.setName(dto.getName());
        yacht.setDescription(dto.getDescription());
        yacht.setPricePerDay(dto.getPricePerDay());
        yacht.setCapacity(dto.getCapacity());
        yacht.setLocation(dto.getLocation());
        yacht.setYachtType(dto.getYachtType());
        yacht.setLength(dto.getLength());
        yacht.setCabins(dto.getCabins());
        yacht.setBathrooms(dto.getBathrooms());
        yacht.setCrew(dto.getCrew());
        yacht.setAmenities(dto.getAmenities());
        yacht.setImageUrls(dto.getImageUrls());
    }
}
