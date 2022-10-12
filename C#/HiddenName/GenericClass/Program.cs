using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GenericClass
{
    class Simple<T>
    {
        private T[] values;
        private int index;

        public Simple(int len)
        {
            values = new T[len];
            index = 0;
        }

        public void add(params T[] args)
        {
            foreach (T item in args)
            {
                values[index++] = item;
            }
        }

        public void print()
        {
            foreach (T item in values)
            {
                Console.WriteLine(item);
            }
        }



    }

    internal class Program
    {
        static void Main(string[] args)
        {
            Simple<String> s = new Simple<string>(3);
            Simple<int> s2 = new Simple<int>(3);
            string[] sArray = { "sdf", "asdf" };
            s.add(sArray);

            s.print();
        }
    }
}
