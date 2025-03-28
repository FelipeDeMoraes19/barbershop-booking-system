<h1 align="center">💈 Barbershop Booking System</h1>

<p align="center">
  Sistema completo de agendamento para barbearias, feito com <strong>Angular</strong> + <strong>Spring Boot</strong> ☕.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=openjdk" />
  <img src="https://img.shields.io/badge/SpringBoot-API-green?style=for-the-badge&logo=spring" />
  <img src="https://img.shields.io/badge/Angular-17-DD0031?style=for-the-badge&logo=angular" />
  <img src="https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql" />
</p>

---

##  Sobre o Projeto

O **Barbershop Booking System** é um sistema FullStack com interface web para barbearias, que permite:

- ✅ Cadastro e listagem de clientes  
- ✅ Criação de serviços com preços personalizados  
- ✅ Agendamento de clientes com horário e status  
- ✅ Interface amigável com Angular e SCSS  
- ✅ Backend REST robusto com Spring Boot

---

##  Tecnologias Utilizadas

###  Backend

- Java 17 
- Spring Boot
- Spring Data JPA
- MapStruct
- Maven
- Lombok

### Frontend

- Angular 17+ 
- TypeScript
- SCSS 
- Angular Services & Routing
- Reactive Forms

---

##  Como Rodar o Projeto

###  Backend (Spring Boot)

1. Crie um banco PostgreSQL chamado `barbershop_db`
2. Atualize `application.properties` com suas credenciais
3. Execute:

```bash
./mvnw spring-boot:run
```
- A API estará disponível em:
```bash
http://localhost:8080
```

Frontend (Angular)
```bash
cd barbershop-frontend
npm install
ng serve
```


## Endpoints da API
```bash
GET	/api/clients	Listar todos os clientes
POST	/api/clients	Criar um novo cliente
GET	/api/barber-services	Listar serviços
POST	/api/barber-services	Criar um novo serviço
GET	/api/appointments	Listar agendamentos
POST	/api/appointments	Criar agendamento
```
