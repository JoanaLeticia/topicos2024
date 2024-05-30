package com.skinstore.service;

import com.skinstore.dto.UsuarioResponseDTO;

public interface JwtService {
    String generateJwt(UsuarioResponseDTO dto);    
}
