package com.arobs.internship.musify.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "encrypted_password")
    private String encryptedPassword;
    private String country;
    private String role;
    private String status;

    @OneToMany(mappedBy = "ownerUser")
    Set<Playlist> ownedPlaylists = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "users_playlists",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "playlist_id") })
    private Set<Playlist> subscribedToPlaylists = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Set<Playlist> getSubscribedToPlaylists() {
        return subscribedToPlaylists;
    }

    public void setSubscribedToPlaylists(Set<Playlist> playlists) {
        this.subscribedToPlaylists = playlists;
    }

    public void subscribeToPlaylist(Playlist playlist) {
        subscribedToPlaylists.add(playlist);
        playlist.getSubscribedUsers().add(this);
    }

    public void unsubscribeFromPlaylist(Playlist playlist) {
        subscribedToPlaylists.remove(playlist);
        playlist.getSubscribedUsers().remove(this);
    }

    public void addOwnedPlaylist(Playlist playlist) {
        ownedPlaylists.add(playlist);
        playlist.setOwnerUser(this);
    }

    public void removeOwnedPlaylist(Playlist playlist) {
        ownedPlaylists.remove(playlist);
        playlist.setOwnerUser(null);
    }
}
