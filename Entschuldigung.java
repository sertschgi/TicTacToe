public class Entschuldigung 
{
    public static void main(String args[])
    {
        for (int i = 0;i<10; ++i)
        {
            System.out.println(String.format("%s", i));
            
        }

        int counter = 0;
        while (counter < 10)
        {
            System.out.println(String.format("%s", ++counter));
        }
    }
}
