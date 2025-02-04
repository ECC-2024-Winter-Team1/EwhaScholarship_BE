package com.ecc.ewhascholarship.repository;

import com.ecc.ewhascholarship.domain.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {

}
