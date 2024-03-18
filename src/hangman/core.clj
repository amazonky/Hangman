(ns hangman.core)

(require '[clj-http.client :as client])
(require '[cheshire.core :as json])
(require '[clojure.string :as str])
(require '[hangman.db :refer :all])

(defn fetch-random-word []
  (let [url "https://random-word-api.p.rapidapi.com/get_word"
        headers {:x-rapidapi-key  "49c4f4f5f5msh1c7d46e8ba824fep11da76jsnea122c26994c"
                 :x-rapidapi-host "random-word-api.p.rapidapi.com"}
        response (client/get url {:headers headers})
        body (json/parse-string (:body response) true)]
    (if (= (:status response) 200)
      (:word body)
      (throw (Exception. (str "Failed to fetch data. Status code: " (:status response)))))))


(defn character-exists-in-word? [word character]
  (if (clojure.string/includes? word character)
    true
    false))


(defn get-current-word [word guessed-letters]
  (apply str
         (map #(if (contains? guessed-letters (str %)) % "_") word)))

(defn word-complete? [current-word]
  (not (some #{\_} current-word)))

(defn get-username []
  (println "Enter your name: ")
  (let [name (read-line)]
    name))

(defn get-guess []
  (loop [guess (clojure.string/trim (read-line))]
    (if (and (= (count guess) 1) (Character/isLetter (int (first guess))))
       guess
      (do (println "Invalid input! Please enter a single letter.")
          (recur (clojure.string/trim (read-line)))))))


(defn -main []
  (println "Welcome to the Word Guessing Game!")
  (let [correct-word (fetch-random-word)
        user-name (get-username)]
    (println (str "Word: " correct-word))
    (loop [guessed-letters #{}
           missed-letters #{}
           count-of-guesses 0]
      (println (str "Current word: " (get-current-word correct-word guessed-letters)))
      (if (word-complete? (get-current-word correct-word guessed-letters))
        ;; game   won
        (do (println "Congratulations! You guessed the word!")
            (save-new-game user-name count-of-guesses correct-word)
            ((fn menu []
               (println "Select one of the following options:")
               (println "1. Play again")
               (println "2. See scoreboard")
               (println "3. Exit game")
               (let [user-input (read-line)]
                 (cond
                   (= user-input "1") (do (println "Great! Let's play again!")
                                          (-main))
                   (= user-input "2") (do (println (print-games-data))
                                          (recur))
                   (= user-input "3") (do (println "Exiting game! Goodbye!")
                                          (System/exit 0))
                   :else (do (println "Wrong input")
                             (recur)))))))
        ;; continue   game
        (do
          (println (str "Missed letters: " (str/join ", " (sort missed-letters))))
          (println (str "Number of guesses: " count-of-guesses))
          (print "Enter a letter: ")
          (flush)
          (let [guess (get-guess)]
            (if (or (contains? guessed-letters guess) (contains? missed-letters guess))
              ;; Letter already  guessed
              (do (println "You already guessed that letter. Try again.")
                  (println)
                  (recur guessed-letters missed-letters count-of-guesses))
              ;; Letter wasn't already guessed  ---> Checking if letter exists in a word
              (if (character-exists-in-word? correct-word guess)
                ;; letter exist in a word
                (do (println "Good job! You guessed the letter!")
                    (println)
                    (recur (conj guessed-letters guess) missed-letters (inc count-of-guesses)))
                ;;letter doesn't exist in a word
                (do (println (str "Incorrect guess. Try again."))
                    (println)
                    (recur guessed-letters (conj missed-letters guess) (inc count-of-guesses)))))))))))