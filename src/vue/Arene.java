package vue;

import javax.swing.ImageIcon;
import java.net.URL;

import controler.Global;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import controler.Controle;
import java.awt.event.KeyAdapter;
import outils.son.Son;

public class Arene extends JFrame implements Global {

	/**
	 * panel general
	 */
	private JPanel contentPane;
	
	/**
	 * zone affichage du t'chat
	 */
	private JTextArea txtChat;
	/**
	 * controle
	 */
	private Controle controle;
	/**
	 * tableau des sons
	 */
	private Son[] lesSons = new Son[SON.length];
	/**
	 * @return txtChat
	 */
	public String getTxtChat() {
		return txtChat.getText();
	}
	/**
	 *setter sur txtChat
	 * @param txtChat
	 */
	public void setTxtChat(String texte) {
		this.txtChat.setText(texte);
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}
	/**
	 * zone de saisie du t'chat
	 */
	private JTextField txtSaisie;
	/**
	 * creation du panel jeu contenant joueurs et boule
	 */
	private JPanel jpnJeu;
	/**
	 * création panel mur
	 */
	private JPanel jpnMurs;
	/**
	 * creation propriete client pour verifier si client
	 */
	private Boolean client;
	/**
	 * getter sur panel mur
	 * @return jpnMurs
	 */
	public JPanel getJpnMurs() {
		return jpnMurs;
	}
	/**
	 * setter sur panel mur
	 * @param jpnMurs
	 */
	public void setJpnMurs(JPanel jpnMurs) {
		this.jpnMurs.add(jpnMurs);
		this.jpnMurs.repaint();
	}
	/**
	 * getter sur panel du jeu
	 * @return jpnJeu
	 */
	public JPanel getJpnJeu() {
		return jpnJeu;
	}
	/**
	 * setter sur panel jeu 
	 */
	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
		this.contentPane.requestFocus();
	}
	/**
	 * methode ajout  mur
	 */
	public void ajoutMur(Object unMur) {
		jpnMurs.add((JLabel)unMur);
		jpnMurs.repaint();
	}
	public void ajoutJLabelJeu(JLabel unJLabel) {
		jpnJeu.add((JLabel)unJLabel);
		jpnJeu.repaint();
	}
	/**
	 * ajoute le texte au chat
	 * @param texte
	 */
	public void ajoutTchat(String phrase) {
		this.txtChat.setText(this.txtChat.getText()+phrase+"\r\n");
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}
	/**
	 * ajout evenement keyPress sur txtSaisie
	 */
	public void txtSaisie_KeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (! this.txtSaisie.getText().equals("")) {
				this.controle.evenementArene(this.txtSaisie.getText());
				this.txtSaisie.setText("");
			}
			this.contentPane.requestFocus();
		}
	}
		
	/**
	 * evenement sur les flecches directionnelles
	 */
	public void contentPane_KeyPressed(KeyEvent e) {
		int touche = -1;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT : 
		case KeyEvent.VK_RIGHT :
		case KeyEvent.VK_UP : 
		case KeyEvent.VK_DOWN :
		case KeyEvent.VK_SPACE :
			touche = e.getKeyCode();
			break;
		}
		if (touche != -1) {
			this.controle.evenementArene(touche);
		}
	}
	/**
	 * joue le son qui convient
	 */
	public void joueSon(Integer leSon) {
		this.lesSons[leSon].play();
	}

	/**
	 * Create the frame.
	 * @param controle instance du controleur
	 * @param typejeu si jeu client ou jeu serveur
	 */
	public Arene(Controle controle, String typeJeu) {
		this.client = typeJeu.equals(CLIENT);
		setTitle("Arene");
		this.getContentPane().setPreferredSize(new Dimension(800, 600 + 25 + 140));
		this.pack();
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				contentPane_KeyPressed(e);
			}
		});
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnJeu.setOpaque(false);
		jpnJeu.setLayout(null);
		contentPane.add(jpnJeu);
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnMurs.setOpaque(false);
		jpnMurs.setLayout(null);
		contentPane.add(jpnMurs);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 800, 600);
		URL resource = getClass().getClassLoader().getResource(FONDARENE);
		lblFond.setIcon(new ImageIcon(resource));
		contentPane.add(lblFond);
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 625, 800, 140);
		contentPane.add(jspChat);
		
		txtChat = new JTextArea();
		txtChat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				contentPane_KeyPressed(e);
			}
		});
		txtChat.setEditable(false);
		jspChat.setViewportView(txtChat);
		
		if (this.client) {
			txtSaisie = new JTextField();
			txtSaisie.addKeyListener(new KeyAdapter(){
				@Override
				public void keyPressed(KeyEvent e) {
					txtSaisie_KeyPressed(e);
				}
			});
			txtSaisie.setBounds(0, 600, 800, 25);
			contentPane.add(txtSaisie);
			txtSaisie.setColumns(10);
		}
		
		if (this.client) {
			for (int k=0; k < SON.length; k++) {
				lesSons[k] = new Son(getClass().getClassLoader().getResource(SON[k]));
			}
		}
		this.controle = controle;
	}
}
