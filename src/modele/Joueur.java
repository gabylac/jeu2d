package modele;

import java.util.Collection;
import controler.Global;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.net.URL;

import javax.swing.SwingConstants;
import java.awt.event.KeyEvent;
/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet implements Global {

	/**
	 * pseudo saisi
	 */
	private String pseudo ;
	/**
	 * n° correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso ; 
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur ;
	/**
	 * numéro d'étape dans l'animation (de la marche, touché ou mort)
	 */
	private int etape ;
	/**
	 * la boule du joueur
	 */
	private Boule boule ;
	/**
	 * message sous le joueur
	 */
	private JLabel message;
	/**
	 * orientation du personnage
	 */
	private int orientation;
	/**
	 * vie du joueur 
	 */
	private int vie;
	/**
	 * getter sur pseudo
	 * @return pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**
	 * @return orientation
	 */
	public Integer getOrientation() {
		return orientation;
	}
		
	/**
	 * Constructeur
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		this.vie = MAXVIE;
		this.etape = 1;
		this.orientation = DROITE;
	}

	/**
	 * Initialisation d'un joueur (pseudo et numéro, calcul de la 1ère position, affichage, création de la boule)
	 * @param pseudo
	 * @param numPerso
	 * @param lesJoueurs collection avec tous les joueurs
	 * @param lesMurs collection avec tous les murs
	 */
	public void initPerso(String pseudo, int numPerso, Collection lesJoueurs, Collection lesMurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		System.out.println("joueur" + pseudo + "- num perso"+ numPerso + " créé");
		super.jLabel = new JLabel();
		this.message = new JLabel();
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("Dialog", Font.PLAIN, 8));
		this.boule = new Boule(this.jeuServeur);
		this.premierePosition(lesJoueurs, lesMurs);
		this.jeuServeur.ajoutJLabelJeuArene(jLabel);
		this.jeuServeur.ajoutJLabelJeuArene(message);
		this.jeuServeur.ajoutJLabelJeuArene(boule.getjLabel());
		this.affiche(MARCHE, this.etape);
		
	}

	/**
	 * Calcul de la première position aléatoire du joueur (sans chevaucher un autre joueur ou un mur)
	 */
	private void premierePosition(Collection lesJoueurs, Collection lesMurs) {
		jLabel.setBounds(0,0, LARGEURPERSO, HAUTEURPERSO);
		do {
			posX = (int)Math.round(Math.random()*(LARGEURARENE-LARGEURPERSO));
			posY = (int)Math.round(Math.random()*(HAUTEURARENE-HAUTEURPERSO-HAUTEURMESSAGE));
		}while (toucheCollectionObjet(lesJoueurs)!= null || toucheCollectionObjet(lesMurs)!= null);
	}
	
	/**
	 * Affiche le personnage et son message
	 */
	public void affiche(String etatJoueur,int etape) {
		super.jLabel.setBounds(posX, posY, LARGEURPERSO, HAUTEURPERSO);
		String chemin = "personnages/perso"+ this.numPerso + etatJoueur + etape + "d" + this.orientation + ".gif";
		System.out.println(chemin);
		URL resource = getClass().getClassLoader().getResource(chemin);
		super.jLabel.setIcon(new ImageIcon(resource));
		this.message.setBounds(posX-10, posY+HAUTEURPERSO,LARGEURPERSO+10, HAUTEURMESSAGE);
		this.message.setText(pseudo + ":" + vie);
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * Gère une action reçue et qu'il faut afficher (déplacement, tire de boule...)
	 */
	public void action(Integer action, Collection lesJoueurs, Collection lesMurs) {
		switch (action) {
		case KeyEvent.VK_LEFT :
			orientation = GAUCHE;
			posX = deplace(posX, action, - PAS, LARGEURARENE - LARGEURPERSO, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_RIGHT :
			orientation = DROITE;
			posX = deplace(posX, action, PAS, LARGEURARENE - LARGEURPERSO, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_UP :
			posY = deplace(posY, action, - PAS, HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_DOWN : 
			posY = deplace(posY, action, PAS, HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE, lesJoueurs, lesMurs);
			break;
		case KeyEvent.VK_SPACE :
			if (!this.boule.getjLabel().isVisible()) {
				this.boule.tireBoule(this, lesMurs);
			}
			break;
		}
		this.affiche(MARCHE, this.etape);
			
	}

	/**
	 * Gère le déplacement du personnage
	 * @param position = position de depart
	 * @param action = action de depalcement gauche droite haut bas
	 * @param lepas = distance du pas parcouru
	 * @param lesJoueurs = les joueurs présents dans l'arene
	 * @param lesMurs  =les murs présents dans l'arene
	 * @return nouvelle position du joueur
	 */
	private int deplace(int position, int action, int lepas, int max, Collection lesJoueurs, Collection lesMurs) {
		int ancpos = position;
		position += lepas;
		position = Math.max(position, 0);
		position = Math.min(position, max);
		if (action==KeyEvent.VK_LEFT || action==KeyEvent.VK_RIGHT){
			posX = position;
		}else {
			posY = position;
		}
		if (toucheCollectionObjet(lesJoueurs)!= null || toucheCollectionObjet(lesMurs)!= null) {
			position = ancpos;
		}
		etape = (etape % NBETAPESMARCHE)+1;
		return position;
	}

	/**
	 * Gain de points de vie après avoir touché un joueur
	 */
	public void gainVie() {
		this.vie += GAIN;
	}
	
	/**
	 * Perte de points de vie après avoir été touché 
	 */
	public void perteVie() {
		this.vie = Math.max(0, this.vie - PERTE); 
	}
	
	/**
	 * vrai si la vie est à 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return (this.vie == 0);
	}
	
	/**
	 * Le joueur se déconnecte et disparait
	 */
	public void departJoueur() {
	}
	
}
