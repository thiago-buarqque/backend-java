package com.evry.analytics.service;

import com.evry.analytics.model.entity.Visitor;

import java.util.Optional;

public interface VisitorService {
    public Visitor addVisitor(Visitor visitor);

    public Optional<Visitor> getVisitorById(String id);
}
