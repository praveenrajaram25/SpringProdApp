package com.spring.production.repository.secondary;

import com.spring.production.model.secondary.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source,String> {


}
