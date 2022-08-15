package com.thirdparty.expensecategorysupplier.repository;

import com.thirdparty.expensecategorysupplier.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, String> {
}
