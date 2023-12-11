package vtys_project.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vtys_project.forum.entity.SubCategories;

import java.util.List;
import java.util.Map;

@Repository
public class SubCategoriesRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SubCategoriesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getAllSubCategories() {
        String sql = "SELECT * FROM SubCategories";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> getSubCategoryById(int subCategoryId) {
        String sql = "SELECT * FROM SubCategories WHERE subCategoryID = ?";
        return jdbcTemplate.queryForMap(sql, subCategoryId);
    }

    public void addSubCategory(SubCategories subCategory) {
        String sql = "INSERT INTO SubCategories (subCategoryName, subCategoryDescription, categoryID) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, subCategory.getSubCategoryName(), subCategory.getSubCategoryDescription(), subCategory.getCategoryID());
    }

    public void updateSubCategory(int subCategoryId, SubCategories subCategory) {
        String sql = "UPDATE SubCategories SET subCategoryName = ?, subCategoryDescription = ?, categoryID = ? WHERE subCategoryID = ?";
        jdbcTemplate.update(sql, subCategory.getSubCategoryName(), subCategory.getSubCategoryDescription(), subCategory.getCategoryID(), subCategoryId);
    }

    public void deleteSubCategory(int subCategoryId) {
        String sql = "DELETE FROM SubCategories WHERE subCategoryID = ?";
        jdbcTemplate.update(sql, subCategoryId);
    }
}
