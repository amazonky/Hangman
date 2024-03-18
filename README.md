# hangman

defn fetch-random-word []- 
retrieves a random word from the "Random Word API" using the RapidAPI platform. If the request is successful, it returns the word otherwise, it throws an exception. Has no arguments.

defn character-exists-in-word? [word character] - 
checks whether a given character exists within a given word. It returns true if the character is found in the word, and false otherwise. Recieves 2 arguments word and a character.

defn get-current-word [word guessed-letters] - 
generates a string representing the current state of the word to be guessed, with guessed letters revealed and unguessed letters replaced with underscores. It takes two arguments: word (a string representing the word to be guessed) and guessed-letters (a collection of characters representing the letters already guessed by the player).

defn word-complete? [current-word] - it checks if the current word had any underscores, if it doesn't it means that the work is complete and returns true, otherwise it returns false.

defn get-guess [] - esentially it checks if the user input is one character and that character is a letter, if not it informs user that the input is invalid.







## Usage

FIXME

## License

Copyright © 2024 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
