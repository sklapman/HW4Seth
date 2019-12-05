package com.example.hw4;

public class birds {
    public String birdName;
    public String Zip;
    public String personSearching;
    public Integer importance;

    public birds(String submitBirdName, String submitZip, String submitPerson, Integer submitImportance) {
    }

    public birds(String birdName, String Zip, String personSearching) {
        this.birdName = birdName;
        this.personSearching = personSearching;
        this.Zip = Zip;
        this.importance = 0;

    }
}
