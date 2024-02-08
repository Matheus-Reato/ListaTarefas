package com.lista.tarefas.controller;

import com.lista.tarefas.dia.DiaService;
import com.lista.tarefas.tarefas.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("dia")
public class DiaController {

    private final DiaService diaService;

    @Autowired
    public DiaController(DiaService diaService){
        this.diaService = diaService;
    }

    @GetMapping("/{data}")
    public ResponseEntity<List<Tarefa>> getTarefaByDia(@PathVariable("data") LocalDate data){
        List<Tarefa> tarefas = diaService.getTarefaPorDia(data);

        if(tarefas.isEmpty()){
            return new ResponseEntity<>(tarefas, HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(tarefas, HttpStatus.OK);
        }
    }
}
