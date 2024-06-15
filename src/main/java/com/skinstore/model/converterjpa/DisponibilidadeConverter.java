package com.skinstore.model.converterjpa;

import com.skinstore.model.Disponibilidade;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DisponibilidadeConverter implements AttributeConverter<Disponibilidade, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Disponibilidade disponibilidade) {
        return (disponibilidade == null ? null : disponibilidade.getId());
    }

    @Override
    public Disponibilidade convertToEntityAttribute(Integer id) {
        return Disponibilidade.valueOf(id);
    }
    
}
