package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;
    
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTopico status;
    
    @Column(nullable = false)
    private String autor;
    
    @Column(nullable = false)
    private String curso;
    
    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
        if (status == null) {
            status = StatusTopico.ABERTO;
        }
    }
} 