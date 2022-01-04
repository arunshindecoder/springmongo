package com.arun.sprmon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.arun.sprmon.entity.Department;
import com.arun.sprmon.entity.Student;
import com.arun.sprmon.entity.StudentPageData;
import com.arun.sprmon.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studRepo;

	public Student saveStudent(Student student) {
		return studRepo.save(student);
	}

	public Student updateStudent(Student student) {
		return studRepo.save(student);
	}

	public Student getByStudentId(String id) {
		Optional<Student> o = studRepo.findById(id);
		if (o.isPresent()) {
			return o.get();
		} else {
			return null;
		}
	}

	public String deleteStudentById(String id) {
		Student stud = getByStudentId(id);
		if (stud != null) {
			studRepo.deleteById(id);
			return "Succesfully Deleted : " + id;
		} else {
			return "Could not find Student with Id : " + id + " to be deleted";
		}
	}

	public List<Student> getAllStudents() {
		return studRepo.findAll();
	}

	public List<Student> getStudentByName(String name) {
		return studRepo.findByName(name);
	}

	public List<Student> getStudentByNameAndEmail(String name, String email) {
		return studRepo.findByNameAndEmail(name, email);
	}

	public List<Student> getStudentByNameOrEmail(String name, String email) {
		return studRepo.findByNameOrEmail(name, email);
	}

	public StudentPageData getAllStudentsWithPagination(int pageNo, int noOfRecords) {
		Pageable pageable = PageRequest.of(pageNo - 1, noOfRecords);
		Page<Student> page = studRepo.findAll(pageable);
		StudentPageData studPageData = new StudentPageData();
		studPageData.setTotalPages(page.getTotalPages());
		studPageData.setNoOfRecords(page.getNumberOfElements());
		studPageData.setStudents(page.getContent());
		return studPageData;
	}

	public StudentPageData getStudentsWithSortedFieldName(int pageNo, int noOfRecords, String fieldName) {
		Sort sort = Sort.by(Sort.Direction.ASC, fieldName);
		Pageable pageable = PageRequest.of(pageNo - 1, noOfRecords, sort);
		Page<Student> page = studRepo.findAll(pageable);
		StudentPageData studPageData = new StudentPageData();
		studPageData.setTotalPages(page.getTotalPages());
		studPageData.setNoOfRecords(page.getNumberOfElements());
		studPageData.setStudents(page.getContent());
		return studPageData;
	}

	public StudentPageData getStudentsByDeptNameAndSortedFieldName(int pageNo, int noOfRecords, String fieldName,
			String deptName) {
		Sort sort = Sort.by(Sort.Direction.ASC, fieldName);
		Pageable pageable = PageRequest.of(pageNo - 1, noOfRecords, sort);
		Department dept = new Department();
		dept.setDepartmentName(deptName);
		Student stud = new Student();
		stud.setDepartment(dept);
		Example<Student> ex = Example.of(stud, ExampleMatcher.matching());
		// Example.of
		Page<Student> page = studRepo.findAll(ex, pageable);

		StudentPageData studPageData = new StudentPageData();
		studPageData.setTotalPages(page.getTotalPages());
		studPageData.setNoOfRecords(page.getNumberOfElements());
		studPageData.setStudents(page.getContent());
		return studPageData;
	}

	public StudentPageData getStudentsByDeptName(int pageNo, int noOfRecords, String deptName) {

		Pageable pageable = PageRequest.of(pageNo - 1, noOfRecords);

		Page<Student> page = studRepo.findByDepartmentDepartmentName(deptName, pageable);

		StudentPageData studPageData = new StudentPageData();
		studPageData.setTotalPages(page.getTotalPages());
		studPageData.setNoOfRecords(page.getNumberOfElements());
		studPageData.setStudents(page.getContent());
		return studPageData;
	}

	public StudentPageData getStudentsBySubjectName(int pageNo, int noOfRecords, String subjectName) {

		Pageable pageable = PageRequest.of(pageNo - 1, noOfRecords);
		Page<Student> page = studRepo.findBySubjectsSubjectName(subjectName, pageable);

		StudentPageData studPageData = new StudentPageData();
		studPageData.setTotalPages(page.getTotalPages());
		studPageData.setNoOfRecords(page.getNumberOfElements());
		studPageData.setStudents(page.getContent());
		return studPageData;
	}
	
	public StudentPageData getStudentsByLikeEmail(int pageNo, int noOfRecords, String email) {

		Pageable pageable = PageRequest.of(pageNo - 1, noOfRecords);
		Page<Student> page = studRepo.findByEmailIsLike(email, pageable);

		StudentPageData studPageData = new StudentPageData();
		studPageData.setTotalPages(page.getTotalPages());
		studPageData.setNoOfRecords(page.getNumberOfElements());
		studPageData.setStudents(page.getContent());
		return studPageData;
	}
	
	public StudentPageData getStudentsStartsWithName(int pageNo, int noOfRecords, String name) {

		Pageable pageable = PageRequest.of(pageNo - 1, noOfRecords);
		Page<Student> page = studRepo.findByNameStartsWith(name, pageable);

		StudentPageData studPageData = new StudentPageData();
		studPageData.setTotalPages(page.getTotalPages());
		studPageData.setNoOfRecords(page.getNumberOfElements());
		studPageData.setStudents(page.getContent());
		return studPageData;
	}
	
	
/*	public List<Student> getStudentsByDeptAndSubject(String deptname, String subjectName) {

		List<Student> studList = studRepo.getStudentsByDeptAndSubject(deptname,subjectName);

		return studList;
	}
*/	
	public StudentPageData getStudentsByDeptAndSubject(int pageNo, int noOfRecords, 
			String deptname, String subjectName) {

		Pageable pageable = PageRequest.of(pageNo - 1, noOfRecords);
		Page<Student> page = studRepo.getStudentsByDeptAndSubject(deptname,subjectName, pageable);

		StudentPageData studPageData = new StudentPageData();
		studPageData.setTotalPages(page.getTotalPages());
		studPageData.setNoOfRecords(page.getNumberOfElements());
		studPageData.setStudents(page.getContent());
		return studPageData;
	}

	

}
