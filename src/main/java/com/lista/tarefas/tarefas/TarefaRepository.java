package com.lista.tarefas.tarefas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT t FROM Tarefa t JOIN t.diaList d WHERE d.id IN :diaIds ORDER BY d.horario ASC")
    List<Tarefa> findByDiaIds(List<Long> diaIds);

    @Query("SELECT t FROM Tarefa t JOIN t.diaList d ORDER BY d.dia ASC, d.horario ASC")
    List<Tarefa> findAllOrderedByDiaAndHorario();
}
