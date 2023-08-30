package com.prudhviraj.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prudhviraj.elearning.entity.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

}
