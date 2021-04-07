package com.deni.gunawan.Sisteminformasiperpustakaan.model;

import com.deni.gunawan.Sisteminformasiperpustakaan.annotation.PasswordMatches;
import com.deni.gunawan.Sisteminformasiperpustakaan.annotation.ValidEmail;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@PasswordMatches
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "firstname", nullable = false, length = 20)
    @NotBlank(message = "{firstname.message}")
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 20)
    @NotBlank(message = "{lastname.message}")
    private String lastname;

    @Column(name = "username" , nullable = false, unique = true, length = 50)
    @NotBlank(message = "{username.message}")
    private String username;

    @Column(nullable = false, unique = true, length = 45)
    @NotBlank(message = "{email.message}")
    @ValidEmail
    private String email;


    @Column(nullable = false, length = 64)
    @NotBlank(message = "{password.message}")
    private String password;

    @Column(name = "matchingpassword", nullable = false, length = 64)
    private String matchingPassword;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            })
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    @JsonManagedReference
    private Set<Role> id_role = new HashSet<>();

    @Column(name = "verificationcode", length = 64)
    private String verificationCode;

    private boolean active;

    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }

    public boolean isActive() {
        return active;
    }

}
