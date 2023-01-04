package com.example.demo.repository;

import com.example.demo.domain.HealthInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface HealthRepository extends JpaRepository<HealthInfo,Long> {


}
