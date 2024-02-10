import { ApolloServer } from "@apollo/server";
import { startStandaloneServer } from "@apollo/server/standalone";
import { ApolloGateway, IntrospectAndCompose } from "@apollo/gateway";

const gateway = new ApolloGateway({
    supergraphSdl: new IntrospectAndCompose({
        subgraphs: [
            { name: "price", url: "http://localhost:8080/graphql" },
            { name: "product", url: "http://localhost:8081/graphql" },
            { name: "review", url: "http://localhost:8082/graphql" }
        ],
    }),
});

const server = new ApolloServer({
    gateway
});

const { url } = await startStandaloneServer(server);
console.log(`🚀 Server ready at ${url}`);
