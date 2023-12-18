package by.bsuir.lukashevich.phonestore.services;

import by.bsuir.lukashevich.phonestore.models.Favorite;
import by.bsuir.lukashevich.phonestore.models.Phone;
import by.bsuir.lukashevich.phonestore.models.User;
import by.bsuir.lukashevich.phonestore.repositories.FavoriteRepository;
import by.bsuir.lukashevich.phonestore.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PhoneService phoneService;
    private final FavoriteRepository favoriteRepository;

    public void addToFavorites(Long phoneId, User user) {

        Phone book = phoneService.getPhoneById(phoneId);
        if (favoriteRepository.findByPhoneIdAndUserId(phoneId, user.getId()).isEmpty()) {
            Favorite favorite = new Favorite();
            favorite.setPhone(book);
            favorite.setUser(user);
            user.getFavorites().add(favorite);
            userRepository.save(user);
        }
    }

    @Transactional
    public void removeFromFavorites(Long phoneId, Principal principal) {
        User user = userService.getUserByPrincipal(principal);

        favoriteRepository.findByPhoneIdAndUserId(phoneId, user.getId())
                .ifPresentOrElse(
                        favorite -> {
                            user.getFavorites().remove(favorite);
                            favoriteRepository.delete(favorite);
                        },
                        () -> { throw new RuntimeException("Favorite not found"); }
                );
    }
}
