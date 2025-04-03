import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = ConexionDB.getInstancia().getConexion();
    }

    public void agregarReserva(Reserva reserva){
        String sql = "INSERT INTO Reserva (cliente_id, habitacion_id, fecha_entrada, fecha_salida) VALUES (?,?,?,?);";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, reserva.getCliente_id());
            stmt.setInt(2, reserva.getHabitacion_id());
            stmt.setDate(3, reserva.getFecha_entrada());
            stmt.setDate(4, reserva.getFecha_salida());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Reserva> listaReservas(){
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * from Reserva";
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                reservas.add(new Reserva(rs.getInt("cliente_id"), rs.getInt("habitacion_id"), rs.getDate("fecha_entrada"), rs.getDate("fecha_salida")));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return reservas;
    }
}
