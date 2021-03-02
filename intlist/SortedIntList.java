package pp.intlist;

public class SortedIntList
{
    private int[] list;

    public SortedIntList()
    {
        list = new int[200];
        for (int i = 0; i < list.length; i++)
        {
            list[i] = -1;
        }
    }

    public synchronized void insert(int value) throws InterruptedException
    {
        if (value >= 0 && list[list.length - 1] == -1)
        {
            int tmp;
            for (int i = 0; i < list.length; i++)
            {
                Thread.yield();
                if (list[i] == -1)
                {
                    Thread.yield();
                    list[i] = value;
                    break;
                }
                else if (list[i] > value)
                {
                    Thread.yield();
                    tmp = list[i];
                    list[i] = value;
                    value = tmp;
                }
            }
        }
    }

    public void print()
    {
        System.out.print("Liste: ");
        for (int i : list)
        {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}
