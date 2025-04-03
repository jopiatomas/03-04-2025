import java.util.List;

public class UsuarioController {

    UsuariosView usuariosView;
    UsuarioDAO usuarioDAO;

    public UsuarioController(UsuariosView usuariosView, UsuarioDAO usuarioDAO) {
        this.usuariosView = usuariosView;
        this.usuarioDAO = usuarioDAO;
    }

    public void agregarUsuario(){
        Usuario usuario = usuariosView.solicitarDatosUsuario();
        usuarioDAO.agregarUsuario(usuario);
    }

    public void listarUsuarios(){
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        usuariosView.mostrarUsuarios(usuarios);
    }
}
