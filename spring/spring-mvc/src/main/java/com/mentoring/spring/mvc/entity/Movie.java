package com.mentoring.spring.mvc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by azargan on 26.07.15.
 */
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginning;
    @OneToMany(mappedBy = "movie")
    private List<Reservation> reservations;
//    @Lob
//    private byte[] picture;

    protected Movie() {
    }

    public Movie(String title) {
        this.title = title;
    }

    public Movie(String title, Date beginning) {
        this.beginning = beginning;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBeginning() {
        return beginning;
    }

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

//    public byte[] getPicture() {
//        return picture;
//    }
//
//    public void setPicture(byte[] picture) {
//        this.picture = picture;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (!title.equals(movie.title)) return false;
        return !(beginning != null ? !beginning.equals(movie.beginning) : movie.beginning != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + (beginning != null ? beginning.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy hh:mm");
        return "Movie{" +
                "title='" + title + '\'' +
                ", beggining=" + format.format(beginning) +
                '}';
    }
}
