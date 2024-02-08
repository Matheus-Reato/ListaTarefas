package com.lista.tarefas.dia;

import com.lista.tarefas.tarefas.Tarefa;
import com.lista.tarefas.tarefas.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DiaService {

    private final DiaRepository diaRepository;
    private final TarefaRepository tarefaRepository;

    @Autowired
    public DiaService(DiaRepository diaRepository, TarefaRepository tarefaRepository){
        this.diaRepository = diaRepository;
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> getTarefaPorDia(LocalDate data){
        List<Long> diaIds = diaRepository.findIdsByData(data);
        return tarefaRepository.findByDiaIds(diaIds);
    }
}
