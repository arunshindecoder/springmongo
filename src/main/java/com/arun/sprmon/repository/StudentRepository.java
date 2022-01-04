package com.arun.sprmon.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.arun.sprmon.entity.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
	
	public List<Student> findByName(String name);
	
	public List<Student> findByNameAndEmail(String name, String email);

	public List<Student> findByNameOrEmail(String name, String email);
	
	public Page<Student> findByDepartmentDepartmentName(String deptName,Pageable page);
	
	public Page<Student> findBySubjectsSubjectName(String subjectName,Pageable page);
	
	public Page<Student> findByEmailIsLike(String email,Pageable page);
	
	public Page<Student> findByNameStartsWith(String name,Pageable page);
	
	/*@Query(value = "{\"department.department_name\" : \"?0\", \"subjects.subject_name\" :\"?1\"}")
	//@Query("{$or : [{\"department.department_name\" : \"?0\"}, {\"subjects.subject_name\" :\"?1\"}]}")
	public List<Student> getStudentsByDeptAndSubject(String deptName, String subjectName);
	*/
	@Query(value = "{\"department.department_name\" : \"?0\", \"subjects.subject_name\" :\"?1\"}")
	public Page<Student> getStudentsByDeptAndSubject(String deptName, String subjectName,Pageable page);
	
}
