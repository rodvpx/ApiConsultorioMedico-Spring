package com.github.rodvpx.apiconsultoriomedicospring.controller;

import com.github.rodvpx.apiconsultoriomedicospring.model.Medico;
import com.github.rodvpx.apiconsultoriomedicospring.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/cadastro")
    public Medico cadastro(@RequestBody Medico medico) {
        return medicoRepository.save(medico);
    }

    @PutMapping("/atualizar-cadastro{id}")
    public Medico atualizar(@RequestBody UUID id, Medico medico) {
    return medicoRepository.save(medico);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable UUID id) {
        medicoRepository.deleteById(id);
    }

    @GetMapping("/search/{nome}")
    public List<Medico> searchName(@PathVariable String nome) {
        return medicoRepository.findByNome(nome);
    }

    @GetMapping("/search/{id}")
    public Medico searchId(@PathVariable UUID id) {
        return medicoRepository.findById(id).orElse(null);
    }

    @GetMapping("/search/{crm}")
    public Medico searchCrm(@PathVariable String crm) {
        return medicoRepository.findByCrm(crm).orElse(null);
    }

    @GetMapping("/search/all")
    public List<Medico> searchAll() {
        return medicoRepository.findAll();
    }
}
