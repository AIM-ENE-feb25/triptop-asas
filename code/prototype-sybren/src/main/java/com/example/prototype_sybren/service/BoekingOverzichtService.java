package com.example.prototype_sybren.service;

import com.example.prototype_sybren.adapter.BookingComAdapter;
import com.example.prototype_sybren.domain.Boeking;
import com.example.prototype_sybren.repository.BoekingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoekingOverzichtService implements BoekingInterface {
    private final BoekingRepository boekingRepository;
    private final BookingComAdapter bookingComAdapter;

    @Autowired
    public BoekingOverzichtService(BoekingRepository boekingRepository, BookingComAdapter bookingComAdapter) {
        this.boekingRepository = boekingRepository;
        this.bookingComAdapter = bookingComAdapter;
    }

    @Override
    public String haalInterneBoekingenOp() {
        return boekingRepository.haalOvernachtingenOp();
    }

    @Override
    public String haalExterneBoekingenOp() {
        return bookingComAdapter.haalOvernachtingenOp();
    }

    public String haalOvernachtingenOp() {
        String interneBoekingen = haalInterneBoekingenOp();
        String externeBoekingen = haalExterneBoekingenOp();
        return interneBoekingen + externeBoekingen;
    }



}
