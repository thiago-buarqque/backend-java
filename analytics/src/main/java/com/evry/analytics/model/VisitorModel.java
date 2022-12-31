package com.evry.analytics.model;

import com.evry.analytics.entity.Visitor;
import com.evry.analytics.repository.VisitorRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class VisitorModel {

    public VisitorModel(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public Visitor addVisitor(Visitor visitor) {
        if(visitor.getCreateDate() == null) {
            visitor.setCreateDate(LocalDateTime.now());
        }

        return visitorRepository.save(visitor);
    }

    public Optional<Visitor> getVisitorById(String id) {
        return visitorRepository.findById(UUID.fromString(id));
    }

    private final VisitorRepository visitorRepository;

}
