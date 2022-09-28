class Programs
{
    static void Swap(ref int x, ref int y)
    {
        int tmp;
        tmp = x; x = y; y = tmp;
        Console.WriteLine("Swap {0} {1}", x, y);
    }
    static void Main(string[] args)
    {
        int x = 1, y = 2;
        Swap(ref x, ref y);
    }
}