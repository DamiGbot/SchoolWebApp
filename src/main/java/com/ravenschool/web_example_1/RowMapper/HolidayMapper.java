package com.ravenschool.web_example_1.RowMapper;

import com.ravenschool.web_example_1.Model.Holiday;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HolidayMapper implements RowMapper<Holiday> {

    @Override
    public Holiday mapRow(ResultSet rs, int rowNum) throws SQLException {
        Holiday holiday = new Holiday();
        holiday.setDay(rs.getString("day"));
        holiday.setReason(rs.getString("reason"));
        holiday.setType(Holiday.Type.valueOf(rs.getString("type")));
        holiday.setCreated_by(rs.getString("created_by"));
        holiday.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
        holiday.setUpdated_by(rs.getString("updated_by"));

        if (rs.getTimestamp("updated_at") != null)
            holiday.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());

        return holiday;
    }
}
