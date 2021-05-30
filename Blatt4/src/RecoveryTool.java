import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecoveryTool {
    static String redo_userdata;
    static String redo_logdata;
    public static void recoverymode(){
        try {
            File redo_udata = new File("C:\\Users\\Leon Weis\\OneDrive\\Documents\\Hamburg\\DatenbankenVorl\\Blatt4\\Blatt4\\userdata.txt");
            Scanner myscnr = new Scanner(redo_udata);
            List<String> redo_write = new ArrayList<String>();
            while (myscnr.hasNextLine()){
                redo_userdata = myscnr.nextLine();
                Integer redo_userlsn = Integer.parseInt(redo_userdata.split(", ")[1]);
                Integer redo_userpageid = Integer.parseInt(redo_userdata.split(", ")[0]);
                
                try {
                    File redo_ldata = new File("C:\\Users\\Leon Weis\\OneDrive\\Documents\\Hamburg\\DatenbankenVorl\\Blatt4\\Blatt4\\logdata.txt");
                    Scanner myScanner = new Scanner(redo_ldata);
                    
                    while (myScanner.hasNextLine()){
                        redo_logdata = myScanner.nextLine();
                        Integer redo_loglsn = Integer.parseInt(redo_logdata.split(", ")[0]);
                        Integer redo_logpageid;
                        if (redo_logdata.split(", ")[2].equalsIgnoreCase("  ")){
                            redo_logpageid = -1;
                        }else{
                            redo_logpageid = Integer.parseInt(redo_logdata.split(", ")[2]);
                        }
                        
                        if ((redo_userlsn < redo_loglsn) && (redo_logpageid == redo_userpageid) ){
                            Integer taid = Integer.parseInt(redo_logdata.split(", ")[1]);
                            Integer paid = redo_logpageid;
                            String data = redo_logdata.split(", ")[3];
                            redo_write.add(taid + ", " + paid + ", " + data);
                            
                        }
                    }
                    myScanner.close();
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
            myscnr.close();
            
            Client recClient = new Client();
            Integer taid;
            Integer paid;
            String data;
            for (String string_redo : redo_write){
                taid = Integer.parseInt(string_redo.split(", ")[0]);
                paid = Integer.parseInt(string_redo.split(", ")[1]);
                data = string_redo.split(", ")[2];
                PersistenceManager.write(taid, paid, data, true);
            }
            
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        
        
    }
}