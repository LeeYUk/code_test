using System;

public class Example
{
    public static void Main()
    {
           String s;

   Console.Clear();
   s = Console.ReadLine();
   char[] charArray = s.ToCharArray();

   for (int i = 0; i < charArray.Length; i++) 
   {
       if (char.IsUpper(charArray[i]))
       {
           charArray[i] = char.ToLower(charArray[i]);
       }
       else if (char.IsLower(charArray[i]))
       {
           charArray[i] = char.ToUpper(charArray[i]);
       }
   }

   string str = new string(charArray);
   Console.WriteLine(str);

    }
}