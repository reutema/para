package pp.ampel;

public class Main
{

    public static void main(String[] args) throws InterruptedException
    {
        int ampelSize = 4;
        int autoSize = ampelSize * 2;
        Ampel[] ampeln = new Ampel[ampelSize];
        Auto[] autos = new Auto[autoSize];

        for (int i = 0; i < ampelSize; i++)
        {
            ampeln[i] = new Ampel();
        }

        AmpelSteuerung as = new AmpelSteuerung(ampeln);
        as.start();

        for (int i = 0; i < autoSize; i++)
        {
            autos[i] = new Auto(ampeln);
            autos[i].start();
        }

        Thread.sleep(20 * 1000);
        for (Auto a : autos)
        {
            a.interrupt();
            a.join();
        }
        as.interrupt();
        as.join();

    }
}
