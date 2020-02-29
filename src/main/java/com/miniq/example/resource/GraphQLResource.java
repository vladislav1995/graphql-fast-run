package com.miniq.example.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniq.example.graphql.request.GraphQLRequestBody;
import com.miniq.example.provider.SchemaProvider;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Uladik
 */
@Path("/graphql/fast-run")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class GraphQLResource {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private SchemaProvider provider;

    @POST
    @Path("/")
    public Response graphql(GraphQLRequestBody graphQLRequestBody) throws JsonProcessingException {
        return Response.ok().entity(mapper.writeValueAsString(executeGraphQLQuery(graphQLRequestBody))).build();
    }

    @GET
    @Path("/")
    public Response graphqlGet(@BeanParam GraphQLRequestBody graphQLRequestBody) throws JsonProcessingException {
        return Response.ok().entity(mapper.writeValueAsString(executeGraphQLQuery(graphQLRequestBody))).build();
    }

    private ExecutionResult executeGraphQLQuery(GraphQLRequestBody body) {
        return provider.getGraphql().execute(convertRequestBody(body));
    }

    public void setProvider(SchemaProvider provider) {
        this.provider = provider;
    }

    private ExecutionInput convertRequestBody(GraphQLRequestBody body) {
        return new ExecutionInput.Builder()
                .operationName(body.getOperationName())
                .query(body.getQuery())
                .variables(body.getVariables())
                .build();
    }
}
