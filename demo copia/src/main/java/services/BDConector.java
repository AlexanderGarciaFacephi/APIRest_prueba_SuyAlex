package services;

import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class BDConector {
    static Connection con = null;
    static String mensaje = "";

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        BDConector.con = con;
    }

    public static void setMensaje(String mensaje) {
        BDConector.mensaje = mensaje;
    }

    public static void Conectar() {
        try {
            if ((con == null) || (!con.isValid(0))) {
                System.out.println("Intentando conectar");

                // Driver de Sql Server
                Class.forName("com.mysql.jdbc.Driver");

                // ************************************
                // LUEGO HABRÁ QUE CAMBIAR PARA AZURE
                // Create a variable for the connection string.
                String hostName = "LT145.local"; //Alex
                //String hostName = "LT230.home"; //Su
                String port 	= "3306";
                String dbName 	= "prueba";
                String user 	= "root";
                String password = "123456";
                // ************************************

                String url ="jdbc:mysql://" + hostName + ":" + port + "/" + dbName;
                con = DriverManager.getConnection(url,user, password);
                /*String connectionUrl = String.format("jdbc:mysql://%s:%s;database=%s;user=%s;password=%s;"
                                + "loginTimeout=30;",
                        hostName, port, dbName, user, password);
*/
                //con = DriverManager.getConnection(connectionUrl);

                if (con.isValid(10)) {
                    System.out.println("Conectado");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BDConector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Desconectar() {
        try {
            // Cerrar conexión
            System.out.println("Intentando Desconectar");
            con.close();
            if (con.isValid(0)) {
                System.out.println("Desconectado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDConector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getMensaje() {
        return mensaje;
    }
}
