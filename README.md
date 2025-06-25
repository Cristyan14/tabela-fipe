# 🚗 Projeto: Consulta de Veículos com API Tabela FIPE

Este é um projeto Java que permite consultar veículos (carros, motos e caminhões) utilizando a [API pública da Tabela FIPE](https://deividfortuna.github.io/fipe/). O usuário interage via terminal e pode buscar por marcas, modelos e consultar valores dos veículos disponíveis por ano.

---

## 📚 Tecnologias Utilizadas

- Java 17+
- Biblioteca `java.net.http.HttpClient` (para requisições HTTP)
- Streams e Lambdas (Java 8+)
- Biblioteca [`Jackson`](https://github.com/FasterXML/jackson) (para conversão de JSON)
- API pública: https://parallelum.com.br/fipe/api/v1/

---

## 🎯 Funcionalidades

- Seleção entre carros, motos e caminhões.
- Listagem de marcas disponíveis para o tipo selecionado.
- Busca de modelos por trecho do nome.
- Listagem de modelos encontrados com filtro por nome.
- Consulta de todos os anos disponíveis para um modelo.
- Retorno dos dados completos do(s) veículo(s) selecionado(s), como:
  - Marca
  - Modelo
  - Ano
  - Valor
  - Tipo de combustível
  - Código FIPE
  - Mês de referência

---

## 📦 Estrutura de Pacotes

com.principal -> Classe Principal (ponto de entrada)
com.services -> Serviços utilitários (Consumo da API, conversão de dados)
com.model -> Modelos de dados (Marca, Modelo, Veículo)

yaml
Copiar
Editar

---

## ▶️ Como Executar

1. Clone o projeto:
   ```bash
   git clone https://github.com/Cristyan14/tabela-fipe.git
Importe no Eclipse como um projeto Java.

Execute a classe Principal.java:

java
Copiar
Editar
public static void main(String[] args) {
    new Principal().exibeMenu();
}
📌 Exemplo de Uso
yaml
Copiar
Editar
********-Tabela Fipe*******
Selecione o tipo de automovél
Carros
Motos
Caminhões
***************
Digite: carros

[Lista de marcas exibida...]

Escreva o nome da marca:
Digite: toyota

[Lista de modelos exibida...]

Digite um trecho do veículo a ser buscado:
Digite: corolla

Modelos encontrados:
[...Modelos Corolla...]

Digite o código do modelo desejado:
Digite: 12345

Veículos Filtrados:
Marca: Toyota
Modelo: Corolla XEi 2.0 Flex 16V Aut.
Ano: 2023
Valor: R$ 145.000,00
...

📋 Notas
A aplicação faz uso extensivo de Optional, Stream, e Collectors para processar os dados.

Os dados retornados são atualizados conforme o mês vigente da Tabela FIPE.

Pode ser expandido facilmente para adicionar filtros por valor, exportação para CSV, ou interface gráfica.
