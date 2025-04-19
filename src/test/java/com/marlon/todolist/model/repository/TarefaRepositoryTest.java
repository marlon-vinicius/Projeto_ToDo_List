package com.marlon.todolist.model.repository;

import com.marlon.todolist.model.entity.Tarefa;
import com.marlon.todolist.model.enums.StatusTarefa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TarefaRepositoryTest {
    @Autowired
    TarefaRepository repository;

    @Test
    public void devePersistirUmaTarefaNaBaseDeDados() {
        // cenário
        Tarefa tarefa = criarTarefa();

        // ação
        Tarefa tarefaSalva = repository.save(tarefa);

        // verificação
        Assertions.assertNotNull(tarefaSalva.getId());
        Assertions.assertEquals(tarefaSalva.getNome(), "Estudar");
    }

    @Test
    public void deveExcluirUmaTarefa() {
        // cenário
        Tarefa tarefa = criarTarefa();
        Tarefa tarefaSalva = repository.save(tarefa);

        // ação
        repository.delete(tarefaSalva);

        // verificação
        Optional<Tarefa> result = repository.findById(tarefaSalva.getId());
        Assertions.assertFalse(result.isPresent());
    }

    public static Tarefa criarTarefa() {
        return Tarefa.builder()
                .nome("Estudar")
                .descricao("Estudar para a prova de matemática")
                .observacoes("Estudar até quarta-feira")
                .status(StatusTarefa.CRIADO)
                .data_criacao(LocalDate.now())
                .data_atualizacao(LocalDate.now())
                .build();
    }
}
