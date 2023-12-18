package by.bsuir.lukashevich.phonestore.repositories;

import by.bsuir.lukashevich.phonestore.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
