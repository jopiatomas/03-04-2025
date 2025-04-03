import java.util.List;
import java.util.Scanner;

public class HabitacionView {

    private Scanner leer = new Scanner(System.in);


    public Habitacion solicitarDatosHabitacion(){

        System.out.print("Ingrese el numero de habitacion: ");
        int numero = leer.nextInt();
        leer.nextLine();

        System.out.print("Ingrese el tipo de habitacion (SIMPLE, DOBLE, SUIT): ");
        String tipoStr = leer.nextLine().toUpperCase();

        TipoHabitacion tipoHabitacion;
        try{
            tipoHabitacion = TipoHabitacion.valueOf(tipoStr); // convierte de String a enum
        } catch (IllegalArgumentException e){
            System.out.println("Tipo de habitacion invalido, usando SIMPLE por defecto");
            tipoHabitacion = TipoHabitacion.SIMPLE;
        }

        System.out.print("Ingrese el precio de la habitacion: ");
        double precio = leer.nextDouble();
        leer.nextLine();


        return new Habitacion(numero, tipoHabitacion, precio);
    }

    public void mostrarHabitaciones(List<Habitacion> habitaciones){
        for(Habitacion h : habitaciones){
            System.out.println("Numero: " + h.getNumero() + " | Tipo: " + h.getTipoHabitacion() + " | Precio: " + h.getPrecio());
        }
    }

}
