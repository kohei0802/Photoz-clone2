package com.foogle.kohei.photoz.clone2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Lob;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("PHOTOZ")
public class Photo {

    @Id
    private Integer id;

    @NotEmpty
    private String fileName;

    private String contentType;

    @JsonIgnore
    @Lob
    private byte[] data;


    public Photo(Integer id, String fileName)
    {
        this.id=id;
        this.fileName=fileName;
    }
}
