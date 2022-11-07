package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.category.CategoryDto;
import com.onurbcd.eruservice.persistency.entity.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends EruRepository<Category, CategoryDto> {

    @Modifying
    @Query("update Category c" +
            " set c.lastBranch = :lastBranch" +
            " where c.id = :id")
    void updateLastBranch(@Param("lastBranch") Boolean lastBranch, @Param("id") UUID id);

    @Query("select count(*)" +
            " from Category c" +
            " inner join c.parent p" +
            " where p.id = :parentId")
    long countChildren(@Param("parentId") UUID parentId);
}
