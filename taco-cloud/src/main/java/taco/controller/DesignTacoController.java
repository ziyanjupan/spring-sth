package taco.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import taco.domain.Ingredient;
import taco.domain.Taco;
import taco.repository.IngredientRepository;
import taco.repository.JdbcIngredientRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Four Tortilla", Ingredient.Type.WRAP),
//                new Ingredient("A", "A Tortilla", Ingredient.Type.CHEESE),
//                new Ingredient("B", "B Tortilla", Ingredient.Type.PROTEIN),
//                new Ingredient("D", "D Tortilla", Ingredient.Type.VEGGIES),
//                new Ingredient("C", "C Tortilla", Ingredient.Type.SAUCE)
//        );
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(item -> ingredients.add(item));
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }
        //todo save logic
        log.debug("Process design" + design);
        return "redirect:/orders/current";
    }
}
