package com.mintlolly.dao;

import com.mintlolly.model.entity.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Create by on jiangbo 2020/9/11 10:20
 * <p>
 * Description:
 */
public interface StudentDao extends CrudRepository<Student,Long> {
//    public Student findBy

}
