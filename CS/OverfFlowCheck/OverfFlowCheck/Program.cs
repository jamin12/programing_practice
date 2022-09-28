int i;
int max = int.MaxValue;
try
{
    checked
    {
        i = max + 1;
    }
        Console.WriteLine(max);
        Console.WriteLine(i);
}
catch (Exception e)
{
    Console.WriteLine(e.ToString());
}