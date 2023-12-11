package vtys_project.forum.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vtys_project.forum.entity.Categories;
import vtys_project.forum.service.CategoriesService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesControlller {

    @Autowired
    CategoriesService categoriesService;

    @PostMapping("/add")
    public ResponseEntity<Categories> addCategory(@RequestBody Categories category){
        return new ResponseEntity<Categories>(categoriesService.addCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Categories>> getAllcategories(){
        return new ResponseEntity<>(categoriesService.getAllCategories(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable int id){
        categoriesService.deleteCategory(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable int id, @RequestBody Categories category){
        return new ResponseEntity<>(categoriesService.updateCategory(id, category), HttpStatus.UPGRADE_REQUIRED);
    }

}
