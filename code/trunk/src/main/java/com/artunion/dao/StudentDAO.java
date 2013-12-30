/**
 * 
 */
package com.artunion.dao;

import org.springframework.stereotype.Repository;

import com.artunion.entity.StudentEntity;

/**
 * @author LiZhao
 *
 */
@Repository("studentDAO")
public class StudentDAO extends UserDAO<StudentEntity> {
    
}
