package co.istad.chaya.itegen3eco.features.category;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);

    @Query(
            value = "SELECT * FROM categories WHERE is_delete = :isDeleted",
            nativeQuery = true
    )
    Page<Category> findAllByIsDeleted(
            @Param("isDeleted") Boolean isDeleted,
            Pageable pageable
    );
//    Page<Category> findAllByIsDeleted(Boolean isDeletedBoolean, Pageable pageable);
//    List<Category> findAllByIsDeletedAndParentCategory(Boolean isDeleted,Category parentCategory);

    @Query(value = "SELECT * FROM categories",nativeQuery = true)
    List<Category> findAllByIsDeleteAndParentCategory(Boolean isDeleted,Category parentCategory);

//    List<Category> findByParentCategory(Category parentCategory);


//    @Query("SELECT u FROM User u WHERE u.firstName = ?1 AND u.lastName = ?2")
//    User findByFullName(String firstName, String lastName);

//    @Query(value = "SELECT * FROM products WHERE status = :statusName", nativeQuery = true)
//    List<Product> findByStatusNative(@Param("statusName") String status);
//}


}
