package pp.intlist;

public class ListReader extends Thread
{
    private SortedIntList list;

    private boolean isRunning;

    public ListReader(SortedIntList list)
    {
        this.list = list;
        setIsRunning(true);
    }

    public void run()
    {
        while (isRunning)
        {
            list.print();
        }
    }

    public void setIsRunning(boolean bool)
    {
        this.isRunning = bool;
    }

    public static void main(String[] args) throws InterruptedException
    {
        SortedIntList list = new SortedIntList();
        ListWriter lw = new ListWriter(list, 200, 1);
        ListReader lr = new ListReader(list);

        lw.start();
        lr.start();

        // Thread.sleep(50);
        lw.join();
        lr.setIsRunning(false);
        lr.join();

    }
}
