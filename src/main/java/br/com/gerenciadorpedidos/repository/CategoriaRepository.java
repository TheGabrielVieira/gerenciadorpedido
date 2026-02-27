package br.com.gerenciadorpedidos.repository;

import br.com.gerenciadorpedidos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
