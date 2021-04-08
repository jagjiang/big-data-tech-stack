package com.mintlolly.service.impl;

import com.mintlolly.dao.StudentDao;
import com.mintlolly.model.entity.Student;
import com.mintlolly.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by on jiangbo 2020/9/11 10:34
 * <p>
 * Description:
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public void save(Student student) {
      studentDao.save(student);
    }

    @Override
    public List<Student> getStudentList() {
        List students = new ArrayList<Student>();
        studentDao.findAll().forEach(student -> students.add(student));
        return students;
    }
}
