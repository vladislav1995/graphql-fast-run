package com.miniq.example.provider;

import com.apollographql.federation.graphqljava.Federation;
import com.miniq.example.resolver.MutationResolver;
import com.miniq.example.resolver.QueryResolver;
import graphql.GraphQL;
import graphql.scalars.datetime.DateScalar;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.util.Collections;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * @author Uladik
 */
@Component
public class SchemaProvider {

    private GraphQL graphQL;

    @Autowired
    private QueryResolver queryResolver;

    @Autowired
    private MutationResolver mutationResolver;

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                    .dataFetcher("getResponse",
                            queryResolver.getResponse()))
                .build();
    }

    @PostConstruct
    private void initGraphQLScheme() throws IOException {
        Resource sdl = new ClassPathResource("schema.graphqls");
        GraphQLSchema schema = Federation.transform(sdl.getFile(), buildWiring())
                .fetchEntities(dataFetchingEnvironment -> Collections.emptyList())
                .resolveEntityType(typeResolutionEnvironment -> null)
                .build();
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    public GraphQL getGraphql() {
        return graphQL;
    }

}
