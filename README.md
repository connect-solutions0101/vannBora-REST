# VannBora-REST

VannBora é uma aplicação backend construída com Spring Boot, criada para atender motoristas de vans escolares na gestão de passageiros, rotas e cobranças. A API oferece integração com banco de dados SQL e com a API do Telegram Bot, possibilitando notificações automáticas e persistência de dados, com foco em acessibilidade e automação de processos.

## Visão Geral

Esta API centraliza funcionalidades como notificações automáticas via Telegram, gerenciamento de alunos e responsáveis, além do controle de cobranças mensais. É uma solução robusta e escalável voltada para motoristas que atuam principalmente em regiões de baixa renda.

## Funcionalidades

- Integração com banco de dados SQL
- Envio e recebimento de mensagens via Telegram Bot API
- Execução de tarefas agendadas (polling automático)
- Suporte a configuração por variáveis de ambiente

## Tecnologias Utilizadas

- Java 17
- Spring Boot (Spring Data JPA, Spring Scheduling)
- Maven
- MySQL
- Telegram Bot API
- Docker (opcional)

## Pré-requisitos

- Java 17 ou superior
- Maven 3.9.6 ou superior
- Banco de dados SQL (da sua escolha)
- Docker (opcional)

## Instruções de Configuração

### 1. Clone o repositório

```
git clone https://github.com/connect-solutions0101/vannBora-REST.git
cd vannBora-REST
```

### 2. Crie o arquivo .env

Crie um arquivo `.env` na raiz do projeto com o seguinte conteúdo:

```
API_HGBRASIL_KEY=
API_SECURITY_TOKEN_SECRET=

DB_HOST=jdbc:mysql://localhost:3306/database
DB_USER=seu_usuario
DB_PASSWORD=sua_senha

TELEGRAM_BOT_TOKEN=seu_token_do_bot
```

### 3. Execute a aplicação

#### Usando IntelliJ IDEA

Abra o projeto e execute a classe `VannboraApplication`.

#### Usando Maven

```
mvn clean package
java -jar target/app.jar
```

#### Usando Docker

```
docker build -t vannbora-app .
docker run -p 8080:8080 --env-file .env vannbora-app
```

### 4. Acesse a aplicação

A aplicação estará disponível em `http://localhost:8080` por padrão.

## Principais Arquivos

- `src/main/resources/application.properties`: Configurações do Spring Boot
- `.env`: Variáveis sensíveis de ambiente
- `Dockerfile`: Configuração para execução em container Docker

## Solução de Problemas

- Verifique se o servidor MySQL está em execução e com as credenciais corretas.
- Para execução via Docker, use `host.docker.internal` como host do banco.
- Confirme se o `TELEGRAM_BOT_TOKEN` está correto e válido.

