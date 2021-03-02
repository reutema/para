package pp.producerconsumersem;

public class Semaphore
{
    private int value, max;

    public Semaphore(int init)
    {
        if (init < 0)
        {
            throw new IllegalArgumentException("Parameter < 0");
        }
        value = init;
        max = init;
    }

    public synchronized void p()
    {
        while (value == 0)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
            }
        }

        value--;

        notifyAll();
    }

    public synchronized void v()
    {
        while (value == max)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
            }
        }
        value++;

        notifyAll();
    }
}
