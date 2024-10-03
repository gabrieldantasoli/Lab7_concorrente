#!/bin/bash

DIR=~/Lab7_concorrente/ecommerce

if [ ! -d "$DIR" ]; then
  echo "Erro: O diretório $DIR não existe."
  exit 1
fi

cd "$DIR" || { echo "Erro: Não foi possível entrar no diretório $DIR."; exit 1; }

echo "Compilando os arquivos Java em $DIR..."
javac *.java

if [ $? -ne 0 ]; then
  echo "Erro: A compilação falhou."
  exit 1
fi

echo "Compilação bem-sucedida!"

echo "Executando o programa..."
java ecommerce.ECommerceSimulacao
