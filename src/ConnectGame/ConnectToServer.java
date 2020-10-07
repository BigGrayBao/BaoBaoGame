package ConnectGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConnectToServer extends Thread {
	
	private Socket skt;        // 客戶端連線Socket物件
    private String host;  // 指定的伺服端IP
    private int port;          // 指定的伺服端連接埠
    private ConnectGame CG;
    private BufferedReader Input;
    private PrintStream Output;
    private MainWindow Mw;
    private boolean isFirstMsgSend = false;
    
	public ConnectToServer(ConnectGame CG) {
		host = CG.getIP() ;
        port = CG.getPORT();
        this.CG = CG;
	}
	
	public void run() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			skt = new Socket(host, port);
			Input = new BufferedReader(new InputStreamReader(skt.getInputStream(),"UTF-8"));
			Output = new PrintStream(skt.getOutputStream(),true,"UTF-8");
			AtPdOutput(CG.getAccount(),CG.getPassword());
			String Command = Input.readLine();
			boolean Connecting = false;
			if(Command.equals("Correct")) {
				Connecting = true;
				CG.setLoginSuccess();
				try {
					Thread.sleep(1600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				CG.setVisible(false);
				/**
				 * 開啟遊戲視窗
				 */
				Mw = new MainWindow(this);
			}else {
				skt.close();
				JOptionPane.showMessageDialog(CG, Command, "登入失敗", 0);
			}
			String Msg,read;
			while(Connecting && (read = Input.readLine())!=null) {
				try {
                	Command = read.substring(0,6);
                	Msg = read.substring(6);
                }catch(ArrayIndexOutOfBoundsException e) {
                	Connecting = false;
                    continue;
                }
				
				if(Command.equals("#AFBC=")) {
					setStartInfo(Msg);
				}else if(Command.equals("#MgFS=")) { //message from server
					if(isFirstMsgSend == false) {
						Mw.setFirstTextArea(Msg);
						isFirstMsgSend = true;
					}else {
						Mw.setTextArea(Msg);
					}
				}else if(Command.equals("#SfPg=")) { // Successful for waiting praying
					Mw.setPairing_nowPeople(2);
				}else if(Command.equals("#RdTS=")) { // Ready to start
					Mw.setFighting();
					Mw.setMultifyFP(true);
					Mw.setMultifyFP_OpponentID(Msg);
				}else if(Command.equals("#OtNS=")) { // Opponent new score
					Mw.setMultifyFP_OpponentScore(Msg);
				}else if(Command.equals("#SFPT=")) { // set FP game time
					setFP_time(Msg);
				}else if(Command.equals("#SFGM=")) { // set FP game music
					Mw.setMultifyFP_gameMusic(Integer.parseInt(Msg));
				}else if(Command.equals("#StFG=")) { // start FP game
					Mw.setMultifyFP_gameStart();
				}else if(Command.equals("#EdFG=")) { // end FP game
					Mw.setMultifyFP_gameEnd();
				}else if(Command.equals("#ALOs=")) { // account login other side
					JOptionPane.showMessageDialog(CG, "情重新登入!", "帳號已在別處登入", 0);
					if(Mw != null)
						System.exit(0);
				}
				
			}
			CG.setLoading(false);
			
		}catch(IOException e) {
			JOptionPane.showMessageDialog(CG, e, "無法連接至伺服器", 0);
			if(Mw != null)
				System.exit(0);
		}
	
	}
	
	/**
	 * Output Account and Password
	 * @param account
	 * @param password
	 */
	
	public void AtPdOutput(String account,String password) {
        Output.println("#AtPd=" + account + ":" + password);
    }
	
	public void MgTSOutput(String message) {
        Output.println("#MgTS=" + message);
    }
	
	public void WaitForMutilyPlay(String account) {
        Output.println("#WFMP=" + account);
    }
	
	public void ExitForMutilyPlay(String account) {
        Output.println("#EFMP=" + account);
    }
	
	public void CancleForWaiting(String account) {
        Output.println("#CFWg=" + account);
    }
	
	public void MyNewScore(int score) {
        Output.println("#MNSe=" + score);
    }
	
	public void setStartInfo(String AFBC) {
		StringTokenizer st = new StringTokenizer(AFBC, ":");
    	String account = st.nextToken();
    	String forage = st.nextToken();
    	String bone = st.nextToken();
    	String cardID = st.nextToken();
    	Mw.setAccount(account);
    	Mw.setForage(forage);
    	Mw.setBone(bone);
	}
	
	public void setFP_time(String ms) {
		StringTokenizer st = new StringTokenizer(ms , ":");
		String min = st.nextToken();
		String sec = st.nextToken();
		Mw.setMultifyFP_time(min, sec);
	}
	
	public boolean getIsFirstMsgSend() {
		return isFirstMsgSend;
	}
}
