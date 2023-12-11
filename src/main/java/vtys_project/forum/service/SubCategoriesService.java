package vtys_project.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vtys_project.forum.entity.SubCategories;
import vtys_project.forum.repository.SubCategoriesRepository;

import java.util.List;
import java.util.Map;

@Service
public class SubCategoriesService {

    private final SubCategoriesRepository subCategoriesRepository;

    @Autowired
    public SubCategoriesService(SubCategoriesRepository subCategoriesRepository) {
        this.subCategoriesRepository = subCategoriesRepository;
    }

    public List<Map<String, Object>> getAllSubCategories() {
        return subCategoriesRepository.getAllSubCategories();
    }

    public Map<String, Object> getSubCategoryById(int subCategoryId) {
        return subCategoriesRepository.getSubCategoryById(subCategoryId);
    }

    public void addSubCategory(SubCategories subCategory) {
        subCategoriesRepository.addSubCategory(subCategory);
    }

    public void updateSubCategory(int subCategoryId, SubCategories subCategory) {
        subCategoriesRepository.updateSubCategory(subCategoryId, subCategory);
    }

    public void deleteSubCategory(int subCategoryId) {
        subCategoriesRepository.deleteSubCategory(subCategoryId);
    }
}
