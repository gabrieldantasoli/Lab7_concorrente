# Sistema de Processamento de Pedidos para E-commerce

Este projeto simula um sistema de processamento de pedidos para uma loja de e-commerce. O objetivo principal é gerenciar múltiplos pedidos simultâneos feitos por diferentes clientes, garantindo a integridade dos dados do estoque com controle de concorrência.

O sistema utiliza várias threads para processar os pedidos em paralelo, reabastece automaticamente o estoque e gera relatórios periódicos sobre o número de pedidos processados, valor total de vendas e quantidade de pedidos rejeitados. Tudo isso é implementado com estruturas avançadas de concorrência do pacote java.util.concurrent em Java.

## Arquitetura do Sistema

- **Simulação de Pedidos:** A classe ECommerceSimulacao é o ponto de entrada da aplicação e simula a geração de pedidos de clientes. Os pedidos são adicionados a uma fila concorrente (BlockingQueue), que é processada por múltiplos trabalhadores.

- **Gerenciamento do Estoque:** A classe Estoque controla o estoque da loja. O acesso ao estoque é sincronizado usando bloqueios de leitura e escrita, permitindo leituras simultâneas, mas garantindo que apenas uma thread faça alterações no estoque por vez.

- **Reabastecimento Automático:** O estoque é reabastecido automaticamente em intervalos regulares pela classe ReabastecimentoAutomatico, que utiliza um agendador para executar essa tarefa periodicamente sem interromper o processamento de pedidos.

- **Relatórios de Vendas:** A classe RelatorioDeVendas é responsável por gerar relatórios periódicos, mostrando o número de pedidos processados, o valor total de vendas e quantos pedidos foram rejeitados devido à falta de estoque.

- **Workers (Threads):** A classe Worker representa os trabalhadores que processam os pedidos retirando-os da fila e verificando a disponibilidade de produtos no estoque.

## Funcionalidades Principais

- **Fila de Pedidos:** Todos os pedidos são armazenados em uma fila concorrente com capacidade limitada, onde múltiplas threads de trabalhadores processam os pedidos simultaneamente.

- **Processamento de Pedidos:** Cada pedido contém uma lista de produtos e suas quantidades. Os pedidos são processados verificando a disponibilidade no estoque.

- **Reabastecimento Automático:** A cada 10 segundos, o estoque é reabastecido automaticamente, sem bloquear as operações de leitura ou processamento de pedidos.

- **Relatórios Periódicos:** A cada 30 segundos, o sistema gera um relatório com o número de pedidos processados, valor total das vendas e quantidade de pedidos rejeitados.

## Melhorias Extras Implementadas
1. **Sistema de Pedidos Prioritários**
Implementamos a capacidade de gerenciar pedidos com diferentes níveis de prioridade. Utilizando uma estrutura de fila de prioridade (PriorityBlockingQueue), o sistema agora processa os pedidos mais urgentes antes dos de menor prioridade. Isso garante que os clientes com maior necessidade sejam atendidos mais rapidamente, otimizando o fluxo de atendimento.

2. **Controle de Transações de Pagamento**
Integramos ao sistema um controle de transações de pagamento, onde os pedidos só são processados após a confirmação do pagamento. Utilizando a classe CompletableFuture, garantimos que a confirmação do pagamento ocorra de forma assíncrona, sem bloquear o processamento de outros pedidos. Assim, melhoramos o desempenho geral do sistema, ao mesmo tempo que garantimos a segurança nas transações.

3. **Reabastecimento Inteligente**
Implementamos um sistema de reabastecimento inteligente que monitora os produtos mais populares e ajusta o reabastecimento conforme a demanda. Produtos com maior procura são reabastecidos com maior frequência, garantindo que o estoque desses itens esteja sempre disponível para novos pedidos. Isso evita a escassez dos itens mais vendidos e aumenta a eficiência do sistema de gerenciamento de estoque.

## Como Executar

1 - Clone o repositorio:
```bash
git clone <url-do-repositorio>
```

2. Compile e execute:
```bash
javac ecommerce/*.java
java ecommerce.ECommerceSimulacao
```

# Membros do Grupo
| [<img src="https://avatars.githubusercontent.com/u/87813261?v=4" width="120px;" /><br /><sub><b>Felipe Gangorra</b></sub><br />121111084](https://github.com/felipegangorra)<br /> | [<img src="https://avatars.githubusercontent.com/u/87827786?v=4 " width="120px;"/><br /><sub><b>Wander Hank</b></sub><br />120211139](https://github.com/wanderhank)<br /> | [<img src="https://avatars.githubusercontent.com/u/96066029?v=4" width="120px;"/><br /><sub><b>Gabriel Dantas</b></sub><br />121110669](https://github.com/gabrieldantasoli)<br /> | [<img src="https://avatars.githubusercontent.com/u/96154109?v=4" width="120px;"/><br /><sub><b>Lucas Lima</b></sub><br />121110517](https://github.com/lucaslimasilvafoligem)<br /> |
| :---: | :---: | :---: | :---: |
