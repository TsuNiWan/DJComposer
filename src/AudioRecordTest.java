import java.util.Scanner;

public class AudioRecordTest {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        AudioRecorder recorder = new AudioRecorder();
        while (true){
            System.out.print("r:開始 s:停止 p:播放 f:存檔:");
            String choice = input.next();
            if(choice.equals("r"))
                recorder.capture();
            if(choice.equals("s"))
                recorder.stop();
            if(choice.equals("p"))
                recorder.play();
            if(choice.equals("f")) {
                recorder.save(input.next());
                break;
            }
        }
        System.out.println("Record Done");
    }
}
