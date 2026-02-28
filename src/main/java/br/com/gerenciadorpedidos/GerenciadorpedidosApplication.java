package br.com.gerenciadorpedidos;

import br.com.gerenciadorpedidos.model.*;
import br.com.gerenciadorpedidos.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class GerenciadorpedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorpedidosApplication.class, args);
	}

	@Bean
	CommandLineRunner run(
			CategoriaRepository categoriaRepository,
			ProdutoRepository produtoRepository,
			PedidoRepository pedidoRepository,
			FornecedorRepository fornecedorRepository) {

		return args -> {

			// Criando categorias
			Categoria categoriaEletronicos = new Categoria(1L, "Eletrônicos");
			Categoria categoriaLivros = new Categoria(2L, "Livros");
			categoriaRepository.saveAll(List.of(categoriaEletronicos, categoriaLivros));

			// Criando fornecedores
			Fornecedor fornecedorTech = new Fornecedor("Tech Supplier");
			Fornecedor fornecedorLivros = new Fornecedor("Livraria Global");
			fornecedorRepository.saveAll(List.of(fornecedorTech, fornecedorLivros));

			// Criando produtos
			Produto produto1 = new Produto("Notebook", 3500.0, categoriaEletronicos);
			Produto produto2 = new Produto("Smartphone", 2500.0, categoriaEletronicos);
			Produto produto3 = new Produto("Livro de Java", 100.0, categoriaLivros);

			produto1.setFornecedor(fornecedorTech);
			produto2.setFornecedor(fornecedorTech);
			produto3.setFornecedor(fornecedorLivros);

			produtoRepository.saveAll(List.of(produto1, produto2, produto3));

			// Criando pedidos
			Pedido pedido1 = new Pedido(1L, LocalDate.now());
			pedido1.setProdutos(List.of(produto1, produto3));

			Pedido pedido2 = new Pedido(2L, LocalDate.now().minusDays(1));
			pedido2.setProdutos(List.of(produto2));

			pedidoRepository.saveAll(List.of(pedido1, pedido2));

			System.out.println("Dados inseridos com sucesso!");
		};
	}
}