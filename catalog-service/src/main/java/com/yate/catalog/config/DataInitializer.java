package com.yate.catalog.config;

import com.yate.catalog.model.Yacht;
import com.yate.catalog.repository.YachtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    
    @Bean
    CommandLineRunner initDatabase(YachtRepository repository) {
        return args -> {
            // Solo inicializar si no hay datos
            if (repository.count() == 0) {
                
                Yacht yacht1 = new Yacht();
                yacht1.setName("Sea Princess");
                yacht1.setDescription("Lujoso yate de motor perfecto para vacaciones familiares");
                yacht1.setPricePerDay(new BigDecimal("2500.00"));
                yacht1.setCapacity(8);
                yacht1.setLocation("Acapulco");
                yacht1.setYachtType("MOTOR");
                yacht1.setLength(18.5);
                yacht1.setCabins(4);
                yacht1.setBathrooms(3);
                yacht1.setCrew(3);
                yacht1.setAmenities(Arrays.asList("WiFi", "Aire acondicionado", "Cocina completa", "Equipo de snorkel"));
                yacht1.setImageUrls(Arrays.asList("https://example.com/yacht1_1.jpg", "https://example.com/yacht1_2.jpg"));
                yacht1.setAvailable(true);
                
                Yacht yacht2 = new Yacht();
                yacht2.setName("Ocean Dream");
                yacht2.setDescription("Elegante yate de vela para aventuras náuticas");
                yacht2.setPricePerDay(new BigDecimal("1800.00"));
                yacht2.setCapacity(6);
                yacht2.setLocation("Puerto Vallarta");
                yacht2.setYachtType("SAILING");
                yacht2.setLength(15.2);
                yacht2.setCabins(3);
                yacht2.setBathrooms(2);
                yacht2.setCrew(2);
                yacht2.setAmenities(Arrays.asList("Velas automáticas", "GPS", "Radio VHF", "Equipo de pesca"));
                yacht2.setImageUrls(Arrays.asList("https://example.com/yacht2_1.jpg"));
                yacht2.setAvailable(true);
                
                Yacht yacht3 = new Yacht();
                yacht3.setName("Paradise Catamaran");
                yacht3.setDescription("Espacioso catamarán ideal para grupos grandes");
                yacht3.setPricePerDay(new BigDecimal("3200.00"));
                yacht3.setCapacity(12);
                yacht3.setLocation("Cancún");
                yacht3.setYachtType("CATAMARAN");
                yacht3.setLength(22.0);
                yacht3.setCabins(6);
                yacht3.setBathrooms(4);
                yacht3.setCrew(4);
                yacht3.setAmenities(Arrays.asList("Jacuzzi", "Bar", "Parrilla", "Sistema de sonido", "Kayaks"));
                yacht3.setImageUrls(Arrays.asList("https://example.com/yacht3_1.jpg", "https://example.com/yacht3_2.jpg", "https://example.com/yacht3_3.jpg"));
                yacht3.setAvailable(true);
                
                repository.saveAll(Arrays.asList(yacht1, yacht2, yacht3));
            }
        };
    }
}
