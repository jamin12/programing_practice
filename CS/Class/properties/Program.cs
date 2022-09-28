class Fraction
{
    private int numerator;
    private int denominator;

    public int Numerator { get => numerator; set => numerator = value; }
    public int Denominator { get => denominator; set => denominator = value; }
}
internal class Programs
{
    static void Main(string[] args)
    {
        Fraction f = new Fraction();
        f.Numerator = 1;
        f.Denominator = 2;
        Console.WriteLine(f.Numerator + f.Denominator);
    }
}