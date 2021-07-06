import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main {

	private JFrame f;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.f.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	private void initialize() {
		f = new JFrame();
		f.setBounds(100, 100, 820, 640);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Board board = new Board();

		f.add(board);
	}

}
