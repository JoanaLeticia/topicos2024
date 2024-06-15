package com.skinstore.model.converterjpa;

import com.skinstore.model.Arma;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ArmaConverter implements AttributeConverter<Arma, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Arma arma) {
        return (arma == null ? null : arma.getId());
    }

    @Override
    public Arma convertToEntityAttribute(Integer id) {
        return Arma.valueOf(id);
    }
    
}
