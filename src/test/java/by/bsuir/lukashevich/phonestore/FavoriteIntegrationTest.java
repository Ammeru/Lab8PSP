package by.bsuir.lukashevich.phonestore;


import by.bsuir.lukashevich.phonestore.models.Favorite;
import by.bsuir.lukashevich.phonestore.models.User;
import by.bsuir.lukashevich.phonestore.models.Phone;
import by.bsuir.lukashevich.phonestore.repositories.FavoriteRepository;
import by.bsuir.lukashevich.phonestore.repositories.PhoneRepository;
import by.bsuir.lukashevich.phonestore.repositories.UserRepository;
import by.bsuir.lukashevich.phonestore.services.FavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FavoriteIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private FavoriteService favoriteService;

    private User user;
    private Phone phone;

    @BeforeEach
    public void setup() {
        // Setup user
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        userRepository.save(user);

        // Setup book
        phone = new Phone();
        phone.setBrand("Test brand");
        phone.setModel("Test model");
        phone.setDescription("Test desc");
        phoneRepository.save(phone);
    }

    @Test
    public void testAddToFavorites() {
        favoriteService.addToFavorites(phone.getId(), user);

        Favorite favorite = favoriteRepository.findByPhoneIdAndUserId(phone.getId(), user.getId()).orElse(null);
        assertNotNull(favorite);
        assertEquals(phone.getId(), favorite.getPhone().getId());
        assertEquals(user.getId(), favorite.getUser().getId());
    }
}
