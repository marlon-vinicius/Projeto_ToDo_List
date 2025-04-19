package com.marlon.todolist.model.entity;

import com.marlon.todolist.model.enums.StatusTarefa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tarefa", schema = "todo")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusTarefa status;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "data_criacao")
    private LocalDate data_criacao;

    @Column(name = "data_atualizacao")
    private LocalDate data_atualizacao;

    @PrePersist
    public void prePersist() {
        this.data_criacao = LocalDate.now();
        this.data_atualizacao = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.data_atualizacao = LocalDate.now();
    }
}
