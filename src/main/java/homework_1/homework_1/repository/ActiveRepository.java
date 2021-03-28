package homework_1.homework_1.repository;
import homework_1.homework_1.model.active;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActiveRepository extends CrudRepository<active,Long> {
    List<active> findAll();
}
