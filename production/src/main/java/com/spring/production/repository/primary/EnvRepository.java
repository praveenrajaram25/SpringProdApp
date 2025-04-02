package com.spring.production.repository.primary;

import com.spring.production.model.primary.Env;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnvRepository extends JpaRepository<Env,String> {



}
