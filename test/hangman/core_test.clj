(ns hangman.core-test
  (:require [clojure.test :refer :all]
            [hangman.core :refer :all]))

;; Test fetch-random-word function
(deftest test-fetch-random-word
  (testing "Fetching a random word"
    (let [word (fetch-random-word)]
      (is (string? word)))))


;; Test character-exists-in-word? function
(deftest test-character-exists-in-word?
  (testing "Checking if character exists in a word"
    (is (character-exists-in-word? "hangman" "h"))
    (is (not (character-exists-in-word? "hangman" "z")))))


