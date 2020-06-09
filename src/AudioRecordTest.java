import java.util.Scanner;

public class AudioRecordTest {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        AudioRecorder recorder = new AudioRecorder();
        while (true){
            System.out.println("q:開始錄音 w:暫停錄音 e:繼續錄音 r:停止錄音");
            System.out.println("t:播放錄音 y:暫停播放 u:繼續播放 i:停止播放 f:存檔:");
            String choice = input.next();
            if(choice.equals("q")) {
                System.out.println("-------------Recording-------------");
                recorder.capture();
            }
            if(choice.equals("w")) {
                System.out.println("-------------Pause Record-------------");
                recorder.pauseRecord();
            }
            if(choice.equals("e")) {
                System.out.println("-------------Resume Record-------------");
                recorder.resumeRecord();
            }
            if(choice.equals("r")) {
                System.out.println("-------------Stop Record-------------");
                recorder.stopRecord();
            }
            if(choice.equals("t")) {
                System.out.println("-------------Playing-------------");
                recorder.play();
            }
            if(choice.equals("y")) {
                System.out.println("-------------Pause Playing-------------");
                recorder.pausePlay();
            }
            if(choice.equals("u")) {
                System.out.println("-------------Resume Playing-------------");
                recorder.resumePlay();
            }
            if(choice.equals("i")) {
                System.out.println("-------------Stop Playing-------------");
                recorder.stopPlay();
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
