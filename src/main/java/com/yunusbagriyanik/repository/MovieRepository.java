package com.yunusbagriyanik.repository;

import com.yunusbagriyanik.model.Movie;
import reactor.core.publisher.Flux;

import java.util.List;

public interface MovieRepository {
    Flux<Movie> findMoviesByVector(List<Double> embedding);
}
