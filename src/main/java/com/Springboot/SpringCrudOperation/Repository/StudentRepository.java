package com.Springboot.SpringCrudOperation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Springboot.SpringCrudOperation.Model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{
//	 List<Student> findByProductName(String name);
	

}
