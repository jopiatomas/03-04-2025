import java.util.List;
import java.util.Scanner;

public class UsuariosView {

    private Scanner leer = new Scanner(System.in);

    public Usuario solicitarDatosUsuario(){
        System.out.print("Ingrese el nombre: ");
        String nombre = leer.nextLine();

        System.out.print("Ingrese el email: ");
        String email = leer.nextLine();

        System.out.print("Ingrese el rol (CLIENTE, RECEPCIONISTA, ADMINISTRADOR): ");
        String rolStr = leer.nextLine();

        TipoUsuario tipoUsuario;
        try{
            tipoUsuario = TipoUsuario.valueOf(rolStr);
        } catch (IllegalArgumentException e){
            System.out.println("Tipo invalido. Ingresando el valor por defecto CLIENTE");
            tipoUsuario = TipoUsuario.CLIENTE;
        }


        return new Usuario(nombre, email, tipoUsuario);
    }


    public void mostrarUsuarios(List<Usuario> usuarios){
        for (Usuario u : usuarios){
            System.out.println("Nombre: " + u.getNombre() + " | Email: " + u.getEmail() + " | Rol: " + u.getTipoUsuario().name());
        }
    }

}
