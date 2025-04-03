import java.util.List;

public class ReservaController {

    private ReservaDAO reservaDAO;
    private ReservaView reservaView;

    public ReservaController(ReservaDAO reservaDAO, ReservaView reservaView) {
        this.reservaDAO = reservaDAO;
        this.reservaView = reservaView;
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
