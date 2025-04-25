package com.github.rodvpx.apiconsultoriomedicospring.service;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.PacienteInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.PacienteOutput;
import com.github.rodvpx.apiconsultoriomedicospring.mapper.PacienteMapper;
import com.github.rodvpx.apiconsultoriomedicospring.model.Paciente;
import com.github.rodvpx.apiconsultoriomedicospring.model.Usuario;
import com.github.rodvpx.apiconsultoriomedicospring.repository.PacienteRepository;
import com.github.rodvpx.apiconsultoriomedicospring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final PacienteMapper pacienteMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, UsuarioRepository usuarioRepository, PacienteMapper pacienteMapper, PasswordEncoder passwordEncoder) {
        this.pacienteRepository = pacienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.pacienteMapper = pacienteMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Criar paciente
    public PacienteOutput save(PacienteInput input) {
        // Verifica CPF e e-mail
        if (verificarSeCpfExiste(input.cpf()) || verificarSeEmailJaCadastrado(input.usuario().email())) {
            System.out.println("ERROR: CPF ou email já cadastrados");
            return null;
        }

        // Cria e salva o usuário
        Usuario usuario = new Usuario();
        usuario.setEmail(input.usuario().email());
        usuario.setSenha(passwordEncoder.encode(input.usuario().senha()));
        usuario.setRole("ROLE_PACIENTE");

        usuarioRepository.save(usuario);

        // Cria e salva o paciente
        Paciente paciente = pacienteMapper.toEntity(input);
        paciente.setUsuario(usuario);

        return pacienteMapper.toOutput(pacienteRepository.save(paciente));
    }

    // Atualizar paciente
    public PacienteOutput update(UUID id, PacienteInput input) {
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        pacienteExistente.setNome(input.nome());
        pacienteExistente.setCpf(input.cpf());
        pacienteExistente.setTelefone(input.telefone());
        pacienteExistente.setEndereco(input.endereco());
        pacienteExistente.setDataNascimento(input.dataNascimento());
        pacienteExistente.setGenero(input.genero());

        return pacienteMapper.toOutput(pacienteRepository.save(pacienteExistente));
    }

    // Buscar paciente por CPF
    public PacienteOutput findByCpf(String cpf) {
        Paciente paciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Paciente com CPF '" + cpf + "' não encontrado"));

        return pacienteMapper.toOutput(paciente);
    }

    // Buscar todos os pacientes
    public List<PacienteOutput> findAll() {
        return pacienteMapper.toOutputList(pacienteRepository.findAll());
    }

    // Excluir paciente
    public void excluirPaciente(UUID id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        usuarioRepository.delete(paciente.getUsuario());
        pacienteRepository.delete(paciente);
    }

    // Verificar se o CPF já está em uso
    public boolean verificarSeCpfExiste(String cpf) {
        return pacienteRepository.existsByCpf(cpf);
    }

    // Verificar se o e-mail já está em uso (para o paciente)
    public boolean verificarSeEmailJaCadastrado(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}

