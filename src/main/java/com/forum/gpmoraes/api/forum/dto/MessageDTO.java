package com.forum.gpmoraes.api.forum.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.forum.gpmoraes.api.forum.model.Message;
import com.forum.gpmoraes.api.forum.model.Post;
import com.forum.gpmoraes.api.forum.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy@HH:mm")
    private Date date;

    private String text;

    private Message parentMessage;

    private User user;

    private Post post;


}
