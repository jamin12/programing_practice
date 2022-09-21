double? num1 = null;
double? num2 = 10.0;

if (num1.HasValue)
{
    Console.WriteLine("num1 = " + num1.Value);
}else
{
    Console.WriteLine("num1 dos not have value.");
}
if (num2.HasValue)
{
    Console.WriteLine("num2 = " + num2.Value);
}
else
{
    Console.WriteLine("num2 dos not have value.");
}