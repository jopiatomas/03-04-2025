import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection conexion;

    public ClienteDAO(Connection conexion) {
        this.conexion = ConexionDB.getInstancia().getConexion();
    }

    public void agregarCliente(Cliente cliente){
        String sql = "INSERT INTO Clientes (nombre, email), VALUES (?, ?);";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getEmail());
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Cliente> listaClientes(){
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try(Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                clientes.add(new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("email")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return clientes;
    }

}
