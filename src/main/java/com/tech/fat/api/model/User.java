package com.tech.fat.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 1, max = 20)
    private String userName;

    @NotNull
    @Size(min = 1, max = 80)
    private String password;

    @NotNull
    @Size(min = 1, max = 20)
    private String roles;

    @NotNull
    private boolean active;
}
