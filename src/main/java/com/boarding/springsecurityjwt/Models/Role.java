package com.boarding.springsecurityjwt.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="roles")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Role {
    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    private Integer idrole;

    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<User> users = new HashSet<>();


    @Override
    public String toString() {
        return "Role{" +
                "idrole='" + idrole + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
