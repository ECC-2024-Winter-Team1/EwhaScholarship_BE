package com.ecc.ewhascholarship.service;

import com.ecc.ewhascholarship.repository.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriteriaService {

    @Autowired
    private CriteriaRepository criteriaRepository;

}
