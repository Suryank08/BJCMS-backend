package com.bjcms.dao.course;

import com.bjcms.entity.course.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseTypeDao extends JpaRepository<CourseType,Integer> {
    @Query("Select type FROM CourseType type WHERE type.courseTypeName =:name")
    public Optional<CourseType> findByCourseTypeName(@Param("name")String name);
}
