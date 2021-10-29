package com.example.octoberexambattleships.service.impl;

import com.example.octoberexambattleships.domain.entity.Categories;
import com.example.octoberexambattleships.domain.entity.CategoryEntity;
import com.example.octoberexambattleships.repo.CategoryRepo;
import com.example.octoberexambattleships.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.example.octoberexambattleships.domain.entity.Categories.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepo.count() != 0) {
            return;
        }

        Arrays.stream(Categories.values())
                .forEach(categoryEnum -> {
                    CategoryEntity category = new CategoryEntity();
                    category.setCategory(categoryEnum);

                    if (categoryEnum.equals(BATTLE)) {
                        category.setDescription("Battle description...");
                    } else if (categoryEnum.equals(CARGO)) {
                        category.setDescription("Cargo description...");
                    } else if (categoryEnum.equals(PATROL)) {
                        category.setDescription("Patrol description...");
                    }

                    this.categoryRepo.save(category);
                });

    }

    @Override
    public CategoryEntity findByCategory(Categories category) {
        return this.categoryRepo.findByCategory(category).get();
    }
}
