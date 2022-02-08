package com.softserve.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "posts")
public class Post{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @ManyToMany
    @JoinTable(name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    private String createdOn;

    @ManyToOne
    private User author;

    @NotBlank(message = "Field body cannot be empty")
    private String text;

    @NotBlank(message = "Field title cannot be empty")
    private String title;

    @NotBlank(message = "Field preview cannot be empty")
    private String previewAttachment;

    private String updatedOn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
