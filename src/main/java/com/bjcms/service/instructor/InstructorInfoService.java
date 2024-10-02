package com.bjcms.service.instructor;

import com.bjcms.entity.instructor.InstructorInfo;

import java.util.List;
import java.util.Optional;

public interface InstructorInfoService {
  
        InstructorInfo addInstructorInfo(InstructorInfo instructorInfo);

        public void deleteInstructorInfo(Integer id);

        public InstructorInfo updateInstructorInfo(InstructorInfo instructor);

        public InstructorInfo findInstructorInfo(Integer id);

        public List<InstructorInfo> getAllInstructorInfo();
        public Optional<InstructorInfo> findById(Integer instructorId);
}


