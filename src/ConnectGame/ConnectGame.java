package ConnectGame;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class  ConnectGame extends Frame{
	
	private String ServerIP = "localhost";
	private int ServerPORT = 5200;
	
	private TextField tAccount,tPassword,rPassword;
	private JLabel lAccount,lPassword,loading,loginSuccess,storeMessage;
	private JLabel registerMessage,rlPassword,rVerifyMessage,rlPasswordMessage1,rlPasswordMessage2;
	private JButton submit,store,register,rSubmit,rVerify;
	private boolean isStore,isRegister = false;
	private boolean rAccountEnable = false,rPasswordFont = false ,rPasswordSame = false;
	private RegisterAccount RA;
	private Font F = new Font("微軟正黑體", Font.BOLD, 20);
	//private Socket socket;
	
	public ConnectGame() {
		loadSetting();
		tAccount = new TextField("");  // 帳號輸入欄位
		lAccount = new JLabel("使用者帳號");
		tPassword = new TextField("");  // 密碼輸入欄位
		lPassword = new JLabel("使用者密碼");
		submit = new JButton("確認");
		store = new JButton("");
		storeMessage = new JLabel("記住我的帳號");
		register = new JButton("立即註冊");
		registerMessage = new JLabel("沒有帳號?");
		rVerifyMessage = new JLabel("請驗證帳號是否能使用");
		rVerify = new JButton("驗證");
		rPassword = new TextField("");
		rlPassword = new JLabel("密碼確認");
		rlPasswordMessage1 = new JLabel("密碼長度:6~12字元");
		rlPasswordMessage2 = new JLabel("再次輸入密碼");
		rSubmit = new JButton("建立帳戶");
		loading = new JLabel(new ImageIcon(getClass().getResource("/png/loading.gif")));
		loginSuccess = new JLabel(new ImageIcon(getClass().getResource("/png/LoginSuccess.gif")));
		
		lAccount.setBounds(75, 60, 150, 30);
		lAccount.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		tAccount.setBounds(75, 90, 150, 30);
		tAccount.setFont(F);
		lPassword.setBounds(75, 130, 150, 30);
		lPassword.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		tPassword.setBounds(75, 160, 150, 30);
		tPassword.setFont(F);
		tPassword.setEchoChar('·');
		submit.setBounds(110, 240, 80, 35);
		submit.setBackground(new Color(240, 240, 130));
		submit.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		store.setBounds(80, 205, 13, 13);
		store.setBorder (BorderFactory.createLineBorder(new Color(80, 80, 80),2));
		store.setBackground(Color.white);
		storeMessage.setBounds(100, 205, 100, 15);
		storeMessage.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		
		register.setBounds(150, 350, 60, 25);
		register.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		register.setForeground(Color.blue);
		register.setBackground(null);
		register.setBorder (null);
		registerMessage.setBounds(95, 350, 60, 25);
		registerMessage.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		rVerify.setBounds(10, 75, 60, 40);
		rVerify.setBackground(new Color(90, 235, 125));
		rVerify.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		rVerify.setVisible(false);
		rVerifyMessage.setBounds(80, 105, 200, 30);
		rVerifyMessage.setFont(new Font("微軟正黑體", Font.PLAIN, 11));
		rVerifyMessage.setForeground(new Color(250,80,80));
		rVerifyMessage.setVisible(false);
		rPassword.setBounds(75, 240, 150, 30);
		rPassword.setFont(F);
		rPassword.setEchoChar('·');
		rPassword.setVisible(false);
		rlPassword.setBounds(75, 210, 150, 30);
		rlPassword.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		rlPassword.setVisible(false);
		rlPasswordMessage1.setBounds(80, 185, 150, 30);
		rlPasswordMessage1.setFont(new Font("微軟正黑體", Font.PLAIN, 11));
		rlPasswordMessage1.setForeground(new Color(250,80,80));
		rlPasswordMessage1.setVisible(false);
		rlPasswordMessage2.setBounds(80, 265, 150, 30);
		rlPasswordMessage2.setFont(new Font("微軟正黑體", Font.PLAIN, 11));
		rlPasswordMessage2.setForeground(new Color(250,80,80));
		rlPasswordMessage2.setVisible(false);
		rSubmit.setBounds(90, 310, 120, 35);
		rSubmit.setBackground(new Color(240, 240, 130));
		rSubmit.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		rSubmit.setEnabled(false);
		rSubmit.setVisible(false);
		
		loading.setBounds(0, 60, 300, 300);
		loading.setVisible(false);
		loginSuccess.setBounds(0, 60, 300, 300);
		loginSuccess.setVisible(false);
		
		getStore();
		
		tAccount.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
	            // 當按下按鈕且為註冊畫面時
	            if(isRegister) {
	            	if(tAccount.getText().length() >=1 && tAccount.getText().length() <12) {
	            		if(!((tAccount.getText().charAt(0)>=65 && tAccount.getText().charAt(0)<=90) ||(tAccount.getText().charAt(0)>=97 && tAccount.getText().charAt(0)<=122)))
	            			setVerifyMessage("帳號須以英文字母開頭(不限大小寫)",false);
	            		else if(tAccount.getText().length() < 4)
	            			setVerifyMessage("格式錯誤!請輸入4~12字元",false);
	            		else {
	            			if(isStringLegal(tAccount.getText()))
	            				setVerifyMessage("請驗證帳號是否能使用",false);
	    					else
	    						setVerifyMessage("帳號內含非法字元!",false);
	            		}
	            	}else{
	            		setVerifyMessage("格式錯誤!請輸入4~12字元",false);
	            	}
	            	rSubmit.setEnabled(false);
	            }
	        }
		}); // 註冊事件
		
		tPassword.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
	            // 如果按下的是「Enter」鍵
	            if(e.getKeyCode() == KeyEvent.VK_ENTER && !isRegister) {
	            	verify(tAccount.getText(),tPassword.getText());
	            }
	         // 當註冊畫面時
	            if(isRegister) {
	            	rSubmit.setEnabled(false);
	            	if(tPassword.getText().equals("")) {
	            		set_rlPasswordMessage1("密碼欄請勿為空",false);
	            	}else if(tPassword.getText().length() < 6 || tPassword.getText().length() > 12) {
	            		set_rlPasswordMessage1("格式錯誤!請輸入6~12字元",false);
	            	}else {
	            		if(isStringLegal(tPassword.getText())) {
	            			set_rlPasswordMessage1("Correct !",true);
	            			if(!rPassword.getText().equals(tPassword.getText()) && rPasswordSame)
	            				set_rlPasswordMessage2("此密碼與上方密碼不一致!",false);
	            			else if((rPassword.getText().equals(tPassword.getText())))
	            				set_rlPasswordMessage2("Correct !",true);	            			
	            			check_rsubmitEnable();	
	            		}else
							set_rlPasswordMessage1("密碼內含非法字元!",false);
	            	}
	            }
	        }
		}); // 註冊事件
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verify(tAccount.getText(),tPassword.getText());
			}
		});
		
		store.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isStore) {
					isStore = false;
					store.setBackground(Color.white);
					setStore(false,"");
				}else {
					isStore = true;
					store.setBackground(Color.LIGHT_GRAY);
				}
			}
		});
		
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isRegister == false) {
					RA = new RegisterAccount(ServerIP,ServerPORT,ConnectGame.this);
					RA.start();	
				}else {
					if(RA.isAlive()) {
						RA.setState("Exit");
					}

				}
			}
		});
		
		rVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tAccount.getText().equals("")) {
					setVerifyMessage("帳號請勿為空!",false);
				}else if(tAccount.getText().length() >=1 && tAccount.getText().length() <12) {
            		if(!((tAccount.getText().charAt(0)>=65 && tAccount.getText().charAt(0)<=90) ||(tAccount.getText().charAt(0)>=97 && tAccount.getText().charAt(0)<=122)))
            			setVerifyMessage("帳號須以英文字母開頭(不限大小寫)",false);
            		else if(tAccount.getText().length() < 4)
            			setVerifyMessage("格式錯誤!請輸入4~12字元",false);
            		else{
            			if(isStringLegal(tAccount.getText()))
    						RA.setState("VerifyAccount");
    					else
    						setVerifyMessage("帳號內含非法字元!",false);
            		}
            	}else {
            		setVerifyMessage("格式錯誤!請輸入4~12字元",false);
            	}			

			}
		});
		
		rPassword.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
	            // 當按下按鈕且為註冊畫面時
	            if(isRegister) {
	            	rSubmit.setEnabled(false);
	            	if(rPassword.getText().equals(tPassword.getText())) {
	            		set_rlPasswordMessage2("Correct !",true);
	            		check_rsubmitEnable();
	            	}else {
	            		set_rlPasswordMessage2("此密碼與上方密碼不一致!",false);
	            	}
	            }
	        }
		}); // 註冊事件
		
		rSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RA.setState("Register");
			}
		});
        setLayout(null);  // 版面配置
        add(tAccount);
        add(tPassword);
        add(lAccount);
        add(lPassword);
        add(submit);
        add(loading);
        add(loginSuccess);
        add(store);
        add(storeMessage);
        add(register);
        add(registerMessage);
        add(rPassword);
        add(rlPassword);
        add(rSubmit);
        add(rVerify);
        add(rVerifyMessage);
        add(rlPasswordMessage1);
        add(rlPasswordMessage2);
        this.setLocation(550, 350);
        this.setResizable(false);
        this.setBackground(new Color(185, 230, 250));
        setSize(300, 400);
        setTitle("Login the Game");
        
        tAccount.enableInputMethods (false);
        tPassword.enableInputMethods (false);
        rPassword.enableInputMethods (false);
        
        addWindowListener(  // 按下關閉鈕時結束
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
    }
	
	public void getStore() {
		try {
			FileReader fr = new FileReader("ClientData/loginInformation.txt");
			BufferedReader br = new BufferedReader(fr);
			StringTokenizer st;
			String line=br.readLine();
			String storeT = "",sInfomation = "";

			st  = new StringTokenizer(line, "[]");
			storeT = st.nextToken();
			sInfomation = st.nextToken();
			
			
			if(storeT.equals("T")) {
				isStore = true;
				store.setBackground(Color.LIGHT_GRAY);
				tAccount.setText(sInfomation.substring(1,sInfomation.length()-1));
			}else {
				store.setBackground(Color.white);
				isStore = false;
			}
			fr.close();
		} catch (FileNotFoundException e) {
			//如果找不到檔案，則新增loginInformation.txt
			File f = new File("ClientData/loginInformation.txt");
			try {
				f.createNewFile();
				setStore(false,"");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setStore(boolean T,String account) {
		FileWriter fw;
		try {
			fw = new FileWriter("ClientData/loginInformation.txt");
			if(T) {
				fw.write("[T]" + "[\"" + account + "\"]");
			}else {
				fw.write("[F]" + "[\"\"]");
			}
			fw.flush();
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void verify(String account,String password) {
		if(account.equals("")) {  
			JOptionPane.showMessageDialog(this, "帳號請勿為空", "登入失敗", 0);
			tAccount.setText("");
			tPassword.setText("");
		}else if(password.equals("")) {
			JOptionPane.showMessageDialog(this, "密碼請勿為空", "登入失敗", 0);
			setStore(isStore,account);
			tPassword.setText("");
			getStore();
		}else {
			setLoading(true);
			setStore(isStore,account);
			ConnectToServer IC = new ConnectToServer(this);
			IC.start();
		}
		
	}
	
	public String getAccount(){
		return tAccount.getText();
	}
	
	public String getPassword(){
		return tPassword.getText();
	}
	
	
	public void setLoading(boolean T) {
		if(T) {
			tAccount.setVisible(false);
			lAccount.setVisible(false);
			tPassword.setVisible(false);
			lPassword.setVisible(false);
			submit.setVisible(false);
			store.setVisible(false);
			storeMessage.setVisible(false);
			register.setVisible(false);
			registerMessage.setVisible(false);
			loading.setVisible(true);
		}else {
			tAccount.setText("");
			tPassword.setText("");
			getStore();
			tAccount.setVisible(true);
			lAccount.setVisible(true);
			tPassword.setVisible(true);
			lPassword.setVisible(true);
			submit.setVisible(true);
			store.setVisible(true);
			storeMessage.setVisible(true);
			register.setVisible(true);
			registerMessage.setVisible(true);
			loading.setVisible(false);
		}
	}
	
	public void setLoginSuccess() {
		tAccount.setVisible(false);
		lAccount.setVisible(false);
		tPassword.setVisible(false);
		lPassword.setVisible(false);
		submit.setVisible(false);
		loading.setVisible(false);
		store.setVisible(false);
		storeMessage.setVisible(false);
		register.setVisible(false);
		registerMessage.setVisible(false);
		loginSuccess.setVisible(true);
	}
	
	public void setVerifyMessage(String msg,boolean T) {
		rVerifyMessage.setText(msg);
		rAccountEnable = T;
		if(T) {
			rVerifyMessage.setForeground(new Color(0,200,0));
		}else {
			rVerifyMessage.setForeground(new Color(250,80,80));
		}
	}
	
	public void set_rlPasswordMessage1(String msg,boolean T) {
		rlPasswordMessage1.setText(msg);
		rPasswordFont = T;
		if(T) {
			rlPasswordMessage1.setForeground(new Color(0,200,0));
		}else {
			rlPasswordMessage1.setForeground(new Color(250,80,80));
		}
	}
	
	public void set_rlPasswordMessage2(String msg,boolean T) {
		rlPasswordMessage2.setText(msg);
		rPasswordSame = T;
		if(T) {
			rlPasswordMessage2.setForeground(new Color(0,200,0));
		}else {
			rlPasswordMessage2.setForeground(new Color(250,80,80));
		}
	}
	
	public void check_rsubmitEnable() {
		if( rAccountEnable && rPasswordFont && rPasswordSame)
			rSubmit.setEnabled(true);
		else
			rSubmit.setEnabled(false);
	}
	
	public boolean isStringLegal(String msg) {
		boolean legal = true;
		for(int i = 0;i<msg.length() && legal;i++) {
			if(!((msg.charAt(i)>=48 && msg.charAt(i)<=57) || (msg.charAt(i)>=65 && msg.charAt(i)<=90) || (msg.charAt(i)>=97 && msg.charAt(i)<=122))){
				legal = false;
			}
		}
		return legal;
	}
	
	public void setRegisterGUI(boolean T) {
		if(T) {
			isRegister = true;
			this.setBackground(new Color(255, 230, 230));
			setVerifyMessage("請驗證帳號是否能使用",false);
			set_rlPasswordMessage1("密碼長度:6~12字元",false);
			set_rlPasswordMessage2("再次輸入密碼",false);
			rSubmit.setEnabled(false);
			tPassword.setText("");
			rPassword.setText("");
			lAccount.setVisible(true);
			lAccount.setText("請輸入帳號");
			lAccount.setLocation(75, 50);
			tAccount.setVisible(true);
			tAccount.setLocation(75, 80);
			lPassword.setVisible(true);
			lPassword.setText("請輸入密碼");
			tPassword.setVisible(true);
			rVerify.setVisible(true);
			rVerifyMessage.setVisible(true);
			rPassword.setVisible(true);
			rlPassword.setVisible(true);
			rlPasswordMessage1.setVisible(true);
			rlPasswordMessage2.setVisible(true);
			rSubmit.setVisible(true);
			submit.setVisible(false);
			store.setVisible(false);
			storeMessage.setVisible(false);
			register.setText("立即登入");
			registerMessage.setText("已有帳號?");
		}else {
			isRegister = false;
			this.setBackground(new Color(185, 230, 250));
			tPassword.setText("");
			lAccount.setVisible(true);
			lAccount.setText("使用者帳號");
			lAccount.setLocation(75, 60);
			tAccount.setVisible(true);
			tAccount.setLocation(75, 90);
			lPassword.setVisible(true);
			lPassword.setText("使用者密碼");
			tPassword.setVisible(true);
			rVerify.setVisible(false);
			rVerifyMessage.setVisible(false);
			rPassword.setVisible(false);
			rlPassword.setVisible(false);
			rlPasswordMessage1.setVisible(false);
			rlPasswordMessage2.setVisible(false);
			rSubmit.setVisible(false);
			submit.setVisible(true);
			store.setVisible(true);
			storeMessage.setVisible(true);
			register.setText("立即註冊");
			registerMessage.setText("沒有帳號?");
		}
	}
	public String getIP() {
		return ServerIP;
	}
	
	public int getPORT() {
		return ServerPORT;
	}
	
	public void loadSetting(){
		Properties properties = new Properties();
		try {
		    properties.load(new FileInputStream("ClientData/connectingSet.properties"));
		} catch (FileNotFoundException ex) {
		    ex.printStackTrace();
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		ServerIP = properties.getProperty("server_IP");
	    ServerPORT = Integer.parseInt(properties.getProperty("server_PORT"));
	}
	
	
	
	public static void main(String[] args) {
		ConnectGame CG = new ConnectGame();
		CG.setVisible(true);
	}
	
	
	
}