package com.skinstore.service;

import java.util.ArrayList;
import java.util.List;

import com.skinstore.model.Arma;
import com.skinstore.model.Tipo;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArmaServiceImpl implements ArmaService {

    @Override
    public List<Arma> getModelosPorTipo(Tipo tipo) {
        List<Arma> modelos = new ArrayList<>();

        switch (tipo) {
            case PISTOLA:
                modelos.add(Arma.GLOCK);
                modelos.add(Arma.USP);
                break;
            case FACA:
                modelos.add(Arma.BAIONETA);
                modelos.add(Arma.KARAMBIT);
                modelos.add(Arma.BUTTERFLY);
                break;
            case RIFLE:
                modelos.add(Arma.AK47);
                modelos.add(Arma.M4A4);
                modelos.add(Arma.COLT);
                break;
            case LUVA:
                modelos.add(Arma.ESPECIALISTA);
                modelos.add(Arma.FAIXAS);
                modelos.add(Arma.MOTORISTA);
                break;
            case PESADA:
                modelos.add(Arma.NOVA);
                modelos.add(Arma.XM);
                break;
            case SMG:
                modelos.add(Arma.MP7);
                modelos.add(Arma.MP9);
                modelos.add(Arma.MAC10);
                break;
            default:
                break;
        }

        return modelos;
    }
}
