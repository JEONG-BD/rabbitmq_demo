package me.example.demo.repository;

import me.example.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

public interface DemoRepository extends JpaRepository<Person, Long>{
}
