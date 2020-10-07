package ConnectGame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class musicPlayer{
	private Clip myClip;
	public musicPlayer(String url){	
		AudioInputStream input;
		try {
		input= AudioSystem.getAudioInputStream(new File(url));
		myClip = AudioSystem.getClip();
		myClip.open(input);
		} catch (UnsupportedAudioFileException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (LineUnavailableException e) {  
            e.printStackTrace();  
        } 
	}
	
	public void start() {
		myClip.setFramePosition(0);
		myClip.start();
	}
	
	public void setPause() {
		myClip.stop();
	}
	
	public void setContinue() {
		myClip.start();
	}
	
	public void setLoop() {
		myClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}
