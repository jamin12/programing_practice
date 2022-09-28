public class Color
{
    public const int FULL = 0xff;
    public const int EMPTY = 0x00;

    private byte red, green, blue;
    public static readonly Color Red = new Color(FULL, EMPTY, EMPTY);
    public static readonly Color Green = new Color(EMPTY, FULL, EMPTY);
    public static readonly Color Blue = new Color(EMPTY, EMPTY, FULL);

    public Color(byte red, byte green, byte blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public static void PrintColor(Color c)
    {
        Console.WriteLine("{0}, {1}, {2}", c.red, c.green, c.blue);
    }
}

public class Programs
{
    static void Main(String[] args)
    {
        Color.PrintColor(Color.Red);
    }
}