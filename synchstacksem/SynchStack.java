package pp.synchstacksem;

public class SynchStack
{
    private Object[] list;

    private Semaphore semaphore;

    private int position;

    public SynchStack()
    {
        int size = 10;
        this.position = 0;
        this.semaphore = new Semaphore(0);
        this.list = new Object[size];
    }

    public void push(Object o)
    {
        semaphore.v();
        list[position++] = o;
    }

    public Object pop()
    {
        semaphore.p();
        Object o = list[--position];
        return o;
    }

    public Object[] getList()
    {
        return list;
    }

    public void setList(Object[] list)
    {
        this.list = list;
    }

    public static void main(String[] args)
    {
        SynchStack stack = new SynchStack();

        for (int i = 1; i < 5; i++)
        {
            stack.push(("X" + i));
        }

        for (int i = 0; i < 4; i++)
        {
            System.out.println(stack.pop());
        }

    }
}
