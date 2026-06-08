package co.istad.chaya.itegen3eco.repoditory;

import co.istad.chaya.itegen3eco.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);

    Page<Category> findAllByIsDeleted(Boolean isDeletedBoolean, Pageable pageable);
    List<Category> findAllByIsDeletedAndParentCategory(Boolean isDeleted,Category parentCategory);

//    List<Category> findByParentCategory(Category parentCategory);




}
