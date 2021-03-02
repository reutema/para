package pp.intlist;

public class ListWriter extends Thread
{

    private SortedIntList list;

    private int min;

    private int max;

    public ListWriter(SortedIntList list, int min, int max) throws InterruptedException
    {
        this.list = list;
        this.min = min;
        this.max = max;

    }

    public void run()
    {

        if (min < max)
        {
            for (int i = min; i <= max; i++)
            {

                try
                {
                    list.insert(i);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        else
        {
            for (int i = min; i >= max; i--)
            {

                try
                {
                    list.insert(i);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        SortedIntList list = new SortedIntList();

        ListWriter l1 = new ListWriter(list, 100, 51);
        ListWriter l2 = new ListWriter(list, 51, -1);

        l1.start();
        l2.start();

        l1.join();
        l2.join();

        list.print();
    }
}
