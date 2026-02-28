package br.com.gerenciadorpedidos.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Categoria {
    @Id
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Produto> produto;

    public Categoria() {
    }

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Produto> getProdutos() {
        return produto;
    }
}
