package com.yate.service.controller;

import com.yate.service.model.Booking;
import com.yate.service.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return service.save(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping("/{id}/pay")
    public Booking confirmPayment(@PathVariable Long id) {
        return service.confirmPayment(id);
    }
}
