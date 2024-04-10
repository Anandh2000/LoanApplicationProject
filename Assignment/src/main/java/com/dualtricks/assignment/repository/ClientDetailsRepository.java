package com.dualtricks.assignment.repository;

import com.dualtricks.assignment.entity.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Long> {

}
