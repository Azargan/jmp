package com.mentoring.spring.mvc.controller;

import com.mentoring.spring.mvc.entity.Movie;
import com.mentoring.spring.mvc.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by azargan on 26.07.15.
 */
@Controller
public class MovieController {

    @Autowired
    private MovieRepository repository;
    @Autowired
    @Qualifier("man-ant")
    private Movie manAntMovie;
    @Autowired
    @Qualifier("pixels")
    private Movie pixelsMovie;

    @RequestMapping("/movie")
    public String getMovies(final Model model) {
        final Movie ant = repository.save(manAntMovie);
        final Movie pixels = repository.save(pixelsMovie);

        final Iterable<Movie> movies = repository.findAll();
        model.addAttribute("movies", movies);

        return "movies";
    }

    @RequestMapping("/movie/{id}/")
    public String getMovie(final Model model, @PathVariable("id") String id) {
        final Movie movie = repository.findOne(Long.valueOf(id));
        if (movie == null) {
            return getMovies(model);
        }

        model.addAttribute("movie", movie);

        return "movie";
    }

    @RequestMapping(value = "/movie/{id}/reservation", method = RequestMethod.GET)
    public String getReservationMenu(final Model model, @PathVariable("id") String id) {
        final Movie movie = repository.findOne(Long.valueOf(id));
        if (movie == null) {
            return getMovies(model);
        }

        model.addAttribute("movie", movie);

        return "reservation.html";
    }

    @RequestMapping(value = "/movie/{id}/reservation", method = RequestMethod.POST)
    public String addReservation(final Model model, @PathVariable("id") String id) {


        return getMovies(model);
    }
}
