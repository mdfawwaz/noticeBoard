package noticeBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticeBoardController {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/prodapt";
    private String jdbcUsername = "fawwazuddin";
    private String jdbcPassword = "fawwaz@123";

    public void addNotice(Notice notice) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO notices (title, content, contactName, contactPhone) VALUES (?, ?, ?, ?)"
            );
            preparedStatement.setString(1, notice.getTitle());
            preparedStatement.setString(2, notice.getContent());
            preparedStatement.setString(3, notice.getContactName());
            preparedStatement.setString(4, notice.getContactPhone());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Notice> getNotices() {
        List<Notice> notices = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM notices");
            while (resultSet.next()) {
                Notice notice = new Notice(
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getString("contactName"),
                    resultSet.getString("contactPhone")
                );
                notices.add(notice);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notices;
    }
}
