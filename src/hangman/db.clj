(ns hangman.db
  (:require [clojure.java.jdbc :as jdbc]))

(def db-spec
  {:classname   "org.h2.Driver"
   :subprotocol "h2:mem"
   :subname     "word_guessing_game;DB_CLOSE_DELAY=-1"})

(defn create-table []
  (jdbc/execute!
    db-spec
    ["CREATE TABLE IF NOT EXISTS games (
       id SERIAL PRIMARY KEY,
       username VARCHAR(255),
       tries INT,
       word VARCHAR(255))"]))

(create-table)


(defn save-new-game [username tries word]
  (jdbc/insert!
    db-spec
    :games
    {:username username
     :tries    tries
     :word     word}))

(defn get-all-games []
  (jdbc/query
    db-spec
    ["SELECT * FROM games ORDER BY tries"]))


(defn print-games-data []
  (let [games (get-all-games)
        data (map (fn [game] (vals (dissoc game :id))) games)]
    (println)
    (println "SCOREBOARD: ")
    (doseq [[index row] (map-indexed vector data)]
      (println (str (inc index) ". " (clojure.string/join "\t" row))))))