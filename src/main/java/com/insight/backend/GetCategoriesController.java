package com.insight.backend;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GetCategoriesController {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /*GET request fpr categories with placeholder data */
    @GetMapping("api/hw")
    public ResponseEntity<Object> getCategory() {
        List<Category> response = new ArrayList<>();
        response.add(new Category(0, "a"));
        response.add(new Category(1, "b"));
        response.add(new Category(2, "c"));
        return ResponseEntity.ok(gson.toJson(response));
    }
}
