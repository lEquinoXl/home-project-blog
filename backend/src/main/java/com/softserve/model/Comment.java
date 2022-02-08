package com.softserve.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "comments")
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @ManyToOne
    private User author;

    @ManyToOne
    private Post post;

    @NotBlank(message = "Field text cannot be empty")
    @Column
    private String text;

    @Column
    private String createdOn;

    @Column
    private String updatedOn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
