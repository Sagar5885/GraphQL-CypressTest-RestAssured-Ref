package com.example.graphql.springbootgraphqljavatest.resource;

import com.example.graphql.springbootgraphqljavatest.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/books")
@RestController
public class BookResource {

    @Autowired
    GraphQLService graphQLService;

    @PostMapping
    public ResponseEntity<ExecutionResult> getAllBooks(@RequestBody String query){
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        //execute.getData();

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
