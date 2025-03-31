package com.example.prototype_sybren.service;

import com.example.prototype_sybren.adapter.BookingComAdapter;
import com.example.prototype_sybren.repository.BoekingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoekingOverzichtService {
    private final BoekingRepository boekingRepository;
    private final BookingComAdapter bookingComAdapter;

    @Autowired
    public BoekingOverzichtService(BoekingRepository boekingRepository, BookingComAdapter bookingComAdapter) {
        this.boekingRepository = boekingRepository;
        this.bookingComAdapter = bookingComAdapter;
    }

    public String haalOvernachtingenOp() {
        return bookingComAdapter.haalExterneOvernachtingenOp();
    }

}
