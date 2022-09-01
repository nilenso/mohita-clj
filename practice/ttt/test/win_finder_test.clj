(ns win_finder_test
  (:require
    [clojure.set]
    [clojure.test :refer :all]
    [ttt.win-finder :refer :all]))


(def test-player-sequence (cycle game-pieces-set))


(deftest winner-of-collection-test
  (testing "With all :e elements"
    (is (= nil (winner-of-collection [:e :e :e]))))

  (testing "With all :x elements "
    (is (= :x (winner-of-collection [:x :x :x]))))

  (testing "With 2 :x elements"
    (is (= nil (winner-of-collection [:x :x :o])))))


(deftest valid-board-test
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

  (testing "Returns nil "
    (testing "when there is a draw"
      (is (= nil (winner-of-board [[:x :e :o]
                                   [:x :x :e]
                                   [:o :x :o]])))))
  (testing "Returns invalid board "
    (testing "when game pieces aren't valid"
      (is (= "Invalid Board" (winner-of-board [[:x :e :o]
                                               [:x :x :e]
                                               [:o 1 :o]]))))
    (testing "when board is not a square matrix "
      (is (= "Invalid Board" (winner-of-board [[:x :e]]))))))


(deftest game-over-test
  (testing "Returns true "
    (testing "when there is a winner in board"
      (is (= true (game-over? [[:e :x :e]
                               [:o :o :o]
                               [:x :e :x]] (take 2 test-player-sequence)))))
    (testing "when there is a draw"
      (is (= true (game-over? [[:x :o :x]
                               [:x :o :o]
                               [:o :x :x]] (take 0 test-player-sequence))))))
  (testing "Returns false"
    (testing "when there no winner with moves left"
      (is (= false (game-over? [[:x :e :o]
                                [:e :x :e]
                                [:o :x :o]] (take 3 test-player-sequence)))))
    (testing "when the board is empty"
      (is (= false (game-over? [[:e :e :e]
                                [:e :e :e]
                                [:e :e :e]] (take 9 test-player-sequence)))))))
