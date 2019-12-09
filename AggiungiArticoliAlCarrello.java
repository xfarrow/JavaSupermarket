import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AggiungiArticoliAlCarrello extends JFrame {

	private JPanel contentPane;
	Controller controller;

	public AggiungiArticoliAlCarrello(Controller ctrl) {
		controller = ctrl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JComboBox<String> comboBoxArticoli = new JComboBox<String>();
		
		//Inserimento degli articoli nella combobox
		ArrayList<String> StringheDaInserireInComboBox = controller.RestituisciAComboboxAggiunta();
		for(String linea: StringheDaInserireInComboBox) {
			comboBoxArticoli.addItem(linea);
		} 
		
		JComboBox<Integer> comboBoxQuantita = new JComboBox<Integer>();
		
		//Inserimento delle quantita nella CB
		for(int i=1;i<=1000;i++) {
			comboBoxQuantita.addItem(i);
		}
		
		JButton okButton = new JButton("OK");
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.daComboBoxACarrello(comboBoxArticoli.getSelectedItem(),comboBoxQuantita.getSelectedItem());
			}
		});
		
		JButton fineButton = new JButton("Fine");
		fineButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.closeAggiungiArticoli();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(okButton)
							.addGap(28)
							.addComponent(fineButton))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(comboBoxQuantita, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(comboBoxArticoli, Alignment.LEADING, 0, 146, Short.MAX_VALUE)))
					.addContainerGap(479, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addComponent(comboBoxArticoli, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(comboBoxQuantita, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(okButton)
						.addComponent(fineButton))
					.addContainerGap(151, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
