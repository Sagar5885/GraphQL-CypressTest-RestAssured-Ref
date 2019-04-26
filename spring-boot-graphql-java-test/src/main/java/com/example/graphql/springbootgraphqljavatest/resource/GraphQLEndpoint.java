package com.example.graphql.springbootgraphqljavatest.resource;

import com.coxautodev.graphql.tools.SchemaParser;
import javax.servlet.annotation.WebServlet;
import graphql.servlet.SimpleGraphQLServlet;


@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    public GraphQLEndpoint() {
        super(SchemaParser.newParser()
                .file("schema.graphqls") //parse the schema file created earlier
                .build()
                .makeExecutableSchema());
    }
}