package by.bsuir.lukashevich.phonestore.services;

import by.bsuir.lukashevich.phonestore.models.FeedBack;
import by.bsuir.lukashevich.phonestore.repositories.FeedBackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedBackService {

    private final FeedBackRepository feedBackRepository;
    public void create(FeedBack feedBack) {
        feedBack.setLocalDate(LocalDate.now());
        feedBackRepository.save(feedBack);
    }

    public void delete(Long feedbackId) {
        feedBackRepository.deleteById(feedbackId);
    }

    public List<FeedBack> findAll() {
        return feedBackRepository.findAll();
    }
}
