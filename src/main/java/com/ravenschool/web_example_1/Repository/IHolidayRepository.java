package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.Holiday;

import java.util.List;

public interface IHolidayRepository {
    List<Holiday> GetAllHolidays();
}
