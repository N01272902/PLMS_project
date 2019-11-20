package com.ceng319.plmsproject;

public class DataStructure {
    private String name;
    private String availability;
    private String message;
    private String timestamp;

public DataStructure(){

}
    public DataStructure(String name, String availability, String message, String timestamp)
    {
        this.name = name;
        this.availability = availability;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }

    public String getAvailability() { return availability; }

    public void setAvailability(String availability){ this.availability = availability; }

    public String getMessage(){ return message; }

    public void setMessage(String message) {this.message = message; }

    public String getTimestamp() { return timestamp; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
