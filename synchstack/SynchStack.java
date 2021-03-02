package pp.synchstack;

import java.util.ArrayList;
import java.util.List;

public class SynchStack
{
    private List<Object> list;

    public SynchStack()
    {
        this.list = new ArrayList<>();
    }

    public synchronized void push(Object o)
    {
        list.add(o);
        notify();
    }

    public synchronized Object pop()
    {
        while (list.size() == 0)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {

            }
        }
        Object o = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return o;
    }

    public List<Object> getList()
    {
        return list;
    }

    public void setList(List<Object> list)
    {
        this.list = list;
    }
}
