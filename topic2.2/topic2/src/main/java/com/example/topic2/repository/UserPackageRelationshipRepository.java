package com.example.topic2.repository;

import com.example.topic2.model.UserPackageRelationship;
import com.example.topic2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPackageRelationshipRepository extends JpaRepository<UserPackageRelationship, Long> {
    List<UserPackageRelationship> findByUserId(User user);
}
