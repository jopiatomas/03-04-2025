import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = ConexionDB.getInstancia().getConexion();
    }

    public void agregarUsuario(Usuario usuario){
        String sql = "INSERT INTO Usuario (nombre, email, rol) VALUES (?,?,?);";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTipoUsuario().name());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Usuario> listarUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario;";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            while(resultSet.next()){
                usuarios.add(new Usuario(resultSet.getString("nombre"), resultSet.getString("email"), TipoUsuario.valueOf(resultSet.getString("rol"))));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return usuarios;
    }

}
