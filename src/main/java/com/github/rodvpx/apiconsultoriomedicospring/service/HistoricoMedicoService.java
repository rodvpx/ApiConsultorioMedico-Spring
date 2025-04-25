package com.github.rodvpx.apiconsultoriomedicospring.service;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.ExameMedicoInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.input.HistoricoMedicoInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.ExameMedicoOutput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.HistoricoMedicoOutput;
import com.github.rodvpx.apiconsultoriomedicospring.mapper.ExameMedicoMapper;
import com.github.rodvpx.apiconsultoriomedicospring.mapper.HistoricoMedicoMapper;
import com.github.rodvpx.apiconsultoriomedicospring.model.*;
import com.github.rodvpx.apiconsultoriomedicospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class HistoricoMedicoService {

    private final HistoricoMedicoRepository historicoMedicoRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final ConsultaRepository consultaRepository;
    private final ExameMedicoRepository exameMedicoRepository;
    private final HistoricoMedicoMapper historicoMedicoMapper;
    private final ExameMedicoMapper exameMedicoMapper;

    @Autowired
    public HistoricoMedicoService(HistoricoMedicoRepository historicoMedicoRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository, ConsultaRepository consultaRepository, ExameMedicoRepository exameMedicoRepository, HistoricoMedicoMapper historicoMedicoMapper, ExameMedicoMapper exameMedicoMapper) {
        this.historicoMedicoRepository = historicoMedicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.consultaRepository = consultaRepository;
        this.exameMedicoRepository = exameMedicoRepository;
        this.historicoMedicoMapper = historicoMedicoMapper;
        this.exameMedicoMapper = exameMedicoMapper;
    }



    // Criar histórico médico
    public HistoricoMedicoOutput criarHistorico(UUID pacienteId, UUID medicoId, UUID consultaId, HistoricoMedicoInput input) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        HistoricoMedico historicoMedico = new HistoricoMedico();
        historicoMedico.setPaciente(paciente);
        historicoMedico.setMedico(medico);
        historicoMedico.setConsulta(consulta);
        historicoMedico.setDiagnostico(input.diagnostico());
        historicoMedico.setPrescricao(input.prescricao());
        historicoMedico.setObservacoes(input.observacoes());
        historicoMedico.setDataRegistro(LocalDateTime.now());

        // Salvar histórico médico
        return historicoMedicoMapper.toOutput(historicoMedicoRepository.save(historicoMedico));
    }

    // Adicionar exame médico ao histórico
    public ExameMedicoOutput adicionarExame(UUID historicoMedicoId, ExameMedicoInput exameInput) {
        HistoricoMedico historicoMedico = historicoMedicoRepository.findById(historicoMedicoId)
                .orElseThrow(() -> new RuntimeException("Histórico médico não encontrado"));

        ExameMedico exameMedico = new ExameMedico();
        exameMedico.setHistoricoMedico(historicoMedico);
        exameMedico.setTipoExame(exameInput.tipoExame());
        exameMedico.setResultado(exameInput.resultado());
        exameMedico.setDataExame(exameInput.dataExame());
        exameMedico.setArquivoUrl(exameInput.arquivoUrl());

        // Salvar exame médico
        return exameMedicoMapper.toOutput(exameMedicoRepository.save(exameMedico));
    }
}
