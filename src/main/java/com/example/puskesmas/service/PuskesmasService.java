package com.example.puskesmas.service;

import com.example.puskesmas.entity.Puskesmas;

import java.util.List;

public interface PuskesmasService {
    Puskesmas create(Puskesmas request);
    List<Puskesmas> getAll();
    Puskesmas getById(int id);
    Puskesmas update(Puskesmas request, int id);
    void delete(int id);
}
