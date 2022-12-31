package com.evry.analytics.repository.impl;

import com.evry.analytics.repository.CustomEventRepository;

import org.jooq.DSLContext;
import org.jooq.DeleteUsingStep;
import org.jooq.Record;
import org.jooq.impl.DSL;

public class EventRepositoryImpl implements CustomEventRepository {

    public EventRepositoryImpl(DSLContext dslContext) {
        _dslContext = dslContext;
    }

    @Override
    public void deleteByUserId(String userId) {

        // I could use the JPA repository to do that, but I wanted to
        // include JOOQ DSLContext somewhere
        DeleteUsingStep<Record> deleteUsingStep = _dslContext.deleteFrom(
                DSL.table("Event"));

        deleteUsingStep.where(
            DSL.field("userId", String.class).eq(userId)
        ).executeAsync();
    }

    private final DSLContext _dslContext;

}