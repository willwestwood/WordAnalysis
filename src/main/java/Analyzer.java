import java.util.*;

public class Analyzer {
    public Analyzer(String text) {
        this.text = text;

        Analyze();
    }

    public long WordCount() {
        return words.size();
    }

    public Map<Integer, Integer> GetWordLengths() {
        return lengths;
    }

    public float GetAverageWordLength(int precision) {
        float average = 0.0f;
        for (Integer key : lengths.keySet())
            average += (float)(key * lengths.get(key));

        return Math.round((average / (float)WordCount()) * (float)Math.pow(10, precision)) / (float)Math.pow(10, precision);
    }

    public Map<Integer, Integer> GetMostFrequentWordLengths()
    {
        Integer maxLength = 0;
        for (Integer value : lengths.values())
            if (value > maxLength) maxLength = value;

        Map<Integer, Integer> result = new HashMap<>();
        for (Integer key : lengths.keySet())
            if (lengths.get(key).equals(maxLength)) result.put(key, maxLength);

        return result;
    }

    public void PrintAnalysis(int precision)
    {
        System.out.println("Word count = " + WordCount());
        System.out.println("Average word length = " + GetAverageWordLength(precision));

        Map<Integer, Integer> wordLengths = GetWordLengths();
        for (Integer key : wordLengths.keySet())
            System.out.println("Number of words of length " + key + " is " + wordLengths.get(key));

        Map<Integer, Integer> mostFreqWordLengths = GetMostFrequentWordLengths();
        StringBuilder mostFreqWordLengthsOutput = new StringBuilder();
        mostFreqWordLengthsOutput.append("The most frequently occurring word length is ");
        boolean firstPass = true;
        Iterator<Map.Entry<Integer, Integer>> it = mostFreqWordLengths.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> pair = it.next();
            if (firstPass)
            {
                mostFreqWordLengthsOutput.append(pair.getValue());
                mostFreqWordLengthsOutput.append(", for word lengths of ");
                firstPass = false;
            }

            mostFreqWordLengthsOutput.append(pair.getKey());
            if (it.hasNext())
                mostFreqWordLengthsOutput.append(" & ");
        }

        System.out.println(mostFreqWordLengthsOutput);
    }

    protected void Analyze() {
        // first, split by whitespace
        // use ArrayList as we're frequently querying the list so need O(1) complexity for accessing
        words = new ArrayList<>(Arrays.asList(text.split("\\s+")));

        // take each word and analyse
        for (int idx = 0; idx < words.size();)
        {
            String word = words.get(idx);

            // remove ignored chars (e.g. question marks, speech marks etc.)
            word = RemoveIgnoredChars(word);

            // if not a valid word then remove an entry and move on without incrementing index
            if (!IsWord(word))
            {
                words.remove(idx);
                continue;
            }

            // add length to set
            int occurrences = 1;
            if (lengths.containsKey(word.length()))
                occurrences += lengths.get(word.length());
            lengths.put(word.length(), occurrences);

            // write back to the words list
            words.set(idx, word);

            // increment idx
            idx++;
        }
    }

    protected static String RemoveIgnoredChars(String input)
    {
        // look at start of word
        int startIdx = 0;
        for (; startIdx < input.length(); startIdx++)
        {
            if (Character.toString(input.charAt(startIdx)).replaceAll(prefixSuffixCharsToIgnoreRegex, "").length() > 0)
                break;
        }

        // look at end of word
        int endIdx = input.length() - 1;
        for (; endIdx > startIdx; endIdx--)
        {
            if (Character.toString(input.charAt(endIdx)).replaceAll(prefixSuffixCharsToIgnoreRegex, "").length() > 0)
                break;
        }

        return input.substring(startIdx, endIdx + 1);
    }

    protected static boolean IsWord(String input) {
        // if for some reason we get an empty string, return false
        if (input.length() == 0)
            return false;

        // if word is 1 char (surrounded by white space), check if it is permissible
        if (input.length() == 1)
            return input.replaceAll(allowedSingleCharsRegex, "").length() == 0;

        if (Utils.RemoveNonAlphanumericChars(input).length() == 0) {
            // word is purely made up of non-alphanumerics
            String candidate = RemoveIgnoredChars(input);
            if (Utils.UniqueCharacters(candidate).length < 2)
                // if word is purely made up of 1 kind of non-alphanumeric
                // then the assumption is it is a line breaker or some other visual aid
                return false;
        }

        // otherwise assume this is a valid word
        return true;
    }

    private final String text;
    private List<String> words;
    private final Map<Integer, Integer> lengths = new HashMap<>();

    // ignore preceding/trailing brackets, speech/quote marks, punctuation marks and special chars
    // allow preceding/trailing chars such as '&', '-', '@', '£', etc
    private static final String prefixSuffixCharsToIgnoreRegex = "[\\(\\)\\[\\]\\{\\}\"`\'?!,.:;/\\\\~_<>^±§*=]";

    private static final String allowedSingleCharsRegex = "[a-zA-Z0-9&+\\-=]";
}
