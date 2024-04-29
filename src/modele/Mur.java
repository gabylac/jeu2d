package modele;

import javax.swing.ImageIcon;
import controler.Global;
import java.net.URL;
import javax.swing.JLabel;

/**
 * Gestion des murs
 *
 */
public class Mur extends Objet implements Global {

	/**
	 * Constructeur
	 */
	public Mur() {
		//calcul position aléatoire mur
		posX = (int)Math.round(Math.random()*(LARGEURARENE-LARGEURMUR));
		posY = (int)Math.round(Math.random()*(HAUTEURARENE-HAUTEURMUR));
		//création du jlabel pour le mur
		jLabel = new JLabel();
		//position et image du mur
		jLabel.setBounds(posX, posY, LARGEURMUR, HAUTEURMUR);
		URL resource = getClass().getClassLoader().getResource(MUR);
		jLabel.setIcon(new ImageIcon(resource));
	}
	
}
