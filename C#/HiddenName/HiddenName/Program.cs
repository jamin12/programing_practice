using System.Security.Cryptography.X509Certificates;

namespace HiddenName
{
    abstract class AAA
    {
        protected int a = 1;
        protected int b = 2;

        public abstract void testA();

        virtual public void MethodA()
        {
            Console.WriteLine("AAA-MethodA");
        }
        virtual public void MethodB()
        {
            Console.WriteLine("BBB-MethodA");
        }
        virtual public void MethodC()
        {
            Console.WriteLine("CCC-MethodA");
        }
    }

    class BBB : AAA
    {
        int c = 3;
        new double b = 4.5;
        public void OutPut()
        {
            Console.WriteLine("{0}\n{1}", a, b);
        }
        override public void MethodA()
        {
            Console.WriteLine("BBB-AAA-MethodA");
        }
        new public void MethodB()
        {
            Console.WriteLine("BBB-BBB-MethodB");
        }
        override public void MethodC()
        {
            Console.WriteLine("BBB-CCC-MethodC");
        }

        public override void testA()
        {
            Console.WriteLine("TEST");
        }
    }
    
    class CCC : BBB
    {
        new public void MethodA()
        {
            Console.WriteLine("CCC-AAA-MethodA");
        }
        new public void MethodB()
        {
            Console.WriteLine("CCC-BBB-MethodB");
        }
        override public void MethodC()
        {
            Console.WriteLine("CCC-CCC-MethodC");
        }
        public new void testA()
        {
            Console.WriteLine("CCC-testC");
        }
    }


    internal class Program
    {
        static void Main(string[] args)
        {
            AAA bbb = new BBB();
            bbb.MethodA();
            bbb.MethodB();
            bbb.MethodC();
            bbb.testA();
        }
    }
}