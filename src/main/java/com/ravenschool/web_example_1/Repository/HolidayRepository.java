package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.Holiday;
import com.ravenschool.web_example_1.RowMapper.HolidayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HolidayRepository implements IHolidayRepository {
    private final JdbcTemplate _jdbcTemplate;

    @Autowired
    public HolidayRepository(JdbcTemplate jdbcTemplate) {
        this._jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Holiday> GetAllHolidays() {
        String SQL_GET_ALL = "select * from holidays";
//        var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
        return _jdbcTemplate.query(SQL_GET_ALL, new HolidayMapper());
    }
}
