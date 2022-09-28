namespace Delegate
{
    delegate void dele();
    delegate void d2(int i);
    class AAA
    {
        public void M1()
        {
            Console.WriteLine("AAA.M1");
        }
        public void M3()
        {
            Console.WriteLine("AAA.M3");
        }
        public void M2(int a)
        {
            Console.WriteLine("AAA.M2" + a);
        }
    }
    internal class Program
    {
        static void Main(string[] args)
        {
            AAA a = new AAA();
            dele d1 = new dele(a.M1);
            d1 += new dele(a.M3);
            d2 d33 = new d2(a.M2);
            d1();
        }
    }
}