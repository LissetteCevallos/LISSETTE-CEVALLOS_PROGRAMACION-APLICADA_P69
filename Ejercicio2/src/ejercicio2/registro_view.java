package ejercicio2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class registro_view extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registro_view frame = new registro_view();
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
	public registro_view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		
		JPanel panel_educacion = new JPanel();
		panel_educacion.setBackground(Color.DARK_GRAY);
		
		JPanel panel_control = new JPanel();
		panel_control.setBackground(Color.DARK_GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_educacion, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_control, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_educacion, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 342, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_control, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton btn_guardar = new JButton("GUARDAR");
		
		JButton btn_limpiar = new JButton("LIMPIAR");
		
		JButton btn_Buscar = new JButton("BUSCAR");
		GroupLayout gl_panel_control = new GroupLayout(panel_control);
		gl_panel_control.setHorizontalGroup(
			gl_panel_control.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_control.createSequentialGroup()
					.addGap(167)
					.addComponent(btn_guardar)
					.addGap(98)
					.addComponent(btn_limpiar)
					.addGap(108)
					.addComponent(btn_Buscar)
					.addContainerGap(192, Short.MAX_VALUE))
		);
		gl_panel_control.setVerticalGroup(
			gl_panel_control.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_control.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_control.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_Buscar)
						.addComponent(btn_limpiar)
						.addComponent(btn_guardar))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		panel_control.setLayout(gl_panel_control);
		
		JLabel lbl_educ = new JLabel("EDUCACION");
		lbl_educ.setForeground(Color.WHITE);
		lbl_educ.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JLabel lbl_estudios = new JLabel("NIVEL DE ESTUDIOS :");
		lbl_estudios.setForeground(Color.LIGHT_GRAY);
		lbl_estudios.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		JLabel lbl_ingles = new JLabel("INGLES :");
		lbl_ingles.setForeground(Color.LIGHT_GRAY);
		lbl_ingles.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		JComboBox comboBox = new JComboBox();
		
		JLabel lbl_escrito = new JLabel("ESCRITO :");
		lbl_escrito.setForeground(Color.LIGHT_GRAY);
		lbl_escrito.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("HABLADO :");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JComboBox cbx_nivelesrito = new JComboBox();
		
		JComboBox cbx_nivelhablado = new JComboBox();
		
		JLabel lblNewLabel = new JLabel("%");
		
		JLabel lblNewLabel_2 = new JLabel("%");
		
		JLabel lbl_Habilidades = new JLabel("HABILIDADES");
		lbl_Habilidades.setForeground(Color.LIGHT_GRAY);
		lbl_Habilidades.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		JCheckBox ckbtn_TE = new JCheckBox("Trabajo en Equipo");
		ckbtn_TE.setForeground(Color.LIGHT_GRAY);
		ckbtn_TE.setBackground(Color.DARK_GRAY);
		ckbtn_TE.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JCheckBox kbtn_resolucion = new JCheckBox("Resolucion de Conflictos");
		kbtn_resolucion.setBackground(Color.DARK_GRAY);
		kbtn_resolucion.setForeground(Color.LIGHT_GRAY);
		kbtn_resolucion.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JCheckBox ckbtn_Cliente = new JCheckBox("Orientacion al cliente ");
		ckbtn_Cliente.setBackground(Color.DARK_GRAY);
		ckbtn_Cliente.setForeground(Color.LIGHT_GRAY);
		ckbtn_Cliente.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		JCheckBox ckbtn_lider = new JCheckBox("Liderazgo");
		ckbtn_lider.setBackground(Color.DARK_GRAY);
		ckbtn_lider.setForeground(Color.LIGHT_GRAY);
		ckbtn_lider.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GroupLayout gl_panel_educacion = new GroupLayout(panel_educacion);
		gl_panel_educacion.setHorizontalGroup(
			gl_panel_educacion.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_educacion.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_educacion.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_educ)
						.addGroup(gl_panel_educacion.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel_educacion.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_educacion.createSequentialGroup()
									.addComponent(lbl_ingles)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lbl_escrito))
								.addComponent(lbl_estudios)
								.addComponent(lblNewLabel_1, Alignment.TRAILING)
								.addComponent(lbl_Habilidades))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_educacion.createParallelGroup(Alignment.LEADING)
								.addComponent(ckbtn_TE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_educacion.createSequentialGroup()
									.addGroup(gl_panel_educacion.createParallelGroup(Alignment.LEADING, false)
										.addComponent(cbx_nivelhablado, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cbx_nivelesrito, 0, 60, Short.MAX_VALUE)
										.addComponent(kbtn_resolucion, Alignment.TRAILING)
										.addComponent(ckbtn_Cliente)
										.addComponent(ckbtn_lider))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_educacion.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_2)
										.addComponent(lblNewLabel))))))
					.addContainerGap(141, Short.MAX_VALUE))
		);
		gl_panel_educacion.setVerticalGroup(
			gl_panel_educacion.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_educacion.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_educacion.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_educacion.createSequentialGroup()
							.addComponent(lbl_educ)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbl_estudios)))
					.addGap(18)
					.addGroup(gl_panel_educacion.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_educacion.createParallelGroup(Alignment.BASELINE)
							.addComponent(cbx_nivelesrito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel))
						.addGroup(gl_panel_educacion.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_educacion.createSequentialGroup()
								.addGap(18)
								.addComponent(lbl_ingles))
							.addGroup(gl_panel_educacion.createSequentialGroup()
								.addGap(29)
								.addComponent(lbl_escrito))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_educacion.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_educacion.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(cbx_nivelhablado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_2))
					.addGap(34)
					.addGroup(gl_panel_educacion.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_Habilidades)
						.addComponent(ckbtn_TE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(kbtn_resolucion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ckbtn_Cliente)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ckbtn_lider)
					.addContainerGap(63, Short.MAX_VALUE))
		);
		panel_educacion.setLayout(gl_panel_educacion);
		
		JLabel lbl_DATOS = new JLabel("DATOS PERSONALES");
		lbl_DATOS.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_DATOS.setForeground(Color.WHITE);
		
		JLabel LBL_CI = new JLabel("CEDULA :");
		LBL_CI.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		LBL_CI.setForeground(Color.LIGHT_GRAY);
		
		JLabel lbl_nombre = new JLabel("NOMBRES :");
		lbl_nombre.setForeground(Color.LIGHT_GRAY);
		lbl_nombre.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		JLabel lbl_apellido = new JLabel("APELLIDOS :");
		lbl_apellido.setForeground(Color.LIGHT_GRAY);
		lbl_apellido.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lbl_generos = new JLabel(" GENERO :");
		lbl_generos.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_generos.setForeground(Color.LIGHT_GRAY);
		
		JRadioButton rbt_fem = new JRadioButton("FEMENINO");
		rbt_fem.setForeground(Color.WHITE);
		rbt_fem.setBackground(Color.DARK_GRAY);
		
		JRadioButton rbt_Mas = new JRadioButton("MASCULINO");
		rbt_Mas.setForeground(Color.WHITE);
		rbt_Mas.setBackground(Color.DARK_GRAY);
		
		JLabel lbl_edad = new JLabel(" EDAD :");
		lbl_edad.setForeground(Color.LIGHT_GRAY);
		lbl_edad.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		JLabel lbl_direc = new JLabel("DIRECCION :");
		lbl_direc.setForeground(Color.LIGHT_GRAY);
		lbl_direc.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		JLabel lbl_ui = new JLabel("CIUDAD :");
		lbl_ui.setForeground(Color.LIGHT_GRAY);
		lbl_ui.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		JLabel lbl_correo = new JLabel("EMAIL :");
		lbl_correo.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_correo.setForeground(Color.LIGHT_GRAY);
		
		JComboBox cbx_edad = new JComboBox();
		
		JComboBox cbx_ciudad = new JComboBox();
		
		JTextArea txa_direccion = new JTextArea();
		
		JTextArea textArea = new JTextArea();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(lbl_DATOS))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(26)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
											.addComponent(lbl_nombre)
											.addComponent(LBL_CI))
										.addGap(18)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
											.addComponent(textField_1)
											.addComponent(textField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
									.addGroup(gl_panel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(lbl_apellido)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
													.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
													.addGroup(gl_panel.createSequentialGroup()
														.addGap(10)
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
															.addComponent(rbt_fem)
															.addComponent(rbt_Mas)))))
											.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
													.addComponent(lbl_ui)
													.addComponent(lbl_direc)
													.addComponent(lbl_correo))
												.addGap(18)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
													.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
													.addComponent(txa_direccion, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
													.addComponent(cbx_ciudad, 0, 171, Short.MAX_VALUE))))))))
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lbl_edad)
								.addComponent(lbl_generos))
							.addGap(18)
							.addComponent(cbx_edad, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
					.addGap(101))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl_DATOS)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(LBL_CI))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_nombre)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_apellido)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_generos)
						.addComponent(rbt_fem))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rbt_Mas)
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_edad)
						.addComponent(cbx_edad, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_ui)
						.addComponent(cbx_ciudad, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_direc)
						.addComponent(txa_direccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_correo)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(121))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

	}
}
