package ConnectGame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ScrollPaneConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {
	
	private JLabel mPage,fight,bag,SE,drawCard,setting,selection_bar; //[主頁][出擊][背包][強化/進化][抽抽]
	private JLabel bgLabel,tempBgLabel,loginbg,pairingbg;
	private JLabel Account,forage,bone;
	private JLabel textarea,textfild,enter_buttom;
	private JLabel singlePlayer_bt,onlineFight_bt;
	private JLabel pairing_nowPeople,pairing_slash,pairing_maxPeople;
	private boolean Ftextarea = false;
	private boolean isWaitForMutilyPlay = false;
	private static JTextArea textArea;
	private static JTextField enterArea;
	private static JScrollPane g;
	private String bgm_homepage_Sound_url = "sound/bgm_homepage.wav";
	private String bt_entered_Sound_url = "sound/bt_entered.wav";
	private String bt_click_Sound_url = "sound/bt_click.wav";
	private ImageIcon maintaining_bg_p = new ImageIcon(getClass().getResource("/png/maintaining_bg.png"));
	private ImageIcon reference_bg_p = new ImageIcon(getClass().getResource("/png/reference_bg.png"));
	private ImageIcon loginBg_p = new ImageIcon(getClass().getResource("/png/welcome.gif"));
	private ImageIcon bg = new ImageIcon(getClass().getResource("/png/bg.png"));
	private ImageIcon blackBackground_p = new ImageIcon(getClass().getResource("/png/b_bg.png"));
	private ImageIcon mainP_p = new ImageIcon(getClass().getResource("/png/home.png"));
	private ImageIcon fight_p = new ImageIcon(getClass().getResource("/png/fight.png"));
	private ImageIcon bag_p = new ImageIcon(getClass().getResource("/png/bagpack.png"));
	private ImageIcon upgrade_p = new ImageIcon(getClass().getResource("/png/upgrade.png"));
	private ImageIcon drawCard_p = new ImageIcon(getClass().getResource("/png/pick_up_card.png"));
	private ImageIcon setting_p = new ImageIcon(getClass().getResource("/png/setting.png"));
	private ImageIcon selection_bar_bg_p = new ImageIcon(getClass().getResource("/png/selection_bar_bg.png"));
	private ImageIcon textarea_small_p = new ImageIcon(getClass().getResource("/png/textarea_small.png"));
	private ImageIcon textarea_big_p = new ImageIcon(getClass().getResource("/png/textarea_big.png"));
	private ImageIcon textfild_p = new ImageIcon(getClass().getResource("/png/textfild.png"));
	private ImageIcon enter_buttom_p = new ImageIcon(getClass().getResource("/png/enter_buttom.png"));
	private ImageIcon enter_buttom_pressed_p = new ImageIcon(getClass().getResource("/png/enter_buttom_pressed.png"));
	private ImageIcon fight_bt_sig_p = new ImageIcon(getClass().getResource("/png/fight_bt_sig.png"));
	private ImageIcon fight_bt_sig_pressed_p = new ImageIcon(getClass().getResource("/png/fight_bt_sig_pressed.png"));
	private ImageIcon fight_bt_mu_p = new ImageIcon(getClass().getResource("/png/fight_bt_mu.png"));
	private ImageIcon fight_bt_mu_pressed_p = new ImageIcon(getClass().getResource("/png/fight_bt_mu_pressed.png"));
	private ImageIcon pairing_bg_p = new ImageIcon(getClass().getResource("/png/pairing/pairing_bg.gif"));
	private ImageIcon pairing_txt_p = new ImageIcon(getClass().getResource("/png/pairing/pairing_txt.gif"));
	private ImageIcon pairing_txt_1_p = new ImageIcon(getClass().getResource("/png/pairing/pairing_txt_1.png"));
	private ImageIcon pairing_txt_2_p = new ImageIcon(getClass().getResource("/png/pairing/pairing_txt_2.png"));
	private ImageIcon pairing_txt_3_p = new ImageIcon(getClass().getResource("/png/pairing/pairing_txt_3.png"));
	private ImageIcon pairing_txt_4_p = new ImageIcon(getClass().getResource("/png/pairing/pairing_txt_4.png"));
	private ImageIcon pairing_txt_slash_p = new ImageIcon(getClass().getResource("/png/pairing/pairing_txt_slash.png"));
	private FightPanel FP = null;//[抽抽]
	private DrawCardPanel DP = null;//[抽抽]
	private ConnectToServer CTS = null;
	private musicPlayer bgm_homepage_Sound,bt_entered_Sound,bt_click_Sound;

	/**
	 * Create the frame.
	 */
	public MainWindow(ConnectToServer CTS) {
		 this.CTS = CTS;
		 setVisible(false);
		 setTitle("baobaoGame");
		 this.setResizable(false);
		 this.setBounds(400, 100, 1280, 720);
		 this.setVisible(true);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 getContentPane().setLayout(null);
		 
		 bgm_homepage_Sound = new musicPlayer(bgm_homepage_Sound_url);
		 bgm_homepage_Sound.setLoop();
		 
		 bt_entered_Sound = new musicPlayer(bt_entered_Sound_url);
		 
		 bt_click_Sound = new musicPlayer(bt_click_Sound_url);
		 
		 bgLabel = new JLabel();
		 bgLabel.setIcon(bg);
		 bgLabel.setBounds(0, 0, getContentPane().getWidth(), getContentPane().getHeight());
		 setContentPane(bgLabel);
		 
		 pairing_nowPeople = new JLabel(pairing_txt_1_p);
		 pairing_nowPeople.setBounds(550, 180, 135, 150);
		 pairing_nowPeople.setVisible(false);
		 getContentPane().add(pairing_nowPeople);
		 
		 pairing_slash = new JLabel(pairing_txt_slash_p);
		 pairing_slash.setBounds(600, 200, 197, 222);
		 pairing_slash.setVisible(false);
		 getContentPane().add(pairing_slash);
		 
		 pairing_maxPeople = new JLabel(pairing_txt_2_p);
		 pairing_maxPeople.setBounds(700, 300, 135, 150);
		 pairing_maxPeople.setVisible(false);
		 getContentPane().add(pairing_maxPeople);
		 
		 pairingbg = new JLabel(pairing_txt_p);
		 pairingbg.setBounds(0, 0, getWidth(), getHeight());
		 pairingbg.setVisible(false);
		 getContentPane().add(pairingbg);
		 
		 loginbg = new JLabel(loginBg_p);
		 loginbg.setBounds(0, 0, getWidth(), getHeight());
		 getContentPane().add(loginbg);
		 repaint();
		 
		 setloginBG slBG = new setloginBG();
		 slBG.start();
		 
		 singlePlayer_bt = new JLabel(fight_bt_sig_p);
		 //singlePlayer_bt.setIcon(fight_bt_p);
		 singlePlayer_bt.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mousePressed(MouseEvent e) {
			 		singlePlayer_bt.setIcon(fight_bt_sig_pressed_p);
			 	}
			 	@Override
			 	public void mouseReleased(MouseEvent e) {
			 		singlePlayer_bt.setIcon(fight_bt_sig_p);
			 		if(!isWaitForMutilyPlay && e.getX()>=0 && e.getX() <=singlePlayer_bt.getWidth() && e.getY() >= 0 && e.getY() <= singlePlayer_bt.getHeight()) {
				    	  bgm_homepage_Sound.setPause();
				    	  setFighting();
			 		}
			 	}
			 });
		 singlePlayer_bt.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		 singlePlayer_bt.setBounds(250, 193, 306, 334);
		 singlePlayer_bt.setVisible(false);
		 getContentPane().add(singlePlayer_bt);
			
		 onlineFight_bt = new JLabel(fight_bt_mu_p);
		 //onlineFight_bt.setIcon(fight_bt_p);
		 onlineFight_bt.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mousePressed(MouseEvent e) {
			 		onlineFight_bt.setIcon(fight_bt_mu_pressed_p);
			 	}
			 	@Override
			 	public void mouseReleased(MouseEvent e) {
			 		onlineFight_bt.setIcon(fight_bt_mu_p);
			 		if( !isWaitForMutilyPlay && e.getX()>=0 && e.getX() <=onlineFight_bt.getWidth() && e.getY() >= 0 && e.getY() <= onlineFight_bt.getHeight()) {
			 			  isWaitForMutilyPlay = true;
			 			  //bgm_homepage_Sound.setPause();
			 			  setPairing();
				    	  CTS.WaitForMutilyPlay(Account.getText());
			 		}			 		
			 	}
			 });
		 onlineFight_bt.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		 onlineFight_bt.setBounds(724, 193, 306, 334);
		 onlineFight_bt.setVisible(false);
		 getContentPane().add(onlineFight_bt);
		 
		 tempBgLabel = new JLabel();
		 tempBgLabel.setIcon( blackBackground_p);
		 tempBgLabel.setBounds(0, 0, getWidth(), getHeight());
		 tempBgLabel.addMouseListener(new MouseAdapter() {
			 	
			 	@Override
			 	public void mouseReleased(MouseEvent e) {
			 		if(!isWaitForMutilyPlay) {
			 			setMpage();
			 			tempBgLabel.setIcon( blackBackground_p);
			 		}	 		
			 	}
			 });
		 tempBgLabel.setVisible(false);
		 getContentPane().add(tempBgLabel);
		 
		 enterArea = new JTextField("");
		 enterArea.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyReleased(KeyEvent e) {
		 		if(enterArea.getText().length()>=30) {
		 			enterArea.setText(enterArea.getText().substring(0, 30));
		 		}
		 		
		 		if(e.getKeyCode() == KeyEvent.VK_ENTER && !enterArea.getText().equals("")) {
		 			CTS.MgTSOutput(enterArea.getText());
		 			enterArea.setText("");
		 		}
		 	}
		 });
		 enterArea.setFont(new Font("微軟正黑體", Font.BOLD, 19));
		 enterArea.setBounds(18, 642, 260, 27);
		 enterArea.setBorder(null);
		 enterArea.setVisible(false);
		 getContentPane().add(enterArea);
		 
		 textfild = new JLabel(textfild_p);
		 textfild.setBounds(15, 640, 265, 30);
		 textfild.setVisible(false);
		 getContentPane().add(textfild);
		 
		 enter_buttom = new JLabel(enter_buttom_p);
		 enter_buttom.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mousePressed(MouseEvent e) {
		 		enter_buttom.setIcon(enter_buttom_pressed_p);
		 	}
		 	@Override
		 	public void mouseReleased(MouseEvent e) {
		 		if(!enterArea.getText().equals("")) {
		 			CTS.MgTSOutput(enterArea.getText());
		 			enterArea.setText("");
		 		}
		 		enter_buttom.setIcon(enter_buttom_p);
		 	}
		 });
		 enter_buttom.setBounds(290, 637, 75, 36);
		 enter_buttom.setVisible(false);
		 getContentPane().add(enter_buttom);
		 
		 textArea = new JTextArea("");
		 textArea.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		 textArea.setEditable(false);
		 textArea.setOpaque(false);
		 textArea.addMouseListener(new MouseAdapter(){
		      public void mouseReleased(MouseEvent e){
		    	  if(Ftextarea == true) {
		    		  setTextarea(!Ftextarea);
		    		  if(CTS.getIsFirstMsgSend()) {
		    			  setTextAreaUpDate();
		    		  }
		    	  }else {
		    		  setTextarea(!Ftextarea);
		    		  if(CTS.getIsFirstMsgSend()) {
		    			  setTextAreaUpDate();
		    		  }
		    	  }
		        }
		      
		      });
		 g = new JScrollPane(textArea);
		 g.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		 g.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		 g.setOpaque(false);
		 g.getViewport().setOpaque(false);
		 g.setBorder (null);
		 g.setBackground(new Color(0,0,0));
	     g.setBounds(15, 580, 350, 85);
	     g.revalidate();
	     getContentPane().add(g);
		 
		 textarea = new JLabel(textarea_small_p);
		 textarea.setBounds(10, 570, 360, 104);
		 textarea.addMouseListener(new MouseAdapter(){
		      public void mouseReleased(MouseEvent e){
		    	  if(Ftextarea == true) {
		    		  setTextarea(!Ftextarea);
		    		  if(CTS.getIsFirstMsgSend()) {
		    			  setTextAreaUpDate();
		    		  }
		    		  Ftextarea = false;
		    	  }else {
		    		  setTextarea(!Ftextarea);
		    		  if(CTS.getIsFirstMsgSend()) {
		    			  setTextAreaUpDate();
		    		  }
		    		  Ftextarea = true;
		    	  }
		        }
		      
		      });
		 getContentPane().add(textarea);
		 textarea.repaint();
		 
		 Account = new JLabel();
		 Account.setHorizontalAlignment(SwingConstants.CENTER);
		 Account.setBounds(75, 2, 300, 35);
		 Account.setFont(new Font("OCR A Extended", Font.BOLD, 30));
		 getContentPane().add(Account);
		 
		 forage = new JLabel();
		 forage.setHorizontalAlignment(SwingConstants.RIGHT);
		 forage.setBounds(455, 18, 100, 35);
		 forage.setFont(new Font("OCR A Extended", Font.BOLD, 32));
		 getContentPane().add(forage);
		 
		 bone = new JLabel();
		 bone.setHorizontalAlignment(SwingConstants.RIGHT);
		 bone.setBounds(680, 18, 100, 35);
		 bone.setFont(new Font("OCR A Extended", Font.BOLD, 32));
		 getContentPane().add(bone);
		 
		 mPage = new JLabel(mainP_p);
		 mPage.addMouseListener(new MouseAdapter(){
		      public void mouseClicked(MouseEvent e){
		    	  bt_click_Sound.start();
		    	  setMpage();
		        }
		      
		      public void mouseEntered(MouseEvent e) {
		    	  bt_entered_Sound.start();
		    	  mPage.setLocation(461, 500);
		      }
		      
		      public void mouseExited(MouseEvent e) {
		    	  mPage.setLocation(450, 550);
		      }
		      });
		 mPage.setBounds(450, 550, 156, 255);
		 getContentPane().add(mPage);
		 
		 fight = new JLabel(fight_p);
		 fight.addMouseListener(new MouseAdapter(){
		      public void mouseClicked(MouseEvent e){
		    	  bt_click_Sound.start();
		    	  setFPpage();
		        }
		      
		      public void mouseEntered(MouseEvent e) {
		    	  bt_entered_Sound.start();
		    	  fight.setLocation(586, 500);
		      }
		      
		      public void mouseExited(MouseEvent e) {
		    	  fight.setLocation(575, 550);
		      }
		      });
		 fight.setBounds(575, 550, 156, 255);
		 getContentPane().add(fight);
		 
		 bag = new JLabel(bag_p);
		 bag.addMouseListener(new MouseAdapter(){
		      public void mouseClicked(MouseEvent e){
		    	  bt_click_Sound.start();
		    	  tempBgLabel.setIcon(maintaining_bg_p);
		    	  tempBgLabel.setVisible(true);
		        }
		      
		      public void mouseEntered(MouseEvent e) {
		    	  bt_entered_Sound.start();
		    	  bag.setLocation(711, 500);
		      }
		      
		      public void mouseExited(MouseEvent e) {
		    	  bag.setLocation(700, 550);
		      }
		      });
		 bag.setBounds(700, 550, 156, 255);
		 getContentPane().add(bag);
		 
		 SE = new JLabel(upgrade_p);
		 SE.addMouseListener(new MouseAdapter(){
		      public void mouseClicked(MouseEvent e){
		    	  bt_click_Sound.start();
		    	  tempBgLabel.setIcon(maintaining_bg_p);
		    	  tempBgLabel.setVisible(true);
		        }
		      
		      public void mouseEntered(MouseEvent e) {
		    	  bt_entered_Sound.start();
		    	  SE.setLocation(836, 500);
		      }
		      
		      public void mouseExited(MouseEvent e) {
		    	  SE.setLocation(825, 550);
		      }
		      });
		 SE.setBounds(825, 550, 156, 255);
		 getContentPane().add(SE);
		 
		 drawCard = new JLabel(drawCard_p);
		 drawCard.addMouseListener(new MouseAdapter(){
		      public void mouseClicked(MouseEvent e){
		    	  bt_click_Sound.start();
		    	  setDCpage();
		        }
		      
		      public void mouseEntered(MouseEvent e) {
		    	  bt_entered_Sound.start();
		    	  drawCard.setLocation(961, 500);
		      }
		      
		      public void mouseExited(MouseEvent e) {
		    	  drawCard.setLocation(950, 550);
		      }
		      });
		 drawCard.setBounds(950, 550, 156, 255);
		 getContentPane().add(drawCard);
		 
		 setting = new JLabel(setting_p);
		 setting.addMouseListener(new MouseAdapter(){
		      public void mouseClicked(MouseEvent e){
		    	  bt_click_Sound.start();
		    	  tempBgLabel.setIcon(reference_bg_p);
		    	  tempBgLabel.setVisible(true);
		        }
		      
		      public void mouseEntered(MouseEvent e) {
		    	  bt_entered_Sound.start();
		    	  setting.setLocation(1086, 500);
		      }
		      
		      public void mouseExited(MouseEvent e) {
		    	  setting.setLocation(1075, 550);
		      }
		      });
		 setting.setBounds(1075, 550, 156, 255);
		 getContentPane().add(setting);
		 
		 selection_bar = new JLabel(selection_bar_bg_p);
		 selection_bar.setBounds(420, 590, 1038, 96);
		 getContentPane().add(selection_bar);
		 
		 DP = new DrawCardPanel(350,70,CTS);
		 DP.setVisible(false);
		 getContentPane().add(DP);
		 
		 FP = new FightPanel(0,0,CTS,this);
		 FP.setVisible(false);
		 getContentPane().add(FP);
		 
		 getContentPane().repaint();
		 //設定背景圖片
		 addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ESCAPE && isWaitForMutilyPlay) {
						CTS.CancleForWaiting(Account.getText());
						isWaitForMutilyPlay = false;
						setMpage();
					}
					if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						setMpage();
					}
				}
		});
		 
		 
	}
	
	public void setFirstTextArea(String S) {
		if(S.length()>=35) {
			  textArea.append(S.substring(0,35)+"\n");
			  textArea.append(S.substring(35));
		  }else {
			  textArea.append(S);
		  }
		  textArea.setCaretPosition(textArea.getDocument().getLength());
	  }
	
	public void setTextArea(String S) {
		  if(S.length()>=40) {
			  textArea.append("\n"+S.substring(0,40)+"\n");
			  textArea.append(S.substring(40));
		  }else {
			  textArea.append("\n"+S);
		  }
		  textArea.setCaretPosition(textArea.getDocument().getLength());
	  }
	
	public void setTextAreaUpDate() {
		textArea.append(" ");
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	public void setPairing() {
		setWaitingBG swbg = new setWaitingBG();
		swbg.start();
	}
	
	public void setPairing_nowPeople(int n){
		if(n == 2) {
			pairing_nowPeople.setIcon(pairing_txt_2_p);
		}else if(n == 3) {
			pairing_nowPeople.setIcon(pairing_txt_3_p);
		}else if(n == 4) {
			pairing_nowPeople.setIcon(pairing_txt_4_p);
		}else {
			pairing_nowPeople.setIcon(pairing_txt_1_p);
		}
	}
	
	public void setMpage() {
		this.requestFocus();
		
		loginbg.setVisible(false);
		pairingbg.setVisible(false);
		pairing_nowPeople.setVisible(false);
		pairing_slash.setVisible(false);
		pairing_maxPeople.setVisible(false);
		
		bgm_homepage_Sound.setContinue();
		Account.setVisible(true);
		forage.setVisible(true);
		bone.setVisible(true);
		
		textarea.setVisible(true);
		textArea.setVisible(true);
		
		mPage.setVisible(true);
		fight.setVisible(true);
		bag.setVisible(true);
		SE.setVisible(true);
		drawCard.setVisible(true);
		setting.setVisible(true);
		selection_bar.setVisible(true);
		
		singlePlayer_bt.setVisible(false);
		onlineFight_bt.setVisible(false);
		tempBgLabel.setVisible(false);
		DP.setVisible(false);
		FP.setVisible(false);
		setTextarea(false);
	}
	
	public void setDCpage() {
		DP.setVisible(true);
		
		singlePlayer_bt.setVisible(false);
		onlineFight_bt.setVisible(false);
		tempBgLabel.setVisible(false);
		FP.setVisible(false);
		setTextarea(false);
		
	}
	
	public void setFighting() {
		isWaitForMutilyPlay = false;
		loginbg.setVisible(false);
		pairingbg.setVisible(false);
		pairing_nowPeople.setVisible(false);
		pairing_slash.setVisible(false);
		pairing_maxPeople.setVisible(false);
		FP = null;
		FP = new FightPanel(0,0,CTS,this);
		getContentPane().add(FP);
		FP.setVisible(true);
		FP.setRequestFocusEnabled(true);
		FP.requestFocus();
		
		Account.setVisible(false);
		forage.setVisible(false);
		bone.setVisible(false);
		
		setTextarea(false);
		textarea.setVisible(false);
		textArea.setVisible(false);
		
		mPage.setVisible(false);
		fight.setVisible(false);
		bag.setVisible(false);
		SE.setVisible(false);
		drawCard.setVisible(false);
		setting.setVisible(false);
		selection_bar.setVisible(false);
		
		singlePlayer_bt.setVisible(false);
		onlineFight_bt.setVisible(false);
		tempBgLabel.setVisible(false);
	}
	
	public void setMultifyFP(boolean T) {
		FP.setMultifyPlayer(T);
	}
	
	public void setMultifyFP_OpponentID(String idName) {
		FP.setOpponentID(idName);
	}
	
	public void setMultifyFP_OpponentScore(String point) {
		FP.setOpponentScore(Integer.parseInt(point));
	}
	
	public void setMultifyFP_gameMusic(int musicChoice) {
		FP.setMusicChoice(musicChoice);
	}
	
	public void setMultifyFP_gameStart() {
		FP.startPlaying();
	}
	
	public void setMultifyFP_gameEnd() {
		FP.endPlaying();
	}
	
	public void setMultifyFP_time(String m,String s) {
		FP.setTime(Integer.parseInt(m), Integer.parseInt(s));
	}
	
	public void setFPpage() {
		
		singlePlayer_bt.setVisible(true);
		onlineFight_bt.setVisible(true);
		tempBgLabel.setVisible(true);
		
		FP.setVisible(false);
		DP.setVisible(false);
		setTextarea(false);
	}
	
	public void setAccount(String account) {
		this.Account.setText(account);
	}
	
	public void setForage(String forage) {
		this.forage.setText(forage);
	}
	
	public void setBone(String bone) {
		this.bone.setText(bone);
	}
	/**
	 * true for big,false for small
	 * @param T
	 */
	public void setTextarea(boolean T) {
		if(T) {
			g.setBounds(15, 254, 350, 382);
			textarea.setBounds(10, 249, 360, 425);
			textarea.setIcon(textarea_big_p);
			textfild.setVisible(T);
			enterArea.setVisible(T);
			enter_buttom.setVisible(T);
			Ftextarea = T;
		}else {
			g.setBounds(15, 580, 350, 85);
			textarea.setBounds(10, 570, 360, 104);
			textarea.setIcon(textarea_small_p);
			textfild.setVisible(T);
			enterArea.setVisible(T);
			enter_buttom.setVisible(T);
			Ftextarea = T;
		}
	}
	
	public class setloginBG extends Thread{
		public setloginBG() {
		}
		
		public void run() {
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			loginbg.setVisible(false);
		}
	}
	
	public class setWaitingBG extends Thread{
		public setWaitingBG() {
		}
		
		public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				loginbg.setIcon(pairing_bg_p);
				loginbg.setVisible(true);
				pairingbg.setVisible(true);
				pairing_nowPeople.setVisible(true);
				pairing_slash.setVisible(true);
				pairing_maxPeople.setVisible(true);
			
		}
	}
}
