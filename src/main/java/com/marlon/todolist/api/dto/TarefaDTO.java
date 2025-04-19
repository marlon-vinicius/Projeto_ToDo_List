package com.marlon.todolist.api.dto;

import com.marlon.todolist.model.enums.StatusTarefa;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TarefaDTO {
    private Long id;

    private String nome;

    private String descricao;

    private StatusTarefa status;

    private String observacoes;
}
