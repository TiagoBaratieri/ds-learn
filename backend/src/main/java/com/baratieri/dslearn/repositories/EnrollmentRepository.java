package com.baratieri.dslearn.repositories;

import com.baratieri.dslearn.entities.Enrollment;
import com.baratieri.dslearn.pk.EnrollmentPK;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentPK> {
}
