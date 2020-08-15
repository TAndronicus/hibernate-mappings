package jb.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DateService {

    public /*@Pure*/ LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public /*@Pure*/ LocalDate printAndGetCurrentLocalDate() {
        var currentDate = LocalDate.now();
        System.out.println(currentDate);
        return currentDate;
    }

}
