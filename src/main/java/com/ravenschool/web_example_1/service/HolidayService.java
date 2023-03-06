package com.ravenschool.web_example_1.service;

import com.ravenschool.web_example_1.Model.Holiday;
import com.ravenschool.web_example_1.Repository.IHolidayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HolidayService {

    private final IHolidayRepository _holidayRepository;

    public Iterable<Holiday> getAllHolidays() {
        Iterable<Holiday> holidays = _holidayRepository.findAll();
        return holidays;
    }
}
