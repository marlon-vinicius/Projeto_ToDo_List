package com.marlon.todolist.service.impl;

import com.marlon.todolist.exception.RegraNegocioException;
import com.marlon.todolist.model.entity.Tarefa;
import com.marlon.todolist.model.enums.StatusTarefa;
import com.marlon.todolist.model.repository.TarefaRepository;
import com.marlon.todolist.service.TarefaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {
    private final TarefaRepository repository;

    public TarefaServiceImpl(TarefaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tarefa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Tarefa salvar(Tarefa tarefa) {
        validar(tarefa);
        tarefa.setStatus(StatusTarefa.CRIADO);
        return repository.save(tarefa);
    }

    @Override
    @Transactional
    public Tarefa atualizar(Tarefa tarefa) {
        Objects.requireNonNull(tarefa.getId(), "O ID da tarefa não pode ser nulo.");

        Optional<Tarefa> tarefaExistenteOpt = buscarPorId(tarefa.getId());
        if (!tarefaExistenteOpt.isPresent()) {
            throw new RegraNegocioException("Tarefa não encontrada.");
        }

        Tarefa tarefaExistente = tarefaExistenteOpt.get();

        tarefa.setData_criacao(tarefaExistente.getData_criacao());

        if (tarefa.getStatus() == null) {
            tarefa.setStatus(tarefaExistente.getStatus());
        }

        validar(tarefa);

        return repository.save(tarefa);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Optional<Tarefa> tarefa = buscarPorId(id);
        if (tarefa.isPresent()) {
            repository.delete(tarefa.get());
        } else {
            throw new RegraNegocioException("Tarefa não encontrada.");
        }
    }

    @Override
    public void validar(Tarefa tarefa) {
        if (tarefa.getNome() == null || tarefa.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("Informe um nome válido.");
        }
        if (tarefa.getDescricao() == null || tarefa.getDescricao().trim().isEmpty()) {
            throw new RegraNegocioException("Informe uma descrição válida.");
        }
    }
}
