package ConnectGame;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * ���A��������A�D�n�ӳB�z�h�ӫȤ�ݪ��ШD
 */
public class ServerThead extends Servers implements Runnable{

    Socket socket;
    String socketName,Command,Msg,Account,cardID;
    int forage,bone;
    ArrayList<String> aAccount = new ArrayList<String>();
    ArrayList<String> aPassword = new ArrayList<String>();
    private ServerThead opponentST;

    public ServerThead(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            //�]�w�ӫȤ�ݪ����I�a�}
            socketName = socket.getRemoteSocketAddress().toString();
            Servers.setTextArea("IP"+socketName+" �w�s�u�ܦ��A��");
            readAtPd();
            boolean flag = true;
            while (flag)
            {
                //����A���ݸӫȤ�ݪ���X�y
                String read = reader.readLine();
                try {
                	Command = read.substring(0,6);
                	Msg = read.substring(6);
                }catch(ArrayIndexOutOfBoundsException e) {
                	flag = false;
                    continue;
                }catch(NullPointerException e) {
                	flag = false;
                    continue;
                }
                
                if(Command.equals("#AtPd=")) {
                	if(login(Msg) == false) {
                		flag = false;
                        continue;
                	}else {
                		readInfo();
                		playerInfo.put(Account, this);
                		displayOnlinePlayers();
                		print("#AFBC="+ Account+":"+forage+":"+bone+":["+cardID+"]");
                	}
                }else if(Command.equals("#VRAt=")) {
                	VerifyRegisterAccount(Msg);
                }else if(Command.equals("#RtAP=")) {
                	register(Msg);
                }else if(Command.equals("#MgTS=")) {
                	if(Msg.length()>=30) {
                		Msg = Msg.substring(0, 30);
                	}
                	broadCast("#MgFS="+ Account + ": "+ Msg);
                }else if(Command.equals("#WFMP=")) {
                	waitingPlayers.add(Msg);
                }else if(Command.equals("#CFWg=")) {
                	waitingPlayers.remove(Msg);
                }else if(Command.equals("#MNSe=")) {
                	opponentST.setOpponentScore(Msg);
                }//else if(Command.equals("#EFMP=")) {
                	
                //}
                
                System.out.println(read);
                //�V�u�W�Ȥ�ݿ�X��T
                
                /* print(Command);
                 * print(Msg);
                 */
            }
            closeConnect();
        } catch (IOException e) {
            try {
            	waitingPlayers.remove(Account);
            	onlinePlayers.remove(Account);
            	displayOnlinePlayers();
            	print(e.toString());
                closeConnect();
            } catch (IOException e1) {
            	waitingPlayers.remove(Account);
                e1.printStackTrace();
            }
        }
    }
    /**
     **�V�ثe�Ȥ�ݶǰe�T��
     * @param msg
     * @throws IOException
     */
    public void print(String msg) throws IOException {
        PrintWriter out = null;
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
            out.println(msg);
            out.flush();
    }
    /**
     **�V�Ҧ��u�W�Ȥ��socket��o�T��
     * @param msg
     * @throws IOException
     */
    private void broadCast(String msg) throws IOException {
        PrintWriter out = null;
        synchronized (sockets){
        for (Socket sc : sockets){
        	out = new PrintWriter(new OutputStreamWriter(sc.getOutputStream(), "UTF-8"), true);
            out.println(msg);
            out.flush();
        	}
        }
    }
    /**
     * ������socket���s�u
     * @throws IOException
     */
    public void closeConnect() throws IOException {
    	Servers.setTextArea("IP"+socketName+" �w���_�s�u");
        //print("[email protected]"+socketName+"�w�h�X���");
        //�����S�s�u�W���Ȥ��
    	synchronized (waitingPlayers){
        	waitingPlayers.remove(Account);
        }
        
        synchronized (onlinePlayers){
        	onlinePlayers.remove(Account);
        }
    	
        synchronized (sockets){
            sockets.remove(socket);
        }
        
        socket.close();
    }
    
    public void setOpponentST(ServerThead st) {
    	opponentST = st;
    }
    
    public void setOpponentScore(String Score) {
    	try {
			print("#OtNS=" + Score);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void setFP_gameMusic(int musicChoice) {
    	try {
			print("#SFGM=" + musicChoice);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void startFP_game() {
    	try {
			print("#StFG=");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void endFP_game() {
    	try {
			print("#EdFG=");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void accountLogingOtherside() {
    	try {
			print("#ALOs=");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public boolean isConnecting() {
    	if(socket.isConnected() == true && socket.isClosed() == false) {
    		return true;
    	}
    	return false;
    }
    
    public void setFP_time (int m,int s) throws IOException{
		print("#SFPT="+ m + ":" + s);
    }
    
    public void readAtPd() {
    	try {
			FileReader fr = new FileReader("data/AtPd.txt");
			BufferedReader br = new BufferedReader(fr);
			StringTokenizer st;
			String line;
			while((line=br.readLine())!=null) {
				//System.out.println(line);
				if(!line.equals("")) {
					st  = new StringTokenizer(line, "[:]");
					aAccount.add(st.nextToken());
					aPassword.add(st.nextToken());
				}
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    public boolean login(String AtPd) {
    	StringTokenizer st = new StringTokenizer(AtPd, ":");
    	Account = st.nextToken();
    	String password = st.nextToken();
    	int state = 0; // 0 �N��d�L���b��,1 �N��K�X���~,2��ܵn�J���\
    	boolean flag = true;
    	//���Ҧ��b���K�X
    	for(int i = 0;i<aAccount.size() && flag;i++) {
    		//���b��
    		if(Account.equals(aAccount.get(i))) {
    			//���K�X
    			if(password.equals(aPassword.get(i))) {
    				state = 2;
    			}else {
    				state = 1;
    			}
    			flag = false;
    		}
    	}
    	
    	try {
    		switch(state) {
        	case 0:
        		Servers.setTextArea("IP"+socketName+" �n�J����:�d�L���b��!");
        		print("�d�L���b��!!!");
        		return false;
        	case 1:
        		Servers.setTextArea("IP"+socketName+" �n�J����:�K�X���~!");
        		print("�K�X���~!");
        		return false;
        	case 2:
        		if(onlinePlayers.indexOf(Account) == -1) {
        			Servers.setTextArea("IP"+socketName+" ���\�n�J! �b��:" + Account);
            		print("Correct");
        			onlinePlayers.add(Account);
        			return true;
        		}else {
        			onlinePlayers.remove(Account);
        			displayOnlinePlayers();
        			Servers.setTextArea("IP"+socketName+" �n�J����:�b���w�b�u�W!");
        			playerInfo.get(Account).accountLogingOtherside();
            		print("���b���w�b�u�W�A�Э��s�n�J!");
            		return false;
        		}
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return false;
    	

    }
    
    public boolean register(String RtAP) {
    	StringTokenizer st = new StringTokenizer(RtAP, ":");
    	String rAccount = st.nextToken();
    	String rPassword = st.nextToken();
    	int state = 0; // 0 �N����U���\,1 �N��K�X���~
    	boolean flag = true;
    	
    	//�ˬd�K�X�O�_�ŦX����(6~12�r��)
    	if(rPassword.length()<6 || rPassword.length()>12) {
    		state = 2;
    		flag = false;
    	}
    	//���Ҧ��b���K�X
    	readAtPd();
    	for(int i = 0;i<aAccount.size() && flag;i++) {
    		//���b��
    		if(rAccount.equals(aAccount.get(i))) {
    			//���K�X
    			state = 1;
    			flag = false;
    		}
    	}
    	
    	try {
    		switch(state) {
        	case 0:
        		FileWriter fw;
        		try {
        			fw = new FileWriter("data/AtPd.txt",true);

        			fw.write("\r\n[" + rAccount + ":" + rPassword + "]");

        			fw.flush();
        			fw.close();
        			//�g�J��l�Ƹ�T
        			File f = new File("data/Info/"+rAccount+".txt");
        			try {
        				f.createNewFile();
        				writeData(f,0,0,"");
        			} catch (IOException e1) {
        				e1.printStackTrace();
        			}
        			Servers.setTextArea("IP"+socketName+" ���U���\:" + rAccount + "�w�إ߱b��!");
            		print("EstablishSuccessfully");
            		return true;
        		} catch (IOException e) {
        			Servers.setTextArea("IP"+socketName+"���U����:�L�k�}�� data/AtPd.txt!");
        			return false;
        		}
 
        	case 1:
        		Servers.setTextArea("IP"+socketName+" ���U����:�b���w�s�b!");
        		print("���U����:�b���w�s�b!");
        		return false;
        	case 2:
        		Servers.setTextArea("IP"+socketName+" ���U����:�K�X���ŦX����(6~12�r��)!");
        		print("���U����:�K�X���ŦX����(6~12�r��)!");
        		return false;
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    public boolean VerifyRegisterAccount(String account) {
    	
    	int state = 0; // 0 �N����U���\,1 �N��b���w�s�b
    	boolean flag = true;
    	//���Ҧ��b���K�X
    	for(int i = 0;i<aAccount.size() && flag;i++) {
    		//���b��
    		if(account.equals(aAccount.get(i))) {
    			//���K�X
    			state = 1;
    			flag = false;
    		}
    	}
    	
    	try {
    		switch(state) {
        	case 0:
        		Servers.setTextArea("IP"+socketName+" ���Ҧ��\:�b��[" +account + "]�i�H�ϥ�!");
        		print("accountEnable");
        		return true;
        	case 1:
        		Servers.setTextArea("IP"+socketName+" ���ҥ���:�b��[" +account + "]�w�s�b!");
        		print("���U����:�b���w�s�b!");
        		return false;
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    public void writeData(File f,int forage,int bone,String cardID) throws IOException{
    	FileWriter fw = new FileWriter(f);
		fw.write("["+forage+"]\r\n");
		fw.write("["+bone+"]\r\n");
		fw.write("["+cardID+"]\r\n");
		fw.flush();
		fw.close();
    }
    
    public void readInfo() {
    	try {
			FileReader fr = new FileReader("data/Info/"+Account+".txt");
			BufferedReader br = new BufferedReader(fr);
			StringTokenizer st;
			String line;
			line = br.readLine();
			this.forage=Integer.parseInt(line.substring(1, line.length()-1));
			line = br.readLine();
			this.bone=Integer.parseInt(line.substring(1, line.length()-1));
			line = br.readLine();
			this.cardID=line.substring(1, line.length()-1);
			//Servers.setTextArea("Account:" + Account+"\n"+"forage:"+forage+"\n"+"bone:"+bone+"\n"+"cardID:"+cardID);
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
}