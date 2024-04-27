package com.skinstore.service;

import java.util.List;

import com.skinstore.model.Arma;
import com.skinstore.model.Tipo;

public interface ArmaService {
    List<Arma> getModelosPorTipo(Tipo tipo);
}
