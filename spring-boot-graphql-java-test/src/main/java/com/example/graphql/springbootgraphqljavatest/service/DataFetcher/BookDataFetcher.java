package com.example.graphql.springbootgraphqljavatest.service.DataFetcher;

import com.example.graphql.springbootgraphqljavatest.model.Book;
import com.example.graphql.springbootgraphqljavatest.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookDataFetcher implements DataFetcher<Optional<Book>> {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Optional<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {

        String isn = dataFetchingEnvironment.getArgument("id");

        return bookRepository.findById(isn);
    }

//    @Override
//    public Optional<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {
//
//        //String isn = dataFetchingEnvironment.getArgument("id");
//
//        return bookRepository.findOne(dataFetchingEnvironment.getArgument("id"));
//    }
}
