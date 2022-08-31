(ns tic-tac-toe-test
  (:require
    [clojure.set]
    [clojure.test :refer :all]
    [ttt.tic-tac-toe :refer :all]))


(deftest winner-of-collection-test
  (testing "With all :e elements"
    (is (= nil (winner-of-collection [:e :e :e]))))

  (testing "With all :x elements "
    (is (= :x (winner-of-collection [:x :x :x]))))

  (testing "With 2 :x elements"
    (is (= nil (winner-of-collection [:x :x :o])))))


(deftest valid-board?-test
  (testing "Validity of a board"
    (testing "with valid game pieces"
      (is (= true (valid-board? [[:x :e :o]
                                 [:x :x :e]
                                 [:o :x :o]]))))
    (testing "with invalid game pieces"
      (is (= false (valid-board? [[:x :e :o]
                                  [:x :x :e]
                                  [:o 1 :o]]))))))


(deftest winner-of-board-test

  (testing "Returns winning game piece "
    (testing "when winner is along a row"
      (is  (= :o (winner-of-board [[:e :x :e]
                                   [:o :o :o]
                                   [:x :e :x]]))))
    (testing "when winner is along column"
      (is (= :x (winner-of-board [[:x :e :o]
                                  [:x :e :e]
                                  [:x :e :o]]))))
    (testing "when winner is across diagonal"
      (is (= :x (winner-of-board [[:x :e :e]
                                  [:o :x :e]
                                  [:o :e :x]])))))

  (testing "Returns no winner when there is a draw"
    (is (= "No Winner" (winner-of-board [[:x :e :o]
                                         [:x :x :e]
                                         [:o :x :o]]))))
  (testing "Returns invalid board "
    (testing "when game pieces aren't valid"
      (is (= "Invalid Board" (winner-of-board [[:x :e :o]
                                               [:x :x :e]
                                               [:o 1 :o]]))))
    (testing "when board is not a square matrix "
      (is (= "Invalid Board" (winner-of-board [[:x :e]]))))))



