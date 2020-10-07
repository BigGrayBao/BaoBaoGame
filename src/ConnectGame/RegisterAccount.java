package ConnectGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RegisterAccount extends Thread{
	
	private Socket skt;        // 客戶端連線Socket物件
    private String host;  // 指定的伺服端IP
    private int port,state = 0;          // 指定的伺服端連接埠
    private ConnectGame CG;
    private BufferedReader Input;
    private PrintStream Output;
    
    public RegisterAccount(String ip, int port,ConnectGame CG) {
		host = ip;
        this.port = port;
        this.CG = CG;
	}
    
    public void run() {
		
		try {
			skt = new Socket(host, port);
			Input = new BufferedReader(new InputStreamReader(skt.getInputStream(),"UTF-8"));
			Output = new PrintStream(skt.getOutputStream(),true,"UTF-8");
			boolean flag = true ,accountEnable = false;
			String Command = "";
			CG.setRegisterGUI(true);
			while(flag) {
				//state: 預設0,驗證帳號為1,註冊為2,離開為3
				if(state == 1) {
					state = 0;
					VerifyRegisterAccount(CG.getAccount());
					Command = Input.readLine();
					if(Command.equals("accountEnable")) {
						CG.setVerifyMessage("此帳號可以使用",true);
						CG.check_rsubmitEnable();
						accountEnable = true;
					}else{
						CG.setVerifyMessage("此帳號已存在!",false);						  
						accountEnable = false;
					}
				}else if(state == 2 && accountEnable) {
					RegisterDataOutput(CG.getAccount(),CG.getPassword());
					Command = Input.readLine();
					if(Command.equals("EstablishSuccessfully")) {
						JOptionPane.showMessageDialog(CG, "已成功創立帳戶:" + CG.getAccount(), "登入成功", 1);
						flag = false;
					}
					
				}else if(state == 3) {
					flag = false;
				}
				try {
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			skt.close();
			CG.setRegisterGUI(false);
		}catch(IOException e) {
			JOptionPane.showMessageDialog(CG, e, "無法連接至伺服器", 0);
		}
	
	}
    
    public void setState(String command) {
    	if(command.equals("VerifyAccount")) {
    		state = 1;
    	}else if(command.equals("Register")) {
    		state = 2;
    	}else if(command.equals("Exit")){
    		state = 3;
    	}else {
    		state = 0;
    	}
    }
    
    public int getstate() {
    	return state;
    }
    
    public void RegisterDataOutput(String account,String password) {
        Output.println("#RtAP=" + account + ":" + password);
    }
    
    public void VerifyRegisterAccount(String account) {
        Output.println("#VRAt=" + account);
    }

}
