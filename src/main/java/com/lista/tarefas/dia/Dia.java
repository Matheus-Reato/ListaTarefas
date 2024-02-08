package com.lista.tarefas.dia;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lista.tarefas.tarefas.Tarefa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dia;

    private String horario;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_tarefa", referencedColumnName = "id")
    private Tarefa tarefa;

    public Dia (DadosCadastroDia dadosCadastroDia){
        this.dia = dadosCadastroDia.dia();
        this.horario = dadosCadastroDia.horario();
    }
}
