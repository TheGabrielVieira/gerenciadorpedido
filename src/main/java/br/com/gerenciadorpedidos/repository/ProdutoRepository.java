package br.com.gerenciadorpedidos.repository;

import br.com.gerenciadorpedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNome(String nome);

    List<Produto> findByCategoriaNome(String nomeCategoria);

    List<Produto> findByPrecoGreaterThan(Double preco);

    List<Produto> findByPrecoLessThan(Double preco);

    List<Produto> findByNomeContaining(String termo);

    List<Produto> findByNomeOrderByPrecoAsc(String nomeCategoria);

    List<Produto> findByNomeContainingOrderByPrecoDesc(String nomeCategoria);

    long countByNomeCategoria(String nomeCategoria);

    long countByPrecoGreaterThan(Double preco);

    List<Produto> findByPrecoLessThanOrNomeContaining(Double preco, String termo);

    List<Produto> findByTop3ByPrecoDesc();

    List<Produto> findByTop5ByCategoriaNomeOrderByPrecoAsc(String nomeCategoria);

}
