package pp.bank;

public class Clerk extends Thread
{
    private Bank bank;

    public Clerk(String name, Bank bank)
    {
        super(name);
        this.bank = bank;
        start();
    }

    public void run()
    {
        for (int i = 0; i < 10000; i++)
        {

            Thread.yield();
            int accountNumber = (int) (Math.random() * 100);
            // int accountNumber = 0;
            Thread.yield();
            float amount = (int) (Math.random() * 1000) - 499;
            // float amount = -100;
            Thread.yield();
            bank.transferMoney(accountNumber, amount);

        }
    }
}
