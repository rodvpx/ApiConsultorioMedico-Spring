package com.github.rodvpx.apiconsultoriomedicospring.service;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.ConsultaInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.ConsultaOutput;
import com.github.rodvpx.apiconsultoriomedicospring.mapper.ConsultaMapper;
import com.github.rodvpx.apiconsultoriomedicospring.model.Consulta;
import com.github.rodvpx.apiconsultoriomedicospring.model.Medico;
import com.github.rodvpx.apiconsultoriomedicospring.model.Paciente;
import com.github.rodvpx.apiconsultoriomedicospring.model.Recepcionista;
import com.github.rodvpx.apiconsultoriomedicospring.repository.ConsultaRepository;
import com.github.rodvpx.apiconsultoriomedicospring.repository.MedicoRepository;
import com.github.rodvpx.apiconsultoriomedicospring.repository.PacienteRepository;
import com.github.rodvpx.apiconsultoriomedicospring.repository.RecepcionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final RecepcionistaRepository recepcionistaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final ConsultaMapper consultaMapper;

    @Autowired
    public ConsultaService(ConsultaRepository consultaRepository, RecepcionistaRepository recepcionistaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository, ConsultaMapper consultaMapper) {
        this.consultaRepository = consultaRepository;
        this.recepcionistaRepository = recepcionistaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.consultaMapper = consultaMapper;
    }


    // Agendar uma consulta
    public ConsultaOutput agendarConsulta(ConsultaInput consultaInput) {
        // Verifica se o paciente, médico e recepcionista existem
        Paciente paciente = pacienteRepository.findById(consultaInput.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        Medico medico = medicoRepository.findById(consultaInput.medicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        Recepcionista recepcionista = recepcionistaRepository.findById(consultaInput.recepcionistaId())
                .orElseThrow(() -> new RuntimeException("Recepcionista não encontrado"));

        // Criação da consulta
        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setRecepcionista(recepcionista);
        consulta.setDataHora(consultaInput.dataHora());
        consulta.setStatus(Consulta.StatusConsulta.AGENDADA);
        consulta.setMotivoCancelamento(null);
        consulta.setObservacoes(consultaInput.observacoes());

        return consultaMapper.toOutput(consultaRepository.save(consulta));
    }

    // Atualizar status da consulta
    public ConsultaOutput atualizarStatus(UUID id, Consulta.StatusConsulta status) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consulta.setStatus(status);
        return consultaMapper.toOutput(consultaRepository.save(consulta));
    }

    // Buscar consultas de um paciente
    public List<ConsultaOutput> buscarConsultasPorPaciente(UUID pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        List<Consulta> consultas = consultaRepository.findByPaciente(paciente);
        return consultaMapper.toOutputList(consultas);
    }
}
