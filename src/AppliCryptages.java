

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.ButtonGroup;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppliCryptage {

	private Cryptage cryptage;

	private JFrame frmCryptage;
	private JTextField textFieldClef;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnCryptage;
	private JRadioButton rdbtnDecryptage;
	private JTextArea textAreaClair;
	private JTextArea textAreaCrypte;
	private JLabel lblMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppliCryptage window = new AppliCryptage();
					window.frmCryptage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppliCryptage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCryptage = new JFrame();
		frmCryptage.setTitle("Cryptage ...");
		frmCryptage.setBounds(100, 100, 450, 300);
		frmCryptage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelHaut = new JPanel();
		frmCryptage.getContentPane().add(panelHaut, BorderLayout.NORTH);
		panelHaut.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelBoutons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBoutons.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelHaut.add(panelBoutons);

		JButton btnDecalage = new JButton("D\u00E9calage");
		btnDecalage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMessage.setText(" ");
				try {
					cryptage = new Decalage(textFieldClef.getText());
				} catch (IllegalArgumentException iae) {
					lblMessage.setText(iae.getMessage());
				}
				if (rdbtnCryptage.isSelected()) {
					String texte = textAreaClair.getText();
					textAreaCrypte.setText(cryptage.cryptage(texte));
				} else {
					String texte = textAreaCrypte.getText();
					textAreaClair.setText(cryptage.deCryptage(texte));					
				}
				// -------------
			}
		});
		panelBoutons.add(btnDecalage);

		JButton btnPlayfair = new JButton("Playfair");
		btnPlayfair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMessage.setText(" ");
				try {
					cryptage = new Playfair(textFieldClef.getText());
				} catch (IllegalArgumentException iae) {
					lblMessage.setText(iae.getMessage());
				}
				if (rdbtnCryptage.isSelected()) {
					String texte = textAreaClair.getText();
					textAreaCrypte.setText(cryptage.cryptage(texte));
				} else {
					String texte = textAreaCrypte.getText();
					textAreaClair.setText(cryptage.deCryptage(texte));					
				}
				// -------------
			}
		});
		panelBoutons.add(btnPlayfair);

		rdbtnCryptage = new JRadioButton("cryptage");
		buttonGroup.add(rdbtnCryptage);
		rdbtnCryptage.setSelected(true);
		panelBoutons.add(rdbtnCryptage);

		rdbtnDecryptage = new JRadioButton("d\u00E9cryptage");
		buttonGroup.add(rdbtnDecryptage);
		panelBoutons.add(rdbtnDecryptage);

		JPanel panelClef = new JPanel();
		panelClef.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelHaut.add(panelClef);
		panelClef.setLayout(new BorderLayout(0, 0));

		JLabel lblClef = new JLabel(" Clef : ");
		lblClef.setForeground(Color.BLUE);
		lblClef.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelClef.add(lblClef, BorderLayout.WEST);

		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelClef.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		textFieldClef = new JTextField();
		panel.add(textFieldClef, BorderLayout.CENTER);
		textFieldClef.setColumns(10);

		JPanel panelCentre = new JPanel();
		frmCryptage.getContentPane().add(panelCentre, BorderLayout.CENTER);
		panelCentre.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelClair = new JPanel();
		panelClair.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentre.add(panelClair);
		panelClair.setLayout(new BorderLayout(0, 0));

		JLabel lblTexteClair = new JLabel("Texte clair");
		lblTexteClair.setForeground(Color.BLUE);
		lblTexteClair.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTexteClair.setHorizontalAlignment(SwingConstants.CENTER);
		panelClair.add(lblTexteClair, BorderLayout.NORTH);

		JPanel panelTxtClair = new JPanel();
		panelTxtClair.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelClair.add(panelTxtClair, BorderLayout.CENTER);
		panelTxtClair.setLayout(new BorderLayout(0, 0));

		textAreaClair = new JTextArea();
		panelTxtClair.add(textAreaClair, BorderLayout.CENTER);

		JPanel panelCrypte = new JPanel();
		panelCentre.add(panelCrypte);
		panelCrypte.setLayout(new BorderLayout(0, 0));

		JLabel lblTexteCrypte = new JLabel("Texte crypt\u00E9");
		lblTexteCrypte.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexteCrypte.setForeground(Color.BLUE);
		lblTexteCrypte.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelCrypte.add(lblTexteCrypte, BorderLayout.NORTH);

		JPanel panelTxtCrypte = new JPanel();
		panelTxtCrypte.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCrypte.add(panelTxtCrypte, BorderLayout.CENTER);
		panelTxtCrypte.setLayout(new BorderLayout(0, 0));

		textAreaCrypte = new JTextArea();
		panelTxtCrypte.add(textAreaCrypte, BorderLayout.CENTER);

		lblMessage = new JLabel(" ");
		lblMessage.setForeground(Color.RED);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		frmCryptage.getContentPane().add(lblMessage, BorderLayout.SOUTH);
	}

}
