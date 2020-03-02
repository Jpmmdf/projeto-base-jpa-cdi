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

Mapear as entidades restantes contidadas no arquivo 

./src/main/resources/db.sql

Alterar o método que retorna umas lista para receber filtros como parâmetro 

~~~java
public Optional<List<T>> getList() {
    CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<T> query = builder.createQuery(classe);
    query.from(classe);
    return Optional.of(getEntityManager().createQuery(query).getResultList());
}
~~~
 
