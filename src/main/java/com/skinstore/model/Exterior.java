package com.skinstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Exterior {
    FN(1, "Factory New"),
    MW(2, "Minimal Wear"),
    FT(3, "Field Tested"),
    WW(4, "Well Worn"),
    BS(5, "Battle Scarred");

    private final Integer id;
    private final String label;

    Exterior(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Exterior valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Exterior exterior : Exterior.values()) {
            if (exterior.getId().equals(id))
                return exterior;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }

}
