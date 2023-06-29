package com.evry.analytics.DTO;

import com.evry.analytics.model.annotation.JSONField;
import com.evry.analytics.model.annotation.JSONSerializable;
import com.evry.analytics.model.entity.Visitor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@Getter
@JSONSerializable
@Setter
public class VisitorDTO {

    public VisitorDTO() {}

    public VisitorDTO(Visitor visitor) {
        if (visitor != null) {
            createDate = visitor.getCreateDate();
            id = visitor.getId();
            userId = visitor.getUserId();
        }
    }

    @JSONField private LocalDateTime createDate;

    @JSONField
    @NotNull(message = "Visitor id must be provided.")
    private String id;

    @JSONField private String userId;
}
