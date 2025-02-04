package com.greta.myproject.daos;


import com.greta.myproject.entities.User;
import com.greta.myproject.exceptions.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = (rs, _) -> new User(
            rs.getInt("id_user"),
            rs.getString("email"),
            rs.getString("password")
    );

    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, userRowMapper);
    }


    public User findByEmail(String email){
        String sql = "SELECT * FROM user WHERE email = ?";
        return jdbcTemplate.query(sql, userRowMapper, email)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("User avec l'email : " + email + " n'existe pas"));

    }


    public User save(User user) {
        String sql = "INSERT INTO user (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword());

        String sqlGetEmail = "SELECT LAST_INSERT_EMAIL()";
        String email = jdbcTemplate.queryForObject(sqlGetEmail, String.class);

        user.setEmail(email);
        return user;
    }



    public User update(String email, User user) {
        if (!userExists(email)) {
            throw new ResourceNotFoundException("L'utilisateur avec l'EMAIL : " + email + " n'existe pas");
        }

        String sql = "UPDATE user SET email = ?, password = ? WHERE email = ?";
        int rowsAffected = jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), email);

        if (rowsAffected <= 0) {
            throw new ResourceNotFoundException("Échec de la mise à jour de l'utilisateur avec l'EMAIL : " + email);
        }

        return this.findByEmail(email);
    }


    // méthode utilitaire à mettre en bas du fichier
    private boolean userExists(String email) {
        String checkSql = "SELECT COUNT(*) FROM user WHERE email = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, email);
        return count > 0;
    }





    public boolean delete(String email) {
        String sql = "DELETE FROM user WHERE email = ?";
        int rowsAffected = jdbcTemplate.update(sql, email);
        return rowsAffected > 0;
    }


}
