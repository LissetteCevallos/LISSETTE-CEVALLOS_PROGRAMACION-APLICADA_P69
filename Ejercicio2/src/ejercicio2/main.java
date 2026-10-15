package ejercicio2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tx_user;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
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
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel pn_main = new JPanel();
		pn_main.setBackground(Color.GRAY);
		pn_main.setForeground(Color.WHITE);
		contentPane.add(pn_main, "name_2109278326400");
		SpringLayout sl_pn_main = new SpringLayout();
		pn_main.setLayout(sl_pn_main);
		
		JLabel label_user = new JLabel("USER :");
		label_user.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		pn_main.add(label_user);
		
		tx_user = new JTextField();
		sl_pn_main.putConstraint(SpringLayout.NORTH, tx_user, 104, SpringLayout.NORTH, pn_main);
		sl_pn_main.putConstraint(SpringLayout.WEST, tx_user, 114, SpringLayout.WEST, pn_main);
		sl_pn_main.putConstraint(SpringLayout.EAST, tx_user, -172, SpringLayout.EAST, pn_main);
		sl_pn_main.putConstraint(SpringLayout.NORTH, label_user, 1, SpringLayout.NORTH, tx_user);
		sl_pn_main.putConstraint(SpringLayout.EAST, label_user, -29, SpringLayout.WEST, tx_user);
		pn_main.add(tx_user);
		tx_user.setColumns(10);
		
		JLabel lb_Password = new JLabel("PASSWORD : ");
		sl_pn_main.putConstraint(SpringLayout.NORTH, lb_Password, 29, SpringLayout.SOUTH, label_user);
		sl_pn_main.putConstraint(SpringLayout.WEST, lb_Password, 24, SpringLayout.WEST, pn_main);
		lb_Password.setFont(new Font("Times New Roman", Font.BOLD, 13));
		pn_main.add(lb_Password);
		
		passwordField = new JPasswordField();
		sl_pn_main.putConstraint(SpringLayout.NORTH, passwordField, -2, SpringLayout.NORTH, lb_Password);
		sl_pn_main.putConstraint(SpringLayout.WEST, passwordField, 6, SpringLayout.EAST, lb_Password);
		sl_pn_main.putConstraint(SpringLayout.EAST, passwordField, 116, SpringLayout.EAST, lb_Password);
		pn_main.add(passwordField);
		
		JButton btn_ingresar = new JButton("INGRESAR");
		sl_pn_main.putConstraint(SpringLayout.SOUTH, btn_ingresar, -37, SpringLayout.SOUTH, pn_main);
		sl_pn_main.putConstraint(SpringLayout.EAST, btn_ingresar, -77, SpringLayout.EAST, pn_main);
		pn_main.add(btn_ingresar);
		
		JLabel lblNewLabel = new JLabel("BIENVENIDO ");
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 17));
		sl_pn_main.putConstraint(SpringLayout.NORTH, lblNewLabel, 33, SpringLayout.NORTH, pn_main);
		sl_pn_main.putConstraint(SpringLayout.WEST, lblNewLabel, 166, SpringLayout.WEST, pn_main);
		pn_main.add(lblNewLabel);

	}
}
