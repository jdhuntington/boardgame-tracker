(ns boardgame-tracker
  (:use  [clojure.http.client]
         [clojure.xml]))

(defn boardgame-search
  "Search for a boardgame at bgg.com"
  [name]
  (clojure.xml/parse (str "http://boardgamegeek.com/xmlapi/search?search=" name)))

(defn post-play-to-boardgamegeek
  "post a play to boardgamegeek"
  [username password-hash game-id date-string]
  (let [the-params {
                    :action "save"
                    :ajax 1
                    :dateinput date-string
                    :incomplete 0
                    :nowinstats 0
                    :objectid game-id
                    :objecttype "thing"
                    :playdate date-string
                    :quantity 1
                    :twitter 0
                    :version 2}]
    (clojure.http.client/request "http://boardgamegeek.com/geekplay.php" :post {} {:bggusername username :bggpassword password-hash} the-params)))