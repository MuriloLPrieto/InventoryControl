# Sistema de Gerenciamento de Estoque (Versão Console)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

Este é um projeto de estudo desenvolvido em Java com o objetivo de consolidar conhecimentos em:
- Programação Orientada a Objetos (POO)
- Acesso a banco de dados com JDBC
- Separação de responsabilidades (Model, DAO, Service)
- Boas práticas de código e estrutura

---

## 🛠 Funcionalidades

- [x] Cadastro de clientes e produtos
- [x] Listagem completa
- [x] Busca por ID
- [x] Atualização de nome, e-mail, preço e estoque
- [x] Soft delete (remoção lógica com campo `active/available`)
- [x] Validações de entrada (nome, e-mail, números negativos)
- [x] Menus interativos via terminal

---

## 💾 Tecnologias e ferramentas

- Java (JDK 24)
- JDBC
- MariaDB / MySQL
- IntelliJ IDEA
- Git & GitHub

---

## 🧱 Estrutura do projeto

```src/
├── app/
│ ├── Main.java
│ └── MenuHandler.java
├── connection/
│ └── GetConnection.java
├── dao/
│ ├── ClientDAO.java
│ ├── ClientDAOImpl.java
│ ├── ProductsDAO.java
│ └── ProductsDAOImpl.java
├── model/
│ ├── Clients.java
│ └── Products.java
├── services/
│ ├── ClientServices.java
│ └── ProductServices.java
```

---

## 🚀 Como executar o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/MuriloLPrieto/InventoryControl
   ```

    Configure o banco de dados (MariaDB/MySQL) com as seguintes tabelas:
```
CREATE TABLE Clients (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50) NOT NULL,
email VARCHAR(100) NOT NULL,
active BOOLEAN DEFAULT TRUE
);

CREATE TABLE Products (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50) NOT NULL,
price DECIMAL(10, 2) NOT NULL,
stock INT NOT NULL,
available BOOLEAN DEFAULT TRUE
);
```
Edite a classe GetConnection.java com suas credenciais e dados de conexão:
```
    DriverManager.getConnection("jdbc:mysql://localhost:3306/suabase", "usuario", "senha");
```

Compile e execute a classe Main.java

📚 Próximos passos

Versão 1: Console com Scanner (finalizada ✅)

Versão 2: Interface gráfica com JavaFX ou Swing

Versão 3: Web App com Spring Boot + API REST + banco de dados

✍️ Autor

Murilo Leite Prieto

- [GitHub](https://github.com/MuriloLPrieto)
- [LinkedIn](https://www.linkedin.com/in/murilo-l-prieto/)

---