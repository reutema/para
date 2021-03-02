package pp.synchstack;

import java.util.Random;

public class Main extends Thread
{

    private SynchStack stack;

    public Main(String name, SynchStack stack)
    {
        super(name);
        this.stack = stack;
    }

    public void run()
    {
        Random ran = new Random();
        for (int i = 0; i < 10; ++i)
        {
            int value = i;// ran.nextInt();
            if (value % 2 == 0)
            {
                System.out.println("Thread " + Thread.currentThread().getName() + " pop: " + stack.pop());
            }
            else
            {
                System.out.println("Thread " + Thread.currentThread().getName() + " push: " + value);
                stack.push(Thread.currentThread().getName() + " " + value);
            }
        }
    }

    public SynchStack getStack()
    {
        return stack;
    }

    public void setStack(SynchStack stack)
    {
        this.stack = stack;
    }

    public static void main(String[] args)
    {
        int threadSize = 5;
        int amountOfDummies = 3;

        SynchStack stack = new SynchStack();
        Main[] threads = new Main[threadSize];
        Random ran = new Random();

        for (int i = 0; i < amountOfDummies; i++)
        {
            stack.push("dummy " + i);
        }

        for (int i = 0; i < threadSize; i++)
        {
            threads[i] = new Main(("m" + i), stack);
        }
        for (Main m : threads)
        {
            m.start();
        }
    }

}
