package co.istad.chaya.itegen3eco.features.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileUpLoadRepository extends JpaRepository<FileUpLoad, Long> {
    Optional<FileUpLoad> findByName(String name);
    void deleteByName(String name);
    boolean existsByName(String name);
}
