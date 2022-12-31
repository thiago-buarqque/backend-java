package com.evry.analytics.DTO;

import com.evry.analytics.annotation.JSONField;
import com.evry.analytics.annotation.JSONSerializable;
import com.evry.analytics.entity.Visitor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@JSONSerializable
@Setter
public class VisitorDTO {

    public VisitorDTO() {

    }

    public VisitorDTO(Visitor visitor) {
        if(visitor != null) {
            createDate = visitor.getCreateDate();
            id = visitor.getId();
            userId = visitor.getUserId();
        }
    }

    @JSONField
    private LocalDateTime createDate;

    @JSONField
    @NotNull(message = "Visitor id must be provided.")
    private String id;

    @JSONField
    private String userId;

}
