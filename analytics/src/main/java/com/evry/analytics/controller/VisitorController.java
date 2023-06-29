package com.evry.analytics.controller;

import com.evry.analytics.DTO.VisitorDTO;
import com.evry.analytics.model.entity.Visitor;
import com.evry.analytics.service.impl.VisitorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.validation.Valid;

@RequestMapping("/visitor")
@AllArgsConstructor
@RestController
public class VisitorController extends BaseController {

    @PostMapping
    public VisitorDTO addVisitor(@Valid @RequestBody VisitorDTO visitorDTO) {
        return objectMapper.convertValue(
                visitorService.addVisitor(objectMapper.convertValue(visitorDTO, Visitor.class)),
                VisitorDTO.class);
    }

    @GetMapping("/{visitorId}")
    public ResponseEntity<VisitorDTO> getVisitorById(@PathVariable String visitorId) {
        Optional<Visitor> visitorOptional = visitorService.getVisitorById(visitorId);

        return visitorOptional
                .map(visitor -> new ResponseEntity<>(new VisitorDTO(visitor), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    private final ObjectMapper objectMapper;
    private final VisitorServiceImpl visitorService;
}
