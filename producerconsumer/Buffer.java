package pp.producerconsumer;

public class Buffer
{

    private int[] data;

    private int head, tail, size, countElements;

    public Buffer(int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("size must be positiv");
        }
        this.size = size;
        this.data = new int[size];
        this.tail = 1;
    }

    public synchronized void put(int x)
    { // _ _ _ tail head _ _ _
        while (countElements == size)
        {
            System.out.println("put, wait head: " + head + ", tail: " + tail);
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
            }
        }
        data[((tail - 1) + size) % size] = x;
        tail = (tail + 1) % size;
        countElements++;
        notifyAll();
    }

    public synchronized int get()
    { // - - - head tail - - -
        while (countElements == 0)
        {
            System.out.println("get, wait head: " + head + ", tail: " + tail);
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
            }
        }
        notifyAll();
        int d = data[head];
        head = (head + 1) % size;
        countElements--;
        return d;
    }

    public static void main(String[] args)
    {
        Buffer buf = new Buffer(1);
        // buf.get();
        buf.put(19);
        buf.get();
    }
}
