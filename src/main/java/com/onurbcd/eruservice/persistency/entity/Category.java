package com.onurbcd.eruservice.persistency.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter
@Setter
public class Category extends Prime {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", updatable = false)
    private Category parent;

    @NotNull
    @Min(1)
    @Column(name = "level", nullable = false, updatable = false)
    private Short level;

    @NotNull
    @Column(name = "last_branch", nullable = false)
    private Boolean lastBranch;

    @Size(max = 250)
    @Column(name = "description", length = 250)
    private String description;

    public Category(UUID id) {
        setId(id);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
