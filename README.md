# sessao-votacao
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/yuriranieri/sessao-votacao/blob/master/LICENSE)

## 📝 Sobre:

O projeto **sessao-votacao** possui o objetivo de gerenciar votações de associados em pautas através de sessões.

### 🎲 Modelo conceitual:

![Modelo conceitual](https://github.com/yuriranieri/assests/blob/master/modeloConceitual.PNG)

---

## 🔨 Tecnologias utilizadas:

- Java 17
- PostgreSQL
- Spring Boot
- Spring Data JPA
- Bean Validation
- JUnit 5 e Mockito
- Maven
- Lombok
- Swagger e OpenApi

## 🏎 Como rodar o projeto:

---

### Pré-requisitos:

- Java Versão 17 (LTS) - [Baixar OpenJDK](https://jdk.java.net/archive/)
- PostgreSQL Versão 14 - [Baixar PostgreSQL](https://www.postgresql.org/download/)
- Criação do banco de dados e suas tabelas:
  ```bash
  CREATE DATABASE sessao_votacao;

  CREATE TABLE associado(
  id serial NOT NULL,
  cpf character varying (11) NOT NULL,
  PRIMARY KEY (id)
  );

  CREATE TABLE pauta(
    id serial NOT NULL,
    assunto character varying (100) NOT NULL,
    descricao character varying (255) NOT NULL,
    PRIMARY KEY (id)
  );

  CREATE TABLE sessao(
    id serial NOT NULL,
    data_inicio timestamp NOT NULL,
    data_final timestamp NOT NULL,
    id_pauta integer NOT NULL,
      PRIMARY KEY (id),
    FOREIGN KEY (id_pauta) REFERENCES pauta (id)
  );

  CREATE TABLE voto(
    id_associado integer NOT NULL,
    id_sessao integer NOT NULL,
    voto character varying (3),
    PRIMARY KEY (id_associado, id_sessao),
    FOREIGN KEY (id_associado) REFERENCES associado (id),
    FOREIGN KEY (id_sessao) REFERENCES sessao (id)
  );

  ```
- Baixar e executar o projeto:
  ```bash
  # clonar o repositório
  git clone https://github.com/yuriranieri/sessao-votacao.git

  # executar o projeto
  ./mvnw spring-boot:run
  ```

## 🙋‍♂️ Autor:

- Yuri Furtado Ranieri