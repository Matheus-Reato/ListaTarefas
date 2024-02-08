package com.lista.tarefas.tarefas;

import com.lista.tarefas.dia.DadosCadastroDia;
import com.lista.tarefas.dia.Dia;
import com.lista.tarefas.dia.DiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final DiaRepository diaRepository;

    @Autowired
    public TarefaService(TarefaRepository tarefaRepository, DiaRepository diaRepository) {
        this.tarefaRepository = tarefaRepository;
        this.diaRepository = diaRepository;
    }

    @Transactional
    public Tarefa createTarefa(Tarefa tarefa) {
        tarefaRepository.save(tarefa);
        List<Dia> diaList = tarefa.getDiaList();
        diaList.forEach(dia -> dia.setTarefa(tarefa));
        diaRepository.saveAll(diaList);
        return tarefa;
    }

    public List<Tarefa> getAllTarefas() {
        //List<Tarefa> tarefa = tarefaRepository.findAll();

        return tarefaRepository.findAllOrderedByDiaAndHorario();
    }

    public Optional<Tarefa> getTarefaById(Long id) {
        return tarefaRepository.findById(id);
    }

    public boolean deleteTarefa(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Tarefa updateTarefa(Tarefa tarefaAtualizada) {

        // Atualiza a tarefa no banco de dados
        Tarefa tarefaExistente = tarefaRepository.findById(tarefaAtualizada.getId()).orElse(null);
        if (tarefaExistente == null) {
            // Se a tarefa não existir, retorna null ou lança uma exceção, conforme necessário
            return null;
        }

        tarefaExistente.setNome_tarefa(tarefaAtualizada.getNome_tarefa());
        tarefaExistente.setDescricao_tarefa(tarefaAtualizada.getDescricao_tarefa());
        tarefaExistente.setStatusTarefa(tarefaAtualizada.getStatusTarefa());
        tarefaExistente = tarefaRepository.save(tarefaExistente);

        // Atualiza os dias associados à tarefa
        List<Dia> diasAtualizados = tarefaAtualizada.getDiaList();
        for (Dia dia : diasAtualizados) {
            dia.setTarefa(tarefaExistente);
        }
        diaRepository.saveAll(diasAtualizados);

        return tarefaExistente;
    }

}


