package me.example.demo.repository;

import me.example.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

import java.util.Optional;

public interface DemoRepository extends JpaRepository<Person, Long>{

    Optional<Person> findByEmail(String email); // ✅ 명시적으로 선언!
}
