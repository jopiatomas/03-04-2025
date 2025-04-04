import java.util.List;
import java.util.Optional;

public class ReservaController {

    private ReservaDAO reservaDAO;
    private ReservaView reservaView;

    public ReservaController(ReservaDAO reservaDAO, ReservaView reservaView) {
        this.reservaDAO = reservaDAO;
        this.reservaView = reservaView;
    }

    public void mostrarReserva(int clienteId, int habitacionId, TipoUsuario tipoUsuario) {
        try {
            Optional<Reserva> reserva = reservaDAO.buscarReserva(clienteId, habitacionId, tipoUsuario);

            reserva.ifPresentOrElse(
                    r -> reservaView.mostrarReserva(r),
                    () -> System.out.println("Esto no debería imprimirse porque la excepción ya maneja el error.")
            );

        } catch (PermisoDenegadoException e) {
            System.out.println("⚠ " + e.getMessage());
        } catch (ReservaNoEncontradaException e) {
            System.out.println("⚠ " + e.getMessage());
        }
    }

    public void actualizarReserva(int idReserva, TipoUsuario tipoUsuario) {
        try {
            reservaDAO.modificarReserva(idReserva, tipoUsuario);
            System.out.println("Reserva actualizada correctamente desde el Controller.");
        } catch (Exception e) {
            System.out.println("Error al actualizar la reserva: " + e.getMessage());
        }
    }

    public void eliminarReserva(int idReserva, TipoUsuario tipoUsuario){
        try{
            reservaDAO.eliminarReserva(idReserva, tipoUsuario);
        } catch (Exception e){
            System.out.println("Error al eliminar la reserva: " + e.getMessage());
        }
    }

    public void agregarReserva(){
        Reserva reserva = reservaView.solicitarDatosReserva();
        reservaDAO.agregarReserva(reserva);
    }

    public void listarReservas(){
        List<Reserva> reservas = reservaDAO.listaReservas();
        reservaView.mostrarReservas(reservas);
    }
}
