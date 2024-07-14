package com.team.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.team.entity.Employee;
import java.util.Optional;


@Repository
public interface TeamRepository extends JpaRepository<Employee,Long>{
    Optional<Employee> findByEmailID(String emailID);
}
