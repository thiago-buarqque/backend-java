package com.evry.analytics.repository.impl;

import com.evry.analytics.repository.CustomEventRepository;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

public class EventRepositoryImpl implements CustomEventRepository {

    public EventRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void deleteByUserId(String userId) {

        // I could use the JPA repository to do that, but I wanted to
        // include JOOQ somewhere :D
        dslContext
                .deleteFrom(DSL.table("Event"))
                .where(DSL.field("userId", String.class).eq(userId))
                .execute();
    }

    private final DSLContext dslContext;
}
