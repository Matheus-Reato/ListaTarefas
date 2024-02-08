package com.lista.tarefas.dia;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public record DadosCadastroDia(
                               LocalDate dia,
                               String horario
                              ) {

}
