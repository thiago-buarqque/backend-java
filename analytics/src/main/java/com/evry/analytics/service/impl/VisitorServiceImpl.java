package com.evry.analytics.service.impl;

import com.evry.analytics.model.entity.Visitor;
import com.evry.analytics.repository.VisitorRepository;

import com.evry.analytics.service.VisitorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VisitorServiceImpl implements VisitorService {

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public Visitor addVisitor(Visitor visitor) {
        visitor.setCreateDate(LocalDateTime.now());

        return visitorRepository.save(visitor);
    }

    public Optional<Visitor> getVisitorById(String id) {
        return visitorRepository.findById(id);
    }

    private final VisitorRepository visitorRepository;
}
