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
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.JOptionPane;

import controler.Controle;
import controler.Global;
import outils.son.Son;

public class ChoixJoueur extends JFrame implements Global {

	/**
	 * panel général
	 */
	private JPanel contentPane;
	/**
	 * zone de saisie du pseudo
	 */
	private JTextField txtPseudo;
	/**
	 * label personnage
	 */
	private JLabel lblPersonnage;
	/**
	 * communication avec controle
	 */
	private Controle Controle;
	/**
	 * numero personnage selectionné
	 */
	private int numPerso;
	/**
	 * son de bienvenue
	 */
	private Son sonBienvenue;
	/**
	 * son bouton GO
	 */
	private Son sonGo;
	/**
	 * son bouton precedent
	 */
	private Son sonPrecedent;
	/**
	 * son bouton suivant
	 */
	private Son sonSuivant;
	/**
	 * clic sur la flèche précédent pour afficher le personnage précédent
	 */
	private void lblPrecedent_clic() {
		numPerso = ((numPerso+1)%NBPERSO)+1;
		this.affichePerso();
		sonPrecedent.play();
	}
	/**
	 * clic sur la flèche suivant pour afficher le personnage suivant
	 */
	private void lblSuivant_clic() {
		numPerso = (numPerso%NBPERSO)+1; 
		this.affichePerso();
		sonSuivant.play();
	}
	/**
	 * clic sur GO pour envoyer les informations
	 */
	private void lblGo_clic() {
		if (this.txtPseudo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "La saisie du pseudo est obligatoire");
			txtPseudo.requestFocus();
		}
		else {
			this.Controle.evenementChoixJoueur(this.txtPseudo.getText(), numPerso);
		}
		sonGo.play();
	}
	/**
	 * affichage du personnage depuis la galerie
	 */
	private void affichePerso() {
		String chemin = "personnages/perso"+ this.numPerso + "marche" + 1 + "d" + 1 + ".gif";
		URL resource = getClass().getClassLoader().getResource(chemin);
		this.lblPersonnage.setIcon(new ImageIcon(resource));
	}
	/**
	 * affichage souris normale
	 */
	public void sourisNormale() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	/**
	 * affichage de la souris doigt pointe
	 */
	public void sourisDoigt() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	/**
	 * Create the frame.
	 * @param controle
	 */
	public ChoixJoueur(Controle Controle) {
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
		
		lblPersonnage = new JLabel("");
		lblPersonnage.setBounds(142, 115, 120, 120);
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPersonnage);
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblPrecedent_clic();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSuivant_clic();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		URL resource = getClass().getClassLoader().getResource(FONDCHOIX);
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
		
		//recuperation de l'instance de controle
		this.Controle = Controle;
		
		//appel de la methode affichePerso pour afficher le premier personnage
		this.numPerso = 1;
		this.affichePerso();
		
		sonBienvenue = new Son(getClass().getClassLoader().getResource("sons/welcome"+ ".wav"));
		sonGo = new Son(getClass().getClassLoader().getResource("sons/go"+ ".wav"));
		sonPrecedent = new Son(getClass().getClassLoader().getResource("sons/precedent"+ ".wav"));
		sonSuivant = new Son(getClass().getClassLoader().getResource("sons/suivant"+ ".wav"));
		sonBienvenue.play();
	}
}
