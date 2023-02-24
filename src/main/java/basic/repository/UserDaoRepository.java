package basic.repository;

import basic.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserDaoRepository extends CrudRepository<UserEntity, Long> {
    @Query("select u from UserEntity u where u.userName = ?1")
    UserEntity findByUserName(String username);
}
