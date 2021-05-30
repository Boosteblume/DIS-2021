public class Client {
  
    static int taid = 0;
    
    public int getTaid(){
        return this.taid;
    }

    public Integer beginTransaction(){
        return ThreadCreator.traid += 1;
    }
    
    public void commit(int taid){
        PersistenceManager.commit(taid);
    }

    public void write(int taid, int pageid, String content){
        PersistenceManager.write(taid, pageid, content, false);
    }

}
