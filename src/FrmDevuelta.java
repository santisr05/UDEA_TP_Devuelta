import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class FrmDevuelta extends JFrame {

    private JComboBox cmbDenominacion;
    private int[] denominaciones = new int[]{100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50};

    public FrmDevuelta() {
        setTitle("Calular Devuelta");
        setSize(400, 400);
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
    }
}
