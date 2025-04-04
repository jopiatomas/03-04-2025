import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ReservaDAO {

    private Connection connection;
    private Scanner leer;

    public ReservaDAO(Connection connection) {
        this.connection = ConexionDB.getInstancia().getConexion();
        this.leer = new Scanner(System.in);
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
                reservas.add(new Reserva(rs.getInt("id"), rs.getInt("cliente_id"), rs.getInt("habitacion_id"), rs.getDate("fecha_entrada"), rs.getDate("fecha_salida")));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return reservas;
    }

    public Optional<Reserva> buscarReserva(int clienteId, int habitacionId, TipoUsuario tipoUsuario) throws PermisoDenegadoException, ReservaNoEncontradaException{

        if(tipoUsuario == TipoUsuario.CLIENTE){
            throw new PermisoDenegadoException("Solamente recepcionistas y administradores pueden realizar esta accion.");
        }

        String sql = "SELECT cliente_id, habitacion_id, fecha_entrada, fecha_salida FROM Reserva;";
        List<Reserva> reservas = new ArrayList<>();

        try(Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ){

            while (rs.next()){
                reservas.add(new Reserva(
                        rs.getInt("cliente_id"),
                        rs.getInt("habitacion_id"),
                        rs.getDate("fecha_entrada"),
                        rs.getDate("fecha_salida")
                ));
            }

            return reservas.stream()
                    .filter(r -> r.getCliente_id() == clienteId || r.getHabitacion_id() == habitacionId)
                    .findFirst()
                    .or(() -> {
                        try {
                            throw new ReservaNoEncontradaException("La reserva no fue encontrada");
                        } catch (ReservaNoEncontradaException e) {
                            throw new RuntimeException(e);
                        }
                    });

        } catch (SQLException e){
            e.printStackTrace();
            throw new ReservaNoEncontradaException("La reserva no fue encontrada");
        }

    }

    public void modificarReserva(int idReserva, TipoUsuario tipoUsuario) throws ReservaNoEncontradaException, PermisoDenegadoException, ParseException {
        List<Reserva> list = listaReservas();
        Reserva reservaExistente = null;

        // Buscar la reserva manualmente
        for (Reserva r : list) {
            if (r.getId() == idReserva) {
                reservaExistente = r;
                break;
            }
        }

        if (reservaExistente == null) {
            throw new ReservaNoEncontradaException("La id de la reserva no existe.");
        }

        ReservaView reservaView = new ReservaView();
        System.out.println("Ingrese los nuevos datos de la reserva con id " + idReserva + ": ");

        if (tipoUsuario == TipoUsuario.CLIENTE) {
            System.out.print("Ingrese la nueva fecha de salida (dd/MM/yyyy): ");
            String nuevaFechaSalidaStr = leer.nextLine();
            Date nuevaFechaSalida = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(nuevaFechaSalidaStr);
            reservaExistente.setFecha_salida(nuevaFechaSalida);
        } else if (tipoUsuario == TipoUsuario.RECEPCIONISTA) {
            System.out.print("Ingrese el nuevo ID del cliente: ");
            int nuevoClienteId = leer.nextInt();
            leer.nextLine();
            System.out.print("Ingrese la nueva fecha de entrada (dd/MM/yyyy): ");
            String nuevaFechaEntradaStr = leer.nextLine();
            System.out.print("Ingrese la nueva fecha de salida (dd/MM/yyyy): ");
            String nuevaFechaSalidaStr = leer.nextLine();
            Date nuevaFechaEntrada = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(nuevaFechaEntradaStr);
            Date nuevaFechaSalida = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(nuevaFechaSalidaStr);
            reservaExistente.setCliente_id(nuevoClienteId);
            reservaExistente.setFecha_entrada(nuevaFechaEntrada);
            reservaExistente.setFecha_salida(nuevaFechaSalida);
        } else if (tipoUsuario == TipoUsuario.ADMINISTRADOR) {
            Reserva nuevaReserva = reservaView.solicitarDatosReserva();
            reservaExistente.setCliente_id(nuevaReserva.getCliente_id());
            reservaExistente.setHabitacion_id(nuevaReserva.getHabitacion_id());
            reservaExistente.setFecha_entrada(nuevaReserva.getFecha_entrada());
            reservaExistente.setFecha_salida(nuevaReserva.getFecha_salida());
        } else {
            throw new PermisoDenegadoException("No tienes permiso para modificar reservas.");
        }

        String sql = "UPDATE Reserva SET cliente_id = ?, habitacion_id = ?, fecha_entrada = ?, fecha_salida = ? WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservaExistente.getCliente_id());
            stmt.setInt(2, reservaExistente.getHabitacion_id());
            stmt.setDate(3, reservaExistente.getFecha_entrada());
            stmt.setDate(4, reservaExistente.getFecha_salida());
            stmt.setInt(5, idReserva);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Reserva actualizada correctamente.");
            } else {
                System.out.println("No se encontró una reserva con ese ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void eliminarReserva(int idReserva, TipoUsuario tipoUsuario) throws PermisoDenegadoException, ReservaNoEncontradaException {

        if (TipoUsuario.CLIENTE .equals(tipoUsuario)) {
            throw new PermisoDenegadoException("El cliente no puede eliminar reservas.");
        }

        List<Reserva> list = listaReservas();
        Reserva reservaExistente = null;

        // Buscar la reserva manualmente
        for (Reserva r : list) {
            if (r.getId() == idReserva) {
                reservaExistente = r;
                break;
            }
        }

        if (reservaExistente == null) {
            throw new ReservaNoEncontradaException("La id de la reserva no existe.");
        }

        String sql = "DELETE FROM Reserva WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, idReserva);
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Reserva eliminada correctamente.");
            } else {
                System.out.println("No se encontró una reserva con ese ID.");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }



    }

}
