package com.yunusbagriyanik.repository;

import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import com.yunusbagriyanik.model.Movie;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

import static com.mongodb.client.model.Aggregates.vectorSearch;
import static com.mongodb.client.model.search.SearchPath.fieldPath;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private final MongoDatabase mongoDatabase;

    public MovieRepositoryImpl(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    private MongoCollection<Movie> getMovieCollection() {
        return mongoDatabase.getCollection("embedded_movies", Movie.class);
    }

    @Override
    public Flux<Movie> findMoviesByVector(List<Double> embedding) {
        var indexName = "vector_index";
        var numCandidate = 100;
        var limit = 5;

        List<Bson> pipeline = List.of(
                vectorSearch(
                        fieldPath("plot_embedding"),
                        embedding,
                        indexName,
                        numCandidate,
                        limit
                )
        );
        return Flux.from(getMovieCollection().aggregate(pipeline, Movie.class));
    }
}
