package pp.synctime;

public class SyncTime implements Runnable
{

    @Override
    public void run()
    {
        long startTime = System.currentTimeMillis();
        System.out.println("run > Start time: " + startTime);
        for (int i = 0; i < 1000; ++i)
        {
            notSync();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("run > End time: " + endTime);
        System.out.println("run > Time: " + (endTime - startTime));

        long startSyncTime = System.currentTimeMillis();
        System.out.println("run > Start syncTime: " + startSyncTime);
        for (int i = 0; i < 1000; ++i)
        {
            sync();
        }
        long endSyncTime = System.currentTimeMillis();
        System.out.println("run > End syncTime: " + endSyncTime);
        System.out.println("run > syncTime: " + (endSyncTime - startSyncTime));

        System.out.println("\nTime not sync = " + (endTime - startTime) + "\nTime sync = " + (endSyncTime - startSyncTime));
    }

    private void notSync()
    {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; ++i)
        {
            // System.out.println(">> " + System.currentTimeMillis());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("notSync > Time: " + (endTime - startTime));

    }

    private synchronized void sync()
    {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; ++i)
        {
            // System.out.println(">> " + System.currentTimeMillis());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("sync > Time: " + (endTime - startTime));

    }

    public static void main(String[] args)
    {
        SyncTime syncTime = new SyncTime();
        new Thread(syncTime).start();
        new Thread(syncTime).start();
        new Thread(syncTime).start();
        new Thread(syncTime).start();
        new Thread(syncTime).start();
        new Thread(syncTime).start();
    }

}
