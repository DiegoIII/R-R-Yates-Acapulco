package com.yate.service.service;

import com.yate.service.model.Booking;
import com.yate.service.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public Booking save(Booking booking) {
        booking.setPaid(false);
        return repository.save(booking);
    }

    public List<Booking> findAll() {
        return repository.findAll();
    }

    public Booking findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Booking confirmPayment(Long id) {
        Booking booking = findById(id);
        if (booking != null) {
            booking.setPaid(true);
            return repository.save(booking);
        }
        return null;
    }
}
