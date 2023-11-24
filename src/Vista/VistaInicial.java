/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.TDA.Lista.Exepciones.EmptyList;
import Controlador.TDA.Lista.ListaDinamica;
import Modelo.Boleto;
import Modelo.Dao.DaoPasajero;
import Modelo.Pasajero;
import Vista.Modelo.ModeloTablaBoleto;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */
public class VistaInicial extends javax.swing.JFrame {
    
    ModeloTablaBoleto modelotabla = new ModeloTablaBoleto();
    DaoPasajero controlPasajeros = new DaoPasajero();
    ListaDinamica<Pasajero> ListaPasajero = new ListaDinamica<>();

    /**
     * Creates new form VistaPrincipal
     * @throws Controlador.TDA.Lista.Exepciones.EmptyList
     */
    public VistaInicial() throws EmptyList {
        initComponents();
        this.setLocationRelativeTo(null);
        CargarTabla();
    }
    
    private void CargarTabla() {
        modelotabla.setListaPasajero(controlPasajeros.getPasajerosLista());
        tblVentas.setModel(modelotabla);
        tblVentas.updateUI();
        cbxOrigen.setSelectedIndex(-1);
        cbxDestino.setSelectedIndex(-1);
        cbxHoraSalida.setSelectedIndex(-1);
        tblVentas.setModel(modelotabla);
        tblVentas.updateUI();
    }
    
    private Boolean Validar() {
        return (!txtNumeroDNI.getText().trim().isEmpty() && !txtNombre.getText().trim().isEmpty() && !txtApellido.getText().trim().isEmpty() && !txtCantidadBoletos.getText().trim().isEmpty() & !txtFechaBoletoSalida.getText().trim().isEmpty() && !txtPrecioFinal.getText().trim().isEmpty());
    }

    private void Limpiar() throws EmptyList {
        txtApellido.setText("");
        txtNombre.setText("");
        txtNumeroDNI.setText("");
        cbxOrigen.setSelectedIndex(-1);
        cbxDestino.setSelectedIndex(-1);
        cbxHoraSalida.setSelectedIndex(-1);
        txtPrecioFinal.setText("");
        txtFechaBoletoSalida.setText("");
        txtCantidadBoletos.setText("");
        controlPasajeros.setPasajero(null);
        CargarTabla();
    }
    

    private void Guardar() throws EmptyList, ParseException {

        if (txtNumeroDNI.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar numero dni", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar nombre", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar apellido", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxOrigen.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta seleccionar combo de origen", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxDestino.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta seleccionar combo de destino", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtCantidadBoletos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar cantidad boletos", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtFechaBoletoSalida.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta llenar fecha salida", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxHoraSalida.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta seleccionar combo hora salida", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            Integer IdPasajero = ListaPasajero.getLongitud() + 1;
            String Origen = cbxOrigen.getSelectedItem().toString();
            String Destino = cbxDestino.getSelectedItem().toString();
            Integer CantidadBoleto = Integer.valueOf(txtCantidadBoletos.getText());
            String FechaSalida = txtFechaBoletoSalida.getText();
            String HoraSalida = cbxHoraSalida.getSelectedItem().toString();
            Float precioFinal = Float.parseFloat(txtPrecioFinal.getText());

            Boleto DatosBoleto = new Boleto(IdPasajero, Origen, Destino, CantidadBoleto, FechaSalida, HoraSalida, precioFinal);

            controlPasajeros.getPasajero().setIdPasajero(IdPasajero);
            controlPasajeros.getPasajero().setNumeroIdentificacion(txtNumeroDNI.getText());
            controlPasajeros.getPasajero().setNombre(txtNombre.getText());
            controlPasajeros.getPasajero().setApellido(txtApellido.getText());
            controlPasajeros.getPasajero().setDatosBoleto(DatosBoleto);

            if (controlPasajeros.Persist()) {
                JOptionPane.showMessageDialog(null, "Boleto comprado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                CargarTabla();

                controlPasajeros.setPasajero(null);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo comprar", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        Limpiar();
    }

    private void CargarVista() {
        int fila = tblVentas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Escoga un registro");
        } else {
            try {
                controlPasajeros.setPasajero(modelotabla.getListaPasajero().getInfo(fila));

                txtNombre.setText(controlPasajeros.getPasajero().getNombre());
                txtApellido.setText(controlPasajeros.getPasajero().getApellido());
                txtNumeroDNI.setText(controlPasajeros.getPasajero().getNumeroIdentificacion());
                txtCantidadBoletos.setText(controlPasajeros.getPasajero().getDatosBoleto().getCantidadBoletos().toString());
                txtPrecioFinal.setText(controlPasajeros.getPasajero().getDatosBoleto().getTotalPagar().toString());
                txtFechaBoletoSalida.setText(controlPasajeros.getPasajero().getDatosBoleto().getFechaViaje());
                cbxOrigen.setSelectedItem(controlPasajeros.getPasajero().getDatosBoleto().getCiudadOrigen());
                cbxDestino.setSelectedItem(controlPasajeros.getPasajero().getDatosBoleto().getCiudadDestino());
                cbxHoraSalida.setSelectedItem(controlPasajeros.getPasajero().getDatosBoleto().getHoraViaje());
            } catch (EmptyList | IndexOutOfBoundsException e) {

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxOrigen = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbxDestino = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbxHoraSalida = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtPrecioFinal = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtCantidadBoletos = new javax.swing.JTextField();
        txtFechaBoletoSalida = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtNumeroDNI = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        btnComprarBoleto = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        btnSeleccinar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCalcularVentas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VENTA DE BOLETOS");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("VENTA DE BOLETOS");

        jPanel1.setToolTipText("");

        jLabel1.setText("Origen");

        cbxOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo", "Cotopaxi", "El Oro", "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos", "Manabí", "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha", "Santa Elena", "Santo Domingo de los Tsáchilas", "Sucumbíos", "Tungurahua", "Zamora-Chinchipe" }));

        jLabel3.setText("Destino");

        cbxDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo", "Cotopaxi", "El Oro", "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos", "Manabí", "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha", "Santa Elena", "Santo Domingo de los Tsáchilas", "Sucumbíos", "Tungurahua", "Zamora-Chinchipe" }));

        jLabel4.setText("Cantidad de boletos");

        jLabel5.setText("Fecha de salida");

        jLabel6.setText("Hora de salida");

        cbxHoraSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00" }));

        jLabel10.setText("Precio final");

        txtPrecioFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("DATOS  BOLETO");

        txtCantidadBoletos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtFechaBoletoSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFechaBoletoSalida)
                            .addComponent(txtCantidadBoletos)
                            .addComponent(cbxDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxHoraSalida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrecioFinal)
                            .addComponent(cbxOrigen, 0, 215, Short.MAX_VALUE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCantidadBoletos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFechaBoletoSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtPrecioFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("DATOS USUARIO");

        jLabel14.setText("Numero DNI");

        jLabel15.setText("Nombre");

        jLabel16.setText("Apellido");

        txtNumeroDNI.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtApellido.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(txtNumeroDNI)
                            .addComponent(txtApellido))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtNumeroDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnComprarBoleto.setText("ADQUIRIR BOLETO");
        btnComprarBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarBoletoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("REGISTRO DE BOLETOS");

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblVentas);

        btnSeleccinar.setText("SELECCIONAR");
        btnSeleccinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccinarActionPerformed(evt);
            }
        });

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnCalcularVentas.setText("TOTAL VENTAS");
        btnCalcularVentas.setToolTipText("");
        btnCalcularVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularVentasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnComprarBoleto)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCalcularVentas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSeleccinar)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnComprarBoleto)
                    .addComponent(btnSeleccinar)
                    .addComponent(btnModificar)
                    .addComponent(btnCalcularVentas))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnComprarBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarBoletoActionPerformed
        if (txtNumeroDNI.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta dni", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta nombre", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta apellido", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxOrigen.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta seleccionar origen", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxDestino.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta seleccionar destino", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtCantidadBoletos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta cantidad boletos", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (txtFechaBoletoSalida.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falta fecha salida", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (cbxHoraSalida.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Falta hora salida", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {

                Integer IdPersona = ListaPasajero.getLongitud() + 1;
                String Origen = cbxOrigen.getSelectedItem().toString();
                String Destino = cbxDestino.getSelectedItem().toString();
                Integer CantidadBoleto = Integer.parseInt(txtCantidadBoletos.getText());
                String FechaSalida = txtFechaBoletoSalida.getText();
                String HoraSalida = cbxHoraSalida.getSelectedItem().toString();
                Float precioFinal = Float.parseFloat(txtPrecioFinal.getText());

                Boleto BoletoPasajero = new Boleto(IdPersona, Origen, Destino, CantidadBoleto, FechaSalida, HoraSalida, precioFinal);

                String NumeroDNI = txtNumeroDNI.getText();
                String NombrePasajero = txtNombre.getText();
                String ApellidoPasajero = txtApellido.getText();

                Pasajero PasajeroGuardar = new Pasajero(IdPersona, NumeroDNI, NombrePasajero, ApellidoPasajero, BoletoPasajero);

                ListaPasajero.Agregar(PasajeroGuardar);

                Guardar();

            } catch (ParseException | EmptyList ex) {
            }
        }
    }//GEN-LAST:event_btnComprarBoletoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        int fila = tblVentas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Escoga un registro");
        } else {
            try {
                Integer IdPersona = ListaPasajero.getLongitud() + 1;
                String Origen = cbxOrigen.getSelectedItem().toString();
                String Destino = cbxDestino.getSelectedItem().toString();
                Integer CantidadBoleto = Integer.parseInt(txtCantidadBoletos.getText());
                String FechaSalida = txtFechaBoletoSalida.getText();
                String HoraSalida = cbxHoraSalida.getSelectedItem().toString();
                Float precioFinal = Float.parseFloat(txtPrecioFinal.getText());

                Boleto BoletoPasajero = new Boleto(IdPersona, Origen, Destino, CantidadBoleto, FechaSalida, HoraSalida, precioFinal);

                String NumeroDNI = txtNumeroDNI.getText();
                String NombrePasajero = txtNombre.getText();
                String ApellidoPasajero = txtApellido.getText();

                Pasajero PasajeroCambiar = new Pasajero(IdPersona, NumeroDNI, NombrePasajero, ApellidoPasajero, BoletoPasajero);

                controlPasajeros.Merge(PasajeroCambiar, fila);

                CargarTabla();
                Limpiar();
            } catch (EmptyList | NumberFormatException e) {

            }
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnSeleccinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccinarActionPerformed
        // TODO add your handling code here:
        CargarVista();
    }//GEN-LAST:event_btnSeleccinarActionPerformed

    private void btnCalcularVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularVentasActionPerformed
        // TODO add your handling code here:
        double sumaPrecioFinal = modelotabla.SumarTotalGanancias(9);
//        
        JOptionPane.showMessageDialog(null, "EL TOTAL VENDIDO ES: "+sumaPrecioFinal+"DOLARES");
        
    }//GEN-LAST:event_btnCalcularVentasActionPerformed

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
            java.util.logging.Logger.getLogger(VistaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            try {
                new VistaInicial().setVisible(true);
            } catch (EmptyList ex) {
                Logger.getLogger(VistaInicial.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcularVentas;
    private javax.swing.JButton btnComprarBoleto;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSeleccinar;
    private javax.swing.JComboBox<String> cbxDestino;
    private javax.swing.JComboBox<String> cbxHoraSalida;
    private javax.swing.JComboBox<String> cbxOrigen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCantidadBoletos;
    private javax.swing.JTextField txtFechaBoletoSalida;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumeroDNI;
    private javax.swing.JTextField txtPrecioFinal;
    // End of variables declaration//GEN-END:variables
}
