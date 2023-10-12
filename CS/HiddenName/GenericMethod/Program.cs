using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GenericMethod
{
    internal class Program
    {
        static void Swap<T>(ref T x, ref T y)
        {
            (y, x) = (x, y);
        }
        static void Main(string[] args)
        {
            int a = 1;
            int b = 2;
            Swap<int>(ref a, ref b);
        }
    }
}
