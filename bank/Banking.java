package pp.bank;

public class Banking
{
    public static void main(String[] args)
    {
        Bank myBank = new Bank();
        new Clerk("Andrea M�ller", myBank);
        new Clerk("Petra Schmitt", myBank);
        new Clerk("Robert Geldlos", myBank);
    }
}
