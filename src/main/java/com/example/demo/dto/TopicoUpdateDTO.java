package com.example.demo.dto;

import com.example.demo.model.StatusTopico;
import lombok.Data;

@Data
public class TopicoUpdateDTO {
    
    private String titulo;
    private String mensagem;
    private StatusTopico status;
    private String autor;
    private String curso;
} 