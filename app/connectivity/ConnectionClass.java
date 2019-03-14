package connectivity;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionClass {
    public static Connection connection;

    public static Connection getConnection(){


        String dbName="library";
        String userName="root";
        String password="";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection= DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return connection;
    }

   /* public List<Product> getProducts() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Statement statement = connectionClass.getConnection().createStatement();
        String query = "SELECT * FROM item_list";
        ResultSet rs = statement.executeQuery(query);
        List<Product> products = new ArrayList<>();
        while (rs.next()){
            products.add(new Product(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4)));
        }
        return products;
    }*/

}