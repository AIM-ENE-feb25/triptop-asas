package com.example.prototype_sybren.service;

import com.example.prototype_sybren.domain.Overnachting;
import com.example.prototype_sybren.dto.OvernachtingResponse;

import java.util.List;

public interface BoekingInterface {

    List<OvernachtingResponse> haalInterneBoekingenOp(Overnachting overnachting);
    List<OvernachtingResponse> haalExterneBoekingenOp(Overnachting overnachting);
}
