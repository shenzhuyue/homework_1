package homework_1.homework_1.repository;
import homework_1.homework_1.model.user;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
public interface UserRepository extends CrudRepository<user,Long> {
    List<user> findAll();
    List<user> findByUsername(String userName);
    user findByUsernameAndAndPassword(String userName,String passWord);
}
