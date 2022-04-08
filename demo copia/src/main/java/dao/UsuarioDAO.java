package dao;

import models.Usuario;
import org.springframework.stereotype.Component;
import services.BDConector;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioDAO {
    private final BDConector conector;

    public UsuarioDAO(BDConector conector) {
        this.conector = conector;
    }
    //GET all
    public List<Usuario> obtenerUsuarios(){
        List<Usuario> usuarios=new ArrayList();
        Statement statement;
        conector.Conectar();
        try{
            String sentenciaSQL = "Select * from usuario";
            statement= conector.getCon().createStatement();
            ResultSet rs= statement.executeQuery(sentenciaSQL);
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad")
                ));
            }
        } catch (SQLException e) {
            String mensaje=e.getMessage();
        }
        conector.Desconectar();

        return usuarios;
    }
}
