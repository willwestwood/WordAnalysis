Local command line program that expects one argument, the filename, and prints the analysis to console.

Program assumes the following:
1. "&" counts as a word.

2. Words that split by a new line (or white space) are counted as multiple words.

3. Any words that are made up purely of one kind of special char are not counted. E.e. a section separator such as "******".

4. Most single char words that are a special character are not counted. Exceptions include ampersands, and mathematics formula such as "2 * 4 = 8".	

5. Number signs are included in word length calculations.

6. Punctuation marks are generally not included in word length calculations.
