package ConnectGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class Servers {
    //�N�����쪺socket�ܦ��@�Ӷ��X
  protected static List<Socket> sockets = new Vector<>();
  protected static List<String> waitingPlayers = new Vector<>();
  protected static List<String> onlinePlayers = new Vector<>();
  protected static Map<String, ServerThead> playerInfo =new HashMap<String, ServerThead>();
  private static JFrame f= new JFrame("Server");
  private static JTextArea textArea;
  private static JTextArea onlineAmount;
  private static Font F = new Font("�L�n������", Font.BOLD, 18);
  
  public Servers() {
  }
  
  public static void setTextArea(String S) {
	  textArea.append(S + "\n");
	  textArea.setCaretPosition(textArea.getDocument().getLength()); 
  }
  
  public static void displayOnlinePlayers() {
	  onlineAmount.setText("");
	  for(String playerName : onlinePlayers) {
		  onlineAmount.append( playerName +  "\n");
	  }
	  onlineAmount.setCaretPosition(onlineAmount.getDocument().getLength()); 
  }
  
  public static void main(String[] args) throws IOException {
        //�إߪA�Ⱥ�
        ServerSocket server = new ServerSocket(5200);
        Servers S = new Servers();
        textArea = new JTextArea("");
        //textArea.setBounds(45, 25, 500, 300);
        textArea.setBorder (BorderFactory.createLineBorder(Color.gray,3));
        textArea.setFont(F);
        textArea.setEditable(false);
        JScrollPane g = new JScrollPane(textArea);
        g.setBounds(45, 25, 550, 400);
        
        onlineAmount = new JTextArea("");
        //textArea.setBounds(45, 25, 500, 300);
        onlineAmount.setBorder (BorderFactory.createLineBorder(Color.gray,3));
        onlineAmount.setFont(F);
        onlineAmount.setEditable(false);
        JScrollPane go = new JScrollPane(onlineAmount);
        go.setBounds(600, 60, 205, 365);
        
        JLabel inlineTxt = new JLabel("�b�u�W��");
        inlineTxt.setHorizontalAlignment(JLabel.CENTER);
        inlineTxt.setFont(F);
        inlineTxt.setBounds(600, 25, 205, 35);
        
        waitingToPlay wtp = new waitingToPlay();
        wtp.start();
        f.setSize(850, 500);
        f.setLocation(500, 300);
        f.setLayout(null);
        f.setResizable(false);
        f.add(g);
        f.add(go);
        f.add(inlineTxt);
        f.setVisible(true);
        f.getContentPane().setBackground(new Color(185, 230, 250));
  	  	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	  	setTextArea("���A���w�}��!");
        boolean flag = true;
        //�����Ȥ�ݽШD
        while (flag){
            try {
             //���뵥�ݫȤ�ݪ��s�u
            Socket accept = server.accept();
            synchronized (sockets){
                sockets.add(accept);
            }
            //�h�Ӧ��A��������i���Ȥ�ݪ��T��
            Thread thread = new Thread(new ServerThead(accept));
            thread.start();
            //���򲧱`�C
            }catch (Exception e){
                flag = false;
                e.printStackTrace();
            }
        }
        //�������A��
        server.close();
    }
  
  public static class waitingToPlay extends Thread{
  	private String player1ID,player2ID;
  	public waitingToPlay(){
  	}
  	
  	public void run() {
  		boolean waiting = true;
  		Random ran = new Random();
  		int choice = 0;
  		while(waiting) {
  			try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
  			if(waitingPlayers.size() >= 2) {
  				player1ID = waitingPlayers.get(0);
  				player2ID = waitingPlayers.get(1);
  				waitingPlayers.remove(1);
  				waitingPlayers.remove(0);
  				
  				playerInfo.get(player1ID).setOpponentST(playerInfo.get(player2ID));
  	  			playerInfo.get(player2ID).setOpponentST(playerInfo.get(player1ID));
  	  			
  	  			choice = ran.nextInt(31);
  	  		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
  			try {
				playerInfo.get(player1ID).print("#SfPg=" + player2ID);//Successful pairing
				playerInfo.get(player2ID).print("#SfPg=" + player1ID);
			} catch (IOException e) {
				e.printStackTrace();
			}
  			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
  			try {
				playerInfo.get(player1ID).print("#RdTS=" + player2ID);//Ready to start
				playerInfo.get(player2ID).print("#RdTS=" + player1ID);
			} catch (IOException e) {
				e.printStackTrace();
			}
  			
  			playerInfo.get(player1ID).setFP_gameMusic(choice);
	  		playerInfo.get(player2ID).setFP_gameMusic(choice);
  			timer t = new timer(playerInfo.get(player1ID),playerInfo.get(player2ID),3,0);
  			t.start();
  			}
  		}
  	}
  }
  
  
  public static class timer extends Thread{
		private int min = 0;
		private int sec = 0;
		private ServerThead player1,player2;
		
		public timer(ServerThead player1,ServerThead player2,int m , int s) {
			this.player1 = player1;
			this.player2 = player2;
			min = m;
			sec = s;
		}
		public void run() {
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			player1.startFP_game();
			player2.startFP_game();
			try {
				Thread.sleep(2300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean connecting = true;
				while(!(min== 0 && sec == -1) && connecting) {
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
					try {
						player1.setFP_time(min,sec);
					}catch(IOException e) {
						connecting = false;
					}
					try {
						player2.setFP_time(min,sec);
					}catch(IOException e) {
						connecting = false;
					}
				}
				if(player1.isConnecting()) {
					player1.endFP_game();
				}
				if(player2.isConnecting()) {
					player2.endFP_game();
				}
		}
		
	}

}
