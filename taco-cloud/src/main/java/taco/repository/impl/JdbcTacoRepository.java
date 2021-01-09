//package taco.repository.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//import taco.domain.Ingredient;
//import taco.domain.Taco;
//import taco.repository.TacoRepository;
//
//import java.sql.Timestamp;
//import java.sql.Types;
//import java.util.Arrays;
//import java.util.Date;
//
//@Repository
//public class JdbcTacoRepository implements TacoRepository {
//
//    private JdbcTemplate jdbc;
//
//    @Autowired
//    public JdbcTacoRepository(JdbcTemplate jdbc) {
//        this.jdbc = jdbc;
//    }
//
//    @Override
//    public Taco save(Taco taco) {
//        long tacoId = saveTacoInfo(taco);
//        taco.setId(tacoId);
//        for (Ingredient ingredient : taco.getIngredients()) {
//            saveIngredientToTaco(ingredient, tacoId);
//        }
//        return taco;
//    }
//
//    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
//        jdbc.update("insert into Taco_Ingredients (taco,ingredient) values (?,?)",
//                tacoId, ingredient.getId());
//    }
//
//    private long saveTacoInfo(Taco taco) {
//        taco.setCreatedAt(new Date());
//        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
//                "insert into Taco(name,createAt) values (?,?)",
//                Types.VARCHAR, Types.TIMESTAMP)
//                .newPreparedStatementCreator(
//                        Arrays.asList(taco.getName(),
//                                new Timestamp(taco.getCreateAt().getTime())));
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbc.update(psc, keyHolder);
//        return keyHolder.getKey().longValue();
//    }
//}
