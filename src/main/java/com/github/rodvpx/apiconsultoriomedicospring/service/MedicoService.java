package com.github.rodvpx.apiconsultoriomedicospring.service;

import com.github.rodvpx.apiconsultoriomedicospring.model.Medico;
import com.github.rodvpx.apiconsultoriomedicospring.model.Usuario;
import com.github.rodvpx.apiconsultoriomedicospring.repository.MedicoRepository;
import com.github.rodvpx.apiconsultoriomedicospring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.medicoRepository = medicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registrar um novo médico
    public Medico save(Medico medico) {
        String senhaCriptografada = passwordEncoder.encode(medico.getUsuario().getSenha());

        Usuario usuario = new Usuario();
        usuario.setEmail(medico.getUsuario().getEmail());
        usuario.setSenha(senhaCriptografada);
        usuario.setRole("ROLE_MEDICO");  // Definindo o papel do usuário como médico

        usuarioRepository.save(usuario);
        medico.setUsuario(usuario);
        return medicoRepository.save(medico);
    }

    // Atualizar um médico existente
    public Medico atualizarMedico(UUID id, Medico newMedico) {
        // Buscar o médico no banco de dados
        Medico medicoExistente = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        // Atualizar os campos do médico
        medicoExistente.setNome(newMedico.getNome());
        medicoExistente.setTelefone(newMedico.getTelefone());
        medicoExistente.setCrm(newMedico.getCrm());
        medicoExistente.setEspecialidade(newMedico.getEspecialidade());
        medicoExistente.setDataContratacao(newMedico.getDataContratacao());
        medicoExistente.setStatus(newMedico.getStatus());

        // Salvar o médico atualizado no banco de dados
        return medicoRepository.save(medicoExistente);
    }

    // Buscar médico pelo nome
    public Medico findByNome(String nome) {
        return medicoRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Médico com o nome '" + nome + "' não encontrado"));
    }

    // Buscar médico pelo CRM
    public Medico findByCrm(String crm) {
        return medicoRepository.findByCrm(crm)
                .orElseThrow(() -> new RuntimeException("Médico com CRM '" + crm + "' não encontrado"));
    }

    // Buscar todos os médicos
    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    // Excluir um médico
    public void excluirMedico(UUID id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        // Excluir o usuário associado ao médico
        usuarioRepository.delete(medico.getUsuario());

        // Excluir o médico
        medicoRepository.delete(medico);
    }

    // Verificar se o CRM já está em uso
    public boolean verificarSeCrmExiste(String crm) {
        return medicoRepository.existsByCrm(crm);
    }

    // Verificar se o email já está em uso (para o médico)
    public boolean verificarSeEmailJaCadastrado(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
