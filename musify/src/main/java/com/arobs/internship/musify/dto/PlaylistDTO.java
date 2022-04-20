package com.arobs.internship.musify.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;

public class PlaylistDTO {
    private Integer id;
    private String type;
    private Date createdDate;
    private Date updatedDate;
    private Integer ownerUserId;

    public PlaylistDTO() {
    }

    public PlaylistDTO(Integer id, String type, Date createdDate, Date updatedDate, Integer ownerUserId) {
        this.id = id;
        this.type = type;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.ownerUserId = ownerUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Integer ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    @JsonIgnore
    public boolean isPrivateOrPublic() {
        return type.equals("private") || type.equals("public");
    }
}
