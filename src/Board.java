import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class Board extends JPanel implements ActionListener, MouseListener {

	Image board;
	Image wStone;
	Image bStone;
	Image bOn;
	Image bOff;
	Image wOn;
	Image wOff;
	Image ban;
	Image Wwin;
	Image Bwin;
	Image Wlose;
	Image Blose;
	Image medal;

	int black = 0;
	int white = 0;

	boolean state[][] = new boolean[19][19];
	int color[][] = new int[19][19];
	// 0 black
	// 1 white
	// 2 착수금지점

	boolean bState[][] = new boolean[19][19];
	boolean wState[][] = new boolean[19][19];

	int locationx[][] = new int[19][19];
	int locationy[][] = new int[19][19];

	boolean BorW = false;
	boolean start = false;

	int startcount = 0;

	Point loca;

	String mode = "";
	String win = "";

	boolean end = false;

	int x, y;
	int std = 0;
	int xsuby, ysubx = 0;

	int trigger = 0;

	public void Play(String fileName) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
			Clip clip = AudioSystem.getClip();
			clip.stop();
			clip.open(ais);
			clip.start();
		} catch (Exception ex) {
		}
	}

	public void detect4() {
		System.out.println("detect 4 run");		
		String wdetect = "";
		String bdetect = "";

		int bcount = 0;
		int wcount = 0;

		for (int i = 0; i < 19; i++) { // ㅡ자라인
			for (int j = 0; j < 19; j++) {
				if (wState[i][j] == true) {
					wcount++;
					wdetect += "(" + i + ", " + j + ")";
//				System.out.println("wcount1 " + wcount);
					if (wcount >= 4) {
						System.out.println("ㅡline white : " + wdetect);

					}
				} else {
					wcount = 0;
					wdetect = "";
				}
				if (bState[i][j] == true) {
					bcount++;
					bdetect += "(" + i + ", " + j + ")";
//				System.out.println("wcount1 " + wcount);
					if (bcount >= 4) {
						System.out.println("ㅡline black : " + bdetect);

					}
				} else {
					bcount = 0;
					bdetect = "";
				}
			}
		}
		for (int i = 0; i < 19; i++) { // |자라인
			for (int j = 0; j < 19; j++) {
				if (wState[j][i] == true) {
					wcount++;
					wdetect += "(" + j + ", " + i + ")";
//				System.out.println("wcount1 " + wcount);
					if (wcount >= 4) {
						System.out.println("| line white : " + wdetect);

					}
				} else {
					wcount = 0;
					wdetect = "";
				}
				if (bState[j][i] == true) {
					bcount++;
					bdetect += "(" + j + ", " + i + ") | ";
//				System.out.println("wcount1 " + wcount);
					if (bcount >= 4) {
						System.out.println("| line black : " + bdetect);

					}
				} else {
					bcount = 0;
					bdetect = "";
				}

			}
		}
//		xsuby = x / 32 - y / 32;
//		ysubx = y / 32 - x / 32;
//		int i = 0;
//		while (true) { // \라인
//			if (xsuby + i >= 19 || i >= 19 || ysubx + i >= 19)
//				break;
//			if ((x / 32) > (y / 32)) {
//				if (wState[i][xsuby + i] == true) {
//					count++;
//					System.out.println("wcount3 " + count);
//					if (count >= 6) {
//						end = true;
//						win = "백돌 ";
//					}
//				} else
//					count = 0;
//			} else {
//				if (wState[ysubx + i][i] == true) {
//					count++;
//					System.out.println("wcount3 " + count);
//					if (count >= 6) {
//						end = true;
//						win = "백돌 ";
//					}
//				} else
//					count = 0;
//				std += 20;
//			}
//			i++;
//		}
//		i = 0;
//		xsuby = x / 32 + y / 32;
//		while (true) { // /라인
//			if (xsuby - i < 0 || i >= 19 || xsuby - 18 + i >= 19 || 18 - i < 0)
//				break;
//			if ((x / 32) + (y / 32) < 18) {
//				if (wState[i][xsuby - i] == true) {
//					count++;
//					System.out.println("wcount4 " + count);
//					if (count >= 6) {
//						end = true;
//						win = "백돌 ";
//					}
//				} else
//					count = 0;
//			} else {
//				if (wState[xsuby - 18 + i][18 - i] == true) {
//					count++;
//					System.out.println("wcount4 " + count);
//					if (count >= 6) {
//						end = true;
//						win = "백돌 ";
//					}
//				} else
//					count = 0;
//			}
//			i++;
//		}

	}

	public void paintComponent(Graphics g) {
		detect4();
		g.drawImage(board, 0, 0, 618, 618, null);
		if (end == false) {
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 19; j++) {
					if (state[i][j] == true) {
						if (color[i][j] == 0) {
							g.drawImage(bStone, locationx[i][j], locationy[i][j], 20, 20, null);

						} else if (color[i][j] == 1) {
							g.drawImage(wStone, locationx[i][j], locationy[i][j], 20, 20, null);

						} else { // 착수금지
							g.drawImage(ban, locationx[i][j], locationy[i][j], 20, 20, null);
						}
					}
				}
			}
			if (start == false) {
				g.drawImage(wOff, 660, 0, 100, 100, null);
				g.drawImage(bOff, 660, 418, 100, 100, null);
			} else {
				if (BorW == true) {
					g.drawImage(wOff, 660, 0, 100, 100, null);
					g.drawImage(bOn, 660, 418, 100, 100, null);
				} else {
					g.drawImage(bOff, 660, 418, 100, 100, null);
					g.drawImage(wOn, 660, 0, 100, 100, null);
				}
			}
		} else {
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 19; j++) {
					if (state[i][j] == true) {
						if (color[i][j] == 0) {
							g.drawImage(bStone, locationx[i][j], locationy[i][j], 20, 20, null);
						} else if (color[i][j] == 1) {
							g.drawImage(wStone, locationx[i][j], locationy[i][j], 20, 20, null);
						} else { // 착수금지
							g.drawImage(ban, locationx[i][j], locationy[i][j], 20, 20, null);
						}
					}
				}
			}
			if (win.equals("백돌 ")) {
				g.drawImage(Wwin, 660, 0, 100, 100, null);
				g.drawImage(Blose, 660, 418, 100, 100, null);
				g.drawImage(medal, 675, 100, 70, 70, null);
			} else {
				g.drawImage(Wlose, 660, 0, 100, 100, null);
				g.drawImage(Bwin, 660, 418, 100, 100, null);
				g.drawImage(medal, 675, 518, 70, 70, null);
			}
		}
	}

	public Board() {
		setBackground(Color.WHITE);
		setBounds(0, 0, 818, 618);

		try {
			board = ImageIO.read(new File("./img/board.png"));
			wStone = ImageIO.read(new File("./img/whiteStone.png"));
			bStone = ImageIO.read(new File("./img/blackStone.png"));
			bOn = ImageIO.read(new File("./img/b_on.png"));
			bOff = ImageIO.read(new File("./img/b_off.png"));
			wOn = ImageIO.read(new File("./img/w_on.png"));
			wOff = ImageIO.read(new File("./img/w_off.png"));
			ban = ImageIO.read(new File("./img/ban.png"));
			Wwin = ImageIO.read(new File("./img/Wwin.png"));
			Bwin = ImageIO.read(new File("./img/Bwin.png"));
			Wlose = ImageIO.read(new File("./img/Wlose.png"));
			Blose = ImageIO.read(new File("./img/Blose.png"));
			medal = ImageIO.read(new File("./img/medal.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JButton startbtn = new JButton("START");
		startbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = true;
				state[9][9] = true;
				bState[9][9] = true;
				color[9][9] = 0;
				locationx[9][9] = 298;
				locationy[9][9] = 298;
				repaint();
				startbtn.setVisible(false);
			}
		});
		setLayout(null);
		startbtn.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 20));
		startbtn.setForeground(Color.BLACK);
		startbtn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		startbtn.setBounds(650, 300, 100, 30);
		add(startbtn);

		addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		loca = e.getPoint();
		System.out.println(loca);

		if (loca.x < 20)
			x = 0;
		else if (loca.x > 592)
			x = 18 * 32;
		else if ((loca.x - 20) % 32 < 16) {
			x = (loca.x - 20) / 32 * 32;
		} else if ((loca.x - 20) % 32 >= 16) {
			x = (loca.x - 20) / 32 * 32 + 32;
		}
		if (loca.y < 20)
			y = 0;
		else if (loca.y > 592)
			y = 18 * 32;
		else if ((loca.y - 20) % 32 < 16) {
			y = (loca.y - 20) / 32 * 32;
		} else if ((loca.y - 20) % 32 >= 16) {
			y = (loca.y - 20) / 32 * 32 + 32;
		}
		if (start == true) {
			if (state[y / 32][x / 32] == true) {
				JOptionPane.showMessageDialog(null, "이미 돌이 있는 자리에는 다시 둘 수 없습니다.", "알림창", JOptionPane.PLAIN_MESSAGE);
			} else {
				locationx[y / 32][x / 32] = x + 10;
				locationy[y / 32][x / 32] = y + 10;
				state[y / 32][x / 32] = true;

				if (BorW == true) {
					mode = "black";
					black++;
					color[y / 32][x / 32] = 0;
					bState[y / 32][x / 32] = true;
					if (black > 1) {
						black = 0;
						BorW = false;
					}
					int count = 0;
					for (int i = 0; i < 19; i++) { // ㅡ자라인
						if (bState[y / 32][i] == true) {
							count++;
							System.out.println("bcount1 " + count);
							if (count >= 6) {
								end = true;
								win = "흑돌 ";
							}
						} else
							count = 0;
					}
					for (int i = 0; i < 19; i++) { // ㅣ자라인
						if (bState[i][x / 32] == true) {
							count++;
							System.out.println("bcount2 " + count);

							if (count >= 6) {
								end = true;
								win = "흑돌 ";
							}
						} else
							count = 0;
					}
					xsuby = x / 32 - y / 32;
					ysubx = y / 32 - x / 32;
					int i = 0;
					while (true) {
						if (xsuby + i >= 19 || i >= 19 || ysubx + i >= 19)
							break;
						if ((x / 32) > (y / 32)) {
							if (bState[i][xsuby + i] == true) {
								count++;
								System.out.println("bcount3 " + count);

								if (count >= 6) {
									end = true;
									win = "흑돌 ";
								}
							} else
								count = 0;
						} else {
							if (bState[ysubx + i][i] == true) {
								count++;
								System.out.println("bcount3 " + count);

								if (count >= 6) {
									end = true;
									win = "흑돌 ";

								}
							} else
								count = 0;
							std += 20;
						}
						i++;

					}
					i = 0;
					xsuby = x / 32 + y / 32;
					while (true) {
						if (xsuby - i < 0 || i >= 19 || xsuby - 18 + i >= 19 || 18 - i < 0)
							break;
						if ((x / 32) + (y / 32) < 18) {
							if (bState[i][xsuby - i] == true) {
								count++;
								System.out.println("bcount4 " + count);

								if (count >= 6) {
									end = true;
									win = "흑돌 ";
								}
							} else
								count = 0;
						} else {
							if (bState[xsuby - 18 + i][18 - i] == true) {
								count++;
								System.out.println("bcount4 " + count);

								if (count >= 6) {
									end = true;
									win = "흑돌 ";
								}
							} else
								count = 0;
						}
						i++;

					}
					Play("sound/click.wav");
					repaint();
					if (end == true)
						JOptionPane.showMessageDialog(null, win + "이 승리했습니다!", "알림창", JOptionPane.PLAIN_MESSAGE);
// -----------------------------------------------------------black 여기까지 -------------------------------

				} else {
					mode = "white";
					white++;
					color[y / 32][x / 32] = 1;
					wState[y / 32][x / 32] = true;
					if (white > 1) {
						white = 0;
						BorW = true;
					}
					int count = 0;
					for (int i = 0; i < 19; i++) { // ㅡ자라인
						if (wState[y / 32][i] == true) {
							count++;
							System.out.println("wcount1 " + count);
							if (count >= 6) {
								end = true;
								win = "백돌 ";
							}
						} else
							count = 0;
					}
					for (int i = 0; i < 19; i++) { // ㅣ자라인
						if (wState[i][x / 32] == true) {
							count++;
							System.out.println("wcount2 " + count);
							if (count >= 6) {
								end = true;
								win = "백돌 ";
							}
						} else
							count = 0;
					}
					xsuby = x / 32 - y / 32;
					ysubx = y / 32 - x / 32;
					int i = 0;
					while (true) { // \라인
						if (xsuby + i >= 19 || i >= 19 || ysubx + i >= 19)
							break;
						if ((x / 32) > (y / 32)) {
							if (wState[i][xsuby + i] == true) {
								count++;
								System.out.println("wcount3 " + count);
								if (count >= 6) {
									end = true;
									win = "백돌 ";
								}
							} else
								count = 0;
						} else {
							if (wState[ysubx + i][i] == true) {
								count++;
								System.out.println("wcount3 " + count);
								if (count >= 6) {
									end = true;
									win = "백돌 ";
								}
							} else
								count = 0;
							std += 20;
						}
						i++;
					}
					i = 0;
					xsuby = x / 32 + y / 32;
					while (true) { // /라인
						if (xsuby - i < 0 || i >= 19 || xsuby - 18 + i >= 19 || 18 - i < 0)
							break;
						if ((x / 32) + (y / 32) < 18) {
							if (wState[i][xsuby - i] == true) {
								count++;
								System.out.println("wcount4 " + count);
								if (count >= 6) {
									end = true;
									win = "백돌 ";
								}
							} else
								count = 0;
						} else {
							if (wState[xsuby - 18 + i][18 - i] == true) {
								count++;
								System.out.println("wcount4 " + count);
								if (count >= 6) {
									end = true;
									win = "백돌 ";
								}
							} else
								count = 0;
						}
						i++;
					}
				}
				Play("./sound/click.wav");
				repaint();
				if (end == true)
					JOptionPane.showMessageDialog(null, win + "이 승리했습니다!", "알림창", JOptionPane.PLAIN_MESSAGE);
			}
		} else {
			if (startcount >= 5) {
				JOptionPane.showMessageDialog(null, "착수금지점은 5개까지만 둘 수 있습니다. ", "알림창", JOptionPane.PLAIN_MESSAGE);
			} else {
				if (y / 32 == 9 && x / 32 == 9) {
					JOptionPane.showMessageDialog(null, "정중앙에 착수금지점을 둘 수 없습니다. ", "알림창", JOptionPane.PLAIN_MESSAGE);
				} else {
					locationx[y / 32][x / 32] = x + 10;
					locationy[y / 32][x / 32] = y + 10;
					state[y / 32][x / 32] = true;
					color[y / 32][x / 32] = 2;
					startcount++;
					repaint();
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
