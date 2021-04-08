package com.mintlolly.service;

import com.mintlolly.model.entity.Student;

import java.util.List;

/**
 * Create by on jiangbo 2020/9/11 10:33
 * <p>
 * Description:
 */
public interface StudentService {
    void save(Student student);

    List<Student> getStudentList();
}
