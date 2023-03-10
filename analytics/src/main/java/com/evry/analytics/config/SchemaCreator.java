package com.evry.analytics.config;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.DatasetInfo;
import com.google.cloud.bigquery.Field;
import com.google.cloud.bigquery.Schema;
import com.google.cloud.bigquery.StandardSQLTypeName;
import com.google.cloud.bigquery.StandardTableDefinition;
import com.google.cloud.bigquery.TableDefinition;
import com.google.cloud.bigquery.TableId;
import com.google.cloud.bigquery.TableInfo;
import com.google.cloud.bigquery.testing.RemoteBigQueryHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchemaCreator {

    @Autowired
    public SchemaCreator() {
        this.bigQuery = BigQueryOptions.getDefaultInstance().getService();
        this.createTestSchema();
    }

    void createTestSchema() {
        System.out.println("Creating schema");
        RemoteBigQueryHelper bigqueryHelper = RemoteBigQueryHelper.create();
        BigQuery bigquery = bigqueryHelper.getOptions().getService();

        String datasetId = "workspace2";

        DatasetInfo dataset =
                DatasetInfo.newBuilder(datasetId)
                        .setDescription(
                                "Esta é uma tabela criada através do " + "client BQ para java")
                        .setFriendlyName("dataset_friendly_name")
                        .build();

        Schema schema =
                Schema.of(
                        Field.of("stringField", StandardSQLTypeName.STRING),
                        Field.of("booleanField", StandardSQLTypeName.BOOL));

        TableDefinition tableDefinition = StandardTableDefinition.of(schema);
        TableInfo tableInfo =
                TableInfo.newBuilder(TableId.of(datasetId, "table1"), tableDefinition).build();

        bigquery.create(dataset);
        bigQuery.create(tableInfo);
    }

    private final BigQuery bigQuery;
}
