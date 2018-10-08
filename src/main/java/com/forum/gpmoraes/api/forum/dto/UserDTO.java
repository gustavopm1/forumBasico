package com.forum.gpmoraes.api.forum.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;
    private String email;
    private String user;
    private String password;

    @JsonFormat(pattern = "dd-MM-yyyy", locale = "pt_BR")
    @Temporal(TemporalType.DATE)
    private Date birthDate;



}
