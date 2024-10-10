package usuario_agenda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

public class boton_login {

private final DBConexion dbConexion; //Declaración de una variable final para la conexión a la base de datos
private final Principal principal; //Declaración de una variable final para la interfaz principal

// Constructor de la clase boton_login que toma como parámetros una instancia de Principal y una de DBConexion
public boton_login(Principal principal, DBConexion dbConexion) {
    this.principal = principal; // Asigna la instancia de Principal al campo principal
    this.dbConexion = dbConexion; // Asigna la instancia de DBConexion al campo dbConexion

    // Añade un ActionListener al botón de login en la interfaz principal
    // Añade un ActionListener al botón de login en la interfaz principal
        principal.login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtiene el texto del campo de texto1 (nombre de usuario)
                String nombre = principal.texto1.getText();
                // Obtiene la contraseña como un array de caracteres del campo de contraseña texto2
                char[] contraseñaArray = principal.texto2.getPassword();
                // Convierte el array de caracteres en un String
                String contraseña = new String(contraseñaArray);

                // Verificar si los campos están vacíos
                if (nombre.isEmpty() || contraseña.isEmpty()) {
                    // Muestra un mensaje de error si los campos están vacíos
                    JOptionPane.showMessageDialog(principal, "Los campos están vacíos, llene los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Termina la ejecución del método si los campos están vacíos
                }

                Connection conn = null; // Inicializa una variable para la conexión a la base de datos
                try {
                    conn = dbConexion.getConnection(); // Intenta obtener una conexión a la base de datos
                    if (conn != null && !conn.isClosed()) {
                        // Define una consulta SQL para buscar en la tabla "usuarios" el nombre y la contraseña
                        String query = "SELECT * FROM usuarios WHERE nombre = ?";
                        // Prepara la consulta SQL
                        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                            // Establece el parámetro de la consulta SQL con el nombre proporcionado
                            pstmt.setString(1, nombre);
                            // Ejecuta la consulta y obtiene los resultados
                            try (ResultSet resultSet = pstmt.executeQuery()) {
                                if (resultSet.next()) {
                                    // Obtiene la contraseña encriptada almacenada en la base de datos
                                    String contraseñaEncriptadaDB = resultSet.getString("contraseña");
                                    // Encripta la contraseña ingresada para compararla con la almacenada
                                    String contraseñaEncriptada = encriptarMD5(contraseña);
                                    // Verifica si las contraseñas coinciden
                                    if (contraseñaEncriptadaDB.equals(contraseñaEncriptada)) {
                                        // Si las contraseñas coinciden, muestra un mensaje de inicio de sesión exitoso
                                        JOptionPane.showMessageDialog(principal, "Inicio de sesión exitoso");
                                        // Abre la agenda de usuarios
                                        openAgendaUsuarios();
                                    } else {
                                        // Si las contraseñas no coinciden, muestra un mensaje de contraseña incorrecta
                                        JOptionPane.showMessageDialog(principal, "Contraseña incorrecta, inténtelo de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    // Si no se encuentra ningún usuario con ese nombre, muestra un mensaje de error
                                    JOptionPane.showMessageDialog(principal, "Error, no existe un usuario con dicho nombre", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } else {
                        // Muestra un mensaje de error si no se puede establecer la conexión a la base de datos
                        JOptionPane.showMessageDialog(principal, "No se pudo establecer la conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    // Muestra un mensaje de error si ocurre una excepción SQL durante la consulta
                    JOptionPane.showMessageDialog(principal, "Error al realizar la consulta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace(); // Imprime la traza de la excepción
                } finally {
                    if (conn != null) {
                        try {
                            conn.close(); // Cierra la conexión a la base de datos
                        } catch (SQLException ex) {
                            ex.printStackTrace(); // Imprime la traza de la excepción si ocurre un error al cerrar la conexión
                        }
                    }
                }
            }
        });
    }

    // Método para abrir la ventana de la agenda de usuarios
    private void openAgendaUsuarios() {
        // Utiliza invokeLater para asegurarse de que el código se ejecute en el hilo de eventos de AWT
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agenda_Usuarios().setVisible(true); // Crea y muestra una nueva instancia de Agenda_Usuarios
            }
        });
    }

    // Método estático para validar si una contraseña cumple con ciertos requisitos de seguridad
    private static boolean validarContraseña(String contra) {
        if (contra.length() < 12) {
            return false; // La contraseña debe tener al menos 12 caracteres
        }
        // La contraseña debe tener al menos una mayúscula, un número y un carácter especial
        if (!contra.matches(".*[A-Z].*") // Al menos una mayúscula
                || !contra.matches(".*\\d.*") // Al menos un número
                || !contra.matches(".*[!@#$%^&*()-_=+\\\\|[{]};:'\",<.>/?].*")) { // Al menos un carácter especial
            return false; // Si no cumple con alguno de estos requisitos, retorna false
        }
        return true; // Si cumple con todos los requisitos, retorna true
    }

 // Método para encriptar usando MD5
private String encriptarMD5(String contraseña) {
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(contraseña.getBytes());
        byte[] digest = md.digest();
        String contraseñaEncriptada = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return contraseñaEncriptada;
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return null; // Manejo de error, en una aplicación real deberías manejar esto correctamente
    }
}
    }
