package br.com.gerenciadorpedidos.repository;

import br.com.gerenciadorpedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
