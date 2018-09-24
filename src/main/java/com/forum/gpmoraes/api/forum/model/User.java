package com.forum.gpmoraes.api.forum.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    private String name;

    @JsonIgnore
    private String email;

    private String user;

    @JsonIgnore
    private String password;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy", locale = "pt_BR")
    @Getter(AccessLevel.NONE)
    private Date birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Message> messages = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    public User(Integer id, String name, String email, String user, String password, Date birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.user = user;
        this.password = password;
        this.birthDate = birthDate;
    }
}
