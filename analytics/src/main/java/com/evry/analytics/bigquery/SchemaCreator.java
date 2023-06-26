package com.evry.analytics.bigquery;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryException;
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

// This class is just for learning purposes. I'll not keep updating it, since
// it costs money to use BigQuery.
@Component
public class SchemaCreator {

    @Autowired
    public SchemaCreator() {
        RemoteBigQueryHelper bigqueryHelper = RemoteBigQueryHelper.create();

        this.bigQuery = bigqueryHelper.getOptions().getService();

//        this.createSchema();
//        this.createTables();
    }

    void createSchema() {
        String datasetId = "workspace2";

        DatasetInfo dataset =
                DatasetInfo.newBuilder(datasetId)
                        .setDescription(
                                "This is dataset crated using BQ API.")
                        .setFriendlyName("dataset_friendly_name")
                        .build();

        System.out.printf("Creating schema '%s'.", datasetId);

        try {
            bigQuery.create(dataset);
        } catch (BigQueryException bigQueryException) {
            System.out.printf("Cannot create schema '%s'.", datasetId);
        }
    }

    private void createTables() {
        Schema schema =
                Schema.of(
                        Field.of("stringField", StandardSQLTypeName.STRING),
                        Field.of("booleanField", StandardSQLTypeName.BOOL));

        TableDefinition tableDefinition = StandardTableDefinition.of(schema);

        String schemaName = "workspace2";

        String tableName = "table1";

        TableInfo tableInfo = TableInfo.newBuilder(
                TableId.of(schemaName, tableName), tableDefinition
        ).build();

        try {
            bigQuery.create(tableInfo);
        } catch (BigQueryException bigQueryException) {
            System.out.printf("Cannot create table '%s' for schema '%s'",
                              tableName, schemaName);
        }
    }

    private final BigQuery bigQuery;
}
