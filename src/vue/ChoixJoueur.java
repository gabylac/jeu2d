package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class ChoixJoueur extends JFrame {

	/**
	 * panel général
	 */
	private JPanel contentPane;
	/**
	 * zone de saisie du pseudo
	 */
	private JTextField txtPseudo;
	/**
	 * clic sur la flèche précédent pour afficher le personnage précédent
	 */
	private void lblPrecedent_clic() {
		System.out.println("clic sur precedent");
	}
	/**
	 * clic sur la flèche suivant pour afficher le personnage suivant
	 */
	private void lblSuivant_clic() {
		System.out.println("clic sur suivant");
	}
	/**
	 * clic sur GO pour envoyer les informations
	 */
	private void lblGo_clic() {
		(new Arene()).setVisible(true);
		this.dispose();
	}

	/**
	 * Create the frame.
	 */
	public ChoixJoueur() {
		//dimension de la frame en fonction de la taille de l'image
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
		this.pack();
		//interdidction de changer la taille
		this.setResizable(false);
		
		setTitle("Choix Joueur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblPrecedent_clic();
			}
		});
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSuivant_clic();
			}
		});
		
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();
			}
		});
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		String chemin = "fonds/fondchoix.jpg";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(resource));
		contentPane.add(lblFond);
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(142, 245, 120, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		lblGo.setBounds(311, 202, 65, 61);
		contentPane.add(lblGo);
		lblPrecedent.setBounds(65, 146, 31, 45);
		contentPane.add(lblPrecedent);
		lblSuivant.setBounds(301, 145, 25, 46);
		contentPane.add(lblSuivant);
		
		//positionnement sur la zone de saisie
		txtPseudo.requestFocus();
	}

}
