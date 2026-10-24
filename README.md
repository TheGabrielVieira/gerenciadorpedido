# Gerenciador de Pedidos

> **Estado atual:** projeto de demonstração de persistência com Spring Boot e Spring Data JPA. A aplicação não possui interface web, API REST nem menu interativo. Há pontos nas consultas dos repositórios que precisam ser corrigidos antes de considerar a inicialização e as consultas funcionais.

## Objetivo

O projeto modela produtos, categorias, fornecedores e pedidos usando entidades JPA. Ao iniciar, um `CommandLineRunner` tenta gravar dados de exemplo no banco de dados. Os repositórios também declaram operações de consulta para essas entidades.

## Tecnologias e requisitos

- Java 21
- Spring Boot 4.0.3
- Spring Data JPA
- PostgreSQL (driver JDBC incluído no projeto)
- Maven Wrapper

É necessário ter um servidor PostgreSQL acessível e um banco criado. As credenciais e a URL de conexão não estão configuradas nos arquivos versionados deste projeto.

## Estrutura do projeto

```text
src/
├── main/
│   ├── java/br/com/gerenciadorpedidos/
│   │   ├── GerenciadorpedidosApplication.java  # Inicialização e carga de dados de exemplo
│   │   ├── main/Main.java                      # Classe vazia; não é o ponto de entrada
│   │   ├── model/                              # Entidades JPA
│   │   └── repository/                         # Repositórios e consultas
│   └── resources/
│       └── application.properties              # Nome da aplicação e perfil ativo
└── test/java/br/com/gerenciadorpedidos/
    └── GerenciadorpedidosApplicationTests.java # Teste de carregamento do contexto
```

`pom.xml` declara dependências, versão do Java e plugin do Spring Boot. `.mvn/wrapper` contém a configuração do Maven Wrapper. `target/` é a saída gerada pela compilação.

## Modelo de dados

- **Produto**: nome obrigatório e único, preço, categoria e fornecedor. O atributo Java `preco` é mapeado para a coluna `valor`.
- **Categoria**: nome e identificador; uma categoria pode estar associada a vários produtos.
- **Fornecedor**: nome e identificador gerado pelo banco.
- **Pedido**: identificador, data e lista de produtos.

Um produto referencia uma categoria e um fornecedor (`ManyToOne`). Um pedido pode conter vários produtos, e um produto pode estar em vários pedidos (`ManyToMany`), relação mapeada pela tabela `pedido_produto`.

## Como a aplicação funciona

O ponto de entrada é `br.com.gerenciadorpedidos.GerenciadorpedidosApplication`. O método `main` inicia o Spring Boot. Após a inicialização, o `CommandLineRunner` tenta salvar:

- duas categorias: Eletrônicos e Livros;
- dois fornecedores;
- três produtos;
- dois pedidos, com datas baseadas no dia da execução.

Se a rotina terminar, imprime `Dados inseridos com sucesso!`. Ela roda a cada inicialização e não verifica se os dados já existem. Como o nome do produto é único, uma nova execução contra um banco que já contém os mesmos produtos pode falhar por violação de unicidade.

Os repositórios estendem `JpaRepository`, que fornece operações básicas de persistência, e incluem métodos derivados do nome e consultas explícitas em JPQL ou SQL nativo.

## Configuração do banco

`src/main/resources/application.properties` contém atualmente apenas:

```properties
spring.application.name=gerenciadorpedidos
spring.profiles.active=dev
```

Não há configuração de conexão PostgreSQL ou arquivo `application-dev.properties` no projeto. Antes de executar, configure as propriedades de conexão para o ambiente, por exemplo `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password`, por variáveis de ambiente, parâmetros de linha de comando ou arquivo local não versionado. Não inclua senhas reais no controle de versão.

O projeto também não declara uma estratégia de criação/atualização de schema (`spring.jpa.hibernate.ddl-auto`) nem scripts de migração. Defina como as tabelas serão criadas e gerenciadas no seu ambiente antes de executar a carga de dados.

## Execução

Com Java 21 instalado, configure o banco conforme a seção anterior. Use o Maven Wrapper na raiz do projeto:

**Windows (PowerShell):**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux/macOS:**

```bash
./mvnw spring-boot:run
```

O wrapper pode baixar a distribuição do Maven na primeira execução. A inicialização pode falhar enquanto as consultas dos repositórios descritas abaixo não forem corrigidas, ou se o banco/schema não estiver disponível.

## Testes

Para executar a suíte:

```powershell
.\mvnw.cmd test
```

No Linux/macOS, use `./mvnw test`. O teste existente, `contextLoads`, verifica o carregamento do contexto Spring; não há testes de comportamento para as operações de persistência ou consultas.

## Pontos a verificar

As declarações atuais dos repositórios aparentam conter nomes de propriedades e consultas incompatíveis com as entidades. Isso pode impedir que o Spring Data crie os repositórios ou que o contexto inicialize:

- `PedidoRepository` referencia propriedades como `dataPedido` e `dataEntrega`, mas `Pedido` declara `data`.
- Uma consulta de `PedidoRepository` seleciona `Produto`, enquanto o método declara retorno `List<Pedido>`.
- `ProdutoRepository` contém consultas JPQL suspeitas, como `WHERE p` sem condição e `COUN(p)`; há também método cujo nome sugere `NomeCategoria`, mas não corresponde diretamente a um atributo de `Produto`.
- Alguns nomes derivados com `Top` devem ser revistos conforme a sintaxe do Spring Data JPA.

Esses itens são observações do código e ainda precisam ser confirmados e corrigidos. Até lá, não se deve assumir que a aplicação compila, inicializa ou executa todas as consultas com sucesso.

