import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner leer = new Scanner(System.in);
    public static void main(String[] args) {

        int ejercicio;
        String rolUsuario;
        List<Reserva> listaReservas;
        ConexionDB conexionDB = new ConexionDB();

        ReservaDAO reservaDAO = new ReservaDAO(conexionDB.getConexion());
        ReservaView reservaView = new ReservaView();
        ReservaController reservaController = new ReservaController(reservaDAO, reservaView);

        do{
            System.out.print("Ingrese el ejercicio: ");
            ejercicio = leer.nextInt();
            leer.nextLine();
            switch (ejercicio){
                case 1:
                    System.out.print("Ingrese su rol (ADMINISTRADOR/RECEPCIONISTA/OTRO): ");
                    rolUsuario = leer.nextLine().toUpperCase();

                    System.out.print("Ingrese el ID del cliente: ");
                    int clienteId = leer.nextInt();
                    System.out.print("Ingrese el número de habitación: ");
                    int habitacionId = leer.nextInt();
                    leer.nextLine();

                    reservaController.mostrarReserva(clienteId, habitacionId, TipoUsuario.valueOf(rolUsuario));
                    break;
                case 2:
                    listaReservas = reservaDAO.listaReservas();
                    reservaView.mostrarReservas(listaReservas);

                    System.out.print("Ingrese su rol (ADMINISTRADOR/RECEPCIONISTA/OTRO): ");
                    rolUsuario = leer.nextLine().toUpperCase();

                    System.out.print("Ingrese el id de la reserva: ");
                    int reservaId = leer.nextInt();
                    leer.nextLine();
                    reservaController.actualizarReserva(reservaId, TipoUsuario.valueOf(rolUsuario));

                    break;
                case 3:
                    System.out.print("Ingrese su rol (ADMINISTRADOR/RECEPCIONISTA/OTRO): ");
                    rolUsuario = leer.nextLine().toUpperCase();

                    listaReservas = reservaDAO.listaReservas();
                    reservaView.mostrarReservas(listaReservas);
                    System.out.print("Ingresar la id de la reserva a eliminar: ");
                    reservaId = leer.nextInt();
                    leer.nextLine();

                    reservaController.eliminarReserva(reservaId, TipoUsuario.valueOf(rolUsuario));

                    listaReservas = reservaDAO.listaReservas();
                    reservaView.mostrarReservas(listaReservas);
                    break;
                case 4:


                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:

                    break;

            }
            System.out.println("Ingrese el ejercicio: ");
            ejercicio = leer.nextInt();
            leer.nextLine();
        } while(ejercicio != 0);




    }
}