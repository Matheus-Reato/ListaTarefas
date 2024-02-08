package com.lista.tarefas.dia;

import com.lista.tarefas.tarefas.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Long> {


    @Query("SELECT d.id FROM Dia d WHERE d.dia = :data")
    List<Long> findIdsByData(@Param("data") LocalDate data);


}
