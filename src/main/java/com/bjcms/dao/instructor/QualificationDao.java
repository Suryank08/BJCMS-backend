package com.bjcms.dao.instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bjcms.entity.instructor.Qualification;

@Repository
public interface QualificationDao extends JpaRepository<Qualification, Integer> {
    @Query("SELECT q FROM Qualification q WHERE q.qualificationName LIKE :name")
   public Qualification findByQualificationName(@Param("name") String qualificationName);
}

