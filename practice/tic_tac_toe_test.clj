(ns tic-tac-toe-test
  (:require
    [clojure.test :refer :all]
    [tic-tac-toe :as ttt]))


(deftest all-empty-input-is-invalid
  (is (= nil (ttt/winner-of-collection [:e :e :e]))))


(deftest all-x-input-is-valid
  (are [x] (= :x (ttt/winner-of-collection x))
    [:x :x :x]))


(run-all-tests #"clojure.test.example")
