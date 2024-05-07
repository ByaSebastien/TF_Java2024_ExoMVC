package be.bstorm.tf_java2024_exomvc.dal.repositories;

import be.bstorm.tf_java2024_exomvc.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.username like :username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("select count(u) > 0 from User u where u.username like :username")
    boolean existsByUsername(@Param("username") String username);
}
