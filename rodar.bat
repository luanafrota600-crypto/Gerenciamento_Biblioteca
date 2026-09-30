@echo off
REM Compila e executa o Gerenciador de Biblioteca (Windows)
chcp 65001 >nul
cd /d "%~dp0"
if not exist out mkdir out
dir /s /b src\*.java > fontes.txt
javac -encoding UTF-8 -d out @fontes.txt
del fontes.txt
java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out biblioteca.aplicacao.Main
pause
