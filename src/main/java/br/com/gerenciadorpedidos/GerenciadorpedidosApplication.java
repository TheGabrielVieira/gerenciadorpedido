package br.com.gerenciadorpedidos;

import br.com.gerenciadorpedidos.model.Categoria;
import br.com.gerenciadorpedidos.model.Pedido;
import br.com.gerenciadorpedidos.model.Produto;
import br.com.gerenciadorpedidos.repository.CategoriaRepository;
import br.com.gerenciadorpedidos.repository.PedidoRepository;
import br.com.gerenciadorpedidos.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class GerenciadorpedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorpedidosApplication.class, args);
	}

	@Bean
	CommandLineRunner run(
			ProdutoRepository produtoRepository,
			CategoriaRepository categoriaRepository,
			PedidoRepository pedidoRepository) {

		return args -> {

			Produto produto = new Produto("Notebook", 3500.0);
			Categoria categoria = new Categoria(1L, "Eletrônicos");
			Pedido pedido = new Pedido(1L, LocalDate.now());

			produtoRepository.save(produto);
			categoriaRepository.save(categoria);
			pedidoRepository.save(pedido);

			System.out.println("Dados salvos com sucesso!");
		};
	}
}