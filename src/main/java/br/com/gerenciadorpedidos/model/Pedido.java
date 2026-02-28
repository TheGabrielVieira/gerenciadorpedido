package br.com.gerenciadorpedidos.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Pedido {
    @Id
    private Long id;
    private LocalDate data;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))

    private List<Produto> produtos;

    public Pedido() {
    }

    public Pedido(Long id, LocalDate data) {
        this.id = id;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
