
package usuario_agenda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

public class boton_registrar {
    private Principal principal; // Variable para la instancia de la interfaz principal
    private DBConexion dbConexion; // Variable para la instancia de la conexión a la base de datos

    // Constructor de la clase boton_registrar que toma como parámetros Principal y DBConexion
    public boton_registrar(Principal principal, DBConexion dbConexion) {
        this.principal = principal; // Asigna la instancia de Principal a la variable principal
        this.dbConexion = dbConexion; // Asigna la instancia de DBConexion a la variable dbConexion

        // Añade un ActionListener al botón de registro en la interfaz principal
        principal.regist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crea una nueva instancia de la ventana de registro
                Segundo registro = new Segundo();
                registro.setVisible(true); // Hace visible la ventana de registro

                // Añade un ActionListener al botón de volver en la ventana de registro
                registro.volver.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        registro.setVisible(false); // Oculta la ventana de registro
                        principal.setVisible(true); // Muestra la ventana principal
                    }
                });

                // Añade un ActionListener al botón de registrar en la ventana de registro
                registro.registrarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtiene el texto de los campos de texto y contraseña en la ventana de registro
                        String nombre = registro.texto1.getText();
                        String email = registro.texto2.getText();
                        String contraseña = new String(registro.texto3.getPassword());
                        String confirmarContraseña = new String(registro.texto4.getPassword());

                        // Valida el email ingresado
                        if (!emailValido(email)) {
                            JOptionPane.showMessageDialog(registro, "El email debe contener '@' y 'gmail'. Inténtelo de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Termina la ejecución del método si el email no es válido
                        }

                        // Verifica si el campo de confirmación de contraseña está vacío
                        if (confirmarContraseña.isEmpty()) {
                            JOptionPane.showMessageDialog(registro, "Confirme su contraseña", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Termina la ejecución del método si el campo está vacío
                        }

                        // Verifica si la contraseña coincide con la confirmación de contraseña
                        if (!contraseña.equals(confirmarContraseña)) {
                            JOptionPane.showMessageDialog(registro, "La contraseña no coincide con la ingresada, inténtelo de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Termina la ejecución del método si las contraseñas no coinciden
                        }

                        // Valida si la contraseña cumple con los requisitos de seguridad
                        if (!validarContraseña(contraseña)) {
                            JOptionPane.showMessageDialog(registro, "Ingrese una contraseña que tenga una longitud de al menos 12 caracteres, al menos una mayúscula, al menos un número y un carácter especial", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Termina la ejecución del método si la contraseña no es válida
                        }

                        // Verifica si el usuario ya existe en la base de datos por nombre o email
                        if (dbConexion.verificarUsuarioPorNombreYEmail(nombre, email)) {
                            JOptionPane.showMessageDialog(registro, "Error, usuario ya existente", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Termina la ejecución del método si el usuario ya existe
                        }

                   String contraseñaEncriptada = encriptarMD5(contraseña);
System.out.println("Contraseña nueva (encriptada): " + contraseñaEncriptada);
                   // Registra el usuario en la base de datos utilizando la contraseña encriptada

                        // Registra el usuario en la base de datos
                      boolean exito = dbConexion.registrarUsuario(nombre, contraseñaEncriptada, email);;
                        if (exito) {
                            JOptionPane.showMessageDialog(registro, "Usuario registrado con éxito");
                            registro.setVisible(false); // Oculta la ventana de registro
                            principal.setVisible(true); // Muestra la ventana principal
                        } else {
                            JOptionPane.showMessageDialog(registro, "Error al registrar el usuario");
                        }
                    }
                });
            }
        });
    }

    // Método estático para validar el formato del email
    private static boolean emailValido(String email) {
        return email.contains("@") && email.contains("gmail.com"); // Verifica si el email contiene '@' y 'gmail.com'
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
     // Método para encriptar una cadena utilizando MD5
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
