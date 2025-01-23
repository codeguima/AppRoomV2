# Aplicativo de Controle de Tarefas

Este é um aplicativo de controle de tarefas desenvolvido para gerenciar e organizar suas atividades diárias. O aplicativo utiliza o **Room** como banco de dados local para persistência de dados e **Jetpack Compose** para a construção da interface do usuário de maneira moderna e eficiente.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal para o desenvolvimento do aplicativo.
- **Jetpack Compose**: Biblioteca para construção da interface de usuário declarativa.
- **Room**: Biblioteca para persistência de dados no dispositivo, usando um banco de dados SQLite local.
- **ViewModel & LiveData**: Para gerenciamento de dados e interação com a UI de forma reativa.
- **Coroutines**: Para operações assíncronas e gerenciamento de threads.

## Funcionalidades

- **Adicionar Tarefas**: O usuário pode adicionar novas tarefas, com título e descrição.
- **Listar Tarefas**: As tarefas são exibidas em uma lista, permitindo ao usuário visualizar suas atividades.
- **Marcar Tarefas como Concluídas**: O usuário pode marcar tarefas como concluídas, alterando seu status.
- **Excluir Tarefas**: O usuário pode excluir tarefas da lista.
- **Armazenamento Local**: As tarefas são salvas localmente no dispositivo utilizando o Room, garantindo que os dados sejam persistentes.

## Como Executar o Projeto

### Pré-requisitos

- Android Studio instalado (com suporte a Kotlin e Jetpack Compose).
- Um dispositivo Android ou emulador configurado para executar o aplicativo.

### Passos para Execução

1. Clone o repositório:
    ```bash
    git clone https://github.com/codeguima/AppRoomV2.git
    ```

2. Abra o projeto no **Android Studio**.

3. Compile e execute o aplicativo no seu dispositivo ou emulador.

4. O aplicativo estará pronto para uso, permitindo adicionar, listar, concluir e excluir tarefas.


