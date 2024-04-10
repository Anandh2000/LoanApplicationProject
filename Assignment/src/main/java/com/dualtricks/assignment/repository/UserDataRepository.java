package com.dualtricks.assignment.repository;

import com.dualtricks.assignment.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
    UserData findByUsername(String username);
}
