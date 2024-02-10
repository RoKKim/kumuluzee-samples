# KumuluzEE GraphQL Federation sample

> Use Federation with a GraphQL microservice.


This Federation tutorial showcases the utilization of Apollo Federation for extending your GraphQL microservices
architecture. Before starting this tutorial, please make sure, that you have finished
[KumuluzEE JPA and CDI with REST](https://github.com/kumuluz/kumuluzee-samples/tree/master/jpa) tutorial.

This guide serves as a simple introduction to using Apollo Federation with KumuluzEE. For a more detailed and advanced
example, please
visit [apollo-federation-subgraph-compatibility](https://github.com/apollographql/apollo-federation-subgraph-compatibility/tree/main/implementations/kumuluzee-graphql).

## Requirements

In order to run this example you will need the following:

1. Java 21 (or newer), you can use any implementation:
    * If you have installed Java, you can check the version by typing the following in a command line:

        ```
        java -version
        ```

2. Maven 3.2.1 (or newer):
    * If you have installed Maven, you can check the version by typing the following in a command line:

        ```
        mvn -version
        ```
3. Git:
    * If you have installed Git, you can check the version by typing the following in a command line:

        ```
        git --version
        ```

## Prerequisites

In order to run this sample you will have to setup the following local PostgreSQL databases:

- Prices:
    - __database host__: localhost:5432
    - __database name__: prices
    - __user__: postgres
    - __password__: postgres
- Products:
    - __database host__: localhost:5433
    - __database name__: products
    - __user__: postgres
    - __password__: postgres

The required tables will be created automatically upon running the sample.
You can run databases with docker:

```bash
docker run -d --name pg-graphql-price -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=prices -p 5432:5432 postgres:latest
docker run -d --name pg-graphql-product -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=products -p 5433:5432 postgres:latest
```

## Usage

This example uses Maven to build and run the microservices individually. Each module corresponds to a distinct
microservice that will be run on its own port.

1. Build all the modules of the sample using maven:

    ```bash
    $ cd kumuluzee-graphql-federation
    $ mvn clean package
    ```

2. Run the samples:

   To run each microservice, follow the instructions for either the Uber-jar or Exploded approach. Ensure you're in the
   respective module directory before running the commands.

* Uber-jar:

    ```bash
    $ java -jar target/${project.build.finalName}.jar
    ```

  in Windows environemnt use the command
    ```batch
    java -jar target/${project.build.finalName}.jar
    ```

* Exploded:

    ```bash
    $ java -cp target/classes:target/dependency/* com.kumuluz.ee.EeApplication
    ```

  in Windows environment use the command
    ```batch
    java -cp target/classes;target/dependency/* com.kumuluz.ee.EeApplication
    ```

Each application/service can be accessed on the following URLs:

- **Price Service**:
    - GraphQL endpoint - http://localhost:8080/graphql
    - GraphiQL endpoint - http://localhost:8080/graphiql
- **Product Service**:
    - GraphQL endpoint - http://localhost:8081/graphql
    - GraphiQL endpoint - http://localhost:8081/graphiql
- **Review Service**:
    - GraphQL endpoint - http://localhost:8082/graphql
    - GraphiQL endpoint - http://localhost:8082/graphiql

To shut down the example simply stop the processes in the foreground.

### Connecting multiple Federated subgraphs

To design a unified GraphQL API from multiple federated subgraphs, you can use solutions like **Apollo Router** or
**Apollo Gateway**. These tools help in orchestrating multiple GraphQL services into a single data graph, enabling
seamless query operations across different domains or services.

- **Apollo Router** is a high-performance graph router optimized for running in production environments. It efficiently
  routes queries to the appropriate subgraphs, enabling smooth operation of your federated graph.
- **Apollo Gateway** serves as an intermediary that merges various GraphQL schemas from your federated services into a
  unified schema. It abstracts the complexity of querying multiple services, making it easier for clients to consume
  your APIs.

You can see an example of how to run an Apollo Gateway within the `kumuluzee-graphql-federation/gateway` folder. This
step is crucial to tie all the microservices together into a unified GraphQL API. To ensure the gateway sample operates
correctly, follow these steps:

1. **Ensure all microservices are running**: Before attempting to start the gateway, make sure all the previously
   mentioned microservices (price, product, review) are up and running. The gateway needs to connect to these services
   to function properly.

2. **Navigate to the Gateway Directory**: change your directory to the gateway folder within your project:
    ```bash
    cd kumuluzee-graphql-federation/gateway
    ```

3. **Install Dependencies**: run the following command to install the necessary Node.js dependencies:
    ```bash
    npm i
    ```

4. **Start the Gateway**: once the dependencies are installed, start the gateway with:
    ```bash
    npm start
    ```

The gateway is configured to connect to your microservices as specified in the [index.ts](gateway/index.ts) file, with
the following setup:

```typescript
const gateway = new ApolloGateway({
    supergraphSdl: new IntrospectAndCompose({
        subgraphs: [
            { name: "price", url: "http://localhost:8080/graphql" },
            { name: "product", url: "http://localhost:8081/graphql" },
            { name: "review", url: "http://localhost:8082/graphql" }
        ],
    }),
});
```

Once the gateway is started, it will be available at http://localhost:4000/.

### Executing queries

You can now explore and execute queries against your schema using Apollo Studio Sandbox, available
at http://localhost:4000/.

You can now try executing queries. You can start with one of these:

```graphql
mutation AddNewProduct {
    addNewProduct(product: {
        id: "1",
        name: "Bio jabolčni sok",
        description: "100% naravni jabolčni sok iz ekološke pridelave"
    }) {
        id
        name
        description
    }
    addNewProductPrice(productPrice: {
        id: "1",
        price: 3.5
    }) {
        id
        price
    }
}

query GetProduct {
    product(productId: "1") {
        id
        name
        description
        price
        reviews {
            text
            starRating
        }
    }
}
```
