#!/usr/bin/env bash
# Compila e executa o Gerenciador de Biblioteca (Linux/macOS)
set -e
cd "$(dirname "$0")"
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out biblioteca.aplicacao.Main
