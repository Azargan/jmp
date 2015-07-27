package com.mentoring.spring.mvc.repository;

import com.mentoring.spring.mvc.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by azargan on 26.07.15.
 */
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    public Reservation findBySerialId(final String serialId);
}
