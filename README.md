# CLI TASK APP
> **Gerenciamento de Estado e Persistência de Dados em Ecossistemas Java.**

Este projeto é uma aplicação de linha de comando (CLI) robusta, desenvolvida para demonstrar competências em engenharia de software, automação de build e integração com bibliotecas de mercado para persistência de dados.

---

## Stack Tecnológica & Diferenciais Estratégicos

* **Java 24 (Latest LTS Focus)**: Utilização de recursos modernos para garantir performance e tipagem segura.
* **Apache Maven**: Orquestração completa do ciclo de vida do projeto (Build, Test, Package).
* **Jackson Databind**: Engine de alta performance para serialização e desserialização de objetos Java em formato JSON.
* **Maven Shade Plugin**: Implementação da estratégia de **Uber-JAR**, consolidando todas as dependências em um único artefato portátil e independente.

---

## Engenharia e Resiliência do Código

A aplicação foi construída sob os pilares da **Qualidade de Software**:

1.  **Tratamento de Exceções**: Implementação de blocos `try-catch` para capturar `NumberFormatException`, `NullPointerException` e `IOException`, garantindo que o software permaneça operacional mesmo diante de entradas inválidas do usuário.
2.  **I/O Handling**: Uso de fluxos de saída seguros com `FileOutputStream` e `writerWithDefaultPrettyPrinter()`, garantindo que o arquivo de dados seja gerado com indentação profissional e legível.
3.  **Persistência Inteligente**: O sistema verifica a existência do banco de dados local (`task.json`) e o inicializa automaticamente caso não exista, reduzindo a fricção no primeiro uso.

---

## Estrutura de Dados (JSON)

O estado das tarefas é preservado de forma estruturada, permitindo fácil integração com outras ferramentas ou futuras APIs:

```json
[
  {
    "descricao": "Estudar arquitetura de microsserviços",
    "id": 0,
    "estado": "Em andamento",
    "data": 1773602204356
  },
  {
    "descricao": "Finalizar projeto CliTaskApp",
    "id": 1,
    "estado": "Terminada",
    "data": 1773602208283
  }
]
````
## Como Executar
A distribuição foi projetada para ser autossuficiente. Não é necessário instalar o Maven para rodar a aplicação final.

Certifique-se de ter o Java (JRE) 17 ou superior instalado.

Clone o repositório ou baixe o arquivo .jar.

Coloque a pasta CLI_TASK_APP-main no disco local (C://)

Renomeie a pasta CLI_TASK_APP-main para CLI_TASK_APP

Execute o arquivo ``execute.bat`` (Windows) ou utilize o terminal:

# O que ocorre sob os panos do .bat:
````Bash
java -jar CliTaskApp-1.0-SNAPSHOT.jar
````
Autor: Geovane Rodrigues dos Santos

Estudante de Ciência da Computação | Foco em Back-end & Industria 4.0
