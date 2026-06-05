package co.istad.chaya.itegen3eco.repoditory;

import co.istad.chaya.itegen3eco.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
