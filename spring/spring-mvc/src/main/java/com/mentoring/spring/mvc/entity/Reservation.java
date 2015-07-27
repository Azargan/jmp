package com.mentoring.spring.mvc.entity;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by azargan on 26.07.15.
 */
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String serialId;
    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false, nullable = false)
    private Movie movie;
    private int place;
    private BigDecimal price;
    @ManyToOne()
    private Customer customer;

    public Reservation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (id != that.id) return false;
        if (place != that.place) return false;
        if (serialId != null ? !serialId.equals(that.serialId) : that.serialId != null) return false;
        if (movie != null ? !movie.equals(that.movie) : that.movie != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return !(customer != null ? !customer.equals(that.customer) : that.customer != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (serialId != null ? serialId.hashCode() : 0);
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        result = 31 * result + place;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", movie='" + movie + '\'' +
                ", customer=" + customer.toString() +
                '}';
    }
}
