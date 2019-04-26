package com.example.graphql.springbootgraphqljavatest.repository;

import com.example.graphql.springbootgraphqljavatest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
