package com.softserve.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "tags")
public class Tag{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
