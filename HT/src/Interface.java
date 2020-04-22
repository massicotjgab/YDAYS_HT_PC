import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class Interface extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	String[] videos = { "Video_1", "Video_2", "Video_3", "Video_4", "Video_5" };
	JComboBox<String> vidList;
	JButton okbt;
	Button sendbt;
	Chrono2 chrono;
	LoadImage LI;
	Panel pimage;
	TextArea tatx, tarx;

	public Interface() {
		setTitle("HUNTING TOWN");
		setLayout(new BorderLayout());
		setSize(1665, 950);
		addWindowListener(new Fermeture());

		vidList = new JComboBox<String>(videos);
		vidList.setSelectedIndex(0);
		vidList.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));

		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();
		Panel p4 = new Panel();
		pimage = new Panel();

		p1.setLayout(new GridLayout(4, 1));
		p2.setLayout(new GridLayout(3, 1));
		p3.setLayout(new GridLayout(1, 1));
		p4.setLayout(new BorderLayout());
		

		chrono = new Chrono2();
		chrono.init();
		chrono.start();

		p3.add(chrono);
		p1.add(p3);
		p1.add(vidList);

		Icon icone = new ImageIcon(getClass().getResource("../bin/ytplay.png"));
		okbt = new JButton("Play", icone);
		okbt.setContentAreaFilled(false);
		okbt.setVerticalTextPosition(SwingConstants.BOTTOM);
		okbt.setHorizontalTextPosition(SwingConstants.CENTER);
		okbt.addActionListener(this);
		okbt.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));

		p1.add(okbt);
		
		tatx = new TextArea(1, 1);
		tarx = new TextArea(1, 1);
		tarx.setEditable(false);
		
		p2.add(p1);
		Label L = new Label("Tchat");
		L.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));
		L.setAlignment(Label.CENTER);

		p1.add(L);
		
		sendbt = new Button("Send");
		sendbt.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 20));
		
		p2.add(tarx);
		p2.add(tatx);
		p4.add("Center", p2);
		p4.add("South", sendbt);

		add("East", p4);
		add("Center", pimage);

		affImage("map.jpg");
	}

	public static void main(String[] args) {
		Interface i = new Interface();
		i.setVisible(true);
	}

	public void affImage(String str) {
		if (LI != null) {
			pimage.remove(LI);
		}
		LI = new LoadImage(str);
		pimage.add(LI);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("general" + e.getActionCommand());
		
		File video = new File(vidList.getSelectedItem()+".mp4");
		try {
			Runtime.getRuntime().exec("C:\\Program Files\\VideoLAN\\VLC\\vlc.exe " + video.getCanonicalPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}


}
