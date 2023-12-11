package vtys_project.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vtys_project.forum.entity.SubCategories;
import vtys_project.forum.service.SubCategoriesService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subcategories")
public class SubCategoriesController {

    private final SubCategoriesService subCategoriesService;

    @Autowired
    public SubCategoriesController(SubCategoriesService subCategoriesService) {
        this.subCategoriesService = subCategoriesService;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllSubCategories() {
        List<Map<String, Object>> subCategories = subCategoriesService.getAllSubCategories();
        return ResponseEntity.ok(subCategories);
    }

    @GetMapping("/{subCategoryId}")
    public ResponseEntity<Map<String, Object>> getSubCategoryById(@PathVariable int subCategoryId) {
        Map<String, Object> subCategory = subCategoriesService.getSubCategoryById(subCategoryId);
        return ResponseEntity.ok(subCategory);
    }

    @PostMapping
    public ResponseEntity<Void> addSubCategory(@RequestBody SubCategories subCategory) {
        subCategoriesService.addSubCategory(subCategory);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{subCategoryId}")
    public ResponseEntity<Void> updateSubCategory(@PathVariable int subCategoryId, @RequestBody SubCategories subCategory) {
        subCategoriesService.updateSubCategory(subCategoryId, subCategory);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{subCategoryId}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable int subCategoryId) {
        subCategoriesService.deleteSubCategory(subCategoryId);
        return ResponseEntity.ok().build();
    }
}
