package by.bsuir.lukashevich.phonestore.repositories;


import by.bsuir.lukashevich.phonestore.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {


    List<Store> findAllByActive(boolean b);
}
