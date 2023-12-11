package vtys_project.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import vtys_project.forum.entity.Categories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriesRepository {

    Categories categories;

    @Value("${spring.datasource.url}")
    private String sql_url;
    @Value("${spring.datasource.username}")
    private String sql_username;
    @Value("${spring.datasource.password}")
    private String sql_password;

    @Autowired
    public CategoriesRepository(Categories categories) {
        this.categories = categories;
    }

    public Categories addCategory(Categories categories)  {
        String sql = "INSERT INTO categories (categoryName, categoryDescription) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, categories.getCategoryName());
                preparedStatement.setString(2, categories.getCategoryDescription());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return categories;
    }

    public List<Categories> getAllCategories() {
        List<Categories> categoriesList = new ArrayList<>();
        String sql = "SELECT * FROM categories";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Categories categories = new Categories();
                categories.setCategoryID(resultSet.getInt("categoryID"));
                categories.setCategoryName(resultSet.getString("categoryName"));
                categories.setCategoryDescription(resultSet.getString("categoryDescription"));
                categoriesList.add(categories);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoriesList;
    }

    public void deleteCategoryById(int categoryId) {
        String sql = "DELETE FROM categories WHERE categoryID = ?";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, categoryId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCategory(int id, Categories updatedCategory) {
        String sql = "UPDATE categories SET categoryName = ?, categoryDescription = ? WHERE categoryID = ?";

        try (Connection connection = DriverManager.getConnection(sql_url, sql_username, sql_password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, updatedCategory.getCategoryName());
            preparedStatement.setString(2, updatedCategory.getCategoryDescription());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
