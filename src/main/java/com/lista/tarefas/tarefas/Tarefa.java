package com.lista.tarefas.tarefas;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lista.tarefas.dia.Dia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_tarefa", length = 50)
    private String nome_tarefa;

    @Column(name = "descricao_tarefa", length = 100)
    private String descricao_tarefa;

    @OneToMany(mappedBy = "tarefa")
    @JsonManagedReference
    private List<Dia> diaList;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTarefa statusTarefa;


    public Tarefa(DadosCadastroTarefa dadosCadastroTarefa){
        this.nome_tarefa = dadosCadastroTarefa.nome_tarefa();
        this.descricao_tarefa = dadosCadastroTarefa.descricao_tarefa();
        this.statusTarefa = dadosCadastroTarefa.status();
        this.diaList = dadosCadastroTarefa.listaDia().stream().map(Dia::new).collect(Collectors.toList());
    }

}
