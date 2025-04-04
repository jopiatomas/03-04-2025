import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static ConexionDB instancia;
    private Connection conexion;
    private final String URL = "jdbc:mysql://127.0.0.1:3306/HotelDB";
    private final String USER = "root";
    private final String PASSWORD = "root";

    public ConexionDB(){
        try{
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e){
            throw new RuntimeException("Error al conectar la base de datos.");
        }
    }

    public static ConexionDB getInstancia(){
        if (instancia == null){
            instancia = new ConexionDB();
        }
        return instancia;
    }

    public Connection getConexion(){
        return conexion;
    }

}
