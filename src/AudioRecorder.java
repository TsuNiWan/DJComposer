import java.io.*;
import javax.sound.sampled.*;


public class AudioRecorder {
    AudioFormat audioFormat;//音頻格式
    AudioInputStream audioInputStream;//音頻輸入流
    TargetDataLine targetDataLine;//目標數據行,可從中讀取音頻數據
    SourceDataLine sourceDataLine;//來源數據行,可寫入數據至此
    ByteArrayInputStream bais;
    ByteArrayOutputStream baos;
    Thread thread1;
    Thread thread2;
    Boolean stopRecordFlag = false;

    public void capture(){
        try {
            audioFormat = getAudioFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine)AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat, targetDataLine.getBufferSize());
            targetDataLine.start();

            Record record = new Record();
            thread1 = new Thread(record);
            thread1.start();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stopRecord(){
        //stopRecordFlag = true;
        thread1 = null;
    }

    public void pauseRecord(){
        targetDataLine.stop();
    }

    public void resumeRecord(){
        targetDataLine.start();
    }

    public void stopPlay(){
        thread2 = null;
    }

    public void pausePlay(){
        sourceDataLine.stop();
    }

    public void resumePlay(){
        sourceDataLine.start();
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
            thread2 = new Thread(play);
            thread2.start();
            //System.out.println(audioFormat.getFrameSize());
            //System.out.println(audioData.length);

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
        File file;
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
        long startRecordTime = System.currentTimeMillis();

        public void run(){
            baos = new ByteArrayOutputStream();
            try {
                //stopRecordFlag = false;
                while (thread1!=null) {
                    //System.out.print("錄了"+(System.currentTimeMillis()-startRecordTime)*0.001+"秒！\r");
                    int numBytesRead = targetDataLine.read(data, 0, data.length);
                    if (numBytesRead > 0) {
                        baos.write(data, 0, numBytesRead);
                        //System.out.println(cnt);
                    }
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
                    targetDataLine.stop();
                    targetDataLine.close();
                }
            }
        }
    }

    class Play implements Runnable{
        //播放baos中的數據
        public void run(){
            byte[] data = new byte[10000];
            while (thread2!=null) {
                try {
                    int numBytesRead = 0;
                    if ((numBytesRead = audioInputStream.read(data, 0, data.length)) == -1)
                        break;
                    int numBytesRemaining = numBytesRead;
                    while (numBytesRemaining > 0) {
                        numBytesRemaining -= sourceDataLine.write(data, 0, numBytesRemaining);
                        //System.out.println(cnt);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            sourceDataLine.stop();
            sourceDataLine.close();
        }
    }
}
