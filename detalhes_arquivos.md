# Detalhes dos arquivos do projeto


### ECommerceSimulacao.java

Este arquivo contém o ponto de entrada da simulação de e-commerce. Ele cria a fila de pedidos com uma capacidade máxima e inicializa o estoque com produtos pré-definidos. Três threads de trabalhadores (workers) são criadas para processar os pedidos da fila. Novos pedidos são gerados aleatoriamente e colocados na fila. A simulação também implementa o fechamento das threads de forma ordenada, aguardando o término do processamento dos pedidos.

### Estoque.java

A classe Estoque é responsável por gerenciar o estoque de produtos da loja. Ela utiliza um bloqueio de leitura e escrita (ReentrantReadWriteLock) para garantir o acesso concorrente seguro aos produtos. Ao processar um pedido, o método verifica a disponibilidade de cada item no estoque e atualiza o estoque, se necessário. Possui métodos para reabastecer o estoque e imprimir o estado atual do estoque.

### Pedido.java

Esta classe representa um pedido realizado por um cliente. Cada pedido possui um identificador único (ID do cliente) e uma lista de produtos com suas respectivas quantidades. A classe oferece métodos para acessar os produtos e o ID do cliente, que serão utilizados pelas threads que processam os pedidos.

### ReabastecimentoAutomatico.java

Esta classe implementa a lógica para reabastecimento automático do estoque. O reabastecimento é realizado periodicamente por meio de um agendador (ScheduledExecutorService). A cada intervalo de tempo definido (por exemplo, 10 segundos), a thread reabastece o estoque com quantidades pré-determinadas de produtos.

### RelatorioDeVendas.java

Esta classe gerencia a geração de relatórios periódicos sobre o estado das vendas. Ela mantém o controle do número total de pedidos processados, o valor total das vendas e o número de pedidos rejeitados. Assim como o reabastecimento, a geração de relatórios também é feita de maneira assíncrona por meio de um agendador. A cada 30 segundos, um novo relatório é impresso.

### Worker.java

Esta classe representa um trabalhador que processa os pedidos na fila. Cada thread de Worker retira um pedido da fila e tenta processá-lo, verificando se os produtos estão disponíveis no estoque. Se o processamento for bem-sucedido, o pedido é completado; caso contrário, ele é rejeitado por falta de estoque.
