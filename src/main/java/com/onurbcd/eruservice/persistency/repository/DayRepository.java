package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.day.CreateMonthDto;
import com.onurbcd.eruservice.persistency.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DayRepository extends JpaRepository<Day, Integer> {

    @Query("select count(*)" +
            " from Day d" +
            " where d.calendarYear = :#{#dto.calendarYear}" +
            " and d.calendarMonth = :#{#dto.calendarMonth}")
    long numberOfDaysInMonth(@Param("dto") CreateMonthDto dto);

    @Query("select d.calendarMonth" +
            " from Day d" +
            " where d.calendarYear = :calendarYear" +
            " group by d.calendarMonth" +
            " order by d.calendarMonth")
    Set<Short> getMonthsInYear(@Param("calendarYear") Short calendarYear);
}
