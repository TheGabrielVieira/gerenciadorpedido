package br.com.gerenciadorpedidos.repository;

import br.com.gerenciadorpedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByDataEngregaIsNull();

    List<Pedido> findByDataEngregaIsNotNull();

    List<Pedido> findByDataPedidoAfter(LocalDate data);

    List<Pedido> findByDataPedidoBefore(LocalDate data);

    List<Pedido> findByDataPedidoBetween(LocalDate dataInicio, LocalDate dataFim);


}
