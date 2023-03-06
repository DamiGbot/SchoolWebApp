package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Integer> {

}
