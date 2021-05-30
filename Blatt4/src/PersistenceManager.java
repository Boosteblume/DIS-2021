import java.io.*;
import java.util.*;
import java.nio.*;
//import java.io.FileWriter


public class PersistenceManager {

    static CharBuffer buff = CharBuffer.allocate(100);
    static String filename = "";
    static String stringspacer = ", ";


//erstellen der Hashtable
    static Hashtable<Integer, String[]> ht = new Hashtable<>();
    static int hashint = 0;


    public static void write(int taid, int pageid, String content, Boolean reco_mode ){
        
        //prüfen ob für einen Write Auftrag im Buffer ein Commit mit selbiger Taid in Logdata vorliegt.
        //Wenn dies der Fall ist, dann wollen wir die WriteAufträge persistent in der Userdata speichern.        
        Set<Integer> Keys = ht.keySet();
        List<Integer> key_to_delete = new ArrayList<Integer>();

        if((ht.size() > 1000) || (reco_mode == true)){
            try {
                File log =  new File("C:\\Users\\Leon Weis\\OneDrive\\Documents\\Hamburg\\DatenbankenVorl\\Blatt4\\Blatt4\\logdata.txt");
                   
                Scanner myScanner = new Scanner(log);
                while(myScanner.hasNextLine()){
                    String line = myScanner.nextLine();
                    String [] text_eot = line.split(", ");
                    System.out.println(text_eot[3]);
                    
                    if(text_eot[3].equalsIgnoreCase("EOT")){
                        String text_taid = text_eot[1];

                        for (Integer key:Keys){
                            String hash = ht.get(key)[0];
                            String[] textsplit = hash.split(", ");
                            String hash_taid = textsplit[1];
                            
                            
                            if(hash_taid.equalsIgnoreCase(text_taid)){
                                safe_persistent(hash);
                                key_to_delete.add(key);
                            }
                        key += 1;
                        }

                    }
                    
                }
                myScanner.close();
                for (Integer del_key : key_to_delete){
                    ht.remove(del_key);
                }
                key_to_delete.clear();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }

        Boolean replace_or_not = false;
        String[] hashcontent = {Integer.toString(ThreadCreator.lsn) + stringspacer + Integer.toString(taid) + stringspacer + Integer.toString(pageid) + stringspacer + content};
    
        //Prüfen ob schon Write Auftrag mit Page Id existiert, wenn ja dann Replace
        for (Integer i:Keys){
            
            String text = ht.get(i)[0];
            
            String[] textsplit = text.split(", ");
            String text_pageid = textsplit[2];
            
            if(pageid == Integer.parseInt(text_pageid)){
                ht.replace(i, hashcontent);
                replace_or_not = true;
            }
            i += 1;
        }
        
        //Hash befüllung falls kein Replace stattgefunden hat
        hashint += 1;
        if (replace_or_not == false){
            ht.put(hashint, hashcontent);
        }
        
        //Logdata 
        ThreadCreator.lsn++;
        try{
            File log =  new File("C:\\Users\\Leon Weis\\OneDrive\\Documents\\Hamburg\\DatenbankenVorl\\Blatt4\\Blatt4\\logdata.txt");

            FileWriter myWriter = new FileWriter(log, true);
            myWriter.append(Integer.toString(ThreadCreator.lsn) + stringspacer + Integer.toString(taid) + stringspacer + Integer.toString(pageid) + stringspacer + content + "\n");
            myWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    



    //Methode commit: Hier speichern wir außschließlich den Commit-Auftrag in logdata

    public static void commit(int taid) {
        ThreadCreator.lsn++;
        try{
            File log =  new File("C:\\Users\\Leon Weis\\OneDrive\\Documents\\Hamburg\\DatenbankenVorl\\Blatt4\\Blatt4\\logdata.txt");

            FileWriter myWriter = new FileWriter(log, true);
            myWriter.append(Integer.toString(ThreadCreator.lsn) + stringspacer + Integer.toString(taid) + stringspacer + "  " + stringspacer + "EOT" + "\n");
            myWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }




    //Methode safe_persistent ist eine Hilfsmethode für die Methode write
        // hasheinträge mit taid für die commit existiert persistent speichern
        // hasheinträge, welche gespeichert werden, aus hashtabelle löschen
        // hashkeys anpassen

    public static void safe_persistent(String hash){

        System.out.println("13");
        
        Integer lsn = Integer.parseInt(hash.split(", ")[0]);
        Integer taid = Integer.parseInt(hash.split(", ")[1]);
        Integer pageid = Integer.parseInt(hash.split(", ")[2]);
        String data = hash.split(", ")[3];
        String udata_filepath ="C:\\Users\\Leon Weis\\OneDrive\\Documents\\Hamburg\\DatenbankenVorl\\Blatt4\\Blatt4\\userdata.txt";
        File udata =  new File("C:\\Users\\Leon Weis\\OneDrive\\Documents\\Hamburg\\DatenbankenVorl\\Blatt4\\Blatt4\\userdata.txt");
        String old_line_data = "";
        //scanner zum Finden des alten Inhalts einer page, damit dieser ersetzt werden kann

        try {
            Scanner myscnr = new Scanner(udata);
            Integer counter = 10;
            while (counter < pageid + 1){
                old_line_data = myscnr.nextLine();
                counter += 1;
            }
            
            myscnr.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
        
        String new_line_data = Integer.toString(pageid) + ", " + (lsn + 1)+ ", " + data;
        modifyFile(udata_filepath, old_line_data, new_line_data);
        //Aufruf der Methode zur Änderung von userdate.txt
        
    
       
    }

    


    //Methode modifyFile ist eine Hilfsmethode der Methode safe_persistent
    //Hier wird ein alter String durch einen neuen String ersetzt 

    public static void modifyFile(String filePath, String oldString, String newString){
        File fileToBeModified = new File(filePath);
         
        String oldContent = "";
         
        BufferedReader reader = null;
         
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
             
            //Reading all the lines of input text file into oldContent
             
            String line = reader.readLine();
             
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                 
                line = reader.readLine();
            }
             
            //Replacing oldString with newString in the oldContent
             
            String newContent = oldContent.replaceAll(oldString, newString);
             
            //Rewriting the input text file with newContent
             
            writer = new FileWriter(fileToBeModified);
             
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                 
                reader.close();
                 
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
}
