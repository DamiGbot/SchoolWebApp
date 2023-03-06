package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String role);
}
