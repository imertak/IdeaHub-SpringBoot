package vtys_project.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vtys_project.forum.entity.Categories;
import vtys_project.forum.repository.CategoriesRepository;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    CategoriesRepository categoriesRepository;

    public Categories addCategory(Categories category) {
        categoriesRepository.addCategory(category);
        return category;
    }

    public Categories getCategories(int categoriesID){
        return categoriesRepository.getCategoriesById(categoriesID);
    }

    public List<Categories> getAllCategories(){
        List<Categories> categoriesList = categoriesRepository.getAllCategories();
        return categoriesList;
    }

    public void deleteCategory(int id){
        categoriesRepository.deleteCategoryById(id);
    }

    public Categories updateCategory(int id, Categories updateCategory){
        categoriesRepository.updateCategory(id, updateCategory);
        return updateCategory;
    }




}
