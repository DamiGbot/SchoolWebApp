package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.Contact;
import com.ravenschool.web_example_1.RowMapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContactRepository {

    private final JdbcTemplate _jdbcTemplate;

    @Autowired
    public ContactRepository(DataSource dataSource) {
        this._jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean saveContactMsg(Contact contact) {
        String SQL_INSERT_NEW_MESSAGE = "insert into contact_msg(name, mobile_num, email, subject, message, " +
                "status, created_by, created_at) values(?,?,?,?,?,?,?,?)";
        return _jdbcTemplate.update(SQL_INSERT_NEW_MESSAGE, contact.getName(), contact.getMobileNum(), contact.getEmail(),
                contact.getSubject(), contact.getMessage(), contact.getStatus(), contact.getCreated_by(), contact.getCreated_at()) > 0;
    }

    public List<Contact> findMsgsWithStatus(String status) {
        String SQL_FIND_MESSAGES_BY_STATUS = "select * from contact_msg where status = ?";
        return _jdbcTemplate.query(SQL_FIND_MESSAGES_BY_STATUS, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, status);
            }
        }, new ContactMapper());
    }

    public boolean updateMsgStatus(int contactId, String status, String name) {

        String SQL_CLOSE_MSG = "update contact_msg set status = ?, updated_by = ?, updated_at = ? where contact_id = ?";
        return _jdbcTemplate.update(SQL_CLOSE_MSG, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, status);
                ps.setString(2, name);
                ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                ps.setInt(4, contactId);
            }
        }) > 0;
    }
}
