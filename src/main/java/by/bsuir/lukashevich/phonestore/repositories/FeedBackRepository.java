package by.bsuir.lukashevich.phonestore.repositories;

import by.bsuir.lukashevich.phonestore.models.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {
}
