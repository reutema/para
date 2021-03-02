package pp.filelocking;

import java.util.ArrayList;

public class File
{
    /*
     * o Alle Klassen müssen sich im Package pp.filelocking befinden.
     */
    /*
     * o Falls dem Konstruktor der Klasse File eine negative Länge übergeben
     * wird, muss eine Ausnahme vom Typ IllegalArgumentException ausgelöst
     * werden.
     */
    /*
     * o Falls der Methode unlock ein Intervall zum Freigeben übergeben wird,
     * welches nicht gesperrt ist, muss eine Ausnahme vom Typ
     * IllegalArgumentException ausgelöst werden. Der Einfachheit halber genügt
     * es, wenn nur solche Bereiche freigegeben werden können, die auch durch
     * einen Aufruf von lock als eine Einheit auf einmal gesperrt wurden. Das
     * heißt, Ihre Lösung muss nicht unterstützen, dass man beispielsweise das
     * Intervall [100, 150] mit lock sperren kann und man dann nur einen Teil
     * davon wie etwa das Intervall [120, 130] oder [100, 110] mit unlock
     * freigeben kann. Es muss auch nicht unterstützt werden, dass man zuerst
     * das Intervall [100, 150] und anschließend das direkt angrenzende
     * Intervall [151, 180] sperrt und man beide dann auf einmal mit unlock(100,
     * 180) freigibt.
     */
    /*
     * o Das Sperren muss auch nicht unterscheiden, ob ein gesperrter Bereich
     * vom aufrufenden Thread oder einem anderen Thread gesperrt ist. Das heißt,
     * wenn ein Thread das Intervall [100, 150] gesperrt hat und derselbe Thread
     * will dasselbe Intervall oder ein überlappendes Intervall wie etwa [130,
     * 170] sperren, so soll hierbei die Methode lock blockieren und nicht den
     * Bereich ein zweites Mal sperren, weil es sich ja um denselben Thread
     * handelt.
     */
    /*
     * o Ferner muss auch nicht geprüft werden, ob derjenige Thread ein
     * gesperrtes Intervall freigibt, der es auch gesperrt hat. Es soll also
     * durchaus möglich sein, dass Thread A das Intervall [100, 150] sperrt und
     * Thread B dieses Intervall freigibt.
     * 
     */
    private int length;

    private ArrayList<Interval> list;

    public File(int length)
    {
        if (length < 0)
        {
            throw new IllegalArgumentException("length to short");
        }
        this.length = length;
        this.list = new ArrayList<Interval>();
    }

    public synchronized void lock(int begin, int end)
    {
        checkInput(begin, end);

        Interval interval = new Interval(begin, end);

        while (isLocked(interval))
        {
            try
            {
                wait();
            }
            catch (Exception e)
            {
            }
        }
        lockInterval(interval);

    }

    public synchronized void unlock(int begin, int end)
    {
        checkInput(begin, end);
        Interval interval = new Interval(begin, end);
        if (!isLocked(interval))
        {
            throw new IllegalArgumentException("Interval is locked");
        }
        unlockInterval(interval);
        notifyAll();
    }

    public void checkInput(int begin, int end)
    {
        if (begin < 0 || end < 0 || end < begin || (length - 1) < end)
        {
            System.out.println("begin: " + begin + " end: " + end + " length: " + list.size());
            throw new IllegalArgumentException("value of interval to short");
        }
    }

    public synchronized boolean isLocked(Interval interval)
    {

        for (Interval iv : list)
        {
            // ueberprueft ob das Intervall (begin, end) innerhalb des
            // Bereiches eines gespeicherten Intervalls liegt
            // iv[begin] interval[begin] iv[end] interval[end]
            // interval[begin] iv[begin] interval[end] iv[end]
            if (!((iv.getBegin() < interval.getBegin() && iv.getEnd() > interval.getBegin()) || (iv.getBegin() < interval.getEnd() && iv.getEnd() > interval.getEnd())))
            {
                return true;
            }
        }

        return false;
    }

    public synchronized void lockInterval(Interval interval)
    {
        list.add(interval);
    }

    public synchronized void unlockInterval(Interval interval)
    {
        System.out.println("Element removed: " + removeInterval(interval));
    }

    public boolean removeInterval(Interval interval)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).equals(interval))
            {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args)
    {
        File file = new File(78);

        Thread[] threads = new Thread[]
        { new Thread(() -> file.lock(0, 15)), new Thread(() -> file.lock(23, 60)), new Thread(() -> file.unlock(0, 15)) };

        for (Thread t : threads)
        {
            t.start();
        }
        for (Thread t : threads)
        {
            try
            {
                t.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

class Interval
{
    private int begin, end;

    public Interval(int begin, int end)
    {
        this.begin = begin;
        this.end = end;
    }

    public int getBegin()
    {
        return begin;
    }

    public int getEnd()
    {
        return end;
    }

    public void setBegin(int begin)
    {
        this.begin = begin;
    }

    public void setEnd(int end)
    {
        this.end = end;
    }

    public boolean equals(Interval interval)
    {
        return (interval.getBegin() == begin && interval.getEnd() == end);
    }
}