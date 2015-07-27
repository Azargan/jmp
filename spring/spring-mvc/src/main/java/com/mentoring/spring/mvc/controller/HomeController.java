package com.mentoring.spring.mvc.controller;

import com.mentoring.spring.mvc.entity.Customer;
import com.mentoring.spring.mvc.entity.Movie;
import com.mentoring.spring.mvc.entity.Reservation;
import com.mentoring.spring.mvc.repository.CustomerRepository;
import com.mentoring.spring.mvc.repository.ReservationRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Created by azargan on 21.07.15.
 */
@Controller
public class HomeController {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping({"/", "/home"})
    public String home(final Model model) {
        return "index";
    }

    @RequestMapping("test_crud")
    public String testCrud(final Model model) {
        // save a couple of customers
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer by ID
        Customer customer = repository.findOne(1L);
        System.out.println("Customer found with findOne(1L):");
        System.out.println("--------------------------------");
        System.out.println(customer);
        System.out.println();

        // fetch customers by last name
        System.out.println("Customer found with findByLastName('Bauer'):");
        System.out.println("--------------------------------------------");
        repository.findByLastName("Bauer").forEach(System.out::println);

        final Reservation reservation = new Reservation();
        Customer me = repository.save(new Customer("Aliaksei", "Vihuro"));
        reservation.setCustomer(me);
        final Movie terminator = new Movie("Terminator", new DateTime(2015, 07, 27, 19, 00).toDate());
        reservation.setMovie(terminator);
        reservation.setPlace(24);
        reservation.setPrice(BigDecimal.valueOf(55000.0));
//        reservation.setBeggining(new DateTime(2015, 7, 27, 19, 0).toDate());
        reservation.setSerialId(UUID.randomUUID().toString());
        final Reservation savedReservation = reservationRepository.save(reservation);
        System.out.println("Saved reservation with id = " + savedReservation.getId());

        final Reservation reservationBySerialId = reservationRepository.findBySerialId(savedReservation.getSerialId());
        System.out.println("Found: " + reservationBySerialId.toString());

        return "greeting";
    }
}
