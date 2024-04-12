package com.codingbox.test.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    private static final Map<Long, Student> store = new HashMap<>();
    private static long sequence = 0L;

    public Student save(Student student) {
        student.setStudentId(++sequence);
        store.put(student.getStudentId(), student);
        return student;
    }

    public Student findById(Long studentID) {
        return store.get(studentID);
    }

    public List<Student> findAll(){
        return new ArrayList<Student>(store.values());
    }

    public void update(Long studentId, Student updateParam) {
        Student findStudent = findById(studentId);
        findStudent.setStudentName(updateParam.getStudentName());
        findStudent.setAge(updateParam.getAge());
        findStudent.setSubject(updateParam.getSubject());
        findStudent.setPhone(updateParam.getPhone());
        findStudent.setAddr(updateParam.getAddr());
    }

}
