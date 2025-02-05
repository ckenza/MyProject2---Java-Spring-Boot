package com.greta.myproject.daos;

import com.greta.myproject.entities.Category;
import com.greta.myproject.entities.User;
import com.greta.myproject.exceptions.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CategoryDao {

    private final JdbcTemplate jdbcTemplate;

    public CategoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private final RowMapper<Category> categoryRowMapper = (rs, _) -> new Category(
            rs.getInt("id_category"),
            rs.getString("category_name")
    );


    public List<Category> findAll() {
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, categoryRowMapper);
    }



    public Category findByCategory_name(String category_name){
        String sql = "SELECT * FROM category WHERE category_name = ?";
        return jdbcTemplate.query(sql, categoryRowMapper, category_name)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Category avec le nom : " + category_name + " n'existe pas"));

    }




    public Category save(Category category) {
        String sql = "INSERT INTO category (category_name) VALUES (?)";
        jdbcTemplate.update(sql, category.getCategory_name());
        return findByCategory_name(category.getCategory_name());

    }




    public Category update(String category_name, Category category) {
        if (!categoryExists(category_name)) {
            throw new ResourceNotFoundException("La catégorie avec le nom : " + category_name + " n'existe pas");
        }

        String sql = "UPDATE category SET category_name = ? WHERE category_name = ?";
        int rowsAffected = jdbcTemplate.update(sql, category.getCategory_name(), category_name);

        if (rowsAffected <= 0) {
            throw new ResourceNotFoundException("Échec de la mise à jour de la catégorie avec le nom : " + category_name);
        }

        return this.findByCategory_name(category_name);
    }

    // méthode utilitaire à mettre en bas du fichier
    private boolean categoryExists(String category_name) {
        String checkSql = "SELECT COUNT(*) FROM category WHERE category_name = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, category_name);
        return count > 0;
    }




    public boolean delete(String category_name) {
        String sql = "DELETE FROM category WHERE category_name = ?";
        int rowsAffected = jdbcTemplate.update(sql, category_name);
        return rowsAffected > 0;
    }

}

