import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author k1gva
 */

public class Todo extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList jLTodos;
	private DefaultListModel jLTodosModel;
	private JTextField jTFneueTodos;
	private JLabel jLUeberschrift;
	private JButton jBtnHinzu;
	private JButton jBtnWeg;
	private JButton jBtnBeenden;
	
	/**
	 * Main Methode die das Aufrufen der GUI ausführt
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Todo frame = new Todo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// GUI Aufbau
	public Todo() {
		// Fenster
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(555, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Todo Liste");
	
		// Überschrift
		jLUeberschrift = new JLabel("Meine Todos:", SwingConstants.CENTER);
		jLUeberschrift.setBounds(0,15,540,10);
		contentPane.add(jLUeberschrift);
		
		// Es wurde erstmal festgelegt, dass nur 20 Einträge in die Todo-Liste eingetragen werden dürfen
		String todos[] = new String[20];
		todos[0] = "test";
		
		// Liste für alle zu erledigenden Todos
		jLTodosModel = new DefaultListModel();
		jLTodos = new JList(todos);
		jLTodos.setModel(jLTodosModel);
		jLTodos.setBounds(5, 30, 530, 270);
		contentPane.add(jLTodos);
		
		// Textfeld um neues Todo hinzuzufügen
		jTFneueTodos = new JTextField();
		jTFneueTodos.setBounds(5, 305, 200, 20);
		contentPane.add(jTFneueTodos);
		String txtTodo = "todo.txt";
		String zeile = null;
		
		try {
			FileReader fileReader = new FileReader(txtTodo);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((zeile = bufferedReader.readLine()) != null) {
				jLTodosModel.addElement(zeile);
			}
			
			bufferedReader.close();
			
		} catch(FileNotFoundException ex) {
			System.out.println("Datei konnte nicht gefunden werden: " + txtTodo);
		} catch(IOException ex) {
			System.out.println("Datei konnte nicht eingeladen werden: " + txtTodo);
		}
		
		// Button um ins Textfeld eingetragenen String in die Liste zu speichern
		jBtnHinzu = new JButton("Hinzufügen");
		jBtnHinzu.setBounds(210, 305, 100, 20);
		contentPane.add(jBtnHinzu);
		/**
		 * Funktion um den Inhalt aus dem Textfeld in die Liste zu übertragen
		 */
		jBtnHinzu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jBtnHinzuActionPerformed(evt);
			}
			private void jBtnHinzuActionPerformed(ActionEvent evt) {
				jLTodosModel.addElement(jTFneueTodos.getText());
			}
		});
		
		// Button um einen Listeneintrag zu löschen
		jBtnWeg = new JButton("Löschen");
		jBtnWeg.setBounds(310, 305, 100, 20);
		contentPane.add(jBtnWeg);
		/**
		 * Funktion um den den gewählten Eintrag aus der Liste zu entfernen
		 */
		jBtnWeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jBtnWegActionPerformed(evt);
			}
			private void jBtnWegActionPerformed(ActionEvent evt) {
				int selectedIndex = jLTodos.getSelectedIndex();
				if (selectedIndex != -1) {
					jLTodosModel.remove(selectedIndex);
				}
			}
		});
		
		// Button um die Anwendung zu schließen
		jBtnBeenden = new JButton("Beenden");
		jBtnBeenden.setBounds(410, 305, 100, 20);
		contentPane.add(jBtnBeenden);
		/**
		 * Funktion um die Anwendung per Klick auf den Button zu beenden
		 * es fehlt noch, dass die aktuelle Liste in einer txt-Datei gespeichert wird!
		 */
		jBtnBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jBtnBeendenActionPerformed(evt);
			}
			private void jBtnBeendenActionPerformed(ActionEvent evt) {
				
				// bevor die Anwendung geschlossen wird, muss erst alles in eine Text-Datei geschrieben werden
				// name der Datei
				String todoTxt = "todo.txt";
				int laenge = jLTodosModel.getSize();
				
				// todo.txt wird erstellt
				try {
					FileWriter fileWriter = new FileWriter(todoTxt);
					
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					
					// alle Elemente der Liste werden in die Datei geschrieben
					for (int i = 0; i < laenge; i++) {
						if((String)jLTodosModel.getElementAt(i) != "") {
							bufferedWriter.write((String)jLTodosModel.getElementAt(i));
							// neue Zeile
							bufferedWriter.newLine();
						}
					}
					// Datei wird geschlossen
					bufferedWriter.close();
					
				// falls es Fehler bei der Erstellung gab, wird eine Fehlermeldung in der Console ausgegeben
				} catch(IOException ex) {
					System.out.println("Fehler beim erstellen der Datei" + todoTxt);
				}
				// nachdem die Datei erstellt wurde, wird die Anwendung geschlossen
				System.exit(0);
			}
		});
	}
}
