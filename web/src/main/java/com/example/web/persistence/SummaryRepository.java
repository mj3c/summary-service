package com.example.web.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SummaryRepository extends MongoRepository<PostDocument, String> {
    @Query(value = "{}", fields = "{ _id: 0, summary: 1 }")
    List<PostDocument> getAllSummaries();
}
