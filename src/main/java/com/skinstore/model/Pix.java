package com.skinstore.model;

import jakarta.persistence.Entity;

@Entity
public class Pix extends DefaultEntity{
    private String chavePix;
    
    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

}