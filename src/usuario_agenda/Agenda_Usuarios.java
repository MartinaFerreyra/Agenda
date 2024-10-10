
package usuario_agenda;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import usuario_agenda.DBConexion;


public class Agenda_Usuarios extends javax.swing.JFrame {
//Un TableModel define los datos que se muestran en una JTable, y DefaultTableModel es una de las formas más comunes de crear y gestionar estos datos.
DefaultTableModel model;
DBConexion dbConexion;

/**

Creates new form Agenda_Usuarios
*/
public Agenda_Usuarios() {
initComponents();  // Inicializa los componentes de la interfaz gráfica. Este método suele ser generado automáticamente por herramientas de diseño gráfico.
setLocationRelativeTo(null);
Desabilitar();            // Llama a un método que deshabilita ciertos componentes de la interfaz, impidiendo su uso hasta que se cumplan ciertas condiciones.
dbConexion = new DBConexion();// Crea una nueva instancia de la clase DBConexion, que probablemente maneja la conexión a la base de datos.
LlenarTabla();// Llama a un método que llena la JTable con datos de la base de datos.
configurarActionListeners();// Configura los ActionListeners para los componentes de la interfaz
}
public void Desabilitar(){
nombre.setEnabled(false);
apellido.setEnabled(false);
dni.setEnabled(false);
email.setEnabled(false);
direccion.setEnabled(false);
localidad.setEnabled(false);

}

public void Habilitar(){
nombre.setEnabled(true);
apellido.setEnabled(true);
dni.setEnabled(true);
email.setEnabled(true);
direccion.setEnabled(true);
localidad.setEnabled(true);
nombre.requestFocus();

}
void LlenarTabla() {
String[] titulos = {"ID", "Nombre", "Apellido", "DNI", "Email", "Direccion", "Localidad"};
model = new DefaultTableModel(null, titulos);// Crea un DefaultTableModel sin datos iniciales, pero con los títulos de las columnas especificados en 'titulos'
jTable1.setModel(model);// Establece el DefaultTableModel 'model' como el modelo de datos de jTable1
String query = "SELECT * FROM agenda";
try (Connection conn = dbConexion.getConnection();
Statement st = conn.createStatement();
ResultSet rs = st.executeQuery(query)) {
while (rs.next()) {
String[] fila = new String[7];
fila[0] = rs.getString("id_usuario");
fila[1] = rs.getString("nombre");
fila[2] = rs.getString("apellido");
fila[3] = rs.getString("dni");
fila[4] = rs.getString("email");
fila[5] = rs.getString("direccion");
fila[6] = rs.getString("localidad");
model.addRow(fila);
}
} catch (SQLException ex) {
JOptionPane.showMessageDialog(null, "Error al llenar la tabla: " + ex.getMessage());
}
}

void ordenarPorNombre() {
String[] titulos = {"ID", "Nombre", "Apellido", "DNI", "Email", "Direccion", "Localidad"};
model = new DefaultTableModel(null, titulos);
jTable1.setModel(model);
String query = "SELECT * FROM agenda ORDER BY nombre ASC";
try (Connection conn = dbConexion.getConnection();
Statement st = conn.createStatement();
ResultSet rs = st.executeQuery(query)) {
while (rs.next()) {
String[] fila = new String[7];
fila[0] = rs.getString("id_usuario");
fila[1] = rs.getString("nombre");
fila[2] = rs.getString("apellido");
fila[3] = rs.getString("dni");
fila[4] = rs.getString("email");
fila[5] = rs.getString("direccion");
fila[6] = rs.getString("localidad");
model.addRow(fila);// Añade una nueva fila a 'model' con los datos especificados en 'fila'
}
} catch (SQLException ex) {
JOptionPane.showMessageDialog(null, "Error al ordenar la tabla por nombre: " + ex.getMessage());
}
}

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dni = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        localidad = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        guardar = new javax.swing.JButton();
        listar = new javax.swing.JButton();
        nuevo = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        Historial = new javax.swing.JButton();

        jScrollPane2.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Agenda de usuarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel2.setText("Apellido:");

        apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel3.setText("DNI:");

        dni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dniActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel4.setText("Email:");

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel5.setText("Direccion:");

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel6.setText("Localidad:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(apellido))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dni, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(email)
                            .addComponent(direccion, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(localidad)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(localidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido", "DNI", "Email", "Direccion", "Localidad"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        listar.setText("Listar");
        listar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarActionPerformed(evt);
            }
        });

        nuevo.setText("Nuevo");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });

        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        actualizar.setText("Actualizar");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        Historial.setText("Historial");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(guardar)
                        .addGap(48, 48, 48)
                        .addComponent(eliminar)
                        .addGap(52, 52, 52)
                        .addComponent(listar)
                        .addGap(38, 38, 38)
                        .addComponent(actualizar)
                        .addGap(90, 90, 90)
                        .addComponent(Historial))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actualizar)
                    .addComponent(listar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminar)
                    .addComponent(guardar)
                    .addComponent(nuevo)
                    .addComponent(Historial))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configurarActionListeners() {
        //El método configurarActionListeners agrega ActionListener a los botones eliminar y listar.
eliminar.addActionListener(new java.awt.event.ActionListener() {
    // Define el método a ejecutar cuando se acciona el botón 'eliminar'
public void actionPerformed(java.awt.event.ActionEvent evt) {
eliminarActionPerformed(evt);
}
});

listar.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
listarActionPerformed(evt);
}
});
}

     
    private void apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoActionPerformed

    private void listarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarActionPerformed
 ordenarPorNombre();
    
    }//GEN-LAST:event_listarActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
 
      limpiarCampos();
      Habilitar();
    }//GEN-LAST:event_nuevoActionPerformed
private void limpiarCampos() {
        nombre.setText("");
        apellido.setText("");
        dni.setText("");
        email.setText("");
        direccion.setText("");
        localidad.setText("");
    }
    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
          try {
        String nombreText = nombre.getText();
        String apellidoText = apellido.getText();
        String dniText = dni.getText();
      String emailText = email.getText().trim().toLowerCase();// Limpiar espacios y convertir a minúsculas
        String direccionText = direccion.getText();
        String localidadText = localidad.getText();

     
    // Verificar que todos los campos estén llenos
    if (nombreText.isEmpty() || apellidoText.isEmpty() || dniText.isEmpty() || emailText.isEmpty() || direccionText.isEmpty() || localidadText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Verificar que el DNI contenga solo números
    if (!dniText.matches("\\d+")) {
        JOptionPane.showMessageDialog(null, "El DNI debe contener solo números. Inténtelo de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Verificar duplicado de DNI
    try {
        if (dbConexion.existeUsuarioConDNI(dniText)) {
            JOptionPane.showMessageDialog(null, "Error, ya existe un usuario con el mismo DNI", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al verificar DNI en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!emailText.contains("@") || !emailText.contains("gmail")) {
        JOptionPane.showMessageDialog(null, "El email debe contener '@' y la palabra 'gmail'. Inténtelo de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    // Verificar duplicado de email
    try {
        if (dbConexion.existeUsuarioPorEmail(emailText)) {
            JOptionPane.showMessageDialog(null, "Error, ya existe un usuario con el mismo email", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al verificar email en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Guardar el usuario si no hay duplicados y el email tiene el formato correcto
    try {
        boolean guardado = dbConexion.guardarUsuario(nombreText, apellidoText, dniText, emailText, direccionText, localidadText);

        if (guardado) {
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
            LlenarTabla();  // Actualiza la tabla después de guardar
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al guardar usuario en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
          

    }//GEN-LAST:event_guardarActionPerformed

boolean mensajeMostrado = false;
    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
       int filaSeleccionada = jTable1.getSelectedRow();
    if (filaSeleccionada == -1) {
        if (!mensajeMostrado) {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario para eliminar");
            mensajeMostrado = true;
        }
        return;
    }

    // Restablecer el flag si se ha seleccionado una fila
    mensajeMostrado = false;

    int id = Integer.parseInt(jTable1.getValueAt(filaSeleccionada, 0).toString());

    // Mostrar el mensaje de confirmación de eliminación
    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar el usuario seleccionado?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
    
    if (confirmacion == JOptionPane.YES_OPTION) {
        // Si el usuario confirma la eliminación
        boolean eliminado = dbConexion.eliminarUsuario(id);
        if (eliminado) {
            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
            LlenarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario");
        }
    }

    }//GEN-LAST:event_eliminarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
  if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
        // Obtiene la fila seleccionada en la tabla
int fila = jTable1.getSelectedRow();
// Habilita ciertos componentes o funciones (asumimos que es un método definido en tu clase)
Habilitar();
try {
String SQL = "SELECT * FROM agenda WHERE id_usuario = " + jTable1.getValueAt(fila, 0);
Connection conn = dbConexion.getConnection();
Statement sent = conn.createStatement();
ResultSet rs = sent.executeQuery(SQL);
 // Si hay un resultado, asigna los valores obtenidos a los campos correspondientes
if (rs.next()) {
nombre.setText(rs.getString("nombre"));
apellido.setText(rs.getString("apellido"));
dni.setText(rs.getString("dni"));
email.setText(rs.getString("email"));
direccion.setText(rs.getString("direccion"));
localidad.setText(rs.getString("localidad"));
}
} catch (SQLException e) {
}
}

    

    }//GEN-LAST:event_jTable1MouseClicked
private void registrarHistorial(int idUsuario, String campoModificado, String valorAnterior, String valorNuevo) {
        String sql = "INSERT INTO historial_modificaciones (id_usuario, campo_modificado, valor_anterior, valor_nuevo) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = dbConexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setString(2, campoModificado);
            ps.setString(3, valorAnterior);
            ps.setString(4, valorNuevo);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el historial: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
     int fila = jTable1.getSelectedRow();
if (fila == -1) {
JOptionPane.showMessageDialog(null, "Seleccione un usuario para actualizar");
return;
}

 int id = Integer.parseInt(jTable1.getValueAt(fila, 0).toString());
 String nombreText = nombre.getText();
 String apellidoText = apellido.getText();
 String dniText = dni.getText();
 String emailText = email.getText();
 String direccionText = direccion.getText();
 String localidadText = localidad.getText();

 // Obtener valores anteriores
        String valorAnteriorNombre = jTable1.getValueAt(fila, 1).toString();
        String valorAnteriorApellido = jTable1.getValueAt(fila, 2).toString();
        String valorAnteriorDNI = jTable1.getValueAt(fila, 3).toString();
        String valorAnteriorEmail = jTable1.getValueAt(fila, 4).toString();
        String valorAnteriorDireccion = jTable1.getValueAt(fila, 5).toString();
        String valorAnteriorLocalidad = jTable1.getValueAt(fila, 6).toString();
 try {
     String SQL = "UPDATE agenda SET nombre = ?, apellido = ?, dni = ?, email = ?, direccion = ?, localidad = ? WHERE id_usuario = ?";
     Connection conn = dbConexion.getConnection();
     PreparedStatement ps = conn.prepareStatement(SQL);
     ps.setString(1, nombreText);
     ps.setString(2, apellidoText);
     ps.setString(3, dniText);
     ps.setString(4, emailText);
     ps.setString(5, direccionText);
     ps.setString(6, localidadText);
     ps.setInt(7, id);

    int n = ps.executeUpdate();
            if (n > 0) {
                // Registrar cambios en el historial
                if (!valorAnteriorNombre.equals(nombreText)) {
                    registrarHistorial(id, "nombre", valorAnteriorNombre, nombreText);
                }
                if (!valorAnteriorApellido.equals(apellidoText)) {
                    registrarHistorial(id, "apellido", valorAnteriorApellido, apellidoText);
                }
                if (!valorAnteriorDNI.equals(dniText)) {
                    registrarHistorial(id, "dni", valorAnteriorDNI, dniText);
                }
                if (!valorAnteriorEmail.equals(emailText)) {
                    registrarHistorial(id, "email", valorAnteriorEmail, emailText);
                }
                if (!valorAnteriorDireccion.equals(direccionText)) {
                    registrarHistorial(id, "direccion", valorAnteriorDireccion, direccionText);
                }
                if (!valorAnteriorLocalidad.equals(localidadText)) {
                    registrarHistorial(id, "localidad", valorAnteriorLocalidad, localidadText);
                }

                LlenarTabla(); // Método para actualizar la tabla después de la modificación.
                JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

    }//GEN-LAST:event_actualizarActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void dniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dniActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Agenda_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agenda_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agenda_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agenda_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              new Agenda_Usuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Historial;
    public javax.swing.JButton actualizar;
    public javax.swing.JTextField apellido;
    public javax.swing.JTextField direccion;
    public javax.swing.JTextField dni;
    public javax.swing.JButton eliminar;
    public javax.swing.JTextField email;
    public javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane1;
    public javax.swing.JButton listar;
    public javax.swing.JTextField localidad;
    public javax.swing.JTextField nombre;
    public javax.swing.JButton nuevo;
    // End of variables declaration//GEN-END:variables

    

   
    
}
