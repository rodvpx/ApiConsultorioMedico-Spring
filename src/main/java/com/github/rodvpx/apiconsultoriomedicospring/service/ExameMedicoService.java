package com.github.rodvpx.apiconsultoriomedicospring.service;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.ExameMedicoInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.ExameMedicoOutput;
import com.github.rodvpx.apiconsultoriomedicospring.mapper.ExameMedicoMapper;
import com.github.rodvpx.apiconsultoriomedicospring.model.ExameMedico;
import com.github.rodvpx.apiconsultoriomedicospring.model.HistoricoMedico;
import com.github.rodvpx.apiconsultoriomedicospring.repository.ExameMedicoRepository;
import com.github.rodvpx.apiconsultoriomedicospring.repository.HistoricoMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExameMedicoService {

    private final ExameMedicoRepository exameMedicoRepository;
    private final HistoricoMedicoRepository historicoMedicoRepository;
    private final ExameMedicoMapper exameMedicoMapper;

    @Autowired
    public ExameMedicoService(ExameMedicoRepository exameMedicoRepository, HistoricoMedicoRepository historicoMedicoRepository, ExameMedicoMapper exameMedicoMapper) {
        this.exameMedicoRepository = exameMedicoRepository;
        this.historicoMedicoRepository = historicoMedicoRepository;
        this.exameMedicoMapper = exameMedicoMapper;
    }

    // Adicionar um exame médico
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

