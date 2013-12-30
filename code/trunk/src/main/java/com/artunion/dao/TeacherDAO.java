/**
 * 
 */
package com.artunion.dao;

import org.springframework.stereotype.Repository;

import com.artunion.entity.TeacherEntity;

/**
 * @author LiZhao
 *
 */
@Repository("teacherDAO")
public class TeacherDAO extends UserDAO<TeacherEntity> {
    
}
