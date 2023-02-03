package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.Holiday;
import org.springframework.data.repository.CrudRepository;

public interface IHolidayRepository extends CrudRepository<Holiday, String> {

}
