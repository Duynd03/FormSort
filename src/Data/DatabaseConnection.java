package Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/sortarray"; 
        String username = "root"; 
        String password = "123456"; 

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi: Không tìm thấy Driver MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Lỗi: Không thể kết nối đến database.");
            e.printStackTrace();
        }

        return connection;
    }


}
//code sao để trả ra  thuật toán nào là tối ưu nhất, trả ra 1 cái thống kê