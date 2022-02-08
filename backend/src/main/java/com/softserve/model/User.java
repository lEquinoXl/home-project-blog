package com.softserve.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @NotBlank(message = "Field name cannot be empty")
    private String name;

    @NotBlank(message = "Field FirstName cannot be empty")
    private String firstName;

    @NotBlank(message = "Field LastName cannot be empty")
    private String lastName;

    @NotBlank(message = "Field email cannot be empty")
    private String email;

    @NotBlank(message = "Field password cannot be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$")
    private String password;

//    @OneToOne
    private String role = new Role("blogger").getName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + this.id +
                ", name='" + this.name + ", firstName='" + this.firstName + ", lastName='" + this.lastName + ", role='" + this.role + '}';
    }
}
