package sounds;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

    public void playSound(String soundFile) throws LineUnavailableException {


        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File("assets/sounds/" + soundFile));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException uae) {
            System.out.println(uae);
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (LineUnavailableException lua) {
            System.out.println(lua);
        }
    }
}
// Tillfällig lösning; för att spela ett ljud
//Sound sound = new Sound();
//                try {
//                    sound.playSound("Niggaaah.wav");
//} catch (LineUnavailableException ex) {
//                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//                }