package com.example.octoberexambattleships.repo;

import com.example.octoberexambattleships.domain.entity.Categories;
import com.example.octoberexambattleships.domain.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByCategory(Categories category);

}
