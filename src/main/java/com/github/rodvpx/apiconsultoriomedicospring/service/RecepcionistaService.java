package com.github.rodvpx.apiconsultoriomedicospring.service;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.RecepcionistaInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.RecepcionistaOutput;
import com.github.rodvpx.apiconsultoriomedicospring.mapper.RecepcionistaMapper;
import com.github.rodvpx.apiconsultoriomedicospring.model.Recepcionista;
import com.github.rodvpx.apiconsultoriomedicospring.model.Usuario;
import com.github.rodvpx.apiconsultoriomedicospring.repository.RecepcionistaRepository;
import com.github.rodvpx.apiconsultoriomedicospring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecepcionistaService {

    private final RecepcionistaRepository recepcionistaRepository;
    private final UsuarioRepository usuarioRepository;
    private final RecepcionistaMapper recepcionistaMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RecepcionistaService(RecepcionistaRepository recepcionistaRepository, UsuarioRepository usuarioRepository, RecepcionistaMapper recepcionistaMapper, PasswordEncoder passwordEncoder) {
        this.recepcionistaRepository = recepcionistaRepository;
        this.usuarioRepository = usuarioRepository;
        this.recepcionistaMapper = recepcionistaMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Criar recepcionista
    public RecepcionistaOutput save(RecepcionistaInput input) {
        // Verifica CPF e e-mail
        if (verificarSeCpfExiste(input.cpf()) || verificarSeEmailJaCadastrado(input.usuario().email())) {
            System.out.println("ERROR: CPF ou email já cadastrados");
            return null;
        }

        // Cria e salva o usuário
        Usuario usuario = new Usuario();
        usuario.setEmail(input.usuario().email());
        usuario.setSenha(passwordEncoder.encode(input.usuario().senha()));
        usuario.setRole("ROLE_RECEPCIONISTA");

        usuarioRepository.save(usuario);

        // Cria e salva o recepcionista
        Recepcionista recepcionista = recepcionistaMapper.toEntity(input);
        recepcionista.setUsuario(usuario);

        return recepcionistaMapper.toOutput(recepcionistaRepository.save(recepcionista));
    }

    // Atualizar recepcionista
    public RecepcionistaOutput update(UUID id, RecepcionistaInput input) {
        Recepcionista existente = recepcionistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recepcionista não encontrado"));

        existente.setNome(input.nome());
        existente.setCpf(input.cpf());
        existente.setTelefone(input.telefone());
        existente.setDataContratacao(input.dataContratacao());
        existente.setStatus(input.status());

        return recepcionistaMapper.toOutput(recepcionistaRepository.save(existente));
    }

    public RecepcionistaOutput findByCpf(String cpf) {
        Recepcionista recepcionista = recepcionistaRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Recepcionista com CPF '" + cpf + "' não encontrado"));

        return recepcionistaMapper.toOutput(recepcionista);
    }

    public List<RecepcionistaOutput> findAll() {
        return recepcionistaMapper.toOutputList(recepcionistaRepository.findAll());
    }

    public void excluirRecepcionista(UUID id) {
        Recepcionista recepcionista = recepcionistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recepcionista não encontrado"));

        usuarioRepository.delete(recepcionista.getUsuario());
        recepcionistaRepository.delete(recepcionista);
    }

    public boolean verificarSeCpfExiste(String cpf) {
        return recepcionistaRepository.existsByCpf(cpf);
    }

    public boolean verificarSeEmailJaCadastrado(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
