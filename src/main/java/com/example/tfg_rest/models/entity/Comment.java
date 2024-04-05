package com.example.tfg_rest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "comment", schema = "public")
@Getter
@Setter
@ToString
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "commentText", nullable = false)
    @NotEmpty(message = "No comment text specified.")
    @Size(min = 3, message = "The comment text must be grater than 3 characters.")
    private String commentText;

    @Basic
    @Column(name = "date", nullable = false)
    @NotEmpty(message = "No date specified.")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "tip_id")
    @JsonIgnore
    private Tip tip;
}
