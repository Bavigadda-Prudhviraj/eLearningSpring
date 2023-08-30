package com.prudhviraj.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prudhviraj.elearning.entity.Query;


@Repository
public interface QueryRepository extends JpaRepository<Query, Integer>{

	

}
