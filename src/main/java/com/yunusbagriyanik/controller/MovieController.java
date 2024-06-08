package com.yunusbagriyanik.controller;

import com.yunusbagriyanik.model.Movie;
import com.yunusbagriyanik.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/semantic-search")
    public Mono<List<Movie>> performSemanticSearch(@RequestParam String plotDesc) {
        return movieService.getMoviesSemanticSearch(plotDesc);
    }
}
