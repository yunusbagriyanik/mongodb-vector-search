package com.yunusbagriyanik.service;

import com.yunusbagriyanik.model.Movie;
import com.yunusbagriyanik.repository.MovieRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final OpenAIService openAIService;

    public MovieService(MovieRepository movieRepository, OpenAIService openAIService) {
        this.movieRepository = movieRepository;
        this.openAIService = openAIService;
    }

    public Mono<List<Movie>> getMoviesSemanticSearch(String plotDesc) {
        return openAIService.createEmbedding(plotDesc)
                .flatMapMany(movieRepository::findMoviesByVector)
                .collectList();
    }
}
