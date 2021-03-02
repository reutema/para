package pp.bank;

public class Account
{
    private float balance;

    public synchronized void transferMoney(float amount)
    {
        Thread.yield();
        balance = (balance + amount) >= 0 ? balance + amount : balance;
        Thread.yield();
        System.out.println("Money = " + balance);
        Thread.yield();
    }

}
