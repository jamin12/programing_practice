using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace test
{
    class myClass<T>
    {
        private T[] values;
        private int index;
        public myClass(int len)
        {
            values = new T[len];
            index = 0;
        }

        public int Index
        {
            get { return index; }
            set { index = value; }
        }

        public void add(params T[] args)
        {
            foreach (T arg in args)
            {

            }
        }
    }
    internal class Program
    {
        static void Main(string[] args)
        {
        }
    }
}
