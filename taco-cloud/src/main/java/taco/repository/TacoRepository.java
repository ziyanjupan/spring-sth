package taco.repository;

import org.springframework.data.repository.CrudRepository;
import taco.domain.Taco;

public interface TacoRepository extends CrudRepository<Taco,Long> {
//    Taco save(Taco taco);
}
