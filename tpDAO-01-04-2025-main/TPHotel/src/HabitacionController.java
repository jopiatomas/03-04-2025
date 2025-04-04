import java.util.List;

public class HabitacionController {

    private HabitacionView habitacionView;
    private HabitacionDAO habitacionDAO;

    public HabitacionController(HabitacionView habitacionView, HabitacionDAO habitacionDAO) {
        this.habitacionView = habitacionView;
        this.habitacionDAO = habitacionDAO;
    }

    public void agregarHabitacion(){
        Habitacion h = habitacionView.solicitarDatosHabitacion();
        habitacionDAO.agregarHabitacion(h);
    }

    public void listarHabitaciones(){
        List<Habitacion> habitaciones = habitacionDAO.listarHabitaciones();
        habitacionView.mostrarHabitaciones(habitaciones);
    }

}
