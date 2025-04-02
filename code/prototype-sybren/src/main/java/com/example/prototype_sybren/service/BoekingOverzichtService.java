package com.example.prototype_sybren.service;

import com.example.prototype_sybren.adapter.BookingComAdapter;
import com.example.prototype_sybren.domain.Boeking;
import com.example.prototype_sybren.domain.Overnachting;
import com.example.prototype_sybren.dto.OvernachtingResponse;
import com.example.prototype_sybren.repository.BoekingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoekingOverzichtService implements BoekingInterface {
    private final BoekingRepository boekingRepository;
    private final BookingComAdapter bookingComAdapter;
    private final InternBoekingSysteem internBoekingSysteem;

    @Autowired
    public BoekingOverzichtService(BoekingRepository boekingRepository, BookingComAdapter bookingComAdapter, InternBoekingSysteem internBoekingSysteem) {
        this.boekingRepository = boekingRepository;
        this.bookingComAdapter = bookingComAdapter;
        this.internBoekingSysteem = internBoekingSysteem;
    }

    @Override
    public List<OvernachtingResponse> haalInterneBoekingenOp(Overnachting overnachting) {
        return internBoekingSysteem.haalOvernachtingenOp(overnachting);
    }

    @Override
    public List<OvernachtingResponse> haalExterneBoekingenOp(Overnachting overnachting) {
        return bookingComAdapter.haalOvernachtingenOp(overnachting);
    }

    public List<OvernachtingResponse> haalOvernachtingenOp(Overnachting overnachting) {
        List<OvernachtingResponse> interneOvernachtingen = haalInterneBoekingenOp(overnachting);
        List<OvernachtingResponse> externeOvernachtingen = haalExterneBoekingenOp(overnachting);
        List<OvernachtingResponse> overnachtingen = new ArrayList<>(externeOvernachtingen);
        overnachtingen.removeAll(interneOvernachtingen);
        interneOvernachtingen.addAll(overnachtingen);

        return interneOvernachtingen;
    }

}
