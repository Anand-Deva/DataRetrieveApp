package com.example.dataretrieveapp;

import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class BioInfoCollection extends RealmObject {

    @PrimaryKey
    @Required
    private ObjectId _id = new ObjectId();

    @Required
    private String _partitionKey;

    @Required
    private Integer age;
    @Required
    private String firstName;
    @Required
    private String lastName;
    @Required
    private String sex;
}