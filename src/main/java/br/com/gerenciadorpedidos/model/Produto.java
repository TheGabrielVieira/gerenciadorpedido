package br.com.gerenciadorpedidos.model;

import jakarta.persistence.*;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(name = "valor")
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    public Produto() {
    }

    public Produto(String nome, Double preco, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
