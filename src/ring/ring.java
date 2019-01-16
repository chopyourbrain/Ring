package ring;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

public class l3 extends JComponent {
	static JFrame f = new JFrame();;
	static JLabel l1;
	static JButton b1;
	static TextField t1;
	static JLabel l2;
	static String[] cn = { "X", "Y" };
	static Object[][] data = { { "8", "4" }, { "8", "5" }, { "8", "6" }, { "8", "13" }, { "8", "14" }, { "5", "9" },
			{ "11", "9" }, { "12", "9" }, { "13", "9" }, { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
			{ "", "" }, { "", "" },

	};

	static JTable tbl = new JTable(data, cn);
	public int maxx, maxy, dots = 0;
	public int maxr;
	public ArrayList<Integer[]> xy = new ArrayList<Integer[]>();
	public ArrayList<Integer[]> rom = new ArrayList<Integer[]>();
	public int h;

	public l3() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int r = 0;
				xy.clear();
				rom.clear();
				h = 10 * Integer.parseInt(t1.getText());
				while ((tbl.getValueAt(r, 0).toString() != "") & (tbl.getValueAt(r, 1).toString() != "")) {
					Integer[] coord = { Integer.parseInt(tbl.getValueAt(r, 0).toString()) * 10 + 50,
							Integer.parseInt(tbl.getValueAt(r, 1).toString()) * 10 + 80 };
					xy.add(coord);
					r++;
				}
				f.getGraphics().clearRect(0, 0, 300, 300);
				paint(f.getGraphics());
				int x1, x2, x3, y1, y2, y3, xa, ya, A, B, C, D, E, F, G;
				double yr;
				double xr;

				double rad;
				for (int i = 0; i < xy.size(); i++) {
					x1 = xy.get(i)[0];
					y1 = xy.get(i)[1];
					for (int j = 0; j < xy.size(); j++) {
						x2 = xy.get(j)[0];
						y2 = xy.get(j)[1];
						for (int m = 0; m < xy.size(); m++) {
							x3 = xy.get(m)[0];
							y3 = xy.get(m)[1];

							A = x2 - x1;
							B = y2 - y1;
							C = x3 - x1;
							D = y3 - y1;
							E = A * (x1 + x2) + B * (y1 + y2);
							F = C * (x1 + x3) + D * (y1 + y3);
							G = 2 * (A * (y3 - y2) - B * (x3 - x2));
							if (G != 0) {
								xr = (D * E - B * F) / G;
								yr = (A * F - C * E) / G;

								rad = (int) Math.sqrt(Math.pow((xr - x1), 2) + Math.pow((yr - y1), 2));
								int counter = 0;
								for (int p = 0; p < xy.size(); p++) {
									xa = xy.get(p)[0];
									ya = xy.get(p)[1];
									int f1 = (int) Math.sqrt(Math.pow((xr - xa), 2) + Math.pow((yr - ya), 2));
									if ((f1 > rad) && f1 < rad + h) {
										counter++;
									}
								}
								if (counter > dots) {
									maxx = (int) xr;
									maxy = (int) yr;
									maxr = (int) rad;
									dots = counter;
								}
							}
						}
					}
				}

				f.getGraphics().clearRect(0, 0, 300, 300);
				paint(f.getGraphics());

			}
		});
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		for (int i = 0; i < xy.size(); i += 1) {

			g.fillOval(xy.get(i)[0] - 4, xy.get(i)[1] - 4, 8, 8);
		}
		g.drawOval(maxx - maxr, maxy - maxr, 2 * maxr, 2 * maxr);
		maxr = maxr + h;
		g.drawOval(maxx - maxr, maxy - maxr, 2 * maxr, 2 * maxr);

	}

	public static void CreateGUI() {
		f.setLayout(null);
		b1 = new JButton("Run");
		l1 = new JLabel("X");
		l2 = new JLabel("Y");
		t1 = new TextField("H");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b1.setBounds(300, 330, 100, 25);
		l1.setBounds(320, 15, 30, 50);
		l2.setBounds(380, 15, 30, 50);
		tbl.setBounds(300, 50, 100, 400);
		t1.setBounds(260, 330, 30, 30);
		f.add(l1);
		f.add(l2);
		f.add(b1);
		f.add(tbl);
		f.add(t1);
		f.add(new l3());
		f.setSize(420, 400);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		CreateGUI();
	}
}
