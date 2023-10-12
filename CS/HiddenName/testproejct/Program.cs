using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace testproejct
{
    class ttes
    {
        public int i = 0;
    }
    struct teststruct
    {
        public int a;
        public teststruct(int a)
        {
            this.a = a;
        }
    }
    internal class Program
    {
        static void ao(object obj)
        {
            Console.WriteLine(obj + " " + (obj as string));
            var ii = obj as string;
            Console.WriteLine(ii.GetType());
        }
        static void aao(ttes s)
        {
            s.i = 5;
        }

        static void aa11(teststruct teststruct)
        {
            teststruct.a = 11;
        }

        
        static void Main(string[] args)
        {
            ttes ao = new ttes();
            aao(ao);
            Console.WriteLine(ao.i);
            teststruct aaa = new teststruct(1);
            aa11(aaa);
            Console.WriteLine(aaa.a);
        }
    }
}
