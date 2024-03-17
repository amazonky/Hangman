(defproject hangman "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [clj-http "3.12.0"]
                 [cheshire "5.10.0"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [com.h2database/h2 "1.4.200"]]
  :main hangman.core
  :repl-options {:init-ns hangman.core})