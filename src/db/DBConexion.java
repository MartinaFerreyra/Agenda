package usuario_agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class DBConexion {


    private Connection conect; // Variable para almacenar la conexión a la base de datos

    // Constructor que establece la conexión a la base de datos
    public DBConexion() {
        conectar(); // Establece la conexión al crear la instancia
    }

    // Método para establecer la conexión
    private void conectar() {
        String url = "jdbc:mysql://localhost:3306/login"; // URL de la base de datos
        String usuario = "root"; // Usuario de la base de datos
        String password = "11550"; // Contraseña de la base de datos

        try {
            conect = DriverManager.getConnection(url, usuario, password); // Intenta establecer la conexión
            System.out.println("Conexión exitosa"); // Mensaje de éxito
        } catch (SQLException ex) {
            System.out.println("Conexión fallida: " + ex.getMessage()); // Mensaje de error
            conect = null; // Asegúrate de que conect sea nulo si falla
        }
    }

    // Método para obtener la conexión a la base de datos
    public Connection getConnection() throws SQLException {
        // Verifica si la conexión es nula o está cerrada
        if (conect == null || conect.isClosed()) {
            conectar(); // Restablece la conexión si está cerrada
        }
        return conect; // Retorna la conexión
    }


    // Método para guardar un nuevo usuario en la base de datos
     // Método para guardar un nuevo usuario en la base de datos
    public boolean guardarUsuario(String nombre, String apellido, String dni, String email, String direccion, String localidad) {
        String query = "INSERT INTO agenda (nombre, apellido, dni, email, direccion, localidad) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, nombre); // Asigna el nombre al primer parámetro
            pst.setString(2, apellido); // Asigna el apellido al segundo parámetro
            pst.setString(3, dni); // Asigna el DNI al tercer parámetro
            pst.setString(4, email); // Asigna el email al cuarto parámetro
            pst.setString(5, direccion); // Asigna la dirección al quinto parámetro
            pst.setString(6, localidad); // Asigna la localidad al sexto parámetro

            int rowsAffected = pst.executeUpdate(); // Ejecuta la inserción
            return rowsAffected > 0; // Retorna true si se afectó al menos una fila
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime la pila de errores
            return false; // Retorna false en caso de error
        }
    }

    // Método para eliminar un usuario por su ID
    public boolean eliminarUsuario(int id) {
        String query = "DELETE FROM agenda WHERE id_usuario = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id); // Asigna el ID al primer parámetro
            int filasAfectadas = pst.executeUpdate(); // Ejecuta la eliminación
            reiniciarAutoIncrement(); // Ajusta el valor de AUTO_INCREMENT después de eliminar el usuario
            return filasAfectadas > 0; // Retorna true si se afectó al menos una fila
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime la pila de errores
            return false; // Retorna false en caso de error
        }
    }

    // Método para ajustar el valor de AUTO_INCREMENT
    public void reiniciarAutoIncrement() {
        String queryMaxId = "SELECT COALESCE(MAX(id_usuario), 0) + 1 FROM agenda";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(queryMaxId)) {
            if (rs.next()) {
                int maxId = rs.getInt(1); // Obtiene el próximo valor de AUTO_INCREMENT
                String queryResetAutoIncrement = "ALTER TABLE agenda AUTO_INCREMENT = " + maxId;
                st.executeUpdate(queryResetAutoIncrement); // Ajusta el valor de AUTO_INCREMENT
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime la pila de errores
        }
    }

    // Método para verificar si existe un usuario por DNI
public boolean existeUsuarioConDNI(String dni) {
    // Consulta SQL para contar cuántos registros existen con el DNI especificado
    String query = "SELECT COUNT(*) AS count FROM agenda WHERE dni = ?";
    try (
        // Establece la conexión a la base de datos
        Connection conn = getConnection();
        // Prepara la declaración SQL con la consulta
        PreparedStatement ps = conn.prepareStatement(query)
    ) {
        // Asigna el valor del DNI al parámetro de la consulta
        ps.setString(1, dni);
        // Ejecuta la consulta y obtiene el resultado
        ResultSet rs = ps.executeQuery();
        // Si hay un resultado, obtiene el recuento de registros
        if (rs.next()) {
            int count = rs.getInt("count");
            // Devuelve true si el recuento es mayor a 0 (existe al menos un usuario con ese DNI)
            return count > 0;
        }
    } catch (SQLException ex) {
        // Muestra un mensaje de error si ocurre una excepción SQL
        JOptionPane.showMessageDialog(null, "Error al verificar existencia de usuario por DNI: " + ex.getMessage());
    }
    // Devuelve false si ocurre un error o si no se encuentra ningún usuario con ese DNI
    return false;
}

// Método para verificar si existe un usuario por Email
public boolean existeUsuarioPorEmail(String email) {
    // Consulta SQL para contar cuántos registros existen con el email especificado
    String query = "SELECT COUNT(*) AS count FROM agenda WHERE email = ?";
    try (
        // Establece la conexión a la base de datos
        Connection conn = getConnection();
        // Prepara la declaración SQL con la consulta
        PreparedStatement ps = conn.prepareStatement(query)
    ) {
        // Asigna el valor del email al parámetro de la consulta
        ps.setString(1, email);
        // Ejecuta la consulta y obtiene el resultado
        ResultSet rs = ps.executeQuery();
        // Si hay un resultado, obtiene el recuento de registros
        if (rs.next()) {
            int count = rs.getInt("count");
            // Devuelve true si el recuento es mayor a 0 (existe al menos un usuario con ese email)
            return count > 0;
        }
    } catch (SQLException ex) {
        // Muestra un mensaje de error si ocurre una excepción SQL
        JOptionPane.showMessageDialog(null, "Error al verificar existencia de usuario por Email: " + ex.getMessage());
    }
    // Devuelve false si ocurre un error o si no se encuentra ningún usuario con ese email
    return false;
}

    // Método para verificar el nombre y contraseña del usuario
    public boolean conexion(String nombre, String contraseña) {
        String queryUsuario = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(queryUsuario)) {
            preparedStatement.setString(1, nombre); // Asigna el nombre al primer parámetro
            preparedStatement.setString(2, contraseña); // Asigna la contraseña al segundo parámetro
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Retorna true si encuentra un usuario con ese nombre y contraseña
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage()); // Mensaje de error
            return false; // Retorna false en caso de error
        }
    }

    // Método para verificar si existe un usuario con el email proporcionado
    public boolean verificarUsuarioPorEmail(String email) {
        String query = "SELECT * FROM usuarios WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, email); // Asigna el email al primer parámetro
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Retorna true si encuentra un usuario con ese email
            }
        } catch (SQLException ex) {
            System.out.println("Error al verificar usuario por email: " + ex.getMessage()); // Mensaje de error
            return false; // Retorna false en caso de error
        }
    }

    // Método para verificar si existe un usuario con el nombre o email proporcionados
    public boolean verificarUsuarioPorNombreYEmail(String nombre, String email) {
        String query = "SELECT * FROM usuarios WHERE nombre = ? OR email = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, nombre); // Asigna el nombre al primer parámetro
            preparedStatement.setString(2, email); // Asigna el email al segundo parámetro
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Retorna true si encuentra un usuario con ese nombre o email
            }
        } catch (SQLException ex) {
            System.out.println("Error al verificar usuario por nombre y email: " + ex.getMessage()); // Mensaje de error
            return false; // Retorna false en caso de error
        }
    }

    // Método para registrar un nuevo usuario
    public boolean registrarUsuario(String nombre, String contraseña, String email) {
        String query = "INSERT INTO usuarios (nombre, contraseña, email) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, nombre); // Asigna el nombre al primer parámetro
            preparedStatement.setString(2, contraseña); // Asigna la contraseña al segundo parámetro
            preparedStatement.setString(3, email); // Asigna el email al tercer parámetro
            int filasAfectadas = preparedStatement.executeUpdate(); // Ejecuta la inserción
            return filasAfectadas > 0; // Retorna true si se afectó al menos una fila
        } catch (SQLException ex) {
            System.out.println("Error al registrar usuario: " + ex.getMessage()); // Mensaje de error
            return false; // Retorna false en caso de error
        }
    }

    // Método para cambiar la contraseña de un usuario
    public boolean cambiarContraseña(String email, String nuevaContraseña) {
        String query = "UPDATE usuarios SET contraseña = ? WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, nuevaContraseña); // Asigna la nueva contraseña al primer parámetro
            preparedStatement.setString(2, email); // Asigna el email al segundo parámetro
            int filasAfectadas = preparedStatement.executeUpdate(); // Ejecuta la actualización
            return filasAfectadas > 0; // Retorna true si se afectó al menos una fila
        } catch (SQLException ex) {
            System.out.println("Error al cambiar la contraseña: " + ex.getMessage()); // Mensaje de error
            return false; // Retorna false en caso de error
        }
    }
    
    


    public void cerrarConexion() {
        if (conect != null) {
            try {
                conect.close(); // Cierra la conexión
                System.out.println("Conexión cerrada"); // Mensaje de cierre
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de excepciones
            }
        }
    }
}