import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class AggiungiArticoli extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JTextField nomeTextField;
	private JTextField codiceTextField;
	private JTextField prezzoTextField;
	
	public AggiungiArticoli(Controller ctrl) {
		controller = ctrl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		nomeTextField = new JTextField();
		nomeTextField.setColumns(10);
		
		codiceTextField = new JTextField();
		codiceTextField.setColumns(10);
		
		JButton btnAggiungiArticolo = new JButton("Aggiungi articolo");
		btnAggiungiArticolo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAggiungiArticolo.addMouseListener(new MouseAdapter() {
			
			/*
			 *  Metodo che gestisce i bottone di inserimento
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.aggiungiArticolo(nomeTextField.getText(),Integer.valueOf(codiceTextField.getText()),Double.valueOf(prezzoTextField.getText()));
				nomeTextField.setText("");
				codiceTextField.setText("");
				prezzoTextField.setText("");
			}
		});
		
		prezzoTextField = new JTextField();
		prezzoTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Fine");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.frameAggiuntaArticoli.setVisible(false);
				controller.frameSceltaUtenteAmministratore.setVisible(true);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Aggiunta articolo al magazzino");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel lblNewLabel_1 = new JLabel("Nome articolo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblCodiceArticolo = new JLabel("Codice articolo");
		lblCodiceArticolo.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addComponent(btnAggiungiArticolo)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton))
							.addComponent(lblPrezzo, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblCodiceArticolo, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel_1))
								.addGap(53)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(codiceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(nomeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(prezzoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(nomeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(codiceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCodiceArticolo, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrezzo, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(prezzoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(68)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAggiungiArticolo)
						.addComponent(btnNewButton))
					.addGap(105))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
