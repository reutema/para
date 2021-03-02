package pp.ampel;

public class Ampel
{
    private static int counter = 0;

    private int number;

    private boolean ampel;

    public Ampel()
    {
        this.number = counter++;
    }

    public String getName()
    {
        return number + "";
    }

    public synchronized void schalteRot()
    {
        ampel = true;
    }

    public synchronized void schalteGruen()
    {
        ampel = false;
        notifyAll(); // notifyAll() damit alle Autos passieren können.
    }

    public synchronized void passieren()
    {

        while (ampel)
        {
            System.out.println(Thread.currentThread().getName() + " wartet an der Ampel " + number);
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {

            }
        }

        System.out.println(Thread.currentThread().getName() + " hat die Ampel " + number + " passiert");
        return;
    }
}
