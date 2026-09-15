# Bookshelf

git
O objetivo inicial do projeto é construir uma aplicação pequena, funcional e bem estruturada utilizando **Java, Spring Boot, PostgreSQL e Thymeleaf**, servindo também como projeto de portfólio para desenvolvimento backend Java.

O desenvolvimento será incremental. A primeira versão terá apenas uma estante global, sem usuários ou autenticação. Perfis individuais e autenticação serão adicionados posteriormente.

---

## Objetivo do MVP

A primeira versão deve implementar um fluxo completo e simples:

```text
Dashboard
    ↓
Cadastrar livro
    ↓
Salvar no PostgreSQL
    ↓
Home
    ↓
Visualizar e pesquisar livros
```

O MVP possui apenas duas páginas:

* Home
* Dashboard

Não existe autenticação ou sistema de usuários nesta etapa.

---

# Stack

## Backend

* Java 21
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* Jakarta Validation
* Gradle

## Banco de dados

* PostgreSQL

## Frontend

* Thymeleaf
* HTML
* CSS
* JavaScript vanilla

## Desenvolvimento

* IntelliJ IDEA
* Git
* GitHub

---

# Dependências iniciais

O projeto deve começar com as seguintes dependências do Spring:

* Spring Web
* Thymeleaf
* Spring Data JPA
* PostgreSQL Driver
* Validation
* Spring Boot DevTools

Spring Security **não faz parte do MVP inicial**.

Ele será introduzido posteriormente junto ao sistema de usuários e autenticação.

---

# Estrutura inicial

```text
src/
├── main/
│   ├── java/
│   │   └── com/victorbesseler/bookshelf/
│   │       ├── BookshelfApplication.java
│   │       │
│   │       └── book/
│   │           ├── Book.java
│   │           ├── BookController.java
│   │           ├── BookService.java
│   │           └── BookRepository.java
│   │
│   └── resources/
│       ├── templates/
│       │   ├── home.html
│       │   └── dashboard.html
│       │
│       ├── static/
│       │   ├── css/
│       │   │   └── style.css
│       │   └── js/
│       │       └── search.js
│       │
│       └── application.properties
│
└── test/
```

A aplicação deve permanecer simples enquanto o domínio ainda for pequeno.

Novos módulos, como `user` e `auth`, devem ser adicionados apenas quando essas funcionalidades forem implementadas.

---

# Modelo inicial

## Book

Representa um livro presente na estante.

Campos iniciais:

```text
id
title
author
rating
coverImage
```

### id

Identificador único do livro.

### title

Título do livro.

Obrigatório.

### author

Nome do autor.

Obrigatório.

### rating

Avaliação pessoal do livro.

Valores permitidos:

```text
1
2
3
4
5
```

### coverImage

Nome ou caminho relativo da imagem de capa.

O arquivo da imagem **não deve ser armazenado diretamente no PostgreSQL**.

No MVP, as imagens serão armazenadas localmente.

Exemplo:

```text
uploads/
└── covers/
    ├── 550e8400-e29b-41d4-a716-446655440000.jpg
    └── 91cfdb87-2614-41e1-97d5-78754cdb407e.png
```

O banco armazena apenas a referência ao arquivo.

---

# Home

Endpoint:

```http
GET /
```

A Home representa a estante.

Ela deve conter:

* barra de pesquisa
* grid de livros cadastrados

Cada livro deve ser exibido através de um card contendo:

```text
┌──────────────────────┐
│                      │
│        CAPA          │
│                      │
├──────────────────────┤
│ Título               │
│ Autor                │
│ ★ ★ ★ ★ ☆            │
└──────────────────────┘
```

## Pesquisa

A primeira implementação da pesquisa será feita no frontend utilizando JavaScript.

Ao digitar na barra de pesquisa, os cards devem ser filtrados dinamicamente.

A busca deve considerar pelo menos:

* título
* autor

Não é necessário realizar uma nova requisição ao servidor durante o MVP.

Posteriormente, a pesquisa poderá ser migrada para um endpoint backend.

Exemplo futuro:

```http
GET /api/books?search=tolkien
```

---

# Dashboard

Endpoint:

```http
GET /dashboard
```

O Dashboard será inicialmente uma página administrativa simples.

Não haverá autenticação nesta etapa.

Ele deve conter um formulário para cadastrar livros.

Campos:

```text
Cover Image
Title
Author
Rating
```

Exemplo:

```text
┌─────────────────────────────┐
│ Add Book                    │
│                             │
│ Cover:  [Choose File]       │
│                             │
│ Title:   [______________]   │
│                             │
│ Author:  [______________]   │
│                             │
│ Rating:  [1 2 3 4 5]        │
│                             │
│          [ Add Book ]       │
└─────────────────────────────┘
```

O formulário deve enviar os dados para o backend.

Endpoint inicial sugerido:

```http
POST /dashboard/books
```

Após cadastrar o livro com sucesso, a aplicação deve redirecionar o usuário para uma página apropriada, inicialmente podendo ser o próprio Dashboard ou a Home.

---

# Arquitetura

O fluxo principal deve seguir:

```text
Browser
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
PostgreSQL
```

## Controller

Responsável pela camada HTTP/web.

Deve:

* receber requisições
* validar entrada
* chamar a camada de serviço
* preparar dados para o Thymeleaf
* realizar redirects quando necessário

Não deve conter regras de negócio ou acesso direto ao banco.

## Service

Responsável pelas operações da aplicação.

Exemplos iniciais:

```text
findAll()
create(...)
```

Posteriormente poderá conter operações como:

```text
findById()
update()
delete()
search()
```

## Repository

Responsável pela persistência.

Deve utilizar Spring Data JPA.

Exemplo conceitual:

```text
BookRepository
    ↓
JpaRepository
    ↓
PostgreSQL
```

---

# Desenvolvimento incremental

O projeto deve ser construído em pequenas etapas funcionais.

## Milestone 1 — Fundação

* [ ] Criar projeto Spring Boot
* [ ] Configurar Gradle
* [ ] Executar aplicação localmente
* [ ] Criar banco PostgreSQL `bookshelf`
* [ ] Configurar datasource
* [ ] Configurar JPA/Hibernate
* [ ] Criar entidade `Book`
* [ ] Criar `BookRepository`
* [ ] Confirmar persistência no PostgreSQL

## Milestone 2 — Listagem

* [ ] Criar `BookService`
* [ ] Criar `BookController`
* [ ] Implementar `GET /`
* [ ] Criar `home.html`
* [ ] Buscar livros no banco
* [ ] Renderizar livros utilizando Thymeleaf
* [ ] Exibir título
* [ ] Exibir autor
* [ ] Exibir rating

Neste ponto, capas podem utilizar placeholders.

## Milestone 3 — Cadastro

* [ ] Criar `dashboard.html`
* [ ] Implementar `GET /dashboard`
* [ ] Criar formulário de cadastro
* [ ] Implementar `POST /dashboard/books`
* [ ] Validar título
* [ ] Validar autor
* [ ] Validar rating
* [ ] Persistir livro
* [ ] Redirecionar após cadastro

## Milestone 4 — Capas

* [ ] Configurar upload multipart
* [ ] Aceitar imagens no Dashboard
* [ ] Gerar nomes únicos para arquivos
* [ ] Salvar imagens em `uploads/covers/`
* [ ] Salvar referência da imagem no PostgreSQL
* [ ] Servir as imagens
* [ ] Mostrar capas nos cards

## Milestone 5 — Interface

* [ ] Criar layout definitivo da Home
* [ ] Criar layout definitivo do Dashboard
* [ ] Criar grid responsivo
* [ ] Estilizar cards
* [ ] Renderizar rating como estrelas
* [ ] Adicionar estados vazios
* [ ] Melhorar feedback de validação

## Milestone 6 — Pesquisa

* [ ] Criar barra de pesquisa
* [ ] Criar `search.js`
* [ ] Filtrar cards dinamicamente
* [ ] Pesquisar por título
* [ ] Pesquisar por autor
* [ ] Exibir estado para nenhum resultado encontrado

---

# Fora do escopo do MVP

As seguintes funcionalidades **não devem ser implementadas durante o MVP inicial**:

* autenticação
* Spring Security
* múltiplos usuários
* perfis
* mensagens privadas
* seguidores
* amizades
* permissões
* API pública
* integração com APIs externas de livros
* paginação
* recomendações
* comentários
* social feed
* sistema complexo de tags

Essas funcionalidades poderão ser adicionadas depois que o fluxo básico estiver completamente funcional.

---

# Evolução planejada

Depois do MVP, a aplicação poderá evoluir para um sistema multiusuário.

A entidade:

```text
Book
```

poderá fazer parte de uma estrutura semelhante a:

```text
User
 └── Bookshelf
      └── Book
```

Cada usuário poderá possuir um perfil público.

Exemplo:

```text
/u/victor
```

Esse perfil poderá mostrar:

* avatar
* nome
* bio
* livros
* avaliações
* estatísticas de leitura

Posteriormente poderão ser adicionados:

* autenticação
* autorização
* edição de perfil
* livros lidos
* livros sendo lidos
* livros que o usuário deseja ler
* avaliações
* favoritos
* mensagens privadas

Essas funcionalidades devem ser implementadas incrementalmente, sem comprometer a simplicidade da arquitetura atual.

---

# Princípios de desenvolvimento

## 1. Fazer funcionar antes de sofisticar

A prioridade é implementar um fluxo completo e funcional antes de investir em detalhes visuais ou abstrações.

## 2. Evitar overengineering

Não criar abstrações para problemas que ainda não existem.

## 3. Backend primeiro

Persistência e regras da aplicação devem funcionar antes do refinamento visual.

## 4. Desenvolvimento incremental

Cada milestone deve resultar em software executável.

## 5. Entender o código

Este projeto também existe para desenvolver competência prática em backend Java.

Código gerado com auxílio de IA não deve ser incorporado sem compreensão do seu funcionamento.

## 6. Manter o escopo

Ideias novas não devem automaticamente entrar no milestone atual.

Primeiro terminar o MVP.

Depois expandir.

---

# Definition of Done do MVP

O MVP estará concluído quando for possível:

1. iniciar a aplicação;
2. conectar ao PostgreSQL;
3. acessar `/dashboard`;
4. preencher título, autor e rating;
5. selecionar uma imagem de capa;
6. cadastrar o livro;
7. persistir seus dados;
8. acessar `/`;
9. visualizar o livro cadastrado;
10. visualizar sua capa;
11. visualizar seu rating em estrelas;
12. pesquisar pelo título ou autor sem recarregar a página;
13. reiniciar a aplicação e continuar vendo os livros anteriormente cadastrados.

Quando todos esses pontos funcionarem, o MVP inicial estará completo.

---

# Próximo passo

O primeiro milestone é deliberadamente pequeno:

```text
Spring Boot
    ↓
PostgreSQL
    ↓
Book Entity
    ↓
BookRepository
    ↓
Persistência funcionando
```

Nenhuma interface precisa ser construída antes dessa fundação estar funcionando.
