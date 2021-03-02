package pp.eventset;

public class EventSet
{
    private boolean[] list;

    public EventSet(int length)
    {
        if (length < 0)
        {
            throw new IllegalArgumentException("length is to short");
        }

        list = new boolean[length];
        for (boolean b : list)
        {
            b = false;
        }
    }

    public synchronized void set(int index, boolean value)
    {
        if (index < 0 || index >= list.length)
        {
            throw new IllegalArgumentException("index not in range of field");
        }
        list[index] = value;
        notifyAll();
    }

    public synchronized void waitAND()
    {
        while (!(countTrue() == list.length))
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {

            }
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " verlaesst waitAND()");
    }

    public synchronized void waitOR()
    {
        while (!(countTrue() > 0))
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {

            }
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " verlaesst waitOR()");
    }

    public int countTrue()
    {
        int counter = 0;
        for (boolean b : list)
        {
            if (b)
            {
                counter++;
            }

        }
        return counter;
    }

}
