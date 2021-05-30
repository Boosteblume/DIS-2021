
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;




public class ThreadCreator{    
    int pageid;
    static int traid;
    static int lsn;
    String content;
    static String latestresult;
    public boolean redo;

    public static void main(String[] args) throws InterruptedException {
        Client client_1 = new Client();
        Client client_2 = new Client();
        Client client_3 = new Client();
        Client client_4 = new Client();
        Client client_5 = new Client();

        try {
            // FileReader input = new FileReader("C:\\Users\\Leon Weis\\OneDrive\\Documents\\Hamburg\\DatenbankenVorl\\Blatt4\\Blatt4\\logdata.txt");

            List<String> result = Files.readAllLines(Paths.get("C:\\Users\\Leon Weis\\OneDrive\\Documents\\Hamburg\\DatenbankenVorl\\Blatt4\\Blatt4\\logdata.txt"));

            // latestresult = result.get(result.size() - 1);
            // String [] latesttaid_result = latestresult.split(", ");
            // traid = Integer.valueOf(latesttaid_result[1]);

            latestresult = result.get(result.size() - 1);
            String [] latestlsn_result = latestresult.split(", ");
            lsn = Integer.valueOf(latestlsn_result[0]);

        }catch(Exception e){
            e.getStackTrace();
        }
        RecoveryTool.recoverymode();
        System.exit(0);


        Integer client_1_taid = client_1.beginTransaction();
        Integer client_2_taid = client_2.beginTransaction();
        Integer client_3_taid = client_3.beginTransaction();
        Integer client_4_taid = client_4.beginTransaction();
        Integer client_5_taid = client_5.beginTransaction();

        client_1.write(client_1_taid, 15, "test1");
        client_1.write(client_1_taid, 17, "test2");
        client_2.write(client_2_taid, 24, "test3");
        client_1.commit(client_1_taid);
        client_2.write(client_2_taid, 28, "test4");
        client_2.write(client_2_taid, 27, "test5");
        client_2.write(client_2_taid, 26, "test6");
        client_2.write(client_2_taid, 23, "test7");
        client_2.write(client_2_taid, 23, "test7 client_2 überschreibt siche selbst");
        client_2.commit(client_2_taid);
        client_3.write(client_3_taid, 34, "test8");
        
        client_3.write(client_3_taid, 36, "test9");
        client_3.write(client_3_taid, 39, "test10");
        client_4.write(client_4_taid, 45, "test11");
        client_3.commit(client_3_taid);
        client_4.write(client_4_taid, 46, "test13");
        client_4.write(client_4_taid, 47, "test14");
        client_4.write(client_4_taid, 17, "test2 client_4 überschreibt client_1");
        client_4.commit(client_4_taid);
        client_5.write(client_5_taid, 52, "test15");
        client_5.write(client_5_taid, 54, "test17");
        client_5.write(client_5_taid, 54, "test18");
        client_5.write(client_5_taid, 54, "test19");
        client_5.write(client_5_taid, 54, "test20");
        client_5.commit(client_5_taid);
    }
}