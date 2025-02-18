package com.ecc.ewhascholarship.repository.specification;

import com.ecc.ewhascholarship.domain.*;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;

public class ScholarshipSpecification {

    public static Specification<Scholarship> hasSearchKeyword(String search) {
        return (root, query, cb) -> {
            if (search == null || search.isEmpty()) return null;
            return cb.like(cb.lower(root.get("name")), "%" + search.toLowerCase() + "%");
        };
    }

    public static Specification<Scholarship> hasGpa(Float gpa) {
        return (root, query, cb) -> {
            if (gpa == null) return null;

            Join<Scholarship, ScholarshipGpa> gpaJoin = root.join("scholarshipGpa", JoinType.LEFT);
            return cb.or(
                    cb.isNull(gpaJoin.get("id")),
                    cb.lessThanOrEqualTo(gpaJoin.get("gpa"), gpa)
            );
        };
    }

    public static Specification<Scholarship> hasIncomeLevel(Integer incomeLevel) {
        return (root, query, cb) -> {
            if (incomeLevel == null) return null;

            Join<Scholarship, ScholarshipIncomeLevel> incomeJoin = root.join("scholarshipIncomeLevel", JoinType.LEFT);
            return cb.or(
                    cb.isNull(incomeJoin.get("id")),
                    cb.greaterThanOrEqualTo(incomeJoin.get("incomeLevel"), incomeLevel)
            );
        };
    }

    public static Specification<Scholarship> hasDepartment(String department) {
        return (root, query, cb) -> {
            if (department == null) return null;

            Join<Scholarship, ScholarshipDepartment> departmentJoin = root.join("scholarshipDepartments", JoinType.LEFT);
            return cb.or(
                    cb.isNull(departmentJoin.get("id")),
                    cb.equal(departmentJoin.get("department"), department)
            );
        };
    }

    public static Specification<Scholarship> hasYear(Integer year) {
        return (root, query, cb) -> {
            if (year == null) return null;

            Join<Scholarship, ScholarshipYear> yearJoin = root.join("scholarshipYears", JoinType.LEFT);
            return cb.or(
                    cb.isNull(yearJoin.get("id")),
                    cb.equal(yearJoin.get("year"), year)
            );
        };
    }

}
