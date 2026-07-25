package Excise_synchornized;

public class Ticket implements Runnable{
    static int ticket = 100;
    private boolean flag;//私有，通过有参构造传参

    public Ticket(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run(){
        while (flag){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sellTicket();
        }
    }
    public void sellTicket(){
        synchronized (Ticket.class){
            if (ticket>0){
                System.out.println(Thread.currentThread().getName()+"卖了第"+ticket+"张票");
                ticket--;
                }
            else {
                flag = false;
                System.out.println("票已经卖完");
            }
        }
    }

}
