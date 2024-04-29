package vue;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

import controler.Controle;

public class entreeJeu extends JFrame {

	/**
	 * panel general
	 */
	private JPanel contentPane;
	/**
	 * zone de saisie adresse IP
	 */
	private JTextField txtIp;
	/**
	 * controle 
	 */
	private Controle Controle;
	/**
	 * clic sur le bouton start pour lancer le serveur
	 */
	private void btnStart_clic() {
		this.Controle.evenementEntreeJeu("serveur");
	}
	/**
	 * clic sur le bouton exit pour arreter l'application
	 */
	private void btnExit_clic() {
		System.exit(0);
	}
	/**
	 * clic sur le bouton connect pour se connecter à un serveur
	 */
	private void btnConnect_clic() {
		this.Controle.evenementEntreeJeu(this.txtIp.getText());
	}
	/**
	 * Create the frame.
	 * @param controle
	 */
	public entreeJeu(Controle Controle) {
		setResizable(false);
		setTitle("Urban Marginal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 302, 159);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStartAServer = new JLabel("Start a server :");
		lblStartAServer.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblStartAServer.setBounds(10, 11, 94, 14);
		contentPane.add(lblStartAServer);
		
		JLabel lblConnectExixtingServer = new JLabel("Connect an existing server :");
		lblConnectExixtingServer.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblConnectExixtingServer.setBounds(10, 36, 197, 14);
		contentPane.add(lblConnectExixtingServer);
		
		JLabel lblIpServer = new JLabel("IP server :");
		lblIpServer.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblIpServer.setBounds(10, 61, 68, 14);
		contentPane.add(lblIpServer);
		
		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnStart_clic();
			}
		});
		btnStart.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnStart.setBounds(186, 7, 89, 23);
		contentPane.add(btnStart);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnConnect_clic();
			}
			
		});
		btnConnect.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnConnect.setBounds(186, 57, 89, 23);
		contentPane.add(btnConnect);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				btnExit_clic();
			}
		});
		btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnExit.setBounds(186, 91, 89, 23);
		contentPane.add(btnExit);
		
		txtIp = new JTextField();
		txtIp.setText("127.0.0.1");
		txtIp.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		txtIp.setBounds(69, 58, 107, 20);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		this.Controle = Controle;
	}
}
