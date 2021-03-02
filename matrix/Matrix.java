package pp.matrix;

import java.util.Random;

import javax.crypto.IllegalBlockSizeException;

public class Matrix extends Thread
{
    private static double[][] vector1;

    private static double[][] vector2;

    private int column, row;

    private double result;

    public Matrix(String name, int row, int column)
    {
        super(name);
        this.column = column;
        this.row = row;
    }

    public Matrix(int row, int column)
    {
        setColumn(column);
        setRow(row);
    }

    public void run()
    {
        for (int n = 0; n < vector1[0].length; n++)
        {
            result += vector1[row][n] * vector2[n][column];
        }
    }

    public int getColumn()
    {
        return column;
    }

    public void setColumn(int column)
    {
        this.column = column;
    }

    public int getRow()
    {
        return row;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public double getResult()
    {
        return result;
    }

    public void setResult(int result)
    {
        this.result = result;
    }

    public static double[][] getVector1()
    {
        return vector1;
    }

    public static void setVector1(double[][] v)
    {
        vector1 = v;
    }

    public static double[][] getVector2()
    {
        return vector2;
    }

    public static void setVector2(double[][] v)
    {
        vector2 = v;
    }

    public static double[][] multiplySequential(double[][] v1, double[][] v2) throws IllegalBlockSizeException
    {
        double[][] result = new double[v1.length][v2[0].length];

        for (int k = 0; k < v2[0].length; k++)
        {
            for (int m = 0; m < v1.length; m++)
            {
                for (int n = 0; n < v1[0].length; n++)
                {
                    result[m][k] += v1[m][n] * v2[n][k];

                }
            }
        }
        return result;
    }

    public static double[][] multiplyParallel(double[][] v1, double[][] v2) throws InterruptedException
    {
        Matrix.setVector1(v1);
        Matrix.setVector2(v2);

        double[][] result = new double[v1.length][v2[0].length];

        Matrix[] threads = new Matrix[v1.length * v2[0].length];

        for (int m = 0, position = 0; m < v1.length; m++)
        {
            for (int k = 0; k < v2[0].length; k++)
            {
                threads[position++] = new Matrix(m, k);
            }
        }

        for (Matrix t : threads)
        {
            t.start();
        }
        for (Matrix t : threads)
        {
            t.join();
            result[t.getRow()][t.getColumn()] = t.getResult();
        }
        return result;
    }

    public static void print(double[][] v)
    {
        for (int i = 0; i < v.length; i++)
        {
            for (int j = 0; j < v[0].length; j++)
            {
                System.out.print(v[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean equals(double[][] vec1, double[][] vec2)
    {
        if (vec1.length == vec2.length && vec1[0].length == vec2[0].length)
        {
            return false;
        }
        for (int i = 0; i < vec1.length; i++)
        {
            for (int j = 0; j < vec1[0].length; j++)
            {
                if (!(vec1[i][j] == vec2[i][j]))
                {
                    return false;
                }
            }

        }
        return true;
    }

    public double[][] makeRandom(int m, int n)
    {
        double[][] vec = new double[m][n];
        Random ran = new Random();

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                vec[i][j] = ran.nextDouble();
            }
        }
        return vec;
    }

    public static void main(String[] args) throws IllegalBlockSizeException, InterruptedException
    {
        // double[][] d1 =
        // {
        // { 12, 3, 14 },
        // { 11, 3, 24 } };
        // double[][] d2 =
        // {
        // { 12, 3 },
        // { 14, 11 },
        // { 3, 24 } };

        double[][] d1 =
        {
            { 1, 2, 3 },
            { 4, 5, 6 } };
        double[][] d2 =
        {
            { 7, 8 },
            { 9, 10 },
            { 11, 12 } };

        /*
         * A = ((1, 2, 3), (4, 5, 6)) B = ((7, 8), (9, 10), (11, 12)) A*B = C =
         * ((58, 64), (139, 154))
         * 
         */
        //
        // double[][] resultSeq = multiplySequential(d1, d2);
        // print(resultSeq);

        double[][] resultPara = multiplyParallel(d1, d2);
        print(resultPara);
        print(multiplyParallel(d1, d2));
    }
}
