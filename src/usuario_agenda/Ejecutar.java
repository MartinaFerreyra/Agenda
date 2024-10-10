package usuario_agenda;

public class Ejecutar {

    public static void main(String[] args) {
        // Crear una instancia de la clase Principal que puede ser la ventana principal de la aplicación
        Principal principal = new Principal();
        // Crear una instancia de la clase DBConexion que maneja la conexión a la base de datos
        DBConexion dbConexion = new DBConexion();
        // Crear una instancia del botón de login y pasarle la ventana principal y la conexión a la base de datos
        new boton_login(principal, dbConexion);
        // Crear una instancia del botón de registrar y pasarle la ventana principal y la conexión a la base de datos
        new boton_registrar(principal, dbConexion);
        // Crear una instancia del botón de recuperar y pasarle la ventana principal y la conexión a la base de datos
        new boton_recuperar(principal, dbConexion);
        // Hacer visible la ventana principal
        principal.setVisible(true);
    }
}
