package com.marlon.todolist.service;

import com.marlon.todolist.exception.RegraNegocioException;
import com.marlon.todolist.model.entity.Tarefa;
import com.marlon.todolist.model.enums.StatusTarefa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class TarefaServiceTest {

    @Autowired
    TarefaService service;

    @Test
    public void deveLancarErroAoValidarTarefaSemNome() {
        // cenario
        Tarefa tarefa = Tarefa.builder()
                .nome("")
                .descricao("Teste de tarefa sem nome")
                .status(StatusTarefa.CRIADO)
                .observacoes("teste")
                .build();

        // ação
        Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(tarefa));
    }

    @Test
    public void deveLancarErroAoValidarDescricaoSemNome() {
        // cenario
        Tarefa tarefa = Tarefa.builder()
                .nome("Teste")
                .descricao("")
                .status(StatusTarefa.CRIADO)
                .observacoes("teste")
                .build();

        // ação
        Assertions.assertThrows(RegraNegocioException.class, () -> service.validar(tarefa));
    }
}
