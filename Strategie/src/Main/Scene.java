package Main;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Scene extends JFrame {

	public static final int HEIGHT = 1022;
	public static final int WIDTH = 1000;

	public Content content = new Content();

	public Scene() {
		setContentPane(content);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);
		setVisible(true);
	}
}
