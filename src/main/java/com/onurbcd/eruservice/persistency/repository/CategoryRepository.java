package com.onurbcd.eruservice.persistency.repository;

import com.onurbcd.eruservice.dto.category.CategoryDto;
import com.onurbcd.eruservice.dto.ItemDto;
import com.onurbcd.eruservice.persistency.entity.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    @Modifying
    @Query("update Category c set c.active = :active where c.id = :id")
    int updateActive(UUID id, Boolean active);

    @Override
    @Query("select new com.onurbcd.eruservice.dto.ItemDto(c.id, c.name)" +
            " from Category c" +
            " where :id is null" +
            " or c.id != :id" +
            " order by c.name")
    List<ItemDto> getItems(UUID id);
}
