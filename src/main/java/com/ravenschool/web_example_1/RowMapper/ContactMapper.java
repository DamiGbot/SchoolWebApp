package com.ravenschool.web_example_1.RowMapper;

import com.ravenschool.web_example_1.Model.Contact;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setContactId(rs.getInt("contact_id"));
        contact.setName(rs.getString("name"));
        contact.setMobileNum(rs.getString("mobile_num"));
        contact.setEmail(rs.getString("email"));
        contact.setSubject(rs.getString("subject"));
        contact.setMessage(rs.getString("message"));
        contact.setStatus(rs.getString("status"));
        contact.setCreated_by(rs.getString("created_by"));
        contact.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
        contact.setUpdated_by(rs.getString("updated_by"));

        if (rs.getTimestamp("updated_at") != null)
            contact.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());

        return contact;
    }
}
