package com.arobs.internship.musify.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistViewDTO {
    private Integer id;
    private String type;
    private Date createdDate;
    private Date updatedDate;
    private Integer ownerUserId;
    private List<SongViewDTO> songs;

    @JsonIgnore
    public boolean isPrivateOrPublic() {
        return type.equals("private") || type.equals("public");
    }
}
