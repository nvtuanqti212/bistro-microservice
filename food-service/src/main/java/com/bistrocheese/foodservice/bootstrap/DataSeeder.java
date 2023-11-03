package com.bistrocheese.foodservice.bootstrap;

import com.bistrocheese.foodservice.model.Category;
import com.bistrocheese.foodservice.repository.CategoryRepo;
import com.bistrocheese.foodservice.repository.FoodRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {

    private final CategoryRepo categoryRepo;
    private final FoodRepo foodRepo;

    @Override
    public void run(String... args) throws ParseException {
//        try {
////            this.createListFood();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadCategory();
    }

    private void loadCategory() {
        List<Category> categories = new ArrayList<>(
                List.of(
                        new Category(1, "Appetizer"),
                        new Category(2, "Main Course"),
                        new Category(3, "Dessert"),
                        new Category(4, "Drink")
                )
        );
        categories.forEach(category -> {
            Optional<Category> optionalCategory = categoryRepo.findByName(category.getName());
            optionalCategory.ifPresentOrElse(System.out::println, () -> categoryRepo.save(category));
        });
    }

//    private void createListFood() throws FileNotFoundException {
//        JSONParser parser = new JSONParser();
//        JSONArray jsonArray;
//        try {
//            jsonArray = (JSONArray) parser.parse(
//                    new FileReader("./src/main/resources/data/menu.json")
//            );
//        } catch (net.minidev.json.parser.ParseException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
