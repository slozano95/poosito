import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import co.edu.javeriana.restaurante.negocio.Ingrediente;
import co.edu.javeriana.restaurante.negocio.IngredientePlato;
import co.edu.javeriana.restaurante.negocio.Plato;
import co.edu.javeriana.restaurante.negocio.PlatoCarta;
import co.edu.javeriana.restaurante.negocio.Restaurante;
import co.edu.javeriana.restaurante.persistencia.ManejadorArchivos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TestGUIrestaurante extends JFrame {

	private JPanel contentPane;
	private Restaurante rest;
	private String[] columnasIngredientes = {"Codigo","Nombre","Precio Unitario","Descripción","Inventario","Mínimo"};
	private JTable tablaIngredientes;
	private JTable tablaPlatos;
	private String[] columnasPlatos = {"Codigo","Nombre","Tipo","Dia"};
	private String[] columnasIngredientesPlatos = {"Codigo","Nombre","Precio Unitario","Cantidad"};
	private String[] columnasIngredientesPlatos2 = {"Codigo","Nombre","Precio Unitario","Cantidad","Selección"};
	private JTable tablaIngredientesPlatos;
	private final JScrollPane scrollPane_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUIrestaurante frame = new TestGUIrestaurante();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestGUIrestaurante() {
		setMinimumSize(new Dimension(800, 700));
		setSize(new Dimension(800, 700));
		setPreferredSize(new Dimension(800, 700));
		getContentPane().setSize(new Dimension(800, 600));
		getContentPane().setPreferredSize(new Dimension(800, 600));
		getContentPane().setMinimumSize(new Dimension(800, 600));
		rest = new Restaurante();

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.NORTH);

		JPanel servicios = new JPanel();
		tabbedPane.addTab("Servicios", null, servicios, null);
		servicios.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		servicios.add(panel, BorderLayout.EAST);

		JLabel lblNewLabel_1 = new JLabel("New label");
		panel.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		servicios.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel servicios_1 = new JPanel();
		panel_1.add(servicios_1);
		servicios_1.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("Datos Basicos");
		servicios_1.add(lblNewLabel);

		JLabel label = new JLabel("");
		servicios_1.add(label);

		JButton btnIngredientes = new JButton("Ingredientes");
		btnIngredientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		servicios_1.add(btnIngredientes);

		JButton btnPlatos = new JButton("Platos");
		servicios_1.add(btnPlatos);

		JPanel servicios_2 = new JPanel();
		panel_1.add(servicios_2);
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
		panel_1.add(servicios_3);
		servicios_3.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel label_4 = new JLabel("Respaldo");
		servicios_3.add(label_4);

		JButton btnGCSistema = new JButton("Guardar/Cargar Sistema");
		btnGCSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		servicios_3.add(btnGCSistema);

		JPanel ingredientes = new JPanel();
		ingredientes.setBorder(new EmptyBorder(20, 20, 20, 20));
		tabbedPane.addTab("Ingredientes", null, ingredientes, null);
		ingredientes.setLayout(new BorderLayout(0, 0));

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(getDatosIngredientes(false));
		scrollPane.setSize(100,200);
		scrollPane.setPreferredSize(new Dimension(500,200));
		scrollPane.setMaximumSize(new Dimension(100, 200));

		ingredientes.add(scrollPane);
		JPanel panel_3 = new JPanel();
		ingredientes.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(2, 3, 0, 0));

		JButton btnNewButton_1 = new JButton("Cargar Ingredientes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser(".\\.");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPG & GIF Images", "jpg", "jpeg", "gif");
				int returnVal = chooser.showOpenDialog(getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					rest.setLIngredientes(ManejadorArchivos.LeerIngredientes(chooser.getSelectedFile().getAbsolutePath()));
					scrollPane.setViewportView(getDatosIngredientes(false));
				}
				else{
				}
			}
		});
		panel_3.add(btnNewButton_1);

		final JButton btnNewButton_2 = new JButton("Almacenar Ingredientes");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = tablaIngredientes.getSelectedRow();
				int selectedColumnIndex = tablaIngredientes.getSelectedColumn();
				if(selectedColumnIndex==-1 || selectedRowIndex==-1){
					JOptionPane.showMessageDialog(null, "Por favor seleccione la nueva fila","Advertencia", JOptionPane.WARNING_MESSAGE);

				}else{
					Ingrediente ing= new Ingrediente();
					boolean pasa=true;
					System.out.println(selectedRowIndex);
					ing.setCodigo((int) tablaIngredientes.getModel().getValueAt(selectedRowIndex, 0));
					for(int i=1;i<6;i++){
						String selectedObject = (String) tablaIngredientes.getModel().getValueAt(selectedRowIndex, i);
						if(selectedObject.equals("")){
							JOptionPane.showMessageDialog(null, "Recuerde llenar todos los campos y dar enter", "Advertencia", JOptionPane.WARNING_MESSAGE);
							pasa=false;
							break;
						}
						switch(i){
						case 1:
							ing.setNombre(selectedObject);
							break;
						case 2:
							ing.setPrecioUnitario(Long.parseLong(selectedObject));
							break;
						case 3:
							ing.setDescripcionUnidad(selectedObject);
							break;
						case 4:
							ing.setInventario(Integer.parseInt(selectedObject));
							break;
						case 5:
							System.out.println("Minimo: "+selectedObject);
							ing.setMinimo(Integer.parseInt(selectedObject));
							break;
						}
					}
					if(pasa){
						rest.getLIngredientes().add(ing);
						JOptionPane.showMessageDialog(null, "El Ingrediente ha sido agregado\n\n Utilice el boton \"Cargar Ingredientes\" para recargar la tabla","Guardado", JOptionPane.PLAIN_MESSAGE);
						//TODO ver como cargar ingredientes
						scrollPane.setViewportView(getDatosIngredientes(false));
						System.out.println(ing);
					}
				}
			}			
		});
		btnNewButton_2.setEnabled(false);

		JButton btnNewButton = new JButton("Nuevo Ingrediente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setViewportView(getDatosIngredientes(true));
				btnNewButton_2.setEnabled(true);
			}
		});
		panel_3.add(btnNewButton);


		panel_3.add(btnNewButton_2);

		JLabel lblNewLabel_2 = new JLabel("");
		panel_3.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		panel_3.add(lblNewLabel_3);

		JButton btnNewButton_3 = new JButton("Regresar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		panel_3.add(btnNewButton_3);

		JLabel lblIngredientes = new JLabel("Ingredientes");
		ingredientes.add(lblIngredientes, BorderLayout.NORTH);

		JPanel platos = new JPanel();
		tabbedPane.addTab("Platos", null, platos, null);
		platos.setLayout(new BorderLayout(0, 0));


		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(0, 20, 20, 20));
		platos.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new GridLayout(2, 3, 0, 0));
		final JScrollPane scrollPane_1 = new JScrollPane();
		JButton btnCargarPlatos = new JButton("Cargar Platos");
		btnCargarPlatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser(".\\.");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPG & GIF Images", "jpg", "jpeg", "gif");
				int returnVal = chooser.showOpenDialog(getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					rest.setLPlatos((HashMap<Integer, Plato>) ManejadorArchivos.LeerPlatos(chooser.getSelectedFile().getAbsolutePath()));
					scrollPane_1.setViewportView(getDatosPlatos(false));
					Dimension d = tablaPlatos.getPreferredSize();
					scrollPane_1.setPreferredSize(new Dimension(d.width,tablaPlatos.getRowHeight()*tablaPlatos.getRowCount()+1));
				}
				else{
				}
			}
		});
		panel_4.add(btnCargarPlatos);

		final JButton btnAlmacenarPlato = new JButton("Almacenar Plato");
		JButton btnNuevoPlato = new JButton("Nuevo Plato");
		btnNuevoPlato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_1.setViewportView(getDatosPlatos(true));
				btnAlmacenarPlato.setEnabled(true);
			}
		});
		panel_4.add(btnNuevoPlato);

		btnAlmacenarPlato.setEnabled(false);
		panel_4.add(btnAlmacenarPlato);

		JLabel label_5 = new JLabel("");
		panel_4.add(label_5);

		JLabel label_6 = new JLabel("");
		panel_4.add(label_6);

		JButton button_3 = new JButton("Regresar");
		panel_4.add(button_3);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(20, 20, 20, 20));
		platos.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_4 = new JLabel("Platos");
		panel_2.add(lblNewLabel_4, BorderLayout.NORTH);


		panel_2.add(scrollPane_1, BorderLayout.SOUTH);

		scrollPane_1.setViewportView(getDatosPlatos(false));
		scrollPane_1.setPreferredSize(new Dimension(500,200));
		//scrollPane_1.setPreferredSize(new Dimension(d.width,tablaPlatos.getRowHeight()*tablaPlatos.getRowCount()+1));
		scrollPane_1.setMaximumSize(new Dimension(100, 100));

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(0, 20, 20, 20));
		platos.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));

		scrollPane_2 = new JScrollPane();
		panel_5.add(scrollPane_2);

		//final JScrollPane scrollPane_1 = new JScrollPane();
		//scrollPane_2.setViewportView(getDatosIngredientePlato(false));

		scrollPane_2.setPreferredSize(new Dimension(500,200));
		//scrollPane_2.setPreferredSize(new Dimension(d1.width,tablaIngredientesPlatos.getRowHeight()*tablaIngredientesPlatos.getRowCount()+1));

		scrollPane_2.setMaximumSize(new Dimension(100, 100));

		JLabel lblIngredientes_1 = new JLabel("Ingredientes");
		panel_5.add(lblIngredientes_1, BorderLayout.NORTH);
		
		JPanel menu = new JPanel();
		menu.setBorder(new EmptyBorder(20, 20, 20, 20));
		tabbedPane.addTab("New tab", null, menu, null);
		menu.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMenu = new JLabel("Menu");
		menu.add(lblMenu, BorderLayout.NORTH);
		
		JPanel panel_7 = new JPanel();
		menu.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("");
		panel_7.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		panel_7.add(lblNewLabel_6);
		
		JButton btnRegresar = new JButton("Regresar");
		panel_7.add(btnRegresar);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		menu.add(scrollPane_3, BorderLayout.CENTER);



	}

	private JTable getDatosIngredientePlato(boolean agregar,int codigo) {
		Vector rowData2 = new Vector();
		int cod=0;
		//System.out.println("Buscando Codigo: "+codigo);
		for(Plato item:rest.getLPlatos().values()){
			//System.out.println(item);
			if(!agregar){
				if(item.getCodigo()==codigo){
					for(IngredientePlato ip :item.getLIngredientePlato()){
						//System.out.println(ip.getIngrediente());
						Object[] fila = new Object[4];
						//System.out.println("a");
						fila[0] = ip.getIngrediente().getCodigo();
						//System.out.println("b");
						fila[1] = ip.getIngrediente().getNombre();
						fila[2] = ip.getIngrediente().getPrecioUnitario();
						fila[3] = ip.getCantidad();
						Vector filaItem = new Vector (Arrays.asList(fila));
						rowData2.add(filaItem);
					}
				}
			}
			else{
				for(IngredientePlato ip :item.getLIngredientePlato()){
					//System.out.println(ip.getIngrediente());
					Object[] fila = new Object[5];
					//System.out.println("a");
					fila[0] = ip.getIngrediente().getCodigo();
					//System.out.println("b");
					fila[1] = ip.getIngrediente().getNombre();
					fila[2] = ip.getIngrediente().getPrecioUnitario();
					fila[3] = ip.getCantidad();
					fila[4] = false;
					Vector filaItem = new Vector (Arrays.asList(fila));
					rowData2.add(filaItem);
				}
			}
		}
		Vector columnNamesV2 = new Vector();
		if(!agregar){
			columnNamesV2 = new Vector(Arrays.asList(this.columnasIngredientesPlatos));
		}else{
			columnNamesV2 = new Vector(Arrays.asList(this.columnasIngredientesPlatos2));
		}
		tablaIngredientesPlatos = new JTable(rowData2,columnNamesV2){
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return Boolean.class;
				default:
					return String.class;
				}
			}

		};
		return tablaIngredientesPlatos;
	}

	private JTable getDatosPlatos(final boolean agregar) {

		Vector rowData2 = new Vector();
		int cod=0;
		for(Plato item:rest.getLPlatos().values()){

			Object[] fila = new Object[4];
			fila[0] = item.getCodigo();
			cod = item.getCodigo();
			fila[1] = item.getNombre();

			if(item instanceof PlatoCarta){
				String diaimp;
				fila[2]="Carta";
				fila[3]=((PlatoCarta) item).getDia();
			}
			else{
				fila[2]="Diario";
				fila[3]="No Aplica";
			}
			Vector filaItem = new Vector (Arrays.asList(fila));
			rowData2.add(filaItem);
		}
		if(agregar){
			Object[] fila = new Object[4];
			fila[0] = cod+1;
			fila[1] = "";
			fila[2] = "";
			fila[3] = "";


			Vector filaItem = new Vector (Arrays.asList(fila));
			rowData2.add(filaItem);
		}

		Vector columnNamesV2 = new Vector(Arrays.asList(this.columnasPlatos));

		tablaPlatos = new JTable(rowData2,columnNamesV2);
		tablaPlatos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tablaPlatos.getSelectedRow() != -1) {

					// print first column value from selected row
					//System.out.println(tablaPlatos.getValueAt(tablaPlatos.getSelectedRow(), 0).toString());
					if(agregar){
						scrollPane_2.setViewportView(getDatosIngredientePlato(true,-1));
					}else{
						scrollPane_2.setViewportView(getDatosIngredientePlato(false,Integer.parseInt(tablaPlatos.getValueAt(tablaPlatos.getSelectedRow(), 0).toString())));
					}
				}
			}
		});

		return tablaPlatos;
	}

	private JTable getDatosIngredientes(boolean agregar) {
		Vector rowData2 = new Vector();
		int cod=0;
		for (Ingrediente item : rest.getLIngredientes()) {
			Object[] fila = new Object[6];
			fila[0] = item.getCodigo();
			cod = item.getCodigo();
			fila[1] = item.getNombre();
			fila[2] = item.getPrecioUnitario();
			fila[3] = item.getDescripcionUnidad();
			fila[4] = item.getInventario();
			fila[5] = item.getMinimo();

			Vector filaItem = new Vector (Arrays.asList(fila));
			rowData2.add(filaItem);
		}
		if(agregar){
			Object[] fila = new Object[6];
			fila[0] = cod+1;
			fila[1] = "";
			fila[2] = "";
			fila[3] = "";
			fila[4] = "";
			fila[5] = "";

			Vector filaItem = new Vector (Arrays.asList(fila));
			rowData2.add(filaItem);
		}

		Vector columnNamesV2 = new Vector(Arrays.asList(this.columnasIngredientes));

		tablaIngredientes = new JTable(rowData2,columnNamesV2); 
		return tablaIngredientes;
	}
}
