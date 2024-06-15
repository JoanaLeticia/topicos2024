package com.skinstore.model.converterjpa;

import com.skinstore.model.Exterior;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ExteriorConverter implements AttributeConverter<Exterior, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Exterior exterior) {
        return (exterior == null ? null : exterior.getId());
    }

    @Override
    public Exterior convertToEntityAttribute(Integer id) {
        return Exterior.valueOf(id);
    }
    
}