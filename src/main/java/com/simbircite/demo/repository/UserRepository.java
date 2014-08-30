package com.simbircite.demo.repository;

import com.simbircite.demo.model.User;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Интерфейс репозитория (DAO) для работы с моделью данных User.
 * Реализовывать интерфейс не нужно, об этом позаботится платформа Spring.
 * Используется в сервисе. Репозиторий не работает с транзакциями.
 * Не содержит бизнес-логику.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    /**
     * SELECT *
     * FROM users
     * WHERE email = ?
     * ORDER BY name
     */
    List<User> findByEmailOrderByNameAsc(String email);
    
}
