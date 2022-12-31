package com.evry.analytics.restController;

import com.evry.analytics.DTO.VisitorDTO;
import com.evry.analytics.entity.Visitor;
import com.evry.analytics.model.VisitorModel;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.validation.Valid;

@RequestMapping("/visitor")
@RestController
public class VisitorRestController extends BaseRestController {

    public VisitorRestController(
            ObjectMapper objectMapper, VisitorModel visitorModel) {

        this.objectMapper = objectMapper;
        this.visitorModel = visitorModel;
    }

    @PostMapping
    public VisitorDTO addVisitor(@Valid @RequestBody VisitorDTO visitorDTO) {
        return objectMapper.convertValue(
                    visitorModel.addVisitor(
                        objectMapper.convertValue(visitorDTO, Visitor.class)
                    ), VisitorDTO.class);
    }

    @GetMapping
    public ResponseEntity<VisitorDTO> getVisitorById(String id) {
        Optional<Visitor> visitorOptional = visitorModel.getVisitorById(id);

        return visitorOptional.map(
                visitor -> new ResponseEntity<>(new VisitorDTO(visitor),
                HttpStatus.OK)).orElseGet(
                        () -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }

    private final ObjectMapper objectMapper;
    private final VisitorModel visitorModel;

}
