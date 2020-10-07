package ConnectGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RegisterAccount extends Thread{
	
	private Socket skt;        // �Ȥ�ݳs�uSocket����
    private String host;  // ���w�����A��IP
    private int port,state = 0;          // ���w�����A�ݳs����
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
				//state: �w�]0,���ұb����1,���U��2,���}��3
				if(state == 1) {
					state = 0;
					VerifyRegisterAccount(CG.getAccount());
					Command = Input.readLine();
					if(Command.equals("accountEnable")) {
						CG.setVerifyMessage("���b���i�H�ϥ�",true);
						CG.check_rsubmitEnable();
						accountEnable = true;
					}else{
						CG.setVerifyMessage("���b���w�s�b!",false);						  
						accountEnable = false;
					}
				}else if(state == 2 && accountEnable) {
					RegisterDataOutput(CG.getAccount(),CG.getPassword());
					Command = Input.readLine();
					if(Command.equals("EstablishSuccessfully")) {
						JOptionPane.showMessageDialog(CG, "�w���\�Х߱b��:" + CG.getAccount(), "�n�J���\", 1);
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
			JOptionPane.showMessageDialog(CG, e, "�L�k�s���ܦ��A��", 0);
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
