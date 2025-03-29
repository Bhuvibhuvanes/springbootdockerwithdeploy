package com.Springboot.SpringCrudOperation.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Springboot.SpringCrudOperation.Model.Student;
import com.Springboot.SpringCrudOperation.Model.StudentNotFoundException;
import com.Springboot.SpringCrudOperation.Repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentrepository;
	
	public List<Student> getStudent(){
		List<Student> students=studentrepository.findAll();
		if(students.size() >0) {
			return students;
		}
		else {
			return new ArrayList<Student>();
		}
	}
	public Student getStudentById(int id) {
		Optional<Student> student = studentrepository.findById(id);
		if(student.isPresent()) {
			return student.get();
		}else {
			throw new StudentNotFoundException("No student record exist for given id");
		}
	}
	public Student updateStudent(Student student,int id) {
		Optional<Student> students = studentrepository.findById(id);
		if(students.isPresent()) {
			Student newStudent=students.get();
			newStudent.setName(student.getName());
			newStudent=studentrepository.save(newStudent);
			return newStudent;
		}
		else {
			throw new StudentNotFoundException("No student record exist for given id");

		}
	}
	public Student createOrUpdateStudent(Student student) {
		Optional<Student> students = studentrepository.findById(student.getId());
		if(students.isPresent()) {
			Student newStudent = students.get();
			newStudent.setId(student.getId());
			newStudent.setName(student.getName());
			
			newStudent = studentrepository.save(newStudent);
			return newStudent;
		}
		else {
			student =studentrepository.save(student);
			return student;
		}
	}
public void deleteStudent(int id) {
	Optional<Student> student = studentrepository.findById(id);
	if(student.isPresent()) {
		studentrepository.deleteById(id);
	}
	else {
		throw new StudentNotFoundException("No student record exist for given id");
	}
}
//public List<String> getLaptops() {
//    List<Student> laptops = studentrepository.findByProductName("senthil");
//    List<String> laptopNames = laptops.stream()
//            .map(Student::getName)
//            .collect(Collectors.toList());
//    return laptopNames;
//}
	
}
