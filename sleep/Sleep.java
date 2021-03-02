package pp.sleep;

import java.util.concurrent.ThreadLocalRandom;

public class Sleep implements Runnable
{
    @Override
    public void run()
    {
        try
        {
            schlafen(ThreadLocalRandom.current().nextInt(2, 20 + 1));
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    };

    public synchronized void schlafen(int time) throws InterruptedException
    {
        long t = System.currentTimeMillis();
        System.out.println(time + ": Re Relaxo ... zZzZ ... Relaxo ...  zZzZz zZzZ");
        Thread.sleep(time * 1000);
        System.out.println(((System.currentTimeMillis() - t) / 1000) + "/" + time + ": ... laxo ... Relaxo ... zZzZ zZz ZzZz zZzZzZ");
    }

    public static void main(String[] args)
    {
        Sleep relaxo = new Sleep();

        new Thread(relaxo).start();
        new Thread(relaxo).start();
        new Thread(relaxo).start();
        new Thread(relaxo).start();
        new Thread(relaxo).start();
        new Thread(relaxo).start();

        // new Thread(new Sleep()).start();
        // new Thread(new Sleep()).start();
        // new Thread(new Sleep()).start();

    }

}
