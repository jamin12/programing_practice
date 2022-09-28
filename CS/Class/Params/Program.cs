class Programs
{
    static void Main(String[] args)
    {
        ParamsTest(123, "Hello", true, 'a');
    }

    private static void ParamsTest(params object[] obj)
    {
        foreach (object obj2 in obj)
        {
            Console.WriteLine(obj2.ToString());
        }
    }
}