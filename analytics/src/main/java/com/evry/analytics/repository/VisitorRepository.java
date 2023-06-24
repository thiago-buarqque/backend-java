package com.evry.analytics.repository;

import com.evry.analytics.entity.Visitor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface VisitorRepository extends JpaRepository<Visitor, String> {
    public void deleteByUserId(String userId);
}
