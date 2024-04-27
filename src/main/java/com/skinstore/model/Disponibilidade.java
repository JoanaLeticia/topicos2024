package com.skinstore.model;

public enum Disponibilidade {
    PRONTAENTREGA(1, "Pronta Entrega"),
    UM(2, "1 dia"),
    DOIS(3, "2 dias"),
    TRES(4, "3 dias"),
    QUATRO(5, "4 dias"),
    CINCO(6, "5 dias"),
    SEIS(7, "6 dias"),
    SETE(8, "7 dias");

    private final Integer id;
    private final String label;

    Disponibilidade(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Disponibilidade valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Disponibilidade disponibilidade : Disponibilidade.values()) {
            if (disponibilidade.getId().equals(id))
                return disponibilidade;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }

}

