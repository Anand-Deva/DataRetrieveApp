package com.example.dataretrieveapp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import org.bson.types.ObjectId;

public class BioInfo extends RealmObject {

    @PrimaryKey
    @Required
    private ObjectId _id = new ObjectId();

    @Required
    private String _partitionKey;

    private Integer age;
    private String firstName;
    private String lastName;
    private String sex;

    // Standard getters & setters
    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }
    public String get_partitionKey() { return _partitionKey; }
    public void set_partitionKey(String _partitionKey) { this._partitionKey = _partitionKey; }
    public Integer getAge() { return age; }
    public void setAge(Integer Age) { this.age = Age; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }
}