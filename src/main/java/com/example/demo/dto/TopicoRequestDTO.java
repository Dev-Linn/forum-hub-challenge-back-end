package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TopicoRequestDTO {
    
    @NotBlank(message = "Título é obrigatório")
    private String titulo;
    
    @NotBlank(message = "Mensagem é obrigatória")
    private String mensagem;
    
    @NotBlank(message = "Autor é obrigatório")
    private String autor;
    
    @NotBlank(message = "Curso é obrigatório")
    private String curso;
} 