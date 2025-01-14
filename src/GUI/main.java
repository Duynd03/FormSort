package GUI;

import java.sql.Connection;
import java.sql.SQLException;
import Data.DatabaseConnection; 

public class main {
    public static void main(String[] args) {
        SortSearchModel model = new SortSearchModel();
        SortSearchView view = new SortSearchView();
        new SortSearchController(model, view);


        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                System.out.println("Kết nối thành công!");
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Kết nối thất bại!");
        }
        AlgorithmAPI api = new AlgorithmAPI(model);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            api.printExecutionData();
        }));
    }
}
