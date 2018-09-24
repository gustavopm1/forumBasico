package com.forum.gpmoraes.api.forum.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy@HH:mm")
    private Date date;
    private String description;

    @OneToMany(mappedBy = "post")
    private List<Message> messages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Post(Integer postId, Date date, String description, User user) {
        this.postId = postId;
        this.date = date;
        this.description = description;
        this.user = user;
    }

}
