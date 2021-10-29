package com.example.octoberexambattleships.service;

import com.example.octoberexambattleships.domain.entity.Categories;
import com.example.octoberexambattleships.domain.entity.CategoryEntity;

public interface CategoryService {

    void initCategories();

    CategoryEntity findByCategory(Categories category);
}
