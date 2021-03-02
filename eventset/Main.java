package pp.eventset;

import java.util.Random;

public class Main extends Thread
{

    private EventSet eSet;

    private int modi;

    public Main(EventSet eSet, int modi)
    {
        this.eSet = eSet;
        this.modi = modi;
    }

    public void run()
    {
        Random ran = new Random();
        boolean bool;
        if (modi == 0)
        {
            for (int index = 0; index < 50; index++)
            {
                bool = ran.nextBoolean();
                System.out.println(Thread.currentThread().getName() + ": set(" + index + ", " + bool + ")");
                eSet.set(index, bool);
            }
        }
        else if (modi == 1)
        {
            System.out.println(Thread.currentThread().getName() + ": waitAND()");
            eSet.waitAND();
        }
        else if (modi == 2)
        {
            System.out.println(Thread.currentThread().getName() + ": waitOR()");
            eSet.waitOR();
        }
        else
        {
            System.out.println(Thread.currentThread().getName() + ": set all true");
            for (int index = 0; index < 50; index++)
            {
                eSet.set(index, true);
            }
        }

    }

    public static void main(String[] args) throws InterruptedException
    {
        EventSet eSet = new EventSet(50);
        Main[] threads = new Main[5];

        for (int i = 0; i < threads.length; i++)
        {
            threads[i] = new Main(eSet, i % 3);
            threads[i].start();
        }
        threads[0].join();
        Main thread4 = new Main(eSet, 3);
        thread4.start();
    }

}
