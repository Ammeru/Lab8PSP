package by.bsuir.lukashevich.phonestore;


import by.bsuir.lukashevich.phonestore.models.*;
import by.latotsky.phonestore.models.*;
import by.bsuir.lukashevich.phonestore.repositories.CartRepository;
import by.bsuir.lukashevich.phonestore.repositories.OrderRepository;
import by.bsuir.lukashevich.phonestore.repositories.PhoneRepository;
import by.bsuir.lukashevich.phonestore.repositories.UserRepository;
import by.bsuir.lukashevich.phonestore.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    private User user;
    private Phone phone;
    private Cart cart;

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
        phone.setPrice(new BigDecimal("100.00"));
        phoneRepository.save(phone);

        // Setup cart and add book to cart
        cart = new Cart();
        cart.setUser(user);
        CartItem cartItem = new CartItem();
        cartItem.setPhone(phone);
        cartItem.setQuantity(4);
        cart.add(cartItem);
        cartRepository.save(cart);
    }

    @Test
    public void testCreateOrderFromCart() {

        Long storeId = 1L;

        Order createdOrder = orderService.createOrderFromCart(user.getId(), storeId);

        assertNotNull(createdOrder);
        assertEquals(user.getId(), createdOrder.getUser().getId());
        assertFalse(createdOrder.getOrderItems().isEmpty());
        assertEquals(phone.getId(), createdOrder.getOrderItems().get(0).getPhone().getId());


        BigDecimal expectedTotalPrice = phone.getPrice(); // Modify as per discount logic if applicable
        assertEquals(0, expectedTotalPrice.compareTo(createdOrder.getTotalPrice()));

    }
}
