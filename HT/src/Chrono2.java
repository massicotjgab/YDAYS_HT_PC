import java.awt.*;
import java.awt.event.*;
import java.applet.*;

@SuppressWarnings("deprecation")
public class Chrono2 extends Applet implements Runnable, ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Thread chronometre;
	int speed = 100;
	private int ds = 36000; // dixième de seconde
	boolean b_pause = true;
	Button b1, b2, b3;
	Panel bas;

	public void init() {
		setLayout(new BorderLayout());
		//setSize(150, 90);
		bas = new Panel();
		setBackground(Color.white);
		setForeground(Color.blue);
		b1 = ajouteBouton("Start", bas);	
		b2 = ajouteBouton("Stop", bas);
		b3 = ajouteBouton("Reset", bas);
		b1.setEnabled(true);
		b2.setEnabled(false);
		b3.setEnabled(false);

		add("South", bas);
	}

	Button ajouteBouton(String S, Panel p) {
		Button but = new Button(S);
		but.setEnabled(true);
		but.setActionCommand(S);
		but.addActionListener(this);
		but.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));
		p.add(but);
		return (but);
	}

	public void start() {
// Au début de l'applet, création et démarrage du chronomètre
		if (chronometre == null) {
			chronometre = new Thread(this);
			chronometre.start();
		}
	}

	public void run() {
		while (chronometre != null) {
			repaint();
			if (b_pause == false) {
				ds--;
			}
			try {
				chronometre.sleep(speed);
			} catch (InterruptedException e) {
			}
		}
	}

	public void stop() {
// A la fin de l'applet, arrêt du chronometre
		chronometre.stop();
		chronometre = null;
	}

	public void paint(Graphics g) {
// Dessine le temps écoulé sous forme de h:mm:ss:d
		String S = ds / 36000 + ":" + (ds / 6000) % 6 + (ds / 600) % 10 + ":" + (ds / 100) % 6 + (ds / 10) % 10 + ":"
				+ ds % 10;
//affichage centré
		g.setFont(new Font("TimesRoman", Font.BOLD, 25));
		FontMetrics fm = g.getFontMetrics();
		int largeur = fm.stringWidth(S);
		int hauteur = fm.getHeight();
		int x = (getSize().width - largeur) / 2;
		int y = (getSize().height - hauteur - bas.getHeight())/2;
		g.drawString(S, x, y + hauteur - fm.getDescent());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// ((Button)arg0.getSource()).setEnabled(false);
		switch (arg0.getActionCommand()) {
		case "Start":
			b_pause = false;
			b1.setEnabled(false);
			b2.setEnabled(true);
			b3.setEnabled(true);
			break;
		case "Stop":
			b_pause = true;
			b1.setEnabled(true);
			b2.setEnabled(false);
			b3.setEnabled(true);
			break;
		case "Reset":
			b_pause = true;
			ds = 36000;
			b1.setEnabled(true);
			b2.setEnabled(false);
			b3.setEnabled(false);
			break;
		default:
			break;
		}
	}
}
