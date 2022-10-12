using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace SimpleThread
{
    internal class Program
    {
        static void ThreadBody()
        {
            for(int i = 0; i < 5; i++)
            {
                Console.WriteLine(DateTime.Now.Second + " : " + i);
                Thread.Sleep(1000);
            }
        }
        static void Main(string[] args)
        {
            ThreadStart ts = new ThreadStart(ThreadBody);
            Thread t = new Thread(ts);
            Console.WriteLine("*** Start of Main");
            t.Start();
            t.Join();
            Console.WriteLine("*** End of Main");
        }
    }
}
