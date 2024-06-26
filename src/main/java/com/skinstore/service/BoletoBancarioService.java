package com.skinstore.service;

import java.util.List;

import com.skinstore.dto.BoletoBancarioDTO;
import com.skinstore.dto.BoletoBancarioResponseDTO;

public interface BoletoBancarioService {
    public BoletoBancarioResponseDTO insert(BoletoBancarioDTO dto);
    public BoletoBancarioResponseDTO update(BoletoBancarioDTO dto, Long id);
    public void delete(Long id);
    public BoletoBancarioResponseDTO findById(Long id);
    public List<BoletoBancarioResponseDTO> findByNumeroBoleto(String numero);
    public List<BoletoBancarioResponseDTO> findByAll(); 
}
