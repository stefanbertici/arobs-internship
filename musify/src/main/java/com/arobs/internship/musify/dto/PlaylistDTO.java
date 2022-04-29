package com.arobs.internship.musify.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
    private Integer id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Type cannot be blank")
    private String type;
    private Date createdDate;
    private Date updatedDate;
    private Integer ownerUserId;

    @JsonIgnore
    public boolean isPrivateOrPublic() {
        return type.equals("private") || type.equals("public");
    }
}
