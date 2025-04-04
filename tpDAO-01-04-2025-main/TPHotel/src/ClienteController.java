import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO;
    private ClienteView clienteView;

    public ClienteController(ClienteDAO clienteDAO, ClienteView clienteView) {
        this.clienteDAO = clienteDAO;
        this.clienteView = clienteView;
    }

    public void agregarCliente(){
        Cliente cliente = clienteView.solicitarDatosCliente();
        clienteDAO.agregarCliente(cliente);
    }

    public void listarClientes(){
        List<Cliente> clientes = clienteDAO.listaClientes();
        clienteView.mostrarClientes(clientes);
    }
}
