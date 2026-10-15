package ejercicio2;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class view_registro extends JFrame {
	

	public view_registro(){
		setBounds(100,100,450,200);
		setResizable (false);
		addWindowListener (new WindowAdapter(){
			public void windowCloses(WindowEvent e) {
				main v1 =new main();
				v1.setVisible(true);
				v1.setLocationRelativeTo(null);
			}
		});
		
	}
}
