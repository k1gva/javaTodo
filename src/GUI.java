import javax.swing.*;
import java.awt.BorderLayout;

/*
 * @author: k1gva
 */

public class GUI extends JFrame {
	public GUI() {
		
		txtMeineTodos = new JTextField();
		txtMeineTodos.setHorizontalAlignment(SwingConstants.CENTER);
		txtMeineTodos.setText("Meine Todos:");
		getContentPane().add(txtMeineTodos, BorderLayout.NORTH);
		txtMeineTodos.setColumns(10);
	}
	
	private static final long serialVersionUID = 1L;
	private JTextField txtMeineTodos;

	public static void main(String[] args) {
		JFrame gui = new JFrame();
		gui.setSize(300, 200);
		gui.setVisible(true);
		gui.setTitle("Todoliste");
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}

}
