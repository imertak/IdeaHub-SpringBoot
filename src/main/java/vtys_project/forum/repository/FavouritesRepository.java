package vtys_project.forum.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vtys_project.forum.entity.Favourites;

@Repository
public class FavouritesRepository {

    @Value("${spring.datasource.url}")
    private String sql_url;
    @Value("${spring.datasource.username}")
    private String sql_username;
    @Value("${spring.datasource.password}")
    private String sql_password;


}
