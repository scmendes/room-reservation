# Reserva de salas

Aplicação que permite agendar salas.
Estão disponíveis as operaçoes de Criar, Listar, Atualizar e Remover.

### Overview da arquitetura
- Java 8 e Spring boot no backEnd
- Maven
- Database H2 em memória
- FrontEnd com HTML5 e Java Script puro

### Frameworks
- Spring boot
- Spring data JPA
- Spring boot data rest
- Maven

### Execução via linha de comando

- Pré-requisito: Java 8 instalado na estação de trabalho
- Pré-requisito: build efetuada: maven clean install
Ir até a pasta target e executar o comando abaixo:
```sh
$ java -jar room-reservation-silvio-0.1.0.jar
```

### Execução via maven em linha de comando
- Pré-requisito: Maven instalado na estação de trabalho
- No diretório raiz, execute o seguinte comando para efetuar a compilação, construção e fazer o deploy da a aplicação:
```sh
$ mvn spring-boot:install
$ mvn spring-boot:run
```

### Execução via Eclipse plugins
```sh
$ Run >> maven clean package install
$ Run >> java application (from the Application.java)
```

### Uso
Na barra de endereços do navegador digite: `http://localhost:8080` para ingressar na aplicação.
- Observação: A aplicação é inicializada com 3 reservas de sala criadas durante a inicialização do sistema.
- Observação: A aplicação está construída com o banco de dados H2 em memória. Assim  reiniciando a aplicação os dados serão resetados.


