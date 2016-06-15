package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.model.Book;
import demo.model.Student;

/**
 * Student repository.
 * @author smakhov
 *
 */
public interface StudentRepository extends JpaRepository<Student, Integer>{
}
