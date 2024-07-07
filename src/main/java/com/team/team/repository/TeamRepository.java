package com.team.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.team.entity.Employee;

@Repository
public interface TeamRepository extends JpaRepository<Employee,Long>{
    
}
