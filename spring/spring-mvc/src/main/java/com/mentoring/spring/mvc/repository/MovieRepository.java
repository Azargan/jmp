package com.mentoring.spring.mvc.repository;

import com.mentoring.spring.mvc.entity.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by azargan on 26.07.15.
 */
public interface MovieRepository extends CrudRepository<Movie, Long> {
    public List<Movie> findByTitle(final String title);
}
