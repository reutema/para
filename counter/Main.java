package pp.counter;

import java.util.Random;

public class Main extends Thread
{

    private BoundedCounter counter;

    public Main(String name, BoundedCounter counter)
    {
        super(name);
        this.counter = counter;
    }

    public void run()
    {
        Random ran = new Random();
        for (int i = 0; i < 10; ++i)
        {
            int value = i;// ran.nextInt();
            if (value % 2 == 0)
            {
                System.out.println("Thread " + Thread.currentThread().getName() + " up");
                counter.up();
            }
            else
            {
                System.out.println("Thread " + Thread.currentThread().getName() + " down");
                counter.down();
            }
        }
    }

    public BoundedCounter getStack()
    {
        return counter;
    }

    public void setStack(BoundedCounter stack)
    {
        this.counter = stack;
    }

    public static void main(String[] args) throws InterruptedException
    {
        int threadSize = 5;
        int min = 4;
        int max = 10;

        BoundedCounter counter = new BoundedCounter(min, max);
        Main[] threads = new Main[threadSize];

        System.out.println("Counter starts at " + counter.get());

        for (int i = 0; i < threadSize; i++)
        {
            threads[i] = new Main(("m" + i), counter);
        }
        for (Main m : threads)
        {
            m.start();
        }
        for (Main m : threads)
        {
            m.join();
        }

        System.out.println("Counter ends with " + counter.get());
    }

}
