package com.cms.bjm_cms.dao.instructor;

import com.cms.bjm_cms.entity.instructor.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QualificationDao extends JpaRepository<Qualification, Integer> {
    @Query("SELECT q FROM Qualification q WHERE q.qualificationName LIKE :name")
   public Qualification findByQualificationName(@Param("name") String qualificationName);
}

