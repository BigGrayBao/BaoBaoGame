package ConnectGame;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DrawCardPanel extends JPanel{
	
	private ImageIcon maintaining_bg_small_p = new ImageIcon(getClass().getResource("/png/maintaining_bg_small.png"));
	
	public DrawCardPanel(int x,int y,ConnectToServer CTS) {
		this.setLocation(x,y);
		this.setSize(725, 475);
		this.setLayout(null);
		this.setOpaque(false);
		
		JPanel JS = new JPanel();
		JS.setPreferredSize(new Dimension(450, 2000)); //不能用setSize
		JS.setLayout(null);
		JS.setOpaque(false);
		
		JLabel dcWindow = new JLabel(maintaining_bg_small_p);
		dcWindow.setBorder (BorderFactory.createLineBorder(new Color(80, 80, 80),2));
		dcWindow.setBounds(25, 15, 600, 350);
		JS.add(dcWindow);
		
		JButton drawOne = new JButton("一回抽取");
		drawOne.setBounds(90, 385, 120, 45);
		JS.add(drawOne);
		
		JButton drawTen = new JButton("十回抽取");
		drawTen.setBounds(450, 385, 120, 45);
		JS.add(drawTen);
		
		JButton drawTen2 = new JButton("十回抽取");
		drawTen2.setBounds(450, 1420, 120, 45);
		JS.add(drawTen2);
		
		JScrollPane scrollPane = new JScrollPane(JS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 10, 650, 450);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		add(scrollPane);
		
		JScrollBar jsb = scrollPane.getVerticalScrollBar();
		
		JButton up = new JButton("/\\");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.getViewport().setViewPosition(new Point(0, 0));
			}
		});
		up.setBounds(670, 30, 50, 50);
		add(up);
		
		JButton down = new JButton("\\/");
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.getViewport().setViewPosition(new Point(0, 500));
			}
		});
		down.setBounds(670, 385, 50, 50);
		add(down);

	}
}
