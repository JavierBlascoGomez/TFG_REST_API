package com.example.tfg_rest.models.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tip", schema = "public")
@Getter
@Setter
@ToString
public class Tip {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "tipText", unique = true, nullable = false)
    @NotEmpty(message = "No text for tip specified.")
    @Size(min = 10, message = "The text of the tip has been grater than 10 characters")
    private String tipText;

    @OneToMany(mappedBy = "tip")
    private List<Comment> comments;
}
