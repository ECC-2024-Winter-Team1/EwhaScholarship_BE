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
                    cb.isNull(gpaJoin.get("id")),  // ✅ GPA 조건이 없는 장학금 포함
                    cb.lessThanOrEqualTo(gpaJoin.get("gpa"), gpa) // ✅ GPA 조건을 만족하는 장학금만 포함
            );
        };
    }

    public static Specification<Scholarship> hasIncomeLevel(Integer incomeLevel) {
        return (root, query, cb) -> {
            if (incomeLevel == null) return null;

            Join<Scholarship, ScholarshipIncomeLevel> incomeJoin = root.join("scholarshipIncomeLevel", JoinType.LEFT);
            return cb.or(
                    cb.isNull(incomeJoin.get("id")),  // ✅ 소득분위 조건이 없는 장학금 포함
                    cb.greaterThanOrEqualTo(incomeJoin.get("incomeLevel"), incomeLevel) // ✅ 소득분위 조건을 만족하는 장학금만 포함
            );
        };
    }

    public static Specification<Scholarship> hasDepartment(String department) {
        return (root, query, cb) -> {
            if (department == null) return null;

            Join<Scholarship, ScholarshipDepartment> departmentJoin = root.join("scholarshipDepartment", JoinType.LEFT);
            return cb.or(
                    cb.isNull(departmentJoin.get("id")),  // ✅ 단과대 조건이 없는 장학금 포함
                    cb.equal(departmentJoin.get("department"), department) // ✅ 단과대 조건을 만족하는 장학금만 포함
            );
        };
    }

    public static Specification<Scholarship> hasYear(Integer year) {
        return (root, query, cb) -> {
            if (year == null) return null;

            Join<Scholarship, ScholarshipYear> yearJoin = root.join("scholarshipYear", JoinType.LEFT);
            return cb.or(
                    cb.isNull(yearJoin.get("id")),  // ✅ 학년 조건이 없는 장학금 포함
                    cb.equal(yearJoin.get("year"), year) // ✅ 학년 조건을 만족하는 장학금만 포함
            );
        };
    }

}
