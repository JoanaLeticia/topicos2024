package com.skinstore.model;

public enum Arma {
    AK47(1, "AK-47"),
    M4A4(2, "M4A4"),
    COLT(3, "Colt"),
    AWP(4, "AWP"),

    GLOCK(5, "Glock"),
    USP(6, "USP"),

    MP9(7, "MP9"),
    MP7(8, "MP7"),
    MAC10(9, "MAC-10"),

    NOVA(10, "Nova"),
    XM(11, "XM1014"),

    KARAMBIT(12, "Karambit"),
    BUTTERFLY(13, "Butterfly"),
    BAIONETA(14, "Baioneta"),

    ESPECIALISTA(15, "Luvas de Especialista"),
    FAIXAS(16, "Faixas"),
    MOTORISTA(17, "Luvas de Motoristas");

    private final Integer id;
    private final String label;

    Arma(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Arma valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Arma arma : Arma.values()) {
            if (arma.getId().equals(id))
                return arma;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }

}
