package kakaotechcampus.scheduleproject.repository;

import kakaotechcampus.scheduleproject.config.DataSourceProperties;
import kakaotechcampus.scheduleproject.dto.schedule.ScheduleCreateResponseDto;
import kakaotechcampus.scheduleproject.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleRepository {

    private final DataSourceProperties dataSourceProperties;

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                dataSourceProperties.getUrl(),
                dataSourceProperties.getUsername(),
                dataSourceProperties.getPassword()
        );
    }

    public ScheduleCreateResponseDto save(Schedule schedule) throws SQLException {
        String sql = "INSERT INTO Schedule (title, author_id, password, createdAt, modifiedAt) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, schedule.getTitle());
            stmt.setLong(2, schedule.getAuthorId());
            stmt.setString(3, schedule.getPassword());
            stmt.setDate(4, Date.valueOf(schedule.getCreatedAt()));
            stmt.setDate(5, Date.valueOf(schedule.getModifiedAt()));
            stmt.executeUpdate();

            try (ResultSet resultSet = stmt.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return ScheduleCreateResponseDto.builder()
                            .id(resultSet.getLong(1))
                            .title(schedule.getTitle())
                            .createdAt(schedule.getCreatedAt())
                            .modifiedAt(schedule.getModifiedAt())
                            .build();

                }
            }
        }
        return null;
    }

    public Schedule findById(Long id) throws SQLException {
        String sql = "SELECT * FROM Schedule WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToSchedule(rs);
            }
        }
        return null;
    }

    public List<Schedule> findAllByModifiedDateAndAuthor(Long authorId, String modifiedDate) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM Schedule WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (authorId != null) {
            sql.append(" AND author_id = ?");
            params.add(authorId);
        }

        if (modifiedDate != null && !modifiedDate.isBlank()) {
            sql.append(" AND DATE(modifiedAt) = ?");
            params.add(Date.valueOf(modifiedDate)); // YYYY-MM-DD
        }

        sql.append(" ORDER BY modifiedAt DESC");

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            List<Schedule> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapResultSetToSchedule(rs));
            }
            return list;
        }
    }

    public void update(Schedule schedule) throws SQLException {
        String sql = "UPDATE Schedule SET title = ?, author_id = ?, modifiedAt = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, schedule.getTitle());
            stmt.setLong(2, schedule.getAuthorId());
            stmt.setDate(3, Date.valueOf(schedule.getModifiedAt()));
            stmt.setLong(4, schedule.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM Schedule WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Schedule mapResultSetToSchedule(ResultSet rs) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(rs.getLong("id"));
        schedule.setTitle(rs.getString("title"));
        schedule.setAuthorId(rs.getLong("author_id"));
        schedule.setPassword(rs.getString("password"));
        schedule.setCreatedAt(rs.getDate("createdAt").toLocalDate());
        schedule.setModifiedAt(rs.getDate("modifiedAt").toLocalDate());
        return schedule;
    }

}
