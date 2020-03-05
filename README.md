# Treinamento Stefanini

Nessa primeira fase do Treinamento serão abordados os assuntos relacionadosa a:

Slides disponiveis em [Slides](https://github.com/Jpmmdf/projeto-base-jpa-cdi/blob/master/src/main/resources/Treinamento-2.pptx)
* Ferramentas para Desenvolvimento
* GIT
   * Clonar Projeto
   * Executar os comandos Pull/Push/Add/Commit
* JAVA
    * Objetos e Variaveis
    * Mapeamento de Entidades
    * JPA
    * CDI
    * Bean Validation

## Requisitos

* [GIT](https://git-scm.com) -  Ferramenta para versionar o código
* [JDK](https://jdk.java.net/13/)  - Kit de Desenvolvimento Java
* [MAVEN](https://maven.apache.org/install.html) - Gerenciador de dependências
* [POSTGRESQL](https://www.postgresql.org/download/) - Sistema de Banco de Dados
* [ECLIPSE](https://www.eclipse.org/downloads/) - Ferramenta para o Desenvolvimento 
* [PGADMIN](https://www.pgadmin.org/download/) - (Opcional) Ferramenta para o Postgres
* [SOURCETREE](https://www.sourcetreeapp.com) - (Opcional) Ferramenta para o GIT

## Uso

### Importando o projeto no Eclipse

Após a configuração do ambiente, clonar esse projeto e importa-lo como Maven Project no Eclipse



## Desafio para a aula 2

* Sugestoes do Colega: Francisco

Mapear as entidades restantes contidadas no arquivo 

./src/main/resources/db.sql

Criar um Metodo na Dao de Pessoa para que seja possivel retorna uma lista de Pessoa com base em filtros

~~~java
public List<Pessoa> getList(FiltroPessoa filtro) {
    //Retornar uma Lista de Pessoa com base no filtro
    return ;
}
~~~
 
