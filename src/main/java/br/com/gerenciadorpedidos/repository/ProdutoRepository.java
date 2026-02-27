package br.com.gerenciadorpedidos.repository;

import br.com.gerenciadorpedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
