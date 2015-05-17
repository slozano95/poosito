import javax.swing.JApplet;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;


public class GUI extends JApplet {

	/**
	 * Create the applet.
	 */
	public GUI() {
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JPanel servicios = new JPanel();
		tabbedPane.addTab("Servicios", null, servicios, null);
		servicios.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		servicios.add(panel, BorderLayout.EAST);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		servicios.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel servicios_1 = new JPanel();
		panel_1.add(servicios_1, BorderLayout.NORTH);
		servicios_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Datos Basicos");
		servicios_1.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		servicios_1.add(label);
		
		JButton btnIngredientes = new JButton("Ingredientes");
		servicios_1.add(btnIngredientes);
		
		JButton btnPlatos = new JButton("Platos");
		servicios_1.add(btnPlatos);
		
		JPanel servicios_2 = new JPanel();
		panel_1.add(servicios_2, BorderLayout.CENTER);
		servicios_2.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblServicios = new JLabel("Servicios");
		servicios_2.add(lblServicios);
		
		JLabel label_2 = new JLabel("");
		servicios_2.add(label_2);
		
		JLabel label_1 = new JLabel("");
		servicios_2.add(label_1);
		
		JLabel label_3 = new JLabel("");
		servicios_2.add(label_3);
		
		JButton btnAgregarOrden = new JButton("Agregar Orden");
		servicios_2.add(btnAgregarOrden);
		
		JButton btnOrdenesDia = new JButton("Órdenes del Dia");
		servicios_2.add(btnOrdenesDia);
		
		JButton btnMenu = new JButton("Menu del Restaurante");
		servicios_2.add(btnMenu);
		
		JButton btnReportes = new JButton("Reportes");
		servicios_2.add(btnReportes);
		
		JPanel servicios_3 = new JPanel();
		panel_1.add(servicios_3, BorderLayout.SOUTH);
		servicios_3.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel label_4 = new JLabel("Respaldo");
		servicios_3.add(label_4);
		
		JButton btnNewButton = new JButton("New button");
		servicios_3.add(btnNewButton);

	}

}
