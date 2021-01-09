package taco.repository;

import org.springframework.data.repository.CrudRepository;
import taco.domain.Ingredient;

//import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient,String> {
//    List<Ingredient> findAll();
//    Ingredient findOne(String id);
//    Ingredient save(Ingredient ingredient);
}
