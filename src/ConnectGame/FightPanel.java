package ConnectGame;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class FightPanel extends JPanel {
	
	private JLabel time_min,time_dot,time_sec_D,time_sec_T;
	private JLabel opponentID_label,opponentScore_label;
	private JLabel opPoint_D1_label,opPoint_D2_label,opPoint_D3_label,opPoint_D4_label,opPoint_D5_label,opPoint_D6_label;
	private JLabel pointLabel,readyLabel,countDownLabel;
	private JLabel comboCounter_D_label,comboCounter_T_label,comboCounter_H_label,comboCounterlabel,comboLabel,comboStateLabel;
	private JLabel point_D1_label,point_D2_label,point_D3_label,point_D4_label,point_D5_label,point_D6_label;
	private JLabel maxCombo_label,accuracy_label,rank_label;
	private JLabel temp_bg,rank_bg,bg,time_bg;
	private JLabel big_gray_bao;
	private JLabel setting,pause_back,pause_continue,pause_retry;
	private JLabel rangeLabel,success_rangeLabel,successEx_rangeLabel,target;
	private String applause_Sound_url = "sound/applause.wav";
	private int point = 0,pressCount = 0,pressSuccessCount = 0,combo = 0,maxCombo = 0,musicChoice = 0;
	private boolean zxc_success = false,ldr_success = false;
	private boolean isStart = false;
	private boolean isLoading = false;
	private boolean isCounting = false;
	private boolean isPause = false;
	private boolean isMultifyPlayer = false;
	private String pressType,pressTypeEx;
	private ImageIcon spinner_big_bao_gray_gif_p = new ImageIcon(getClass().getResource("/png/fightpage/character/spinner_big_bao-gray.gif"));
	private ImageIcon spinner_big_bao_gray_png_p = new ImageIcon(getClass().getResource("/png/fightpage/character/spinner_big_bao-gray.png"));
	private ImageIcon back_bt_p = new ImageIcon(getClass().getResource("/png/fightpage/back_bt.png"));
	private ImageIcon back_bt_pressed_p = new ImageIcon(getClass().getResource("/png/fightpage/back_bt_pressed.png"));
	private ImageIcon pause_overlay_p = new ImageIcon(getClass().getResource("/png/fightpage/pause/pause_overlay.png"));
	private ImageIcon pause_back_p = new ImageIcon(getClass().getResource("/png/fightpage/pause/pause_back.png"));
	private ImageIcon pause_back_entered_p = new ImageIcon(getClass().getResource("/png/fightpage/pause/pause_back_entered.png"));
	private ImageIcon pause_continue_p = new ImageIcon(getClass().getResource("/png/fightpage/pause/pause_continue.png"));
	private ImageIcon pause_continue_entered_p = new ImageIcon(getClass().getResource("/png/fightpage/pause/pause_continue_entered.png"));
	private ImageIcon pause_retry_p = new ImageIcon(getClass().getResource("/png/fightpage/pause/pause_retry.png"));
	private ImageIcon pause_retry_entered_p = new ImageIcon(getClass().getResource("/png/fightpage/pause/pause_retry_entered.png"));
	private ImageIcon nowLoading_p = new ImageIcon(getClass().getResource("/png/nowLoading.gif"));
	private ImageIcon presskey_txt_p = new ImageIcon(getClass().getResource("/png/fightpage/presskey_txt.gif"));
	private ImageIcon count1_p = new ImageIcon(getClass().getResource("/png/fightpage/count1.png"));
	private ImageIcon count2_p = new ImageIcon(getClass().getResource("/png/fightpage/count2.png"));
	private ImageIcon count3_p = new ImageIcon(getClass().getResource("/png/fightpage/count3.png"));
	private ImageIcon fight_bg_p = new ImageIcon(getClass().getResource("/png/fightpage/fight_bg.png"));
	private ImageIcon time_bg_p = new ImageIcon(getClass().getResource("/png/fightpage/time/time_bg.png"));
	private ImageIcon time_dot_p = new ImageIcon(getClass().getResource("/png/fightpage/time/dot.png"));
	private ImageIcon time_0_p = new ImageIcon(getClass().getResource("/png/fightpage/time/time-0.png"));
	private ImageIcon time_1_p = new ImageIcon(getClass().getResource("/png/fightpage/time/time-1.png"));
	private ImageIcon time_2_p = new ImageIcon(getClass().getResource("/png/fightpage/time/time-2.png"));
	private ImageIcon time_3_p = new ImageIcon(getClass().getResource("/png/fightpage/time/time-3.png"));
	private ImageIcon time_4_p = new ImageIcon(getClass().getResource("/png/fightpage/time/time-4.png"));
	private ImageIcon time_5_p = new ImageIcon(getClass().getResource("/png/fightpage/time/time-5.png"));
	private ImageIcon time_6_p = new ImageIcon(getClass().getResource("/png/fightpage/time/time-6.png"));
	private ImageIcon time_7_p = new ImageIcon(getClass().getResource("/png/fightpage/time/time-7.png"));
	private ImageIcon time_8_p = new ImageIcon(getClass().getResource("/png/fightpage/time/time-8.png"));
	private ImageIcon time_9_p = new ImageIcon(getClass().getResource("/png/fightpage/time/time-9.png"));
	private ImageIcon score_0_p = new ImageIcon(getClass().getResource("/png/fightpage/combo/score-0.png"));
	private ImageIcon score_1_p = new ImageIcon(getClass().getResource("/png/fightpage/combo/score-1.png"));
	private ImageIcon score_2_p = new ImageIcon(getClass().getResource("/png/fightpage/combo/score-2.png"));
	private ImageIcon score_3_p = new ImageIcon(getClass().getResource("/png/fightpage/combo/score-3.png"));
	private ImageIcon score_4_p = new ImageIcon(getClass().getResource("/png/fightpage/combo/score-4.png"));
	private ImageIcon score_5_p = new ImageIcon(getClass().getResource("/png/fightpage/combo/score-5.png"));
	private ImageIcon score_6_p = new ImageIcon(getClass().getResource("/png/fightpage/combo/score-6.png"));
	private ImageIcon score_7_p = new ImageIcon(getClass().getResource("/png/fightpage/combo/score-7.png"));
	private ImageIcon score_8_p = new ImageIcon(getClass().getResource("/png/fightpage/combo/score-8.png"));
	private ImageIcon score_9_p = new ImageIcon(getClass().getResource("/png/fightpage/combo/score-9.png"));
	private ImageIcon combo_p = new ImageIcon(getClass().getResource("/png/fightpage/combo/ranking-maxcombo.png"));
	private ImageIcon ranking_panel_p = new ImageIcon(getClass().getResource("/png/fightpage/ranking/ranking_panel.png"));
	private ImageIcon ranking_s_p = new ImageIcon(getClass().getResource("/png/fightpage/ranking/ranking_s.png"));
	private ImageIcon ranking_a_p = new ImageIcon(getClass().getResource("/png/fightpage/ranking/ranking_a.png"));
	private ImageIcon ranking_b_p = new ImageIcon(getClass().getResource("/png/fightpage/ranking/ranking_b.png"));
	private ImageIcon ranking_c_p = new ImageIcon(getClass().getResource("/png/fightpage/ranking/ranking_c.png"));
	private ImageIcon ranking_d_p = new ImageIcon(getClass().getResource("/png/fightpage/ranking/ranking_d.png"));
	private MainWindow Mw;
	private ConnectToServer CTS;
	private comboState cs;
	private musicPlayer bgm_fighting_Sound,applause_Sound;

	public FightPanel(int x,int y,ConnectToServer CTS,MainWindow Mw) {
		this.enableInputMethods (false);
		this.Mw = Mw;
		this.CTS = CTS;
		this.setLocation(x,y);
		this.setSize(1280, 720);
		this.setLayout(null);
		pause_back = new JLabel(pause_back_p);
		pause_back.setBounds(120, 473, 520, 203);
		pause_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pause_back.setIcon(pause_back_p);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getX()>=0 && e.getX() <=pause_back.getWidth() && e.getY() >= 0 && e.getY() <= pause_back.getHeight()) {
					
					if(isMultifyPlayer) {
						
					}
					
					endLoadingBg elbg = new endLoadingBg();
					elbg.start();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pause_back.setIcon(pause_back_entered_p);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pause_back.setIcon(pause_back_p);
			}
		});
		pause_back.setVisible(false);
		add(pause_back);
		
		pause_continue = new JLabel(pause_continue_p);
		pause_continue.setBounds(120, 287, 520, 196);
		pause_continue.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pause_continue.setIcon(pause_continue_p);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getX()>=0 && e.getX() <=pause_continue.getWidth() && e.getY() >= 0 && e.getY() <= pause_continue.getHeight()) {
					setPause(false);
					
					if(bgm_fighting_Sound!=null && isStart) {
						bgm_fighting_Sound.setContinue();
					}			
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pause_continue.setIcon(pause_continue_entered_p);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pause_continue.setIcon(pause_continue_p);
			}
		});
		pause_continue.setVisible(false);
		add(pause_continue);
		
		pause_retry = new JLabel(pause_retry_p);
		pause_retry.setBounds(120, 88, 520, 221);
		pause_retry.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pause_retry.setIcon(pause_retry_p);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getX()>=0 && e.getX() <=pause_retry.getWidth() && e.getY() >= 0 && e.getY() <= pause_retry.getHeight()) {
					
					rank_bg.setVisible(false);
					point_D1_label.setText("");
					point_D2_label.setText("");
					point_D3_label.setText("");
					point_D4_label.setText("");
					point_D5_label.setText("");
					point_D6_label.setText("");
					maxCombo_label.setText("");
					accuracy_label.setText("");
					rank_label.setIcon(null);
					
					isStart = false;
					setReadyLabel(true);
					maxCombo = 0;
					point = 0;
					setPoint(point);
					isCounting = false;
					big_gray_bao.setIcon(spinner_big_bao_gray_png_p);
					setTarget(rangeLabel.getX()+2);
					setTime(3,0);
					setPause(false);
					if(bgm_fighting_Sound!=null) {
						bgm_fighting_Sound.setPause();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pause_retry.setIcon(pause_retry_entered_p);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pause_retry.setIcon(pause_retry_p);
			}
		});
		pause_retry.setVisible(false);
		add(pause_retry);
		
		temp_bg = new JLabel(nowLoading_p);
		temp_bg.setBounds(0, -30, 1280, 720);
		temp_bg.setVisible(false);
		add(temp_bg);
		
		point_D1_label = new JLabel();
		point_D1_label.setBounds(485, 25, 45, 80);
		point_D1_label.setForeground(new Color(255, 255, 255));
		point_D1_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(point_D1_label);
		
		point_D2_label = new JLabel();
		point_D2_label.setBounds(445, 25, 45, 80);
		point_D2_label.setForeground(new Color(255, 255, 255));
		point_D2_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(point_D2_label);
		
		point_D3_label = new JLabel();
		point_D3_label.setBounds(405, 25, 45, 80);
		point_D3_label.setForeground(new Color(255, 255, 255));
		point_D3_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(point_D3_label);
		
		point_D4_label = new JLabel();
		point_D4_label.setBounds(365, 25, 45, 80);
		point_D4_label.setForeground(new Color(255, 255, 255));
		point_D4_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(point_D4_label);
		
		point_D5_label = new JLabel();
		point_D5_label.setBounds(325, 25, 45, 80);
		point_D5_label.setForeground(new Color(255, 255, 255));
		point_D5_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(point_D5_label);
		
		point_D6_label = new JLabel();
		point_D6_label.setBounds(285, 25, 45, 80);
		point_D6_label.setForeground(new Color(255, 255, 255));
		point_D6_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(point_D6_label);
		
		opPoint_D1_label = new JLabel();
		opPoint_D1_label.setBounds(505, 145, 45, 80);
		opPoint_D1_label.setForeground(new Color(255, 255, 255));
		opPoint_D1_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(opPoint_D1_label);
		
		opPoint_D2_label = new JLabel();
		opPoint_D2_label.setBounds(465, 145, 45, 80);
		opPoint_D2_label.setForeground(new Color(255, 255, 255));
		opPoint_D2_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(opPoint_D2_label);
		
		opPoint_D3_label = new JLabel();
		opPoint_D3_label.setBounds(425, 145, 45, 80);
		opPoint_D3_label.setForeground(new Color(255, 255, 255));
		opPoint_D3_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(opPoint_D3_label);
		
		opPoint_D4_label = new JLabel();
		opPoint_D4_label.setBounds(385, 145, 45, 80);
		opPoint_D4_label.setForeground(new Color(255, 255, 255));
		opPoint_D4_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(opPoint_D4_label);
		
		opPoint_D5_label = new JLabel();
		opPoint_D5_label.setBounds(345, 145, 45, 80);
		opPoint_D5_label.setForeground(new Color(255, 255, 255));
		opPoint_D5_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(opPoint_D5_label);
		
		opPoint_D6_label = new JLabel();
		opPoint_D6_label.setBounds(305, 145, 45, 80);
		opPoint_D6_label.setForeground(new Color(255, 255, 255));
		opPoint_D6_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(opPoint_D6_label);
		
		maxCombo_label = new JLabel();
		maxCombo_label.setBounds(80, 440, 400, 80);
		maxCombo_label.setForeground(new Color(255, 255, 255));
		maxCombo_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(maxCombo_label);
		
		accuracy_label = new JLabel();
		accuracy_label.setBounds(400, 440, 400, 80);
		accuracy_label.setForeground(new Color(255, 255, 255));
		accuracy_label.setFont(new Font("Sylfaen", Font.BOLD, 60));
		add(accuracy_label);
		
		opponentID_label = new JLabel();
		opponentID_label.setForeground(Color.WHITE);
		opponentID_label.setFont(new Font("Tw Cen MT", Font.BOLD, 40));
		opponentID_label.setBounds(10, 115, 300, 40); // 20 140
		opponentID_label.setVisible(false);
		add(opponentID_label);
		
		opponentScore_label = new JLabel();
		opponentScore_label.setForeground(new Color(30, 144, 255));
		opponentScore_label.setFont(new Font("Tw Cen MT", Font.BOLD, 35));
		opponentScore_label.setBounds(40, 165, 300, 40);
		opponentScore_label.setVisible(false);
		add(opponentScore_label);
		
		rank_label = new JLabel();
		rank_label .setBounds(425, 520, 127, 155);
		add(rank_label );
		
		rank_bg = new JLabel(ranking_panel_p);
		rank_bg.setBounds(0, -28, 1347, 709);
		rank_bg.setVisible(false);
		add(rank_bg);
		
		countDownLabel = new JLabel();
		countDownLabel.setBounds(510, 75, 261, 305);
		countDownLabel.setVisible(false);
		add(countDownLabel);
		
		readyLabel = new JLabel(presskey_txt_p);
		readyLabel.setBounds(181, 400, 918, 114);
		readyLabel.setVisible(false);
		add(readyLabel);
		
		setting = new JLabel(back_bt_p);
		setting.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setting.setIcon(back_bt_pressed_p);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				setting.setIcon(back_bt_p);
				if(e.getX()>=0 && e.getX() <=setting.getWidth() && e.getY() >= 0 && e.getY() <= setting.getHeight()) {
					setPause(true);
				}
			}
		});
		setting.setBounds(1150, 10, 109, 108);
		add(setting);
		
		big_gray_bao = new JLabel(spinner_big_bao_gray_png_p);
		big_gray_bao.setBounds(440, 250, 400, 400);
		add(big_gray_bao);
		
		comboCounter_D_label = new JLabel();
		comboCounter_D_label.setBounds(960, 80, 40, 60);
		add(comboCounter_D_label);
		
		comboCounter_T_label = new JLabel();
		comboCounter_T_label.setBounds(920, 80, 40, 60);
		add(comboCounter_T_label);
		
		comboCounter_H_label = new JLabel();
		comboCounter_H_label.setBounds(880, 80, 40, 60);
		add(comboCounter_H_label);
		
		comboCounterlabel = new JLabel(combo_p);
		comboCounterlabel.setBounds(1000, 90, 200, 50);
		comboCounterlabel.setVisible(false);
		add(comboCounterlabel);
		
		comboLabel = new JLabel();
		comboLabel.setBounds(920, 160, 255, 25);
		comboLabel.setBorder (BorderFactory.createLineBorder(new Color(0, 255, 80),1));
		comboLabel.setVisible(false);
		add(comboLabel);
		
		comboStateLabel = new JLabel();
		comboStateLabel.setBounds(920, 160, 255, 25);
		comboStateLabel.setBackground(new Color(0,255,0));
		comboStateLabel.setOpaque(true);
		comboStateLabel.setVisible(false);
		add(comboStateLabel);
		
		time_min = new JLabel();
		time_min.setBounds(584, 78, 33, 41);
		add(time_min);
		
		time_dot = new JLabel(time_dot_p);
		time_dot.setBounds(617, 73, 33, 41);
		time_dot.setVisible(false);
		add(time_dot);
		
		time_sec_T = new JLabel();
		time_sec_T.setBounds(650, 78, 33, 41);
		add(time_sec_T);
		
		time_sec_D = new JLabel();
		time_sec_D.setBounds(683, 78, 33, 41);
		add(time_sec_D);
		
		time_bg = new JLabel(time_bg_p);
		time_bg.setBounds(574, 73, 150, 50);
		add(time_bg);
		
		pointLabel = new JLabel("0");
		pointLabel.setForeground(new Color(255, 172, 200));
		pointLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 60));
		pointLabel.setBounds(200, 50, 300, 121);
		add(pointLabel);
		
		target = new JLabel();
		target.setBounds(475, 642, 2, 21);
		target.setBorder (BorderFactory.createLineBorder(new Color(255, 0, 0),2));
		add(target);
		
		success_rangeLabel = new JLabel();
		success_rangeLabel.setForeground(Color.DARK_GRAY);
		success_rangeLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		success_rangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		success_rangeLabel.setSize(50, 21);
		success_rangeLabel.setBackground(Color.green);
		success_rangeLabel.setBorder (BorderFactory.createLineBorder(new Color(0, 200, 100),1));
		success_rangeLabel.setOpaque(true);
		add(success_rangeLabel);
		
		successEx_rangeLabel = new JLabel();
		successEx_rangeLabel.setBackground(new Color(0, 191, 255));
		successEx_rangeLabel.setForeground(Color.DARK_GRAY);
		successEx_rangeLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		successEx_rangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		successEx_rangeLabel.setSize(50, 21);
		successEx_rangeLabel.setBorder (BorderFactory.createLineBorder(new Color(0, 200, 100),1));
		successEx_rangeLabel.setOpaque(true);
		add(successEx_rangeLabel);
		
		rangeLabel = new JLabel();
		rangeLabel.setBounds(464, 640, 351, 25);
		rangeLabel.setBackground(new Color(255, 255, 255));
		rangeLabel.setBorder (BorderFactory.createLineBorder(new Color(0, 0, 0),2));
		rangeLabel.setOpaque(true);
		add(rangeLabel);
		successRange_reset();		
		
		loadingBg lbg = new loadingBg();
		lbg.start();
		
		bg = new JLabel(fight_bg_p);
		bg.setBounds(0, 0, 1280, 720);
		bg.setVisible(false);
		add(bg);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_Z && !isLoading && isCounting) {
					pressCount = pressCount + 1;
					if(success_rangeLabel.isVisible() && pressType.equals("Z") && target.getX() >= success_rangeLabel.getX() && target.getX() <= success_rangeLabel.getX()+success_rangeLabel.getWidth()) {
						zxc_success = true;
						success_rangeLabel.setVisible(false);
						typedSuccessfully();
					}else {
						combo = 0;
					}
					setComboCount(combo);
		 		}else if(e.getKeyCode() == KeyEvent.VK_X && !isLoading && isCounting) {
		 			pressCount = pressCount + 1;
					if(success_rangeLabel.isVisible() && pressType.equals("X") &&target.getX() >= success_rangeLabel.getX() && target.getX() <= success_rangeLabel.getX()+success_rangeLabel.getWidth()) {
						zxc_success = true;
						success_rangeLabel.setVisible(false);
						typedSuccessfully();
					}else {
						combo = 0;
					}
					setComboCount(combo);
		 		}else if(e.getKeyCode() == KeyEvent.VK_C && !isLoading && isCounting) {
		 			pressCount = pressCount + 1;
					if(success_rangeLabel.isVisible() && pressType.equals("C") &&target.getX() >= success_rangeLabel.getX() && target.getX() <= success_rangeLabel.getX()+success_rangeLabel.getWidth()) {
						zxc_success = true;
						success_rangeLabel.setVisible(false);
						typedSuccessfully();
					}else {
						combo = 0;
					}
					setComboCount(combo);
		 		}else if(e.getKeyCode() == KeyEvent.VK_LEFT && !isLoading && isCounting) {
		 			pressCount = pressCount + 1;
					if(successEx_rangeLabel.isVisible() && pressTypeEx.equals("<<") && target.getX() >= successEx_rangeLabel.getX() && target.getX() <= successEx_rangeLabel.getX()+successEx_rangeLabel.getWidth()) {
						ldr_success = true;
						successEx_rangeLabel.setVisible(false);
						typedSuccessfully();
					}else {
						combo = 0;
					}
					setComboCount(combo);
		 		}else if(e.getKeyCode() == KeyEvent.VK_DOWN && !isLoading && isCounting) {
		 			pressCount = pressCount + 1;
					if(successEx_rangeLabel.isVisible() && pressTypeEx.equals("\\/") &&target.getX() >= successEx_rangeLabel.getX() && target.getX() <= successEx_rangeLabel.getX()+successEx_rangeLabel.getWidth()) {
						ldr_success = true;
						successEx_rangeLabel.setVisible(false);
						typedSuccessfully();
					}else {
						combo = 0;
					}
					setComboCount(combo);
		 		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT && !isLoading && isCounting) {
		 			pressCount = pressCount + 1;
					if(successEx_rangeLabel.isVisible() && pressTypeEx.equals(">>") &&target.getX() >= successEx_rangeLabel.getX() && target.getX() <= successEx_rangeLabel.getX()+successEx_rangeLabel.getWidth()) {
						ldr_success = true;
						successEx_rangeLabel.setVisible(false);
						typedSuccessfully();
					}else {
						combo = 0;
					}
					setComboCount(combo);
		 		}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE && !isLoading){
					if(bgm_fighting_Sound!=null && isPause) {
						bgm_fighting_Sound.setContinue();
					}
					setPause(!isPause);
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE && !isLoading && !isCounting && !isPause && !isMultifyPlayer && !isStart) {
					isStart = true;
					setReadyLabel(false);
					startPlaying();
				}
			}
		});
		
	}
	public void setReadyLabel(boolean T) {
		if(isMultifyPlayer == false) {
			readyLabel.setVisible(T);
		}else {
			readyLabel.setVisible(false);
		}
	}
	
	public void startPlaying() {
		startPlay stp = new startPlay();
		stp.start();
	}
	
	public void endPlaying() {
		isCounting = false;
		big_gray_bao.setIcon(spinner_big_bao_gray_png_p);
		setRanking();
		setOpponentRanking();
	}
	
	public void setRanking(){
		setRankingPg srp = new setRankingPg(point_D1_label,point_D2_label,point_D3_label,point_D4_label,point_D5_label,point_D6_label,point);
		srp.start();
	}
	
	public void setOpponentRanking(){
		opponentScore_label.setVisible(false);
		opponentID_label.setVisible(true);
		opponentID_label.setLocation(20,145);
		int n = 0;
		if(!opponentScore_label.getText().equals("")) {
			n = Integer.parseInt(opponentScore_label.getText());
		}
		setRankingPg srp = new setRankingPg(opPoint_D1_label,opPoint_D2_label,opPoint_D3_label,opPoint_D4_label,opPoint_D5_label,opPoint_D6_label,n);
		srp.start();
	}
	
	public void setMultifyPlayer(boolean T) {
		isMultifyPlayer = T;
		opponentID_label.setVisible(T);
		opponentScore_label.setVisible(T);
	}
	
	public void setOpponentID(String idName){
		opponentID_label.setText(idName);
	}
	
	public void setOpponentScore(int point){
		opponentScore_label.setText(Integer.toString(point));
	}
	
	public void setTarget(int x) {
		target.setLocation(x, target.getY());
	}
	
	public void setComboCount(int comboCount) {
		int D,T,H;
		if(comboCount > 0) {
			comboCounterlabel.setVisible(true);
			comboLabel.setVisible(true);
			comboStateLabel.setVisible(true);
			if(comboCount < 10) {
				setComboIcon(comboCounter_D_label,comboCount);
			}else if(comboCount < 100) {
				T = comboCount/10;
				D = comboCount - T*10;
				setComboIcon(comboCounter_D_label,D);
				setComboIcon(comboCounter_T_label,T);
			}else if(comboCount < 1000) {
				H = comboCount/100;
				T = comboCount/10 - H*10;
				D = comboCount - H*100 - T*10;
				setComboIcon(comboCounter_D_label,D);
				setComboIcon(comboCounter_T_label,T);
				setComboIcon(comboCounter_H_label,H);
			}
		}else {
			comboCounterlabel.setVisible(false);
			comboCounter_D_label.setIcon(null);
			comboCounter_T_label.setIcon(null);
			comboCounter_H_label.setIcon(null);
			comboLabel.setVisible(false);
			comboStateLabel.setVisible(false);
		}
	}
	
	public void setTimeIcon(JLabel timeLabel,int n) {
		 if(n == 0) {
			 timeLabel.setIcon(time_0_p);
		}else if(n == 1) {
			timeLabel.setIcon(time_1_p);
		}else if(n == 2) {
			timeLabel.setIcon(time_2_p);
		}else if(n == 3) {
			timeLabel.setIcon(time_3_p);
		}else if(n == 4) {
			timeLabel.setIcon(time_4_p);
		}else if(n == 5) {
			timeLabel.setIcon(time_5_p);
		}else if(n == 6) {
			timeLabel.setIcon(time_6_p);
		}else if(n == 7) {
			timeLabel.setIcon(time_7_p);
		}else if(n == 8) {
			timeLabel.setIcon(time_8_p);
		}else if(n == 9) {
			timeLabel.setIcon(time_9_p);
		}
	}
	
	public void setComboIcon(JLabel comboLabel,int n) {
		 if(n == 0) {
			comboLabel.setIcon(score_0_p);
		}else if(n == 1) {
			comboLabel.setIcon(score_1_p);
		}else if(n == 2) {
			comboLabel.setIcon(score_2_p);
		}else if(n == 3) {
			comboLabel.setIcon(score_3_p);
		}else if(n == 4) {
			comboLabel.setIcon(score_4_p);
		}else if(n == 5) {
			comboLabel.setIcon(score_5_p);
		}else if(n == 6) {
			comboLabel.setIcon(score_6_p);
		}else if(n == 7) {
			comboLabel.setIcon(score_7_p);
		}else if(n == 8) {
			comboLabel.setIcon(score_8_p);
		}else if(n == 9) {
			comboLabel.setIcon(score_9_p);
		}
	}
	
	public void setPoint(int point) {
		pointLabel.setText(Integer.toString(point));
	}
	
	public void successRange_reset() {
		Random ran = new Random();
		int rx = ran.nextInt(rangeLabel.getWidth() - success_rangeLabel.getWidth());
		int type = ran.nextInt(3)+1;
		if(type == 1) {
			pressType = "Z";
		}else if(type == 2) {
			pressType = "X";
		}else {
			pressType = "C";
		}
		int rEx;
		if(rx > ((rangeLabel.getWidth() - success_rangeLabel.getWidth())/2) ) {
			rEx = ran.nextInt( rx - successEx_rangeLabel.getWidth() );
		}else {
			rEx = ran.nextInt( rangeLabel.getWidth() - rx - success_rangeLabel.getWidth() - successEx_rangeLabel.getWidth()) + rx + success_rangeLabel.getWidth();
		}
		type = ran.nextInt(3)+1;
		if(type == 1) {
			pressTypeEx = "<<";
		}else if(type == 2) {
			pressTypeEx = "\\/";
		}else {
			pressTypeEx = ">>";
		}
		zxc_success = false;
		ldr_success = false;
		success_rangeLabel.setVisible(true);
		success_rangeLabel.setText(pressType);
		success_rangeLabel.setLocation(rangeLabel.getX()+rx+2, rangeLabel.getY()+2);
		successEx_rangeLabel.setVisible(true);
		successEx_rangeLabel.setText(pressTypeEx);
		successEx_rangeLabel.setLocation(rangeLabel.getX()+rEx+2, rangeLabel.getY()+2);
	}
	
	public void typedSuccessfully() {
		pressSuccessCount = pressSuccessCount + 1;
		combo = combo + 1;
		if(maxCombo < combo) {
			maxCombo = combo;
		}
		if(combo <= 10) {
			point = point + 100;
		}else if(combo <= 20) {
			point = point + 110;
		}else if(combo <= 30) {
			point = point + 120;
		}else if(combo <= 40) {
			point = point + 130;
		}else if(combo <= 50) {
			point = point + 140;
		}else if(combo <= 60) {
			point = point + 150;
		}else if(combo <= 70) {
			point = point + 160;
		}else if(combo <= 80) {
			point = point + 140;
		}else if(combo <= 90) {
			point = point + 180;
		}else if(combo <= 100) {
			point = point + 190;
		}else {
			point = point + 250;
		}
		if(isMultifyPlayer) {
			CTS.MyNewScore(point);
		}
		setPoint(point);
		if(ldr_success && zxc_success) {
			successRange_reset();
		}
		cs.resetCombo();
	}
	
	public void setMusicChoice(int choice) {
		musicChoice = choice;
	}
	
	public void startMusic() {
		String bgm_fighting_Sound_url = "";
		if(musicChoice == 0) {
			bgm_fighting_Sound_url = "sound/bgm_fightingpage/bgm_fightingpage.wav";
		}else if(musicChoice <= 4) {
			bgm_fighting_Sound_url = "sound/bgm_fightingpage/bgm_fightingpage"+ musicChoice +".wav";
		}
		
		bgm_fighting_Sound = new musicPlayer(bgm_fighting_Sound_url);
		bgm_fighting_Sound.start();
	}
	
	public void setPause(boolean T) {
		if(bgm_fighting_Sound!=null) {
				if(T) {
					bgm_fighting_Sound.setPause();
					applause_Sound = new musicPlayer(applause_Sound_url);
					applause_Sound.start();
					applause_Sound.setLoop();
				}else {
					applause_Sound.setPause();
				}
		}else {
			if(T) {
				applause_Sound = new musicPlayer(applause_Sound_url);
				applause_Sound.start();
				applause_Sound.setLoop();
			}else {
				applause_Sound.setPause();
			}
		}
		
		if(isMultifyPlayer == false) {
			pause_retry.setVisible(T);
			isPause = T;
		}else {
			pause_retry.setVisible(false);
			isPause = false;
		}
			temp_bg.setBounds(0, -15, 1280, 720);
			temp_bg.setIcon(pause_overlay_p);
			temp_bg.setVisible(T);
			pause_back.setVisible(T);
			pause_continue.setVisible(T);
		
	}
	
	public void setTime(int m,int s) {
		setTimeIcon(time_min,m);
		if(s<10) {
			setTimeIcon(time_sec_T,0);
			setTimeIcon(time_sec_D,s);
		}else {
			setTimeIcon(time_sec_T,s/10);
			setTimeIcon(time_sec_D,s-s/10*10);
		}
	}
	
	public class startPlay extends Thread{
		
		public startPlay() {
			
		}
		
		public void run() {
			reciprocal rp = new reciprocal();
			rp.start();
			try {
				Thread.sleep(2300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!isMultifyPlayer) {
				Random ran = new Random();
				setMusicChoice(ran.nextInt(5));
			}
			
			rank_bg.setVisible(false);
			point_D1_label.setText("");
			point_D2_label.setText("");
			point_D3_label.setText("");
			point_D4_label.setText("");
			point_D5_label.setText("");
			point_D6_label.setText("");
			maxCombo_label.setText("");
			accuracy_label.setText("");
			
			startMusic();
			isCounting = true;
			big_gray_bao.setIcon(spinner_big_bao_gray_gif_p);
			maxCombo = 0;
			point = 0;
			setPoint(point);
			timer t = new timer(3,0);
			t.start();
			targetMove tm = new targetMove(rangeLabel.getX()+1,rangeLabel.getX()+rangeLabel.getWidth()-2);
			tm.start();
			cs = new comboState();
			cs.start();
			repaint();
		}
	}
	
	public class targetMove extends Thread{
		private int startX,endX,locationX;
		public targetMove(int startX,int endX){	
			this.startX = startX;
			this.endX = endX;
			this.locationX = startX;
		}
		
		public void run() {
			boolean increasing = true;
			while(isCounting) {
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(increasing && locationX < endX) {
					locationX = locationX + 1;
				}else if(!increasing && locationX > startX) {
					locationX = locationX - 1;
				}else {
					increasing = !increasing;
				}
				setTarget(locationX);
				target.updateUI();
				while(isPause) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}
	
	public class comboState extends Thread{
		private int n = 0;
		public comboState(){	
		}	
		public void run() {
			n = 0;
			int width = comboLabel.getWidth();
			while(isCounting) {
				if(combo > 0) {
					if(n>=0 && n <= 127) {
						comboStateLabel.setBackground(new Color(0,255-2*n,40));
					}else {
						comboStateLabel.setBackground(new Color((-127+n)*2,0,40));
					}
					n = n+1;
					if(n >= 255) {
						setComboCount(0);
						combo = 0;
						n = 0;
					}else {
						comboStateLabel.setBounds(comboLabel.getX() + n , comboStateLabel.getY(), width-n, comboStateLabel.getHeight());
					}
				}
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while(isPause) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			setComboCount(0);
			combo = 0;
		}
		
		public void resetCombo(){
			n = 0;
		}
	}
	
	public class reciprocal extends Thread{
		public reciprocal(){	
		}
		
		public void run() {
			musicPlayer CountDown_mic = new musicPlayer("sound/countdown.wav");
			CountDown_mic.start();
			countDownLabel.setVisible(true);
			countDownLabel.setIcon(count3_p);
			try {
				Thread.sleep(720);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			countDownLabel.setIcon(count2_p);
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			countDownLabel.setIcon(count1_p);
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			countDownLabel.setVisible(false);
		}
	}
	
	public class loadingBg extends Thread{
		public loadingBg(){	
		}
		
		public void run() {
			setting.setVisible(false);
			big_gray_bao.setVisible(false);
			pointLabel.setVisible(false);
			rangeLabel.setVisible(false);
			target.setVisible(false);
			success_rangeLabel.setVisible(false);
			time_min.setVisible(false);
			
			isLoading = true;
			temp_bg.setVisible(true);
			try {
				Thread.sleep(4600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setting.setVisible(true);
			bg.setVisible(true);
			big_gray_bao.setVisible(true);
			pointLabel.setVisible(true);
			rangeLabel.setVisible(true);
			target.setVisible(true);
			success_rangeLabel.setVisible(true);
			time_min.setVisible(true);
			setReadyLabel(true);
			
			temp_bg.setVisible(false);
			isLoading = false;
		}
	}
	
	
	public class endLoadingBg extends Thread{
		public endLoadingBg(){	
		}
		
		public void run() {
			temp_bg.setBounds(0, -30, 1280, 720);
			pause_back.setVisible(false);
			pause_continue.setVisible(false);
			pause_retry.setVisible(false);
			
			rank_bg.setVisible(false);
			point_D1_label.setText("");
			point_D2_label.setText("");
			point_D3_label.setText("");
			point_D4_label.setText("");
			point_D5_label.setText("");
			point_D6_label.setText("");
			maxCombo_label.setText("");
			accuracy_label.setText("");
			
			if(bgm_fighting_Sound!=null) {
				bgm_fighting_Sound.setPause();
			}
			
			isLoading = true;
			temp_bg.setIcon(nowLoading_p);
			temp_bg.setVisible(true);
			applause_Sound.setPause();
			try {
				Thread.sleep(4600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			temp_bg.setVisible(false);
			isLoading = false;
			Mw.setMpage();
		}
	}
	
	public class setRankingPg extends Thread{
		
		private JLabel D1,D2,D3,D4,D5,D6;
		private int Point;
		
		public setRankingPg(JLabel D1,JLabel D2,JLabel D3,JLabel D4,JLabel D5,JLabel D6,int Point){
			this.D1 = D1;
			this.D2 = D2;
			this.D3 = D3;
			this.D4 = D4;
			this.D5 = D5;
			this.D6 = D6;
			this.Point = Point;
		}
		
		public void setRanking_delay(JLabel rank_D_label,int D) {
			for(int n = 0;n <10;n++) {
				rank_D_label.setText(Integer.toString(n));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			rank_D_label.setText(Integer.toString(D));
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			rank_bg.setVisible(true);
			D1.setText("");
			D2.setText("");
			D3.setText("");
			D4.setText("");
			D5.setText("");
			D6.setText("");
			rank_label.setIcon(null);
			int pointLenth = Integer.toString(Point).length();
			
			if(pointLenth == 1) {
				setRanking_delay(D1,0);
			}else if(pointLenth == 3) {
				setRanking_delay(D1,Integer.parseInt(Integer.toString(Point).charAt(2) + "" ));
				setRanking_delay(D2,Integer.parseInt(Integer.toString(Point).charAt(1) + "" ));
				setRanking_delay(D3,Integer.parseInt(Integer.toString(Point).charAt(0) + "" ));
			}else if(pointLenth == 4) {
				setRanking_delay(D1,Integer.parseInt(Integer.toString(Point).charAt(3) + "" ));
				setRanking_delay(D2,Integer.parseInt(Integer.toString(Point).charAt(2) + "" ));
				setRanking_delay(D3,Integer.parseInt(Integer.toString(Point).charAt(1) + "" ));
				setRanking_delay(D4,Integer.parseInt(Integer.toString(Point).charAt(0) + "" ));
			}else if(pointLenth == 5) {
				setRanking_delay(D1,Integer.parseInt(Integer.toString(Point).charAt(4) + "" ));
				setRanking_delay(D2,Integer.parseInt(Integer.toString(Point).charAt(3) + "" ));
				setRanking_delay(D3,Integer.parseInt(Integer.toString(Point).charAt(2) + "" ));
				setRanking_delay(D4,Integer.parseInt(Integer.toString(Point).charAt(1) + "" ));
				setRanking_delay(D5,Integer.parseInt(Integer.toString(Point).charAt(0) + "" ));
			}else if(pointLenth == 6) {
				setRanking_delay(D1,Integer.parseInt(Integer.toString(Point).charAt(5) + "" ));
				setRanking_delay(D2,Integer.parseInt(Integer.toString(Point).charAt(4) + "" ));
				setRanking_delay(D3,Integer.parseInt(Integer.toString(Point).charAt(3) + "" ));
				setRanking_delay(D4,Integer.parseInt(Integer.toString(Point).charAt(2) + "" ));
				setRanking_delay(D5,Integer.parseInt(Integer.toString(Point).charAt(1) + "" ));
				setRanking_delay(D6,Integer.parseInt(Integer.toString(Point).charAt(0) + "" ));
			}
			
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				maxCombo_label.setText(Integer.toString(maxCombo));
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int acy = 0;
				if(pressCount != 0) {
					acy = (pressSuccessCount * 100 /pressCount);
				}
				accuracy_label.setText( acy + "%");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(maxCombo >= 100 && acy >= 95 && point >= 32000) {
					rank_label.setIcon(ranking_s_p);
				}else if(maxCombo >= 80 && acy >= 85 && point >= 26000) {
					rank_label.setIcon(ranking_a_p);
				}else if(maxCombo >= 50 && acy >= 70 && point >= 20000) {
					rank_label.setIcon(ranking_b_p);
				}else if(maxCombo >= 30 && acy >= 60 && point >= 15000) {
					rank_label.setIcon(ranking_c_p);
				}else{
					rank_label.setIcon(ranking_d_p);
				}
				
			
		}
	}
	
	public class timer extends Thread{
		private int min = 0;
		private int sec = 0;
		
		public timer(int m , int s) {
			min = m;
			sec = s;
		}
		public void run() {
				time_dot.setVisible(true);
				while(!(min== 0 && sec == -1) && isCounting && !isMultifyPlayer) {
					setTime(min,sec);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(min>0 && sec == 0) {
						min = min - 1;
						sec = 59;
					}else {
						sec = sec - 1;
					}
					while(isPause) {
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			if(isMultifyPlayer) {
				isCounting = true;
			}else {
				isCounting = false;
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				setRanking();
			}
		}
		
	}
}
