package kakaotechcampus.scheduleproject.repository;

import kakaotechcampus.scheduleproject.config.DataSourceProperties;
import kakaotechcampus.scheduleproject.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
@RequiredArgsConstructor
public class AuthorRepository {

    private final DataSourceProperties dataSourceProperties;

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                dataSourceProperties.getUrl(),
                dataSourceProperties.getUsername(),
                dataSourceProperties.getPassword()
        );
    }

    public Long save(Author author) throws SQLException{
        String sql = "INSERT INTO Author (name, email, createdAt, modifiedAt) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, author.getName());
            stmt.setString(2, author.getEmail());
            stmt.setDate(3, Date.valueOf(author.getCreatedAt()));
            stmt.setDate(4, Date.valueOf(author.getModifiedAt()));
            stmt.executeUpdate();

            try (ResultSet resultSet = stmt.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
        }
        return null;
    }

    public Author findById(Long id) throws SQLException {
        String sql = "SELECT * FROM Author WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToAuthor(rs);
            }
        }
        return null;
    }

    private Author mapResultSetToAuthor(ResultSet rs) throws SQLException {
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setName(rs.getString("name"));
        author.setEmail(rs.getString("email"));
        author.setCreatedAt(rs.getDate("createdAt").toLocalDate());
        author.setModifiedAt(rs.getDate("modifiedAt").toLocalDate());
        return author;
    }

    public void delete(Long id) throws SQLException{
        String sql = "DELETE FROM Author WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(Author author) throws SQLException {
        String sql = "UPDATE Author SET name = ?, email = ?, modifiedAt = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, author.getName());
            stmt.setString(2, author.getEmail());
            stmt.setDate(3, Date.valueOf(author.getModifiedAt()));
            stmt.setLong(4, author.getId());
            stmt.executeUpdate();
        }
    }
}
