package com.Springboot.SpringCrudOperation.Controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.Springboot.SpringCrudOperation.Model.ErrorDetails;
import com.Springboot.SpringCrudOperation.Model.Student;
import com.Springboot.SpringCrudOperation.Model.StudentNotFoundException;
import com.Springboot.SpringCrudOperation.Service.StudentService;

@RestController
@ControllerAdvice
@RequestMapping("/students")
public class StudentController extends ResponseEntityExceptionHandler {

	@Autowired
	StudentService studentservice;

	@GetMapping
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> list = studentservice.getStudent();
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {
		Student entity = studentservice.getStudentById(id);
		return new ResponseEntity<Student>(entity, HttpStatus.OK);
	}

//	@GetMapping
//    public List<String> getLaptopNames() {
//        return studentservice.getLaptops();
//    }
	@PostMapping
//	@RequestMapping(method =
//	RequestMethod.POST)
	public ResponseEntity<Student> createOrUpdateStudent(@RequestBody Student student) {
		Student update = studentservice.createOrUpdateStudent(student);
		return new ResponseEntity<Student>(update, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable int id) {
		Student entity = studentservice.updateStudent(student, id);
		return new ResponseEntity<Student>(entity, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteStudent(@PathVariable("id") int id) {
		studentservice.deleteStudent(id);
		return HttpStatus.OK;
	}

	@ExceptionHandler(StudentNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(StudentNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

}
