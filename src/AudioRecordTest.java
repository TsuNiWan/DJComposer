import java.util.Scanner;

public class AudioRecordTest {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        AudioRecorder recorder = new AudioRecorder();
        while (true){
            System.out.print("r:開始錄音 s:停止錄音 p:播放錄音 f:存檔:");
            String choice = input.next();
            if(choice.equals("r")) {
                System.out.println("-------------Recording-------------");
                recorder.capture();
            }
            if(choice.equals("s")) {
                System.out.println("-------------Stop Record-------------");
                recorder.stopRecord();
            }
            if(choice.equals("p")) {
                System.out.println("-------------Playing-------------");
                recorder.play();
            }
            if(choice.equals("f")) {
                System.out.print("檔名:");
                recorder.save(input.next());
                break;
            }
        }
        System.out.println("Record Done");
    }
}
