# CLI TASK APP
CLI Task Manager: Gerenciamento de Estado e Persistência em Java
Um sistema de gerenciamento de tarefas via terminal (CLI), projetado com foco em resiliência, distribuição autônoma (Uber-JAR) e persistência de dados em JSON.

Resumo Executivo
O CLI Task Manager é uma aplicação Java de linha de comando desenvolvida para oferecer um gerenciamento rápido e eficiente de tarefas cotidianas. Mais do que um simples aplicativo de console, este projeto foi arquitetado para demonstrar competências essenciais na engenharia de software moderna: automação de build, gerenciamento de dependências, tratamento rigoroso de exceções e serialização avançada de dados.

A arquitetura do projeto simula cenários reais de aplicações do mercado, onde scripts e aplicações em background precisam rodar de forma confiável, silenciosa e sem dependência de interfaces gráficas (GUI), consumindo o mínimo de recursos do sistema.

Stack Tecnológica & Práticas de Mercado
Linguagem: Java (Utilizando recursos modernos de encapsulamento e tipagem forte).

Build Tool: Apache Maven.

Persistência de Dados: Biblioteca Jackson (Databind/Core).

Paradigma: Programação Orientada a Objetos (POO) & Defensive Programming.

Decisões Arquiteturais e Competências Técnicas
1. Automação de Build e Distribuição (Maven & Shade Plugin)
Um dos grandes diferenciais deste projeto é a sua esteira de empacotamento. Em ambientes corporativos, não basta o código rodar na máquina do desenvolvedor; ele precisa ser facilmente distribuível.

Gerenciamento de Dependências: O pom.xml foi configurado para gerenciar as bibliotecas do ecosistema Jackson de forma centralizada.

Estratégia de Uber-JAR (Fat-JAR): Utilizei o maven-shade-plugin durante a fase de package do ciclo de vida do Maven. Isso me permitiu compilar o código-fonte e "fundir" todas as dependências externas (como o Jackson) em um único arquivo .jar executável.

Disponibilização Profissional: O usuário final não precisa ter o Maven ou o Jackson instalados. O software é entregue como um artefato autossuficiente (apenas 6.7 MB), acompanhado de um script .bat para execução nativa com um clique (Double-Click Execution) no Windows, mascarando a complexidade do terminal para o usuário final.

2. Persistência de Dados e Mapeamento Objeto-Relacional (Jackson)
A aplicação mantém o estado das tarefas entre as execuções utilizando arquivos JSON, o formato padrão da indústria para troca de dados e APIs RESTful.

Serialização Inteligente: Utilização do ObjectMapper da biblioteca Jackson para converter instâncias da classe Task em objetos JSON. Foi implementado o método writerWithDefaultPrettyPrinter() para garantir que o arquivo task.json gerado seja humanamente legível, facilitando auditorias e debugs.

Desserialização Complexa: Ao iniciar, o sistema lê o arquivo e reconstrói a lista de objetos Java dinamicamente utilizando a TypeFactory do Jackson (constructCollectionType(ArrayList.class, Task.class)), garantindo a integridade dos tipos e coleções.

Criação Autônoma do Banco de Dados: A aplicação não quebra se o ambiente for novo. Implementei uma lógica no main utilizando arquivo.exists() e arquivo.createNewFile() para forjar a infraestrutura de dados (C://CLI_TASK_APP/task.json) automaticamente na primeira execução.

3. Programação Defensiva e Resiliência (Tratamento de Exceções)
Aplicações de terminal estão altamente suscetíveis a erros de input humano (User Error). Para garantir a estabilidade (uptime) do software, implementei uma camada espessa de validações:

Resiliência de Input: Entradas no Scanner são validadas através de blocos try-catch. Casos de NumberFormatException (quando o usuário digita uma letra no lugar de um ID numérico) são interceptados, impedindo que a JVM (Java Virtual Machine) encerre a aplicação de forma abrupta.

Proteção de Memória e Listas: Validações de IndexOutOfBoundsException e NullPointerException garantem que operações de exclusão (removeTask) e atualização (updateTask) apenas ocorram em endereços de memória válidos.

I/O Handling: Todas as operações de leitura e gravação no disco são protegidas contra IOException, e o uso de recursos é otimizado com a sintaxe Try-with-Resources (try (FileOutputStream fos = new FileOutputStream(arquivo))), garantindo que os fluxos de dados sejam fechados automaticamente, prevenindo vazamento de memória (Memory Leaks).

💻 Estrutura de Dados (Exemplo do Modelo)
A classe Task foi projetada respeitando o encapsulamento (Getters e Setters), controle de IDs dinâmicos atrelados ao tamanho do array, e versionamento de data com java.util.Date. O resultado é um payload JSON altamente estruturado:

JSON[
  {
    "descricao": "Estudar Spring Boot e REST APIs",
    "id": 0,
    "estado": "Em andamento",
    "data": 1773602204356
  },
  {
    "descricao": "Refatorar pom.xml",
    "id": 1,
    "estado": "Terminada",
    "data": 1773602208283
  }
]
Como Executar e Testar
Graças à arquitetura de distribuição adotada, rodar este projeto é extremamente simples, exigindo apenas o Java Runtime Environment (JRE) versão 17 ou superior.

Faça o clone do repositório ou baixe o arquivo compilado na aba Releases.

Coloque a pasta "CLI_TASK_APP" no disco local (C:)

No Windows, basta dar um duplo clique no arquivo execute.bat.

O script inicializará a máquina virtual Java e abrirá o terminal interativo automaticamente:

Bash
# O que ocorre sob os panos do .bat:
java -jar CliTaskApp-1.0-SNAPSHOT.jar

Autor: Geovane Rodrigues dos Santos

Estudante de Ciência da Computação | Foco em Back-end & Industria 4.0
