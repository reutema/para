package pp.producerconsumersem;

public class Buffer
{

    private int[] data;

    private Semaphore semaphore;

    private int head, tail, size;

    public Buffer(int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("size must be positiv");
        }
        this.size = size;
        this.data = new int[size];
        this.semaphore = new Semaphore(size);
    }

    public void put(int x)
    {

        semaphore.p();

        data[tail] = x;
        tail = (tail + 1) % size;
    }

    public int get()
    {

        semaphore.v();

        int d = data[head];
        head = (head + 1) % size;
        return d;
    }

    public static void main(String[] args)
    {
        Buffer buf = new Buffer(1);
        new Thread(() -> System.out.println(buf.get())).start();
        ;
        new Thread(() -> buf.put(42)).start();
        ;
        new Thread(() -> System.out.println(buf.get())).start();
        ;

    }
}
