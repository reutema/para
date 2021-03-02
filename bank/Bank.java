package pp.bank;

public class Bank
{
    private Account[] account;

    public Bank()
    {
        account = new Account[100];
        for (int i = 0; i < account.length; i++)
        {
            account[i] = new Account();
        }
    }

    public void transferMoney(int accountNumber, float amount)
    {
        Thread.yield();
        System.out.println("Account " + accountNumber + ": " + amount);
        Thread.yield();
        account[accountNumber].transferMoney(amount);
        Thread.yield();
    }
}
