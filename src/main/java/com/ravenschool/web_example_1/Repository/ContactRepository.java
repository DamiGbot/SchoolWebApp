package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ContactRepository implements IContactRepository {

    JdbcTemplate jdbcTemplate;

//    private final String SQL_FIND_PERSON = "select * from people where id = ?";
//    private final String SQL_DELETE_PERSON = "delete from people where id = ?";
//    private final String SQL_UPDATE_PERSON = "update people set first_name = ?, last_name = ?, age  = ? where id = ?";
//    private final String SQL_GET_ALL = "select * from people";
//    private final String SQL_INSERT_PERSON = "insert into people(id, first_name, last_name, age) values(?,?,?,?)";
    private final String SQL_INSERT_NEW_MESSAGE = "insert into contact_msg(name, mobile_num, email, subject, message, " +
        "status, created_by, created_at) values(?,?,?,?,?,?,?,?)";

    @Autowired
    public ContactRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean saveContactMsg(Contact contact) {
        return jdbcTemplate.update(SQL_INSERT_NEW_MESSAGE, contact.getName(), contact.getMobileNum(), contact.getEmail(),
                contact.getSubject(), contact.getMessage(), contact.getStatus(), contact.getCreated_by(), contact.getCreated_at()) > 0;
    }
}
