package com.arun.sprmon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arun.sprmon.entity.Student;
import com.arun.sprmon.entity.StudentPageData;
import com.arun.sprmon.service.StudentService;

@RestController
@RequestMapping(path = "/api/student")
public class StudentController {
	
	@Autowired
	StudentService studService;
	
	@PostMapping(path="/create")
	public Student createStudent(@RequestBody Student student) {
		return studService.saveStudent(student);
	}
	
	@GetMapping(path="/{id}")
	public Student getStudent(@PathVariable String id) {
		return studService.getByStudentId(id);
	}
	
	@GetMapping(path="/all")
	public List<Student> getAllStudents() {
		return studService.getAllStudents();
	}
	
	@PutMapping(path="/update")
	public Student updateStudent(@RequestBody Student student) {
		return studService.updateStudent(student);
	}
	
	@DeleteMapping(path="/delete/{id}")
	public String deleteStudent(@PathVariable String id) {
		return studService.deleteStudentById(id);
	}
	
	//this method is created by us in the mongoRepository.
	@GetMapping(path="/name/{name}")
	public List<Student> getStudentByName(@PathVariable String name) {
		return studService.getStudentByName(name);
	}

	//this method is created by us in the mongoRepository.
	@GetMapping(path="/nameANDemail/name/{name}/email/{email}")
	public List<Student> getStudentByNameAndEmail(@PathVariable String name, @PathVariable String email) {
		return studService.getStudentByNameAndEmail(name,email);
	}
	
	@GetMapping(path="/nameANDemail")
	public List<Student> getStudentByNameAndEmailWithQueryParam(@RequestParam(name = "studName") String name, 
			@RequestParam String email) {
		return studService.getStudentByNameAndEmail(name,email);
	}
	

	@GetMapping(path="/nameORemail/name/{name}/email/{email}")
	public List<Student> getStudentByNameOrEmail(@PathVariable String name,
			@PathVariable String email) {
		System.out.println("In getStudentByNameOrEmail");
		return studService.getStudentByNameOrEmail(name,email);
	}
	
	@GetMapping(path="/nameORemail")
	public List<Student> getStudentByNameOrEmailWithQueryParam(@RequestParam(name = "studName", required = false) String name, 
			@RequestParam(defaultValue = "nitin@yahoo.com") String email) {
		return studService.getStudentByNameOrEmail(name,email);
	}
	
	@GetMapping(path="/getStudents")
	public StudentPageData getAllStudentsWithPagination(@RequestParam(name = "page") int pageNo, 
			@RequestParam(defaultValue = "10") int pageSize) {
		return studService.getAllStudentsWithPagination(pageNo,pageSize);
	}
	
	@GetMapping(path="/getStudentsWithSortedNames")
	public StudentPageData getStudentsWithSortedFieldName(@RequestParam(name = "page", defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(name="sortedFieldName") String fieldName) {
		return studService.getStudentsWithSortedFieldName(pageNo,pageSize,fieldName);
	}
	
	@GetMapping(path="/getStudentsByDeptNameAndSortedNames")
	public StudentPageData getStudentsByDeptNameAndSortedFieldName(
			@RequestParam(name = "page", defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = "10") int pageSize, 
			@RequestParam(name="sortedFieldName") String fieldName,
			@RequestParam(name="deptName") String deptName) {
		return studService.getStudentsByDeptNameAndSortedFieldName(pageNo,pageSize,fieldName,deptName);
	}
	
	@GetMapping(path="/getStudentsByDeptName")
	public StudentPageData getStudentsByDeptName(
			@RequestParam(name = "page", defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = "10") int pageSize, 
			@RequestParam(name="deptName") String deptName) {
		return studService.getStudentsByDeptName(pageNo,pageSize,deptName);
	}
	
	@GetMapping(path="/getStudentsBySubjectName")
	public StudentPageData getStudentsBySubjectName(
			@RequestParam(name = "page", defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = "10") int pageSize, 
			@RequestParam String subjectName) {
		return studService.getStudentsBySubjectName(pageNo,pageSize,subjectName);
	}
	
	@GetMapping(path="/getStudentsByLikeEmail")
	public StudentPageData getStudentsByLikeEmail(
			@RequestParam(name = "page", defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = "10") int pageSize, 
			@RequestParam String email) {
		return studService.getStudentsByLikeEmail(pageNo,pageSize,email);
	}
	
	@GetMapping(path="/getStudentsStartsWithName")
	public StudentPageData getStudentsStartsWithName(
			@RequestParam(name = "page", defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = "10") int pageSize, 
			@RequestParam String name) {
		return studService.getStudentsStartsWithName(pageNo,pageSize,name);
	}
	
/*	@GetMapping(path="/studentsByDeptAndSubject/{deptName}/{subjectName}")
	public List<Student> getStudentsByDeptAndSubject( 
			@PathVariable String deptName,
			@PathVariable String subjectName) {
		System.out.println("getStudentsByDeptAndSubject " + deptName  +" " + subjectName);
		return studService.getStudentsByDeptAndSubject(deptName,subjectName);
	}
	*/
	
	@GetMapping(path="/studentsByDeptAndSubject/{deptname}/{subjectName}")
	public StudentPageData getStudentsByDeptAndSubject(
			@PathVariable String deptname,
			@PathVariable String subjectName,
			@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo, 
			@RequestParam(defaultValue = "10") int pageSize) {
		
		return studService.getStudentsByDeptAndSubject(pageNo,pageSize,deptname,subjectName);
	}
}
