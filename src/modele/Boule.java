package modele;

import javax.swing.JLabel;
import java.net.URL;
import javax.swing.ImageIcon;
import controler.Global;
import java.util.Collection;
/**
 * Gestion de la boule
 *
 */
public class Boule extends Objet implements Global, Runnable{

	/**
	 * instance de JeuServeur pour la communication
	 */
	private JeuServeur jeuServeur ;
	/**
	 * collection les murs
	 */
	private Collection lesMurs;
	/**
	 * joueur attaquant
	 */
	private Joueur attaquant;
	
	/**
	 * Constructeur
	 */
	public Boule(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		super.jLabel = new JLabel();
		super.jLabel.setVisible(false);
		String chemin = "boules/boule"+".gif";
		URL resource = getClass().getClassLoader().getResource(chemin);
		super.jLabel.setIcon(new ImageIcon(resource));
		super.jLabel.setBounds(0,0, LARGEURBOULE, HAUTEURBOULE);
		
	}
	
	/**
	 * Tire d'une boule
	 */
	public void tireBoule(Joueur attaquant, Collection lesMurs) {
		this.attaquant = attaquant;
		this.lesMurs = lesMurs;
		if (attaquant.getOrientation()== GAUCHE) {
			posX = attaquant.getPosX() - LARGEURBOULE -1;
		}else {
			posX = attaquant.getPosX() + LARGEURPERSO +1;
		}
		posY = attaquant.getPosY() + HAUTEURPERSO/2;
		new Thread(this).start();
	}
	@Override
	public void run() {
		this.attaquant.affiche(MARCHE, 1);
		super.jLabel.setVisible(true);
		Joueur victime = null;
		int lePas;
		if (attaquant.getOrientation()== DROITE) {
			lePas = PAS;
		}else {
			lePas = -PAS;
		}
		do {
			posX += lePas;
			jLabel.setBounds(posX, posY, LARGEURBOULE, HAUTEURBOULE);
			this.jeuServeur.envoiJeuATous();
			Collection lesJoueurs = this.jeuServeur.getJoueurs();
			victime= (Joueur)super.toucheCollectionObjet(lesJoueurs);
		}while (posX >= 0 && posX <= LARGEURARENE && victime == null && this.toucheCollectionObjet(lesMurs)== null);
		if (victime != null && !victime.estMort()) {
			victime.perteVie();
			attaquant.gainVie();
			for (int k=1; k<= NBETAPESTOUCHE; k++) {
				victime.affiche(TOUCHE,  k);
				pause(80, 0);
			}
			if (victime.estMort()) {
				for (int k=1; k<= NBETAPESMORT; k++) {
					victime.affiche(MORT,  k);
					pause(80, 0);
				}
			}else {
				victime.affiche(MARCHE, 1);
			}
		}
		this.jLabel.setVisible(false);
		// envoyer le nouveau jeu à tous
		this.jeuServeur.envoiJeuATous();
	}
	public void pause(long milliseconde, int nanoseconde) {
		try {
			Thread.sleep(milliseconde, nanoseconde);
		}catch (InterruptedException e){
			System.out.println("erreur pause");
		}
	}
}
