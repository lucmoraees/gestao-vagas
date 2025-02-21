<h1>🏢 Gestão de Vagas API</h1>

<h2>📌 Sobre o Projeto</h2>
<p>A <strong>Gestão de Vagas API</strong> é uma <strong>API REST</strong> para controle de <strong>empresas</strong>, <strong>vagas</strong> e <strong>aplicações de candidatos</strong>, utilizando os principais pacotes do <strong>Spring Boot</strong> para persistência, segurança e autorização. O projeto segue os princípios de <strong>Domain-Driven Design (DDD)</strong> para garantir uma arquitetura escalável e modular.</p>

---

<h2>🏗 Arquitetura do Projeto</h2>
<p>A API foi construída utilizando <strong>Spring Boot</strong> e está organizada por módulos seguindo os <strong>Bounded Contexts</strong> do DDD. Cada módulo possui seus próprios controladores, entidades, DTOs, repositórios e casos de uso.</p>

<h3>📁 Estrutura do Projeto</h3>
<pre>
gestao-vagas-api
│── src
│   ├── main
│   │   ├── java/com/lucasmoraes/gestaovagas
│   │   │   ├── config
│   │   │   ├── exceptions
│   │   │   ├── provider
│   │   │   ├── security
│   │   │   ├── modules
│   │   │   │   ├── candidate
│   │   │   │   │   ├── controllers
│   │   │   │   │   ├── DTO
│   │   │   │   │   ├── entities
│   │   │   │   │   ├── repositories
│   │   │   │   │   ├── useCases
│   │   │   │   ├── company
│   │   │   │   │   ├── controllers
│   │   │   │   │   ├── DTO
│   │   │   │   │   ├── entities
│   │   │   │   │   ├── repositories
│   │   │   │   │   ├── useCases
│   │   ├── resources
│   ├── test
│── Dockerfile
│── docker-compose.yml
│── pom.xml
│── README.md
</pre>

---

<h2>🚀 Tecnologias Utilizadas</h2>

<h3>📌 <strong>Backend</strong></h3>
<ul>
    <li><strong>Java 17</strong></li>
    <li><strong>Spring Boot</strong></li>
    <li><strong>Spring Data JPA</strong></li>
    <li><strong>Spring Security</strong></li>
    <li><strong>JWT (JSON Web Token)</strong></li>
    <li><strong>Springdoc OpenAPI (Swagger)</strong></li>
</ul>

<h3>🛠 <strong>Banco de Dados e Persistência</strong></h3>
<ul>
    <li><strong>PostgreSQL</strong> (Banco de dados principal)</li>
    <li><strong>JPA / Hibernate</strong> (Mapeamento ORM)</li>
</ul>

<h3>☁️ <strong>Infraestrutura e Deploy</strong></h3>
<ul>
    <li><strong>AWS EC2</strong> (Hospedagem do backend)</li>
    <li><strong>AWS RDS</strong> (Banco de dados gerenciado)</li>
    <li><strong>Docker & Docker Compose</strong> (Containerização)</li>
    <li><strong>Docker Hub</strong> (Registro de imagens)</li>
    <li><strong>CI/CD com GitHub Actions</strong> (Pipeline de deploy)</li>
    <li><strong>Render</strong> (Deploy da API)</li>
</ul>

<h3>📊 <strong>Observabilidade e Monitoramento</strong></h3>
<ul>
    <li><strong>Prometheus</strong> (Coleta de métricas)</li>
    <li><strong>Grafana</strong> (Dashboard de monitoramento)</li>
    <li><strong>SonarQube</strong> (Análise de código)</li>
</ul>

<h3>🧪 <strong>Testes</strong></h3>
<ul>
    <li><strong>JUnit 5</strong></li>
    <li><strong>Mockito</strong> (Mocking de dependências)</li>
</ul>

<h3>🔒 <strong>Segurança e Autenticação</strong></h3>
<ul>
    <li><strong>JWT</strong> (Autenticação)</li>
    <li><strong>Validação de roles para manipulação de endpoints</strong> (Role-Based Access Control - RBAC)</li>
</ul>

---

<h2>📜 Documentação da API</h2>
<p>A API possui uma documentação interativa gerada com <strong>Swagger</strong>. Após rodar a aplicação, acesse:</p>
<p>🔗 <a href="http://localhost:8080/swagger-ui.html" target="_blank"><strong>Swagger UI - Documentação da API</strong></a></p>

---

<h2>🔧 Configuração e Execução</h2>

<h3>🚀 <strong>Rodando a API Localmente</strong></h3>
<ol>
    <li><strong>Clone o repositório:</strong>
        <pre>git clone https://github.com/seu-usuario/gestao-vagas-api.git</pre>
    </li>
    <li><strong>Acesse o diretório do projeto:</strong>
        <pre>cd gestao-vagas-api</pre>
    </li>
    <li><strong>Configure o banco de dados no <code>application.properties</code>:</strong>
        <pre>
spring.datasource.url=jdbc:postgresql://localhost:5432/gestao_vagas
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update
        </pre>
    </li>
    <li><strong>Execute a aplicação:</strong>
        <pre>mvn spring-boot:run</pre>
    </li>
</ol>

<h3>🐳 <strong>Rodando com Docker</strong></h3>
<ol>
    <li><strong>Crie a imagem Docker:</strong>
        <pre>docker build -t gestao-vagas-api .</pre>
    </li>
    <li><strong>Inicie os containers:</strong>
        <pre>docker-compose up -d</pre>
    </li>
</ol>

---

<h2>📊 Observabilidade e Monitoramento</h2>
<p>A API expõe métricas para <strong>Prometheus</strong>, que podem ser acessadas em:</p>
<p>🔗 <a href="http://localhost:9090" target="_blank">http://localhost:9090</a></p>

<p>Os dashboards no <strong>Grafana</strong> podem ser configurados para exibir métricas sobre requisições, uso de CPU, latência e erros.</p>

---

<h2>📝 Licença</h2>
<p>Este projeto foi desenvolvido para fins educacionais e está disponível sob a licença <strong>MIT</strong>.</p>
