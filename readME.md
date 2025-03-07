# Spring-Boot-PetShop-API
Desafio do Decola Tech, criação de API  utilizando SpringBoot

## Funcionamento
Foi implementado um CRUD visando simular um sistema de Petshop, foi utilizado swagger para documentação e Postgres como banco de dados

## Screenshot da página do Swagger
![image](https://github.com/user-attachments/assets/4d37a68f-2112-4a47-b929-726a5379f1d1)

## Diagrama de Classes
```mermaid
classDiagram
    class User {
        +id: int
        +name: str
        +email: str
        +phone: str
    }

    class Pet {
        +id: int
        +name: str
        +species: str
        +breed: str
        +age: int
    }

    class Appointment {
        +id: int
        +date: datetime
        +status: str
    }

    class Service {
        +id: int
        +name: str
        +price: float
        +description: str
    }

    User "1" -- "0..*" Pet : owns
    User "1" -- "0..*" Appointment : schedules
    Appointment "1" -- "1" Pet : for
    Appointment "1" -- "1" Service : includes
