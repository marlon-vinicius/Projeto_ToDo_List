package com.marlon.todolist.service;

import com.marlon.todolist.model.entity.Tarefa;
import com.marlon.todolist.model.enums.StatusTarefa;

import java.util.List;
import java.util.Optional;

public interface TarefaService {

    List<Tarefa> listarTodas();

    Optional<Tarefa> buscarPorId(Long id);

    Tarefa salvar(Tarefa tarefa);

    Tarefa atualizar(Tarefa tarefa);

    void deletar(Long id);

    void validar(Tarefa tarefa);
}
