package com.skinstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Tipo {
    PISTOLA(1, "Pistola"),
    FACA(2, "Faca"),
    LUVA(3, "Luva"),
    RIFLE(4, "Rifle"),
    PESADA(5, "Pesada"),
    SMG(6, "SMG");

    private final Integer id;
    private final String label;

    Tipo(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Tipo valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Tipo tipo : Tipo.values()) {
            if (tipo.getId().equals(id))
                return tipo;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }

}
