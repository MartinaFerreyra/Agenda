
package usuario_agenda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

public class boton_recuperar {
    private Principal principal; // Declaración de una variable principal de tipo Principal
    private Cuarto cuarto; // Declaración de una variable cuarto de tipo Cuarto
    private DBConexion dbConexion; // Declaración de una variable dbConexion de tipo DBConexion

    public boton_recuperar(Principal principal, DBConexion dbConexion) { // Constructor de la clase boton_recuperar
        this.principal = principal; // Asigna el parámetro principal al campo de la clase
        this.dbConexion = dbConexion; // Asigna el parámetro dbConexion al campo de la clase

        principal.recuperar.addActionListener(new ActionListener() { // Agrega un ActionListener al botón recuperar de la clase Principal
            @Override
            public void actionPerformed(ActionEvent e) { // Método que se ejecuta cuando se hace clic en el botón recuperar
                cuarto = new Cuarto(); // Crea una nueva instancia de la clase Cuarto
                cuarto.setVisible(true); // Hace visible el JFrame Cuarto

                // Agregar el listener para el botón "siguiente" dentro del JFrame "Cuarto"
                cuarto.siguiente.addActionListener(new ActionListener() { // Agrega un ActionListener al botón siguiente de Cuarto
                    @Override
                    public void actionPerformed(ActionEvent e) { // Método que se ejecuta cuando se hace clic en el botón siguiente
                        verificarEmail(cuarto); // Llama al método verificarEmail y le pasa la instancia de Cuarto
                    }
                });
            }
        });
    }

    private void verificarEmail(Cuarto cuarto) { // Método privado que verifica el correo electrónico
        String email = cuarto.texto.getText(); // Obtiene el correo electrónico ingresado desde el JTextField texto de Cuarto

        boolean usuarioExiste = dbConexion.verificarUsuarioPorEmail(email); // Verifica si existe un usuario con el correo electrónico proporcionado en la base de datos

        if (usuarioExiste) { // Si el usuario existe
            // El usuario existe, abrir el JFrame Form "Tercero"
            Tercera terceroFrame = new Tercera(); // Crea una nueva instancia de la clase Tercera
            terceroFrame.setVisible(true); // Hace visible el JFrame Tercera

            terceroFrame.jButton1.addActionListener(new ActionListener() { // Agrega un ActionListener al botón jButton1 de Tercera
                @Override
                public void actionPerformed(ActionEvent e) { // Método que se ejecuta cuando se hace clic en jButton1
                    String nuevaContraseña = new String(terceroFrame.contra_nueva.getPassword()); // Obtiene la nueva contraseña ingresada desde el campo contra_nueva
                    String confirmarContraseña = new String(terceroFrame.confirmar.getPassword()); // Obtiene la confirmación de la contraseña ingresada desde el campo confirmar

                    if (!nuevaContraseña.equals(confirmarContraseña)) { // Si las contraseñas no coinciden
                        JOptionPane.showMessageDialog(terceroFrame, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE); // Muestra un mensaje de error
                        return; // Sale del método actionPerformed
                    }
                    
                    System.out.println("Contraseña nueva (sin encriptar): " + nuevaContraseña);

                     // Aquí se encripta la contraseña antes de guardarla en la base de datos
                      String contraseñaEncriptada = encriptarMD5(nuevaContraseña);
                    System.out.println("Contraseña nueva (encriptada): " + contraseñaEncriptada);
                    
                    // Cambiar la contraseña del usuario en la base de datos
                    boolean cambioExitoso = dbConexion.cambiarContraseña(email, contraseñaEncriptada); // Intenta cambiar la contraseña en la base de datos
                    if (cambioExitoso) { // Si el cambio de contraseña fue exitoso
                        JOptionPane.showMessageDialog(terceroFrame, "Contraseña cambiada exitosamente"); // Muestra un mensaje de éxito
                        terceroFrame.dispose(); // Cierra la ventana de Tercera después de cambiar la contraseña
                    } else { // Si hubo un error al cambiar la contraseña en la base de datos
                        JOptionPane.showMessageDialog(terceroFrame, "Error al cambiar la contraseña", "Error", JOptionPane.ERROR_MESSAGE); // Muestra un mensaje de error
                    }
                }
            });
        } else { // Si el usuario no existe en la base de datos con el correo electrónico proporcionado
            // El usuario no existe con ese correo electrónico, mostrar un mensaje de error
            JOptionPane.showMessageDialog(cuarto, "El correo electrónico no está registrado.", "Error", JOptionPane.ERROR_MESSAGE); // Muestra un mensaje de error
        }
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