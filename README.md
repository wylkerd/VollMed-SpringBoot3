## 💻 Sobre o projeto

Voll.med é uma clínica médica fictícia que precisa de um aplicativo para gestão de consultas. O aplicativo deve possuir funcionalidades que permitam o cadastro de médicos e de pacientes, e também o agendamento e cancelamento de consultas.

Enquanto um time de desenvolvimento será responsável pelo aplicativo mobile, o nosso será responsável pelo desenvolvimento da API Rest desse projeto.

---

## ⚙️ Funcionalidades

- [x] CRUD de médicos;
- [x] CRUD de pacientes;
- [ ] Agendamento de consultas(em breve);
- [ ] Cancelamento de consultas(em breve);

---

## 🎨 Layout

O layout da aplicação mobile está disponível neste link: <a href="https://www.figma.com/file/N4CgpJqsg7gjbKuDmra3EV/Voll.med">Figma</a>

---

## 📄 Documentação

A documentação das funcionalidades da aplicação pode ser acessada neste link: <a href="https://trello.com/b/O0lGCsKb/api-voll-med">Trello</a>

---

## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- **[Java 17](https://www.oracle.com/java)**
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org)**
- **[MySQL](https://www.mysql.com)**
- **[Hibernate](https://hibernate.org)**
- **[Flyway](https://flywaydb.org)**
- **[Lombok](https://projectlombok.org)**

---

## 📝 Licença

Projeto desenvolvido por [Alura](https://www.alura.com.br) e utilizado nos cursos de Spring Boot.

Instrutor: [Rodrigo Ferreira](https://cursos.alura.com.br/user/rodrigo-ferreira)

---
## Demais dicas
* Adicione algo novo como uma Classe ou Pacote clicando no diretório desejado (example med.voll.api) e pressionando <b>ALT + INSERT (or INS)<b/>.
* Auto reload do Spring Boot Devtools, deve ser configurado no Intellij IDE, em <b>Settings (ctrl + alt + s) -> Build, Execution, Deployment -> Compiler -> e marque a opção 'Build Project Automatically'<b/>. Também em <b>Settings (ctrl + alt + s) -> Advanced settings -> marque a opção 'Allow auto-making to start even if developed application is currently running'<b/>.
<br/>
## Como gerar um novo projeto com Spring Initializr?
### * SpringBoot tem o servidor Tomcat embutido por padrão para execução da aplicação
<img src="./SpringInitializer_configurcao_SpringBoot3.png"/>

## Como adicionar novas dependencias?
1. Acessar Spring Initializr e selecionar as dependecias que deseja (Dependencias que adicionamos para Validação e Persistencia de dados: Validation, MySQL Driver, Spring Data JPA e Flyway Migration) https://start.spring.io/
2. Após selecionar as dependencias, certifique de que é um PROJETO MAVEN, clicar em 'EXPLORE' e copiar as dependencias que foram inclusas no pom.xml. 
3. Colar as dependencias copiadas no projeto, no arquivo pom.xml
4. Na aba 'Maven' no canto superior direito do Inttellij, clique em reload, para que seja feito o download das dependencias, pare o projeto e rode-o novamente.

<img src="./adicionando_dependencias.png"/>
<img src="./copiando_deps.png"/>