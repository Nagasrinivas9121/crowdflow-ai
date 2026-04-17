package com.crowdflow.repository;

import com.crowdflow.model.CrowdZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrowdZoneRepository extends JpaRepository<CrowdZone, Long> {
}
