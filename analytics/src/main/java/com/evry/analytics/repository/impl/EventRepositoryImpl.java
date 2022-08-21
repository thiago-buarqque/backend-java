package com.evry.analytics.repository.impl;

import com.evry.analytics.repository.CustomEventRepository;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.DeleteUsingStep;
import org.jooq.Record;
import org.jooq.impl.DSL;

public class EventRepositoryImpl implements CustomEventRepository {

    public EventRepositoryImpl(DSLContext dslContext) {
        _dslContext = dslContext;
    }

    @Override
    public void deleteByUserId(Long userId) {

        // I could use the JPA repository to do that, but I wanted to
        // include JOOQ DSLContext somewhere
        DeleteUsingStep<Record> deleteUsingStep = _dslContext.deleteFrom(
                DSL.table("Event"));

        Condition condition = DSL.condition("userId=" + userId);

        deleteUsingStep.where(condition).executeAsync();
    }

    private DSLContext _dslContext;
}
