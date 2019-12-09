import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class MenuUtente extends JFrame {

	private JPanel contentPane;
	public JTable table;
	Controller controller;
	public DefaultTableModel model;
	public JLabel labelTotale;

	public MenuUtente(Controller ctrl) {
		controller = ctrl;
		/* Codice generato all'inizio */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnAggiungiArticolo = new JButton("Aggiungi articolo");
		btnAggiungiArticolo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.AggiungiArticoliIsClicked();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Carrello");
		lblNewLabel.setFont(new Font("MS PGothic", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Totale: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		labelTotale = new JLabel("0.0 €");
		labelTotale.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnSvuotaCarrello = new JButton("Svuota carrello");
		btnSvuotaCarrello.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.SvuotaCarrello();
			}
		});
		
		JButton btnRimuoviArticoli = new JButton("Rimuovi articoli");
		btnRimuoviArticoli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.rimuoviArticoliIsClicked();
			}
		});
		
		JButton btnStampaCarrello = new JButton("Stampa Carrello (test)");
		btnStampaCarrello.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.stampaCarrello();
			}
		});
		
		JButton btnProcediAlPagamento = new JButton("Procedi al pagamento");
		btnProcediAlPagamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.ProcediAlPagamento();
			}
		});
		btnProcediAlPagamento.setFont(new Font("Tahoma", Font.BOLD, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 521, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnAggiungiArticolo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnSvuotaCarrello, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnRimuoviArticoli, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(105))
								.addComponent(btnProcediAlPagamento, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(labelTotale)
							.addGap(176)
							.addComponent(btnStampaCarrello, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(34)
							.addComponent(lblNewLabel)
							.addGap(37)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(98)
							.addComponent(btnAggiungiArticolo)
							.addGap(31)
							.addComponent(btnSvuotaCarrello)
							.addGap(31)
							.addComponent(btnRimuoviArticoli)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnProcediAlPagamento)))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(labelTotale)
						.addComponent(btnStampaCarrello))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Prodotto", "Prezzo unitario", "Quantit\u00E0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(77);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setToolTipText("");
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
		 model = (DefaultTableModel) table.getModel();
		// model.addRow(new Object[]{"Column 1", "Column 2", "Column 3"});
		
		 
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		/* Fine codice generato all'inizio */
	}
}
