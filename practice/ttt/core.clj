(ns ttt.core
  (:gen-class)
  (:require
    [cli4clj.cli :as cli]
    [ttt.console :as console]))


(def ^:dynamic initial-board
  [[:e :e :e]
   [:e :e :e]
   [:e :e :e]])


(defn -main
  [& args]
  (cli/start-cli {:cmds {:start {:fn console/-main
                                 :short-info "Test Command"
                                 :long-info "Prints a test message to stdout."
                                 :completion-hint "This is a test command without arguments."}}
                  :allow-eval true
                  :prompt-string "cli# "
                  :alternate-scrolling (some #(= % "alt") args)
                  :alternate-height 3
                  :entry-message (str "This is the example of cli4clj!" "\n"
                                      "\n"
                                      "To see a list of available commands, type <Tab>." "\n"
                                      "To exit this interactive cli tool, type \"q\".")}))
