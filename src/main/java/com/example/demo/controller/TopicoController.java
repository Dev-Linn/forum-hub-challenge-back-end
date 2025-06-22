package com.example.demo.controller;

import com.example.demo.dto.TopicoRequestDTO;
import com.example.demo.dto.TopicoResponseDTO;
import com.example.demo.dto.TopicoUpdateDTO;
import com.example.demo.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    
    @Autowired
    private TopicoService topicoService;
    
    // GET /topicos - Listar todos os tópicos
    @GetMapping
    public ResponseEntity<List<TopicoResponseDTO>> listarTodos() {
        List<TopicoResponseDTO> topicos = topicoService.listarTodos();
        return ResponseEntity.ok(topicos);
    }
    
    // GET /topicos/{id} - Buscar tópico específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> buscarPorId(@PathVariable Long id) {
        TopicoResponseDTO topico = topicoService.buscarPorId(id);
        return ResponseEntity.ok(topico);
    }
    
    // POST /topicos - Criar novo tópico
    @PostMapping
    public ResponseEntity<TopicoResponseDTO> criar(@Valid @RequestBody TopicoRequestDTO requestDTO) {
        TopicoResponseDTO novoTopico = topicoService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTopico);
    }
    
    // PUT /topicos/{id} - Atualizar tópico
    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> atualizar(
            @PathVariable Long id, 
            @RequestBody TopicoUpdateDTO updateDTO) {
        TopicoResponseDTO topicoAtualizado = topicoService.atualizar(id, updateDTO);
        return ResponseEntity.ok(topicoAtualizado);
    }
    
    // DELETE /topicos/{id} - Deletar tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        topicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
} 