# Sistema de Agendamento para Barbearia

Projeto em Java desenvolvido com foco em lógica de programação, Programação Orientada a Objetos (POO), manipulação de arquivos e organização de código.

## Funcionalidades

- Agendar horário
- Listar agendamentos
- Cancelar agendamento
- Buscar cliente por nome
- Editar agendamento
- Validar horários no formato `HH:MM`
- Permitir apenas horários com minutos `00` ou `30`
- Impedir horários duplicados
- Salvar os dados em arquivo `.txt`
- Carregar os dados automaticamente ao iniciar o sistema

## Tecnologias usadas 

- Java
- Programação Orientada a Objetos (POO)
- ArrayList
- Scanner
- FileWriter
- PrintWriter
- Leitura de arquivos com Scanner

## Estrutura do projeto

- `Main.java` → menu principal e fluxo do sistema
- `SistemaAgendamento.java` → regras do sistema
- `Cliente.java` → dados do cliente
- `Agendamento.java` → dados do agendamento

## Como executar

1. Compile os arquivos `.java`
2. Execute a classe `Main`
3. Use o menu no terminal

## Exemplos de horários válidos

- `08:00`
- `08:30`
- `14:00`
- `18:30`

## Exemplos de horários inválidos

- `8:00`
- `14:15`
- `99:99`
- `abcde`

## Melhorias futuras

- Interface gráfica
- Versão web com link para clientes
- Banco de dados
- Painel administrativo para o barbeiro
- Seleção de serviços
- Controle por data e não apenas por horário

## Autor

Leonardo Sironi Da Rosa