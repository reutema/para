package pp.ampel;

import java.util.Random;

public class AmpelSteuerung extends Thread
{
    private static int counter = 0;

    private int number;

    private Ampel[] ampeln;

    public AmpelSteuerung(Ampel[] ampeln)
    {
        this.ampeln = ampeln;
        this.number = counter++;
    }

    public void run()
    {
        Random ran = new Random();
        while (true)
        {
            for (Ampel a : ampeln)
            {
                if (ran.nextBoolean())
                {
                    System.out.println("Ampelanlage " + number + "\nSchalte Ampel " + a.getName() + " nun auf rot!");
                    a.schalteRot();
                }
                else
                {
                    System.out.println("Ampelanlage " + number + "\nSchalte Ampel " + a.getName() + " nun auf gruen!");
                    a.schalteGruen();
                }
            }
            try
            {
                sleep(3);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
