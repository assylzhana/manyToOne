package com.bitlab.manyToOne.repositories;

import com.bitlab.manyToOne.models.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<ApplicationRequest, Long> {
}