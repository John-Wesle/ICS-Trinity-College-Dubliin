/* SELF ASSESSMENT

1. readDictionary
- I have the correct method definition [Mark out of 5:5]
- Comment: yes, I have the correct method definition
- My method reads the words from the "words.txt" file. [Mark out of 5:5]
- Comment:Yes,My method reads the words from the "words.txt" file.
- It returns the contents from "words.txt" in a String array or an ArrayList. [Mark out of 5:5]
- Comment:Yes,It returns the contents from "words.txt" in an ArrayList.

2. readWordList
- I have the correct method definition [Mark out of 5:5]
- Comment:Yes,I have the correct method definition.
- My method reads the words provided (which are separated by commas, saves them to an array or ArrayList of String references and returns it. [Mark out of 5:5]
- Comment:Yes, My method reads the words provided (which are separated by commas, saves them to an ArrayList of String references and returns it.

3. isUniqueList
- I have the correct method definition [Mark out of 5:5]
- Comment:Yes,I have the correct method definition.
- My method compares each word in the array with the rest of the words in the list. [Mark out of 5:5]
- Comment:Yes, My method compares each word in the array with the rest of the words in the list.
- Exits the loop when a non-unique word is found. [Mark out of 5:5]
- Comment:Exits the loop when a non-unique word is found
- Returns true is all the words are unique and false otherwise. [Mark out of 5:5]
- Comment:Returns true is all the words are unique and false otherwise.

4. isEnglishWord
- I have the correct method definition [Mark out of 5:5]
- Comment: Yes, I have the correct method definition.
- My method uses the binarySearch method in Arrays library class. [Mark out of 3:3]
- Comment: Yes, My method uses the binarySearch method in Arrays library class.
- Returns true if the binarySearch method return a value >= 0, otherwise false is returned. [Mark out of 2:2]
- Comment:Returns true if the binarySearch method return a value >= 0, otherwise false is returned.

5. isDifferentByOne
- I have the correct method definition [Mark out of 5:5]
- Comment: Yes,I have the correct method definition.
- My method loops through the length of a words comparing characters at the same position in both words searching for one difference. [Mark out of 10:10]
- Comment: Yes,My method loops through the length of a words comparing characters at the same position in both words searching for one difference.

6. isWordChain
- I have the correct method definition [Mark out of 5:5]
- Comment:Yes,I have the correct method definition.
- My method calls isUniqueList, isEnglishWord and isDifferentByOne methods and prints the appropriate message [Mark out of 10:10]
- Comment:Yes,My method calls isUniqueList, isEnglishWord and isDifferentByOne methods and prints the appropriate message.

7. main
- Reads all the words from file words.txt into an array or an ArrayList using the any of teh Java.IO classes covered in lectures [Mark out of 10:10]
- Comment:Reads all the words from file words.txt into an array or an ArrayList using the any of teh Java.IO classes covered in lectures.
- Asks the user for input and calls isWordChain [Mark out of 5:5]
- Comment:Yes, it asks the user for input and calls isWordChain.

 Total Mark out of 100 (Add all the previous marks):100
*/


import java.io.*;
import java.util.*;

public class LewisCarrollsGame {

    public static ArrayList<String> DICTIONARY = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        // sample input smile,stile,stale,stare,stars,sears,tears
        File word = new File("C:\\Users\\th3j9\\eclipse-workspace\\2.Week 8. Lewis Carroll's Game\\bin\\words.txt");
        ArrayList<String> dictionary = readDictionary(word);
        DICTIONARY = dictionary;
        Scanner i = new Scanner(System.in);
        boolean finished = false;
        do {
            ArrayList<String> userInput = readWordList();
            if (userInput.get(0) == "")
                finished = true;
            else {
                boolean validChain = isWordChain(userInput);
                if (validChain == true)
                    System.out.println("Valid chain of words from Lewis Carroll's word-links game.");
                else
                    System.out.println("Not a valid chain of words from Lewis Carroll's word-links game.");
            }
        } while (!finished);

    }

    public static ArrayList<String> readDictionary(File words) throws IOException {
        ArrayList<String> Dictionary = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(words));
        String word;
        while ((word = br.readLine()) != null)
            Dictionary.add(word);
        return Dictionary;
    }

    public static ArrayList<String> readWordList() {
        System.out.println("Enter a comma separated list of words (or an empty list to quit):");
        Scanner input = new Scanner(System.in);
        String userInputLine = input.nextLine();
        ArrayList<String> userInputList = new ArrayList<String>();
        ArrayList<String> wordList = new ArrayList<String>();
        String word = "", lastWord = "";
        int finalIndex = 1;
        for (int i = 0; i < userInputLine.length(); i++) {
            if (userInputLine.charAt(i) == ',') {
                if (userInputLine.charAt(i + 1) == ' ')
                    i += 1;
                wordList.add(word);
                word = "";
                finalIndex = i + 1;
            } else {
                word += userInputLine.charAt(i);
            }
        }
        for (; finalIndex < userInputLine.length(); finalIndex++) {
            lastWord += userInputLine.charAt(finalIndex);
        }
        wordList.add(lastWord);
        return wordList;
    }

    public static boolean isUniqueList(ArrayList<String> userInput) {
        // compares all the elements and returns if there are same elements
        boolean uniqueElements = true;
        for (int index = 1; index < userInput.size(); index++) {
            if ((userInput.get(index)).equals(userInput.get(index - 1)))
                return false;
        }
        return uniqueElements;
    }

    public static boolean isEnglishWord(String word) {
        String[] dictionaryArray = DICTIONARY.toArray(new String[DICTIONARY.size()]);
        boolean englishWord = false;
        int binarySearch = Arrays.binarySearch(dictionaryArray, word);
        if (binarySearch >= 0)
            englishWord = true;
        return englishWord;
    }

    public static boolean isDifferentByOne(String word1, String word2) {
        boolean differentByOne = false;
        int sameCharCount = 0;
        if (word1.length() == word2.length()) {
            for (int index = 0; index < word1.length(); index++) {
                if (word1.charAt(index) == word2.charAt(index))
                    sameCharCount++;
            }
            if (sameCharCount == (word1.length() - 1))
                differentByOne = true;
            else
                differentByOne = false;

        }
        return differentByOne;
    }

    public static boolean isWordChain(ArrayList<String> userInput) {
        // determines if the chain is a valid one for the LCWL game
        boolean wordChain = false, correctWord = true, differentByOne = true;
        if (isUniqueList(userInput) == true) {
            for (int index = 1; index < userInput.size(); index++) {
                String word = userInput.get(index);
                if (isEnglishWord(word) != true)
                    correctWord = false;

                if (isDifferentByOne(userInput.get(index - 1), userInput.get(index)) != true)
                    differentByOne = false;

            }
        }
        if (correctWord == true && differentByOne == true)
            wordChain = true;
        return wordChain;
    }

}
