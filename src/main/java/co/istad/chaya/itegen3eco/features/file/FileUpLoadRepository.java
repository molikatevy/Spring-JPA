package co.istad.chaya.itegen3eco.features.file;



import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FileUpLoadRepository extends JpaRepository<FileUpLoad,Long> {
    Optional<FileUpLoad> findByName(String name);

}
