package com.spring.production.repository.secondary;

import com.spring.production.model.secondary.EmpRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RatingRepository extends JpaRepository<EmpRating,Number> {
}
