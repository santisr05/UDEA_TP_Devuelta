import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmDevuelta extends JFrame {

    private int MAXIMO_VALOR_MONEDA = 1000;

    private JComboBox cmbDenominacion;
    private JTextField txtExistencia, txtDevuelta;
    private JTable tblDevuelta;

    private int[] denominaciones = new int[] { 100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50 };
    private int[] existencias = new int[denominaciones.length];

    private String[] encabezados = new String[] { "Cantidad", "Presentación", "Denominación" };

    // Constructor
    public FrmDevuelta() {
        setTitle("Calular Devuelta");
        setSize(500, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblDenominacion = new JLabel("Denominacion");
        lblDenominacion.setBounds(10, 10, 100, 25);
        getContentPane().add(lblDenominacion);

        cmbDenominacion = new JComboBox();
        for (int denominacion : denominaciones) {
            cmbDenominacion.addItem(denominacion);
        }
        cmbDenominacion.setBounds(110, 10, 100, 25);
        getContentPane().add(cmbDenominacion);

        JButton btnActualizarExistencia = new JButton("Actualizar Existencia");
        btnActualizarExistencia.setBounds(10, 40, 170, 25);
        getContentPane().add(btnActualizarExistencia);

        txtExistencia = new JTextField();
        txtExistencia.setBounds(180, 40, 100, 25);
        getContentPane().add(txtExistencia);

        JLabel lblDevuelta = new JLabel("Valor a devolver");
        lblDevuelta.setBounds(10, 70, 100, 25);
        getContentPane().add(lblDevuelta);

        txtDevuelta = new JTextField();
        txtDevuelta.setBounds(110, 70, 100, 25);
        getContentPane().add(txtDevuelta);

        JButton btnDevuelta = new JButton("Devolver");
        btnDevuelta.setBounds(210, 70, 100, 25);
        getContentPane().add(btnDevuelta);

        tblDevuelta = new JTable();
        JScrollPane spDevuelta = new JScrollPane(tblDevuelta);
        spDevuelta.setBounds(10, 100, 450, 150);
        getContentPane().add(spDevuelta);

        DefaultTableModel dtm = new DefaultTableModel(null, encabezados);
        tblDevuelta.setModel(dtm);

        // Agregar eventos
        btnActualizarExistencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarExistencia();
            }
        });

        cmbDenominacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarExistencia();
            }
        });

        btnDevuelta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularDevuelta();
            }
        });
    }

    private void actualizarExistencia() {
        if (cmbDenominacion.getSelectedIndex() <= 0) {
            existencias[cmbDenominacion.getSelectedIndex()] = Integer.parseInt(txtExistencia.getText());
        }
    }

    private void mostrarExistencia() {
        if (cmbDenominacion.getSelectedIndex() <= 0) {
            txtExistencia.setText(String.valueOf(existencias[cmbDenominacion.getSelectedIndex()]));
        }
    }

    private void calcularDevuelta() {
        int[] cantidades = new int[denominaciones.length];
        int devuelta = Integer.parseInt(txtDevuelta.getText());
        int filaDenominacion = 0
        
        while (devuelta > 0 && filaDenominacion < denominaciones.length) {
            int cantidadNecesaria = (devuelta
                    - devuelta % denominaciones[filaDenominacion] )/ denominaciones[filaDenominacion];
            //int cantidadNecesaria = int (devuelta / denominaciones[filaDenominacion]);
            cantidades[filaDenominacion] = existencias[filaDenominacion] >= cantidadNecesaria ? cantidadNecesaria
                    : existencias[filaDenominacion];
            
            devuelta -= cantidades[filaDenominacion] * denominaciones[filaDenominacion];

            filaDenominacion++;

        }

        int filasNecesarias = 0;
        for (int cantidad : cantidades) {
            if (cantidad > 0) {
                filasNecesarias++;
            }
        }
        String[][] datos = new String[filasNecesarias][encabezados.length];
        filasNecesarias = 0;
        for (int i = 0;i<denominaciones.length;i++) {
            if (cantidades[i] > 0) {
                datos[filasNecesarias][0] = String.valueOf(cantidades[i]);
                datos[filasNecesarias][1] = String.valueOf(denominaciones[i] > MAXIMO_VALOR_MONEDA ? "Billetes" : "Monedas");
                datos[filasNecesarias][2] = String.valueOf(denominaciones[i]);
                filasNecesarias++;
            }
        }

        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tblDevuelta.setModel(dtm);

        if (devuelta>0) {
            JOptionPane.showMessageDialog(null, "Quedo pendiente devolver: " + devuelta);
        }
    }
}
