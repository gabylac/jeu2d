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
	 * zone de saisie du t'chat
	 */
	private JTextField txtSaisie;
	/**
	 * création panel mur
	 */
	private JPanel jpnMurs;
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
	 * methode ajout  mur
	 */
	public void ajoutMur(Object unMur) {
		jpnMurs.add((JLabel)unMur);
		jpnMurs.repaint();
	}

	/**
	 * Create the frame.
	 */
	public Arene() {
		setTitle("Arene");
		this.getContentPane().setPreferredSize(new Dimension(800, 600 + 25 + 140));
		this.pack();
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		jspChat.setViewportView(txtChat);
		
		txtSaisie = new JTextField();
		txtSaisie.setBounds(0, 600, 800, 25);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);
		
		
	}
}
