package com.ravenschool.web_example_1.service;

import com.ravenschool.web_example_1.Model.Holiday;
import com.ravenschool.web_example_1.Repository.IHolidayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {
    private final IHolidayRepository _holidayRepository;

    public HolidayService(IHolidayRepository holidayRepository) {
        this._holidayRepository = holidayRepository;
    }

    public List<Holiday> getAllHolidays() {
        List<Holiday> holidays = _holidayRepository.GetAllHolidays();
        return holidays;
    }
}
