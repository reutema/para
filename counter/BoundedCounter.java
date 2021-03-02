package pp.counter;

public class BoundedCounter
{
    private int min, max, counter;

    public BoundedCounter(int min, int max)
    {
        if (max <= min)
        {
            throw new IllegalArgumentException("the max value is equal or shorter than the min value");
        }
        this.min = min;
        this.max = max;
        this.counter = min;
    }

    public synchronized void up()
    {
        while (counter >= max)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {

            }
        }

        counter++;
        notifyAll();
    }

    public synchronized void down()
    {
        while (counter <= min)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {

            }
        }

        counter--;
        notifyAll();
    }

    public synchronized int get()
    {
        return counter;
    }

}
