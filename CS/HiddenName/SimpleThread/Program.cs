using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace SimpleThread
{
    class a
    {
        public a()
        {
            int ii = 0;
        }
    }
    class b : a
    {
        public b()
        {
            int jj = 0;
        }
    }
    internal class Program
    {
        static void ThreadBody<T>(T a, T b)
        {
            Console.WriteLine("tt");
        }
        static void Main(string[] args)
        {
            a bb= new a();
            ThreadBody<int>(1,2);
        }
    }
}
