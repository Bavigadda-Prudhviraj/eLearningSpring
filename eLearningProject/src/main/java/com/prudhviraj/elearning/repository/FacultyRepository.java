package com.prudhviraj.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prudhviraj.elearning.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, String>{

}
