Assignment 2 — Cool Weather Application (Kotlin Exercises)

Course: Desenvolvimento de Aplicações Móveis (DAM)
Student(s): Lucas Mendes Duarte
Date: 12/04/2026
Repository URL: https://github.com/LucasDuarte2003/DAM_TP2_Kotlin

## 1. Introduction

Este trabalho tem como objetivo aprofundar os conhecimentos da linguagem Kotlin através de quatro exercícios práticos. Os exercícios abrangem o uso de sealed classes, funções de extensão, funções de ordem superior, genéricos, lambdas e sobrecarga de operadores.

## 2. System Overview

O projeto está dividido em quatro exercícios principais:

- Exercício 1.1: Sistema de registo de eventos com sealed classes, funções de extensão e funções de ordem superior.
- Exercício 1.2: Cache genérica em memória com suporte a múltiplos tipos de chave e valor.
- Exercício 1.3: Pipeline configurável de transformações sobre listas de strings.
- Exercício 1.4: Biblioteca de vetores 2D com sobrecarga de operadores.

## 3. Architecture and Design

O projeto segue uma estrutura baseada em pacotes:

- `section1` — contém os quatro ficheiros dos exercícios Kotlin

Cada exercício está num ficheiro separado dentro do pacote `section1` para maior clareza e organização.

## 4. Implementation

### Exercício 1.1 — Event Log

Foi implementado um sistema de registo de eventos usando:

- `sealed class Event` com três subclasses: `Login`, `Purchase` e `Logout`
- Função de extensão `filterByUser` sobre `List<Event>` para filtrar eventos por utilizador
- Função de extensão `totalSpent` sobre `List<Event>` para calcular o total gasto por utilizador usando `filterIsInstance` e `sumOf`
- Função de ordem superior `processEvents` que aplica um lambda a cada evento
- Expressão `when` dentro do lambda para imprimir uma descrição legível de cada evento

### Exercício 1.2 — Cache Genérica

Foi implementada uma classe genérica `Cache<K : Any, V : Any>` com:

- Armazenamento interno usando `MutableMap<K, V>`
- Métodos `put`, `get`, `evict` e `size` para operações básicas
- Método `getOrPut` que devolve o valor existente ou calcula e guarda um novo valor
- Método `transform` que aplica uma transformação ao valor existente e devolve `true` se a chave existir
- Método `snapshot` que devolve uma cópia imutável do conteúdo atual

### Exercício 1.3 — Pipeline

Foi implementada uma classe `Pipeline` com:

- Lista interna de etapas, onde cada etapa tem um nome e uma função de transformação do tipo `(List<String>) -> List<String>`
- Método `addStage` para adicionar etapas à pipeline
- Método `execute` que corre o input por todas as etapas em ordem
- Método `describe` que imprime o nome de cada etapa
- Função de topo `buildPipeline` que aceita um lambda com `Pipeline` como recetor

### Exercício 1.4 — Vec2

Foi implementada uma `data class Vec2` com sobrecarga de operadores:

- Operadores aritméticos `+`, `-`, `*` e `-` unário
- Operador `compareTo` para comparar vetores pela sua magnitude, implementando `Comparable<Vec2>`
- Operador `get` para aceder às componentes por índice
- Funções `magnitude`, `dot` e `normalized`

## 5. Testing and Validation

- Exercício 1.1: O output corresponde exatamente ao esperado pelo enunciado, incluindo os totais gastos por utilizador e a lista de eventos filtrados.
- Exercício 1.2: Foram testadas duas combinações de tipos diferentes (`Cache<String, Int>` e `Cache<Int, String>`), com o output a corresponder ao esperado.
- Exercício 1.3: A pipeline processa corretamente os logs, filtrando apenas os erros e aplicando todas as transformações na ordem correta.
- Exercício 1.4: Todos os operadores e funções foram testados com os dados do enunciado, com o output a corresponder ao esperado.

## 6. Usage Instructions

1. Clonar o repositório: `git clone https://github.com/LucasDuarte2003/DAM_TP2_Kotlin.git`
2. Abrir o projeto no IntelliJ IDEA
3. Correr cada exercício abrindo o ficheiro correspondente e clicando no botão play

As secções 7 a 11 não se aplicam a este repositório pois nenhum exercício era [AC OK, AI OK].

## Development Process

### 12. Version Control and Commit History

O Git foi usado ao longo do projeto com commits feitos após a conclusão de cada exercício. O repositório está disponível no GitHub em https://github.com/LucasDuarte2003/DAM_TP2_Kotlin.

### 13. Difficulties and Lessons Learned

- Exercício 1.1: O compilador não reconhecia o `when` como exaustivo dentro de um lambda do `filter`, mesmo com uma `sealed class`. A solução foi adicionar um ramo `else -> false` para satisfazer o compilador.
- Exercício 1.2: Foi necessário explorar a documentação do Kotlin para perceber como o `MutableMap.getOrPut` funciona antes de implementar a versão própria.
- Exercício 1.4: Implementar o `compareTo` com a keyword `operator` e perceber como isso interage com a interface `Comparable<T>` exigiu leitura cuidadosa da documentação.

### 14. Future Improvements

- O exercício 1.2 poderia incluir um mecanismo de expiração automática das entradas do cache.
- A pipeline do exercício 1.3 poderia suportar execução paralela das etapas independentes.
- O Vec2 poderia ser estendido para suportar mais operações matemáticas como rotação e projeção.

### 15. AI Usage Disclosure (Mandatory)

- Ferramenta de IA utilizada: Claude (claude.ai)
- Como foi utilizada: Exclusivamente para esclarecer dúvidas e ajudar na realização deste relatório.
- Responsabilidade: Eu, Lucas Mendes Duarte (nº 50735), sou totalmente responsável por todo o conteúdo submetido.
