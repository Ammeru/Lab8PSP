package by.bsuir.lukashevich.phonestore.repositories;

import by.bsuir.lukashevich.phonestore.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPhoneId(Long bookId);

    Optional<Review> getReviewById(Long reviewId);
}
