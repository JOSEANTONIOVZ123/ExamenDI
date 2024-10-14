import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ExamenDI extends JFrame {
    private final JTextArea correoElectronicoTextArea;
    private final JComboBox<String> paisComboBox;
    private final JComboBox<String> plataformaComboBox;
    private final JTable table1;
    private final JLabel mensajeLabel;

    public ExamenDI() {
        setTitle("Examen DI");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));  // Cambiar a 5 filas

        // Título
        JLabel titleLabel = new JLabel("Añadir Usuario", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));  // Ajustar el tamaño y estilo de fuente
        inputPanel.add(titleLabel);
        inputPanel.add(new JLabel());  // Espacio vacío en la cuadrícula

        correoElectronicoTextArea = new JTextArea(1, 20);
        paisComboBox = new JComboBox<>(new String[]{"España", "Francia", "Argentina", "Rusia", "Suiza"});
        plataformaComboBox = new JComboBox<>(new String[]{"Escritorio", "Móvil", "Desconocido"});

        inputPanel.add(new JLabel("Correo Electrónico:"));
        inputPanel.add(correoElectronicoTextArea);
        inputPanel.add(new JLabel("País:"));
        inputPanel.add(paisComboBox);
        inputPanel.add(new JLabel("Plataforma:"));
        inputPanel.add(plataformaComboBox);

        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton confirmarYGuardarButton = new JButton("Confirmar y Guardar");
        buttonPanel.add(confirmarYGuardarButton);
        buttonPanel.add(new JButton("Volver Atrás"));

        // Tabla para mostrar datos
        table1 = new JTable(new DefaultTableModel(new Object[]{"Correo", "País", "Plataforma"}, 0));
        JScrollPane tableScrollPane = new JScrollPane(table1);

        // Label para mostrar mensajes
        mensajeLabel = new JLabel();
        mensajeLabel.setForeground(Color.RED);

        // Añadir componentes a la ventana
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(mensajeLabel, BorderLayout.SOUTH);
        add(tableScrollPane, BorderLayout.EAST);

        // Acción del botón confirmar y guardar
        confirmarYGuardarButton.addActionListener(this::validarYGuardar);

        setVisible(true);
    }

    private void validarYGuardar(ActionEvent e) {
        String correo = correoElectronicoTextArea.getText().trim();
        String pais = (String) paisComboBox.getSelectedItem();
        String plataforma = (String) plataformaComboBox.getSelectedItem();

        if (correo.isEmpty() || pais.isEmpty() || plataforma.isEmpty()) {
            mensajeLabel.setText("Falta algún campo por completar.");
        } else {
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.addRow(new Object[]{correo, pais, plataforma});
            JOptionPane.showMessageDialog(this, "Usuario almacenado correctamente.\nCorreo: " + correo, "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        correoElectronicoTextArea.setText("");
        mensajeLabel.setText("");
        paisComboBox.setSelectedIndex(0);
        plataformaComboBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new ExamenDI();
    }
}
