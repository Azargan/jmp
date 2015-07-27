package com.mentoring.spring.mvc;

import com.mentoring.spring.mvc.init.MoviesFiller;
import com.mentoring.spring.mvc.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by azargan on 21.07.15.
 */
@SpringBootApplication
public class Application {

    @Autowired
    private MoviesFiller moviesFiller;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("*******************************************");
    }

}
