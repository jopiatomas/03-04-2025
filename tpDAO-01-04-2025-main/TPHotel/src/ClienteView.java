import java.util.List;
import java.util.Scanner;

public class ClienteView {

    private Scanner leer = new Scanner(System.in);

    public Cliente solicitarDatosCliente(){
        System.out.print("Ingresar nombre: ");
        String nombre = leer.nextLine();
        System.out.print("Ingresar email: ");
        String email = leer.nextLine();
        return new Cliente(nombre, email);
    }

    public void mostrarClientes(List<Cliente> clientes){
        for (Cliente c : clientes){
            System.out.println("ID: " + c.getId() + " | Nombre: " + c.getNombre() + " | Email: " + c.getEmail());
        }
    }

}
