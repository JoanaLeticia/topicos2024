package com.skinstore.service;

import com.skinstore.dto.UsuarioResponseDTO;

public interface JwtService {

    public String generateJwt(UsuarioResponseDTO dto);
    
}
