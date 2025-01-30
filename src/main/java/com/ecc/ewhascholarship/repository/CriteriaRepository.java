package com.ecc.ewhascholarship.repository;

import com.ecc.ewhascholarship.domain.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, Long> {

}
