# 🚀 SysTrack

**SysWeather** é uma aplicação desenvolvida com **Java (Spring Boot)** para auxiliar na prevenção de eventos climáticos extremos, oferecendo filtros personalizados, paginação e ordenação de dados.

## 📌 Índice

- [🧾 Sobre o Projeto](#-sobre-o-projeto)
- [⚙️ Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [🧪 Como Executar](#-como-executar)
- [📌 Endpoints da API](#-endpoints-da-api)
- [✅ Funcionalidades](#-funcionalidades)
- [🗂 Estrutura do Projeto](#-estrutura-do-projeto)
- [🗃️ Modelo de Dados](#-modelo-de-dados)
- [👨‍💻 Nossa equipe](#-nossa-equipe)

---

## 🧾 Sobre o Projeto

### SysWeather – Sistema de Monitoramento de Eventos Climáticos Extremos

**Desafio:**  
Desenvolvido para o desafio “Eventos Extremos” da FIAP, o SysWeather é uma solução tecnológica inovadora para prevenção, detecção e monitoramento de desastres naturais, como enchentes, tempestades e alagamentos urbanos. O sistema integra tecnologias modernas, incluindo IoT, aplicativo mobile, banco de dados relacional, API RESTful, Docker e arquitetura empresarial, com o objetivo de proteger vidas e otimizar a resposta emergencial em áreas de risco.

**Objetivos:**  
- Monitorar condições meteorológicas em tempo real por meio de sensores IoT.  
- Emitir alertas de risco para usuários cadastrados.  
- Exibir informações sobre cidades afetadas, status dos eventos e recomendações de ações.  
- Permitir o gerenciamento remoto e seguro das informações por equipes técnicas.

**Público-Alvo:**  
Cidadãos de áreas de risco, gestores públicos municipais, Defesa Civil, agentes de campo e voluntários envolvidos na resposta a desastres ambientais.

**Impactos Esperados:**  
- Redução do tempo de resposta a eventos climáticos extremos.  
- Maior conscientização da população sobre riscos ambientais.  
- Criação de uma base de dados climática para análises futuras.  
- Escalabilidade para outras regiões e tipos de desastres.

---

## ⚙️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Oracle Database
- Lombok
- Jakarta Validation
- Swagger/OpenAPI
- Maven

---

## 🧪 Como Executar

### Pré-requisitos

- Java 17+
- Maven 3.8+

### Passos

```bash
# Clone o repositório
git clone https://github.com/guurangel/SysWeather.git

# Acesse a pasta do projeto
cd SysTrack

# Compile o projeto
mvn clean install

# Execute a aplicação
mvn spring-boot:run
```

A API estará disponível em:  
📍 `http://localhost:8080`

Acesse o Swagger para testar os endpoints:  
📘 `http://localhost:8080/swagger-ui.html`

---

## 📌 Endpoints da API

### 🏙️ Municípios

- `GET /municipios` — Lista municípios com filtros e paginação
- `POST /municipios` — Cadastra um novo município
- `GET /municipios/{id}` — Busca município por ID
- `PUT /municipios/{id}` — Atualiza dados de um município
- `DELETE /municipios/{id}` — Remove um município

**Filtros disponíveis (como parâmetros da URL):**

- `nome` — nome do município (parcial ou completo)
- `estado` — estado (UF)
- `numeroHabitantes`, `numeroHabitantesMin`, `numeroHabitantesMax` — número exato ou intervalo de habitantes
- `regiao` — região do país
- `areaKm2`, `areaKm2Min`, `areaKm2Max` — área total em km², exata ou intervalo
- `altitude`, `altitudeMin`, `altitudeMax` — altitude, exata ou intervalo

---

### 🔔 Notificações

- `GET /notificacoes` — Lista todas as notificações de eventos climáticos (ordenadas por data de envio)

---

### ⚠️ Ocorrências

- `GET /ocorrencias` — Lista ocorrências registradas com paginação
- `POST /ocorrencias` — Cadastra uma nova ocorrência
- `GET /ocorrencias/{id}` — Busca ocorrência por ID

---

### 👥 Usuários

- `GET /usuarios` — Lista usuários com filtros e paginação
- `POST /usuarios` — Cadastra um novo usuário
- `GET /usuarios/{id}` — Busca usuário por ID
- `PUT /usuarios/{id}` — Atualiza dados de um usuário
- `DELETE /usuarios/{id}` — Remove um usuário

**Filtros disponíveis (como parâmetros da URL):**

- `municipioNome` — nome do município associado ao usuário
- `dataCadastro`, `dataCadastroInicio`, `dataCadastroFim` — data exata ou intervalo de cadastro
- `dataNascimento`, `dataNascimentoInicio`, `dataNascimentoFim` — data exata ou intervalo de nascimento

---

## ✅ Funcionalidades

- 🔎 Filtros dinâmicos com `JpaSpecificationExecutor`
- 🧱 Organização em camadas (controller, service, repository)
- 📖 Validações detalhadas com mensagens amigáveis
- 📊 Documentação interativa via Swagger
- 📦 Paginação e ordenação nos endpoints

---

## 🗂 Estrutura do Projeto

```plaintext
SYSWEATHER/
├── .mvn/
├── vscode/
├── src/
│   ├── main/
│   │   ├── java/com/java/sysweather/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   │   ├── MunicipioController.java
│   │   │   │   ├── NotificacaoController.java
│   │   │   │   ├── OcorrenciaController.java
│   │   │   │   └── UsuarioController.java
│   │   │   ├── dto/response/
│   │   │   │   ├── MunicipioResumoResponse.java
│   │   │   │   ├── NotificacaoOcorrenciaResponse.java
│   │   │   │   ├── OcorrenciaResponse.java
│   │   │   │   ├── OcorrenciaResumoResponse.java
│   │   │   │   ├── UsuarioResponse.java
│   │   │   │   └── UsuarioResumoResponse.java
│   │   │   ├── exception/
│   │   │   │   └── ValidationHandler.java
│   │   │   ├── mapper/
│   │   │   │   ├── MunicipioMapper.java
│   │   │   │   ├── NotificacaoMapper.java
│   │   │   │   ├── OcorrenciaMapper.java
│   │   │   │   └── UsuarioMapper.java
│   │   │   ├── model/
│   │   │   │   ├── enums/
│   │   │   │   │   ├── Clima.java
│   │   │   │   │   ├── Estado.java
│   │   │   │   │   ├── NivelRisco.java
│   │   │   │   │   ├── Regiao.java
│   │   │   │   │   └── TipoOcorrencia.java
│   │   │   │   ├── Municipio.java
│   │   │   │   ├── NotificacaoOcorrencia.java
│   │   │   │   ├── Ocorrencia.java
│   │   │   │   └── Usuario.java
│   │   │   ├── repository/
│   │   │   │   ├── MunicipioRepository.java
│   │   │   │   ├── NotificacaoRepository.java
│   │   │   │   ├── OcorrenciaRepository.java
│   │   │   │   └── UsuarioRepository.java
│   │   │   ├── service/
│   │   │   │   ├── MunicipioService.java
│   │   │   │   ├── OcorrenciaService.java
│   │   │   │   └── UsuarioService.java
│   │   │   ├── specification/
│   │   │   │   ├── MunicipioSpecification.java
│   │   │   │   └── UsuarioSpecification.java
│   │   │   ├── App.java
│   │   │   └── resources/
│   │   │       ├── static/
│   │   │       ├── templates/
│   │   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```
---

## 🗃️ Modelo de Dados

### Municipio

```java
id: Long
nome: String
estado: Estado
numero_habitantes: Integer
clima: Clima
regiao: Regiao
altitude: Double
areaKm2: Double
usuarios: List<Usuario>
ocorrencias: List<Ocorrencia>
```

### Usuario

```java
id: Long
nome: String
email: String
senha: String
cpf: String
dataNascimento: LocalDate
dataCadastro: LocalDateTime
municipio: Municipio
notificacoes: List<NotificacaoOcorrencia>
```

### Ocorrencia
```java
id: Long
descricao: String
tipo: TipoOcorrencia
nivelRisco: NivelRisco
dataOcorrencia: LocalDateTime
municipio: Municipio
```

### NotificacaoOcorrencia
```java
id: Long
mensagem: String
dataEnvio: LocalDateTime
usuario: Usuario
ocorrencia: Ocorrencia
```

---

## 👨‍💻 Nossa equipe

**Gustavo Rangel**  
💼 Estudante de Análise e Desenvolvimento de Sistemas na FIAP  
🔗 [linkedin.com/in/gustavoorangel](https://www.linkedin.com/in/gustavoorangel)

**David Rapeckman**  
💼 Estudante de Análise e Desenvolvimento de Sistemas na FIAP  
🔗 [linkedin.com/in/davidrapeckman](https://www.linkedin.com/in/davidrapeckman)

**Luis Felippe Morais**  
💼 Estudante de Análise e Desenvolvimento de Sistemas na FIAP  
🔗 [linkedin.com/in/luis-felippe-morais-das-neves-16219b2b9](https://www.linkedin.com/in/luis-felippe-morais-das-neves-16219b2b9)
