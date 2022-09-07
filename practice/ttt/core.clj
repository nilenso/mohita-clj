(ns ttt.core
  (:gen-class)
  (:require [ttt.game :as game]
            [ttt.board :as board]))

(defn -main
  []
  (game/play board/empty-board))
