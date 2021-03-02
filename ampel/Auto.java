package pp.ampel;

public class Auto extends Thread
{

    private static int counter = 0;

    private Ampel[] ampeln;

    public Auto(Ampel[] ampeln)
    {
        super("Auto " + (counter++));
        this.ampeln = ampeln;
    }

    public void run()
    {
        while (true)
        {
            for (Ampel a : ampeln)
            {
                a.passieren();
            }
        }
    }

}
