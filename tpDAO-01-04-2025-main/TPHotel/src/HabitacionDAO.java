import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HabitacionDAO {

    private Connection connection;

    public HabitacionDAO(){
        this.connection = ConexionDB.getInstancia().getConexion();
    }

    public void agregarHabitacion(Habitacion habitacion){
        String sql = "INSERT INTO Habitacion (numero, tipo, precio) VALUES (?,?,?);";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, habitacion.getNumero());
            stmt.setString(2, habitacion.getTipoHabitacion().name());
            stmt.setDouble(3, habitacion.getPrecio());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Habitacion> listarHabitaciones(){ // hay un problema al usar "*"?? //
        // porque selecciona a todo incluyendo id
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "SELECT * FROM Habitacion;";
        try(Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                habitaciones.add(new Habitacion(rs.getInt("numero"), TipoHabitacion.valueOf(rs.getString("tipo")), rs.getDouble("precio")));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return habitaciones;
    }

}
