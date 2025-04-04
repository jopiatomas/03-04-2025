import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class ReservaView {

    private Scanner leer = new Scanner(System.in);

    public Reserva solicitarDatosReserva() {
        System.out.print("Ingresa la id del cliente: ");
        int idCliente = leer.nextInt();
        leer.nextLine();

        System.out.print("Ingresa la id de la habitacion: ");
        int idHabitacion = leer.nextInt();
        leer.nextLine();

        System.out.print("Ingrese la fecha de ingreso (dd/MM/yyyy): ");
        String fechaIngresoStr = leer.nextLine();

        System.out.print("Ingrese la fecha de egreso (dd/MM/yyyy): ");
        String fechaEgresoStr = leer.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Convertimos a java.sql.Date para compatibilidad con la base de datos
            Date fechaIngreso = new Date(formato.parse(fechaIngresoStr).getTime());
            Date fechaEgreso = new Date(formato.parse(fechaEgresoStr).getTime());

            return new Reserva(idCliente, idHabitacion, fechaIngreso, fechaEgreso);
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Use dd/MM/yyyy.");
            return null; // Retorna null si la fecha es inválida
        }
    }

    public void mostrarReserva(Reserva reserva) {
        System.out.println("Reserva ID: " + reserva.getId());
        System.out.println("Cliente ID: " + reserva.getCliente_id());
        System.out.println("Habitación ID: " + reserva.getHabitacion_id());
        System.out.println("Fecha de Entrada: " + reserva.getFecha_entrada());
        System.out.println("Fecha de Salida: " + reserva.getFecha_salida());
    }


    public void mostrarReservas(List<Reserva> reservas){
        for(Reserva r : reservas){
            System.out.println("Reserva ID: " + r.getId() + " | ID cliente: " + r.getCliente_id() + " | ID habitacion: " + r.getHabitacion_id() + " | Fecha entrada: " + r.getFecha_entrada() + " | Fecha salida: " + r.getFecha_salida());
        }
    }
}