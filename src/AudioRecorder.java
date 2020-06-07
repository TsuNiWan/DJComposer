import java.io.*;
import javax.sound.sampled.*;


public class AudioRecorder {
    AudioFormat audioFormat;//音頻格式
    AudioInputStream audioInputStream;//音頻輸入流
    TargetDataLine targetDataLine;//目標數據行,可從中讀取音頻數據
    SourceDataLine sourceDataLine;//來源數據行,可寫入數據至此
    ByteArrayInputStream bais;
    ByteArrayOutputStream baos;
    Boolean stopFlag = false;

    public void capture(){
        try {
            audioFormat = getAudioFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine)AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();

            Record record = new Record();
            Thread thread1 = new Thread(record);
            thread1.start();

        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop(){
        stopFlag = true;
    }

    public void play(){
        byte[] audioData = baos.toByteArray();//将baos中的數據轉換為byte數據
        bais = new ByteArrayInputStream(audioData);
        audioFormat = getAudioFormat();
        audioInputStream = new AudioInputStream(bais, audioFormat, audioData.length/audioFormat.getFrameSize());
        try {
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();

            Play play = new Play();
            Thread thread2 = new Thread(play);
            thread2.start();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(audioInputStream != null)
                    audioInputStream.close();
                if(bais != null)
                    bais.close();
                if(baos != null)
                    baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void save(String fileName){
        audioFormat = getAudioFormat();
        byte[] audioData = baos.toByteArray();
        bais = new ByteArrayInputStream(audioData);
        audioInputStream = new AudioInputStream(bais, audioFormat, audioData.length/audioFormat.getFrameSize());
        File file = null;
        try {
            File filePath = new File("audioRecord");
            if(!filePath.exists())
                filePath.mkdir();
            file = new File(filePath.getPath()+"/"+fileName+".wav");
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, file);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                if(bais!=null)
                    bais.close();
                if(audioInputStream!=null)
                    audioInputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public AudioFormat getAudioFormat(){
        float sampleRate = 44100F;//採樣率
        int sampleSizeInBits = 16;//每個樣本中的bit
        int channels = 1;//1:單聲道 2:立體聲道
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    class Record implements Runnable{
        byte[] data = new byte[10000];

        public void run(){
            baos = new ByteArrayOutputStream();
            try {
                stopFlag = false;
                while (stopFlag != true){
                    int cnt = targetDataLine.read(data, 0, data.length);
                    if(cnt > 0)
                        baos.write(data, 0, cnt);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    if (baos != null)
                        baos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    targetDataLine.drain();
                    targetDataLine.close();
                }
            }
        }
    }

    class Play implements Runnable{
        //播放baos中的數據
        public void run(){
            byte[] data = new byte[10000];
            try {
                int cnt;
                while ((cnt = audioInputStream.read(data, 0, data.length))!=-1) {
                    if (cnt > 0)
                        sourceDataLine.write(data, 0, cnt);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                sourceDataLine.drain();
                sourceDataLine.close();
            }
        }
    }
}
