package com.arobs.internship.musify.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bands")
public class Band extends Artist{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBand;
    @Column(name = "band_name")
    private String bandName;
    private String location;

    @OneToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToMany
    @JoinTable(name = "bands_persons",
            joinColumns = { @JoinColumn(name = "band_id") },
            inverseJoinColumns = { @JoinColumn(name = "person_id") })
    private final Set<Person> persons = new HashSet<>();

    public Band() {
    }

    public Band(String bandName, String location, String startDate, String endDate) {
        this.bandName = bandName;
        this.location = location;
        super.setStartDate(startDate);
        super.setEndDate(endDate);
    }

    public void addPerson(Person person) {
        this.persons.add(person);
        person.getBands().add(this);
    }

    public void removePerson(Person person) {
        this.persons.remove(person);
        person.getBands().remove(this);
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
