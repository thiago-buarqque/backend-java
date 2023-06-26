package com.evry.analytics.repository.impl;

import com.evry.analytics.repository.CustomEventRepository;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;

@AllArgsConstructor
public class EventRepositoryImpl implements CustomEventRepository {

    @Override
    public void deleteByUserId(String userId) {
        SelectConditionStep<Record1<String>> selectConditionStep =
                dslContext
                        .select(DSL.field("id", String.class))
                        .from(DSL.table("Visitor"))
                        .where(DSL.field("userId", String.class).eq(userId));

        dslContext
                .deleteFrom(DSL.table("Event"))
                .where(DSL.field("visitorId", String.class).in(selectConditionStep))
                .execute();
    }

    private final DSLContext dslContext;
}
