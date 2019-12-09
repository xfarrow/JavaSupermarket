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
import javax.swing.LayoutStyle.ComponentPlacement;

public class RimuoviArticoli extends JFrame {

	private JPanel contentPane;
	Controller controller;
	
	public RimuoviArticoli(Controller ctrl) {
		controller = ctrl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JComboBox <String>comboBoxArticoli = new JComboBox<String>();
		JComboBox<Integer>comboBoxQuantita = new JComboBox<Integer>();
		
		//Elementi in combobox articoli
		ArrayList<String> StringheDaInserireInComboBox = controller.RestituisciAComboboxRimozione();
		for(String linea: StringheDaInserireInComboBox) {
			comboBoxArticoli.addItem(linea);
		}
		
		for(int i=1;i<=1000;i++) {
			comboBoxQuantita.addItem(i);
		}
		
		JButton btnOK = new JButton("OK");
		btnOK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.RimuoviArticoli(comboBoxArticoli.getSelectedItem(),comboBoxQuantita.getSelectedItem());
			}
		});
		
		JButton btnFine = new JButton("Fine");
		btnFine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.chiudiFrameRimozione();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnOK)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnFine))
						.addComponent(comboBoxQuantita, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(comboBoxArticoli, 0, 165, Short.MAX_VALUE))
					.addContainerGap(584, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addComponent(comboBoxArticoli, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(58)
					.addComponent(comboBoxQuantita, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(72)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOK)
						.addComponent(btnFine))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
