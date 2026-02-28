package br.com.gerenciadorpedidos.repository;

import br.com.gerenciadorpedidos.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
