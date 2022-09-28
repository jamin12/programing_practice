class Color
{
    private string[] color1 = new string[5];
    public string this[int index] { get => color1[index]; set => color1[index] = value; }
}
class StringIndexer
{
    public char this[string str, int index]
    {
        get { return str[index]; }
    }
    public string this[string str, int index, int len]
    {
        get { return str.Substring(index, len); }
    }
}
internal class Indexer
{
    static void Main()
    {
        String str = "asdfqw";
        StringIndexer si = new StringIndexer();
        Console.WriteLine(si[str, 3]);
        Console.WriteLine(si[str, 1,4]);
        Color c = new Color();
        c[0] = "빨";
        c[1] = "주";
        c[2] = "노";
        c[3] = "초";
        c[4] = "파";
        for (int i = 0; i < 5; i++)
        {
            Console.WriteLine(c[i]);
        }
    }
}