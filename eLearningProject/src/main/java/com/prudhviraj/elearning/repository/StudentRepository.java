package com.prudhviraj.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prudhviraj.elearning.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{

}
