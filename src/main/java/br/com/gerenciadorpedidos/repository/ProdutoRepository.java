package br.com.gerenciadorpedidos.repository;

import br.com.gerenciadorpedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT p FROM Produto p WHERE p.preco > :preco")
    List<Produto> buscaPorPrecoMaior(@Param("preco") Double preco);

    @Query("SELECT p FROM Produto p WHERE p ORDER BY p.preco ASC")
    List<Produto> buscaOrdenadaPorPrecoAsc();

    @Query("SELECT p FROM Produto p ORDER BY p.preco DESC")
    List<Produto> buscaOrdenadoPorNomeDesc();

    @Query("SELECT AVG(p.preco) FROM Produto p")
    Double calcularMediaPrecoProdutos();

    @Query("SELECT MAX(p.preco) FROM Produto p WHERE p.categoria.nome = :categoria")
    Double buscaPrecoMaximoPorCategoria(@Param("categoria") String nomeCategoria);

    @Query("SELECT c.nome, COUN(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome")
    List<Object[]> contarProdutosPorCategoria();

    @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome HAVING COUNT(p) > :quantidade")
    List<Object[]> categoriaComMaisDe();

    @Query("SELECT p FROM Produto p WHERE (:nome IS NULL OR p.nome = :nome) AND (:categoria IS NULL OR p.categoria.nome = :categoria)")
    List<Produto> buscarProdutosFIltrados(@Param("nome") String nome, @Param("categoria") String categoria);

    @Query(value = "SELECT * FROM produto ORDER BY preco DESC LIMIT 5", nativeQuery = true)
    List<Produto> buscarTop5ProdutosMaisCaros();
}
