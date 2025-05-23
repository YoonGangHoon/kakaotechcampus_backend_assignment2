package kakaotechcampus.scheduleproject.repository;

import kakaotechcampus.scheduleproject.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepository {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "your_user", "your_password");
    }

    public Long save(Schedule schedule) throws SQLException {
        String sql = "INSERT INTO Schedule (title, author, password, createdAt, modifiedAt) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, schedule.getTitle());
            pstmt.setString(2, schedule.getAuthor());
            pstmt.setString(3, schedule.getPassword());
            pstmt.setTimestamp(4, Timestamp.valueOf(schedule.getCreatedAt()));
            pstmt.setTimestamp(5, Timestamp.valueOf(schedule.getModifiedAt()));
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return null;
    }

    public Schedule findById(Long id) throws SQLException {
        String sql = "SELECT * FROM Schedule WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToSchedule(rs);
            }
        }
        return null;
    }

    public List<Schedule> findAll(String author, String modifiedDate) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM Schedule WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (author != null && !author.isBlank()) {
            sql.append(" AND author = ?");
            params.add(author);
        }

        if (modifiedDate != null && !modifiedDate.isBlank()) {
            sql.append(" AND DATE(modifiedAt) = ?");
            params.add(Date.valueOf(modifiedDate)); // YYYY-MM-DD
        }

        sql.append(" ORDER BY modifiedAt DESC");

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = pstmt.executeQuery();
            List<Schedule> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapResultSetToSchedule(rs));
            }
            return list;
        }
    }

    public void update(Schedule schedule) throws SQLException {
        String sql = "UPDATE Schedule SET title = ?, author = ?, modifiedAt = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, schedule.getTitle());
            pstmt.setString(2, schedule.getAuthor());
            pstmt.setTimestamp(3, Timestamp.valueOf(schedule.getModifiedAt()));
            pstmt.setLong(4, schedule.getId());
            pstmt.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM Schedule WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    private Schedule mapResultSetToSchedule(ResultSet rs) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(rs.getLong("id"));
        schedule.setTitle(rs.getString("title"));
        schedule.setAuthor(rs.getString("author"));
        schedule.setPassword(rs.getString("password"));
        schedule.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        schedule.setModifiedAt(rs.getTimestamp("modifiedAt").toLocalDateTime());
        return schedule;
    }
}
