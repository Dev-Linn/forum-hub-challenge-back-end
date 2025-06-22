package com.example.demo.service;

import com.example.demo.dto.TopicoRequestDTO;
import com.example.demo.dto.TopicoResponseDTO;
import com.example.demo.dto.TopicoUpdateDTO;
import com.example.demo.model.StatusTopico;
import com.example.demo.model.Topico;
import com.example.demo.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {
    
    @Autowired
    private TopicoRepository topicoRepository;
    
    public List<TopicoResponseDTO> listarTodos() {
        return topicoRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    public TopicoResponseDTO buscarPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico não encontrado"));
        return convertToResponseDTO(topico);
    }
    
    public TopicoResponseDTO criar(TopicoRequestDTO requestDTO) {
        Topico topico = new Topico();
        topico.setTitulo(requestDTO.getTitulo());
        topico.setMensagem(requestDTO.getMensagem());
        topico.setAutor(requestDTO.getAutor());
        topico.setCurso(requestDTO.getCurso());
        topico.setStatus(StatusTopico.ABERTO);
        
        Topico topicoSalvo = topicoRepository.save(topico);
        return convertToResponseDTO(topicoSalvo);
    }
    
    public TopicoResponseDTO atualizar(Long id, TopicoUpdateDTO updateDTO) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico não encontrado"));
        
        if (updateDTO.getTitulo() != null) {
            topico.setTitulo(updateDTO.getTitulo());
        }
        if (updateDTO.getMensagem() != null) {
            topico.setMensagem(updateDTO.getMensagem());
        }
        if (updateDTO.getStatus() != null) {
            topico.setStatus(updateDTO.getStatus());
        }
        if (updateDTO.getAutor() != null) {
            topico.setAutor(updateDTO.getAutor());
        }
        if (updateDTO.getCurso() != null) {
            topico.setCurso(updateDTO.getCurso());
        }
        
        Topico topicoAtualizado = topicoRepository.save(topico);
        return convertToResponseDTO(topicoAtualizado);
    }
    
    public void deletar(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico não encontrado");
        }
        topicoRepository.deleteById(id);
    }
    
    private TopicoResponseDTO convertToResponseDTO(Topico topico) {
        return new TopicoResponseDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
} 