package br.com.gerenciadorpedidos.repository;

import br.com.gerenciadorpedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByDataEngregaIsNull();

    List<Pedido> findByDataEngregaIsNotNull();

    List<Pedido> findByDataPedidoAfter(LocalDate data);

    List<Pedido> findByDataPedidoBefore(LocalDate data);

    List<Pedido> findByDataPedidoBetween(LocalDate dataInicio, LocalDate dataFim);

    @Query("SELECT p FROM Produto p WHERE p.nome LIKE :letra%")
    List<Pedido> buscarProdutoPorLetraInicial(@Param("letra") String letra);

    @Query("SELECT p FROM Pedido p WHERE p.dataPedido BETWEEN :inicio AND :fim")
    List<Pedido> buscarPedidoPorPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
