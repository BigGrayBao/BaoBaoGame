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
 * 伺服器執行緒，主要來處理多個客戶端的請求
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
            //設定該客戶端的端點地址
            socketName = socket.getRemoteSocketAddress().toString();
            Servers.setTextArea("IP"+socketName+" 已連線至伺服器");
            readAtPd();
            boolean flag = true;
            while (flag)
            {
                //阻塞，等待該客戶端的輸出流
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
                //向線上客戶端輸出資訊
                
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
     **向目前客戶端傳送訊息
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
     **向所有線上客戶端socket轉發訊息
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
     * 關閉該socket的連線
     * @throws IOException
     */
    public void closeConnect() throws IOException {
    	Servers.setTextArea("IP"+socketName+" 已中斷連線");
        //print("[email protected]"+socketName+"已退出聊天");
        //移除沒連線上的客戶端
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
    	int state = 0; // 0 代表查無此帳號,1 代表密碼錯誤,2表示登入成功
    	boolean flag = true;
    	//比對所有帳號密碼
    	for(int i = 0;i<aAccount.size() && flag;i++) {
    		//比對帳號
    		if(Account.equals(aAccount.get(i))) {
    			//比對密碼
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
        		Servers.setTextArea("IP"+socketName+" 登入失敗:查無此帳號!");
        		print("查無此帳號!!!");
        		return false;
        	case 1:
        		Servers.setTextArea("IP"+socketName+" 登入失敗:密碼錯誤!");
        		print("密碼錯誤!");
        		return false;
        	case 2:
        		if(onlinePlayers.indexOf(Account) == -1) {
        			Servers.setTextArea("IP"+socketName+" 成功登入! 帳號:" + Account);
            		print("Correct");
        			onlinePlayers.add(Account);
        			return true;
        		}else {
        			onlinePlayers.remove(Account);
        			displayOnlinePlayers();
        			Servers.setTextArea("IP"+socketName+" 登入失敗:帳號已在線上!");
        			playerInfo.get(Account).accountLogingOtherside();
            		print("此帳號已在線上，請重新登入!");
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
    	int state = 0; // 0 代表註冊成功,1 代表密碼錯誤
    	boolean flag = true;
    	
    	//檢查密碼是否符合長度(6~12字元)
    	if(rPassword.length()<6 || rPassword.length()>12) {
    		state = 2;
    		flag = false;
    	}
    	//比對所有帳號密碼
    	readAtPd();
    	for(int i = 0;i<aAccount.size() && flag;i++) {
    		//比對帳號
    		if(rAccount.equals(aAccount.get(i))) {
    			//比對密碼
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
        			//寫入初始化資訊
        			File f = new File("data/Info/"+rAccount+".txt");
        			try {
        				f.createNewFile();
        				writeData(f,0,0,"");
        			} catch (IOException e1) {
        				e1.printStackTrace();
        			}
        			Servers.setTextArea("IP"+socketName+" 註冊成功:" + rAccount + "已建立帳號!");
            		print("EstablishSuccessfully");
            		return true;
        		} catch (IOException e) {
        			Servers.setTextArea("IP"+socketName+"註冊失敗:無法開啟 data/AtPd.txt!");
        			return false;
        		}
 
        	case 1:
        		Servers.setTextArea("IP"+socketName+" 註冊失敗:帳號已存在!");
        		print("註冊失敗:帳號已存在!");
        		return false;
        	case 2:
        		Servers.setTextArea("IP"+socketName+" 註冊失敗:密碼不符合長度(6~12字元)!");
        		print("註冊失敗:密碼不符合長度(6~12字元)!");
        		return false;
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    public boolean VerifyRegisterAccount(String account) {
    	
    	int state = 0; // 0 代表註冊成功,1 代表帳號已存在
    	boolean flag = true;
    	//比對所有帳號密碼
    	for(int i = 0;i<aAccount.size() && flag;i++) {
    		//比對帳號
    		if(account.equals(aAccount.get(i))) {
    			//比對密碼
    			state = 1;
    			flag = false;
    		}
    	}
    	
    	try {
    		switch(state) {
        	case 0:
        		Servers.setTextArea("IP"+socketName+" 驗證成功:帳號[" +account + "]可以使用!");
        		print("accountEnable");
        		return true;
        	case 1:
        		Servers.setTextArea("IP"+socketName+" 驗證失敗:帳號[" +account + "]已存在!");
        		print("註冊失敗:帳號已存在!");
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