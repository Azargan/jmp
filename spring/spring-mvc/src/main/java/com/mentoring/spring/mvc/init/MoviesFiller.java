package com.mentoring.spring.mvc.init;

import com.mentoring.spring.mvc.entity.Movie;
import com.mentoring.spring.mvc.repository.MovieRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * Created by azargan on 26.07.15.
 */
@Configuration
public class MoviesFiller {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private MovieRepository repository;

    @Bean(name = "man-ant")
    public Movie getManAntMovie() {
        final Movie movie = new Movie("Человек-Муравей", new DateTime(2015, 7, 27, 21, 00).toDate());
        final Resource img = context.getResource("classpath:img/men-ant.jpg");
//        final DataBufferByte buffer = (DataBufferByte) ImageIO.read(img.getInputStream()).getRaster().getDataBuffer();
//        movie.setPicture(buffer.getData());
        return movie;
    }

    @Bean(name = "pixels")
    public Movie getPixelsMovie() {
        final Movie movie = new Movie("Пиксели", new DateTime(2015, 7, 27, 19, 00).toDate());
        final Resource img = context.getResource("classpath:img/pixels.jpg");
//        final DataBufferByte buffer = (DataBufferByte) ImageIO.read(img.getInputStream()).getRaster().getDataBuffer();
//        movie.setPicture(buffer.getData());
        return movie;
    }
}
