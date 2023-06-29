package com.evry.analytics.DTO;

import com.evry.analytics.model.annotation.JSONField;
import com.evry.analytics.model.annotation.JSONSerializable;
import com.evry.analytics.model.entity.Event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@Getter
@JSONSerializable
@Setter
public class EventDTO {

    public EventDTO() {}

    public EventDTO(Event event) {
        browserName = event.getBrowserName();
        canonicalUrl = event.getCanonicalUrl();
        createDate = event.getCreateDate();
        dateTime = event.getDateTime();
        deviceType = event.getDeviceType();
        id = event.getId().toString();
        metadata = event.getMetadata();
        pageTitle = event.getPageTitle();
        properties = event.getProperties();
        referrer = event.getReferrer();
        sessionId = event.getSessionId();
        timezoneOffset = event.getTimezoneOffset();
        type = event.getType();
        visitorId = event.getVisitorId();
    }

    @JSONField private String browserName;

    @JSONField @NotNull private String canonicalUrl;

    @JSONField private LocalDateTime createDate;

    @JSONField private LocalDateTime dateTime;

    @JSONField private String deviceType;

    @JSONField private String id;

    @JSONField
    @NotEmpty(message = "Metadata must be provided.")
    private String metadata;

    @JSONField private String pageTitle;

    @JSONField private String properties;

    @JSONField private String referrer;

    @JSONField @NotNull private String sessionId;

    @JSONField @NotNull private String timezoneOffset;

    @JSONField
    @NotEmpty(message = "Event type must be provided.")
    private String type;

    @JSONField @NotNull private String visitorId;
}
