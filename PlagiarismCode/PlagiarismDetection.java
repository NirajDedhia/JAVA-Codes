/**
* Importing the java libraries.
*/
import java.io.*;
import java.util.*;

/**
 *
 * The class PlagiarismDetection compares file1 with file2
 * and calculates the percentage value for plagiarism detected.
 *
 * @ author Niraj Dedhia
 */
public class PlagiarismDetection
{
  public static HashMap<String,Integer> synonyms = new HashMap<String,Integer>();
  public static HashSet<String> groups = new HashSet<String>();

  /**
  * main() It is the driver method which initiates the program.
  * Reading the files and tuple size from user.
  *
  * @param  args [command line arguments]
  *
  * @return null
  */
  public static void main(String[] args)
  {
    Scanner s = new Scanner(System.in);

    System.out.println("Enter name of the Synonyms file (eg: synonyms.txt )");
    String synonymsFile = s.next();
    System.out.println("Enter name of the file1 (eg: file1.txt )");
    String file1 = s.next();
    System.out.println("Enter name of the file2 (eg: file2.txt )");
    String file2 = s.next();
    System.out.println("Enter Tuple size eg: 3");
    int tuppleSize = Integer.parseInt(s.next());

    createDictionary(synonymsFile);
    createGroup(convertFileToStringArray(file1), tuppleSize);
    String numberOfTupleMatched = comparison(convertFileToStringArray(file2), tuppleSize);

    String parameters[] = numberOfTupleMatched.split(" ");
    float DetectedPlagiarism = ( Float.parseFloat(parameters[0])/ Float.parseFloat(parameters[1])) * 100 ;
    System.out.println(DetectedPlagiarism+"%");
  }

  /**
  * createDictionary() Reads the file and stores it in hashmap
  *
  * @param  fileName
  *
  * @return null
  */
  public static void createDictionary(String file)
  {
    try
    {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        int key = 0;

        while((line = br.readLine()) != null)
        {
            addToDictionary(line, key);
            key++;
        }
        br.close();
    }
    catch(Exception e)
    {
      System.out.println( "Exception in File "+ file);
    }
  }

  /**
  * addToDictionary() Adds the words to HashMap
  *
  * @param  line , key
  *
  * @return null
  */
  public static void addToDictionary(String line, Integer key)
  {
    String[] words = line.split(" ");
    for(String word : words)
    {
      synonyms.put(word.toUpperCase(), key);
    }
  }

  /**
  * convertFileToStringArray() Convert file to String Array
  *
  * @param  fileName
  *
  * @return null
  */
  public static String[] convertFileToStringArray (String file)
  {
    try
    {
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      String line = null, stringList = "";

      while((line = br.readLine()) != null)
      {
          stringList += line;
          stringList += " ";
      }
      br.close();
      return (stringList.split(" "));
    }
    catch(Exception e)
    {
      System.out.println( "Exception in File "+ file);
    }
    return null;
  }

  /**
  * createGroup() Reads file2 and creates the groups based on tuple Size
  * and stores in to HashMap
  *
  * @param  StringArray, TupleSize
  *
  * @return null
  */
  public static void createGroup(String[] stringArray, int tuppleSize)
  {
    for(int i=0; i<=stringArray.length-tuppleSize; i++)
    {
      String group = "";
      for(int j=0; j<tuppleSize; j++)
      {
        if(synonyms.containsKey(stringArray[i+j].toUpperCase()))
          group += synonyms.get(stringArray[i+j].toUpperCase());
        else
          group += stringArray[i+j].toUpperCase();
      }
      groups.add(group);
    }
  }

  /**
  * comparison() Reads file1 and compares the number of tuple matches in the file2
  *
  * @param  StringArray, TupleSize
  *
  * @return String (tupple matched, total tuples)
  */
  public static String comparison(String[] words, int tuppleSize)
  {
    int counter = 0, totalCounter = 0;
    for(int i=0; i<=words.length-tuppleSize; i++)
    {
      String group = "";
      for(int j=0; j<tuppleSize; j++)
      {
        if(synonyms.containsKey(words[i+j].toUpperCase()))
          group += synonyms.get(words[i+j].toUpperCase());
        else
          group += words[i+j].toUpperCase();
      }
      if(groups.contains(group))
        counter++;
      totalCounter++;
    }
    return counter+" "+totalCounter;
  }

}