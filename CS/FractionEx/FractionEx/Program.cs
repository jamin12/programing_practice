namespace myFraction
{
    class Fraction
    {
        int num1;
        int num2;

        public Fraction(int num1, int num2)
        {
            this.num1 = num1;
            this.num2 = num2;
        }

        public void PrintFraction()
        {
            Console.WriteLine(num1 + "/" + num2);
        }
    }

    class FractionEx
    {
        static void Main(String[] args)
        {
            Fraction sdf = new Fraction(1, 2);
            sdf.PrintFraction();
        }
    }
}