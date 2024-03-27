package models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "roles", schema = "public")
@Getter
@Setter
public class Role {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "rolesAssociated", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> usersAssociated;
}
