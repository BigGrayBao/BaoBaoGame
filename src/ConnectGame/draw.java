package ConnectGame;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class draw extends JPanel implements Runnable{

	private ArrayList<String> original = new ArrayList<String>();
	private ArrayList<String> theNew = new ArrayList<String>();
	private ArrayList<Integer> x = new ArrayList<Integer>();
	private ArrayList<Integer> y = new ArrayList<Integer>();
	private int total;
	private int Different1 = -1,Different2 = -1;
	private boolean Different = false;
	
	public draw(ArrayList<String> og) {
		original = og;
		total = original.size();
		int tempX = 50;
		int tempY = 100;
		for(int n = 0;n<total;n++) {
			x.add(tempX);
			y.add(tempY);
			
			tempX = tempX + 50;
		}
		this.setSize(350, 300);
		this.setLocation(500, 300);
		setLayout(null);
	}
	
	public void paintIt() {
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() {
		
			if(Different) {	
				repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//數值設定
				int tempY = 100;
				int distanceX = (Different2 - Different1)*50/10;
				int distance = 0;
				int x1 = x.get(Different1);
				int x2 = x.get(Different2);
				//將Y座標往上移，做兩次
				for(int n = 0;n<2;n++) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tempY = tempY - 10;
					y.set(Different1, tempY);
					y.set(Different2, tempY);
					repaint();
				}
				//開始移動X座標，10次
				for(int n = 1;n<=10;n++) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					distance = distanceX*n;
					x.set(Different1, x1 + distance);
					x.set(Different2, x2 - distance);
					repaint();
				}
				//將Y座標放回去
				for(int n = 0;n<2;n++) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tempY = tempY + 10;
					y.set(Different1, tempY);
					y.set(Different2, tempY);
					repaint();
				}
				//移動完成後將數值放至正確位置
				String temp = original.get(Different1);
				original.set(Different1, original.get(Different2));
				original.set(Different2,temp);
				int tempx = x.get(Different1);
				x.set(Different1, x.get(Different2));
				x.set(Different2,tempx);
				Different = false;
			}
		
	}
	

	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		for(int n = 0;n<total ;n++) {
			g.drawString(original.get(n), x.get(n), y.get(n));
		}
		
	}
	

	public void setNew(ArrayList<String> tn) {
		theNew = tn;
		while(Different) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(!Different) {
			Different1 = -1;
			Different2 = -1;
			for(int n = 0;n<total;n++) {
				if(theNew.get(n).equals(original.get(n)) == false) {
					if(Different1 != -1) {
						Different2 = n;
						Different = true;
						continue;
					}
					Different1 = n;
				}
			}
			System.out.println("Different1 :" + Different1 + "\nDifferent2 :" + Different2 );
			paintIt();
		}
	}
	
	 public static void main(String args[]) {
		 ArrayList<String> a = new ArrayList<String>();
		 ArrayList<String> b = new ArrayList<String>();
		 ArrayList<String> c = new ArrayList<String>();
		 // a ->[5,4,1,3]
		 a.add("5");
		 a.add("4");
		 a.add("1");
		 a.add("3");
		 // b ->[1,4,5,3]
		 b.add("1");
		 b.add("4");
		 b.add("5");
		 b.add("3");
		 // c ->[3,4,5,1]
		 c.add("3");
		 c.add("4");
		 c.add("5");
		 c.add("1");
		 JFrame f = new JFrame();
		 draw d = new draw(a);
		 f.setSize(500, 500);
		 f.add(d);
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 d.setNew(b);
		 f.setVisible(true);
		 d.setNew(c);
		 f.setVisible(true);
	 }
}
