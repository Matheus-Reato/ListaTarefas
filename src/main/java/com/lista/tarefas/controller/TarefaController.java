package com.lista.tarefas.controller;


import com.lista.tarefas.dia.Dia;
import com.lista.tarefas.dia.DiaService;
import com.lista.tarefas.tarefas.DadosCadastroTarefa;
import com.lista.tarefas.tarefas.StatusTarefa;
import com.lista.tarefas.tarefas.Tarefa;
import com.lista.tarefas.tarefas.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tarefa")
public class TarefaController {

    private final TarefaService tarefaService;

    @Autowired
    public TarefaController(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<String> createTarefa(@RequestBody DadosCadastroTarefa dadosCadastroTarefa){
        Tarefa tarefa = tarefaService.createTarefa(new Tarefa(dadosCadastroTarefa));
        return new ResponseEntity<>("Tarefa salva com sucesso", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> getAllTarefas(){
        List<Tarefa> tarefas = tarefaService.getAllTarefas();
        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable Long id){
        Optional<Tarefa> tarefa = tarefaService.getTarefaById(id);
        return tarefa.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTarefa(@PathVariable Long id){
        boolean delete = tarefaService.deleteTarefa(id);

        if(delete){
            return new ResponseEntity<>("Tarefa deletada com sucesso", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Tarefa não encontrada ou não pode ser excluída", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada){
        Tarefa tarefa = tarefaService.updateTarefa(tarefaAtualizada);

        if(tarefa == null){
            return new ResponseEntity<>("Tarefa não encontrada ou não pode ser alterada", HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>("Tarefa atualizada com sucesso", HttpStatus.OK);
        }
    }


}
