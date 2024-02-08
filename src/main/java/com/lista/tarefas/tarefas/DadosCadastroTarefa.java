package com.lista.tarefas.tarefas;

import com.lista.tarefas.dia.DadosCadastroDia;

import java.util.List;

public record DadosCadastroTarefa(
                                  String nome_tarefa,
                                  String descricao_tarefa,
                                  StatusTarefa status,
                                  List<DadosCadastroDia> listaDia
                                 ) {

}


