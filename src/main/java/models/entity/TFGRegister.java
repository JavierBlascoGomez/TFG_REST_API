package models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "tfg_register", schema = "public")
@Setter
@Getter
@ToString
public class TFGRegister {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "register_date")
    private Date register_date;

    @Basic
    @Column(name = "serum_creatinine")
    private Double serum_creatinine;

    @Basic
    @Column(name = "result")
    private Double result;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
