(ns game-test
  (:require
    [clojure.set]
    [clojure.test :refer :all]
    [ttt.game :refer :all]))


(def test-player-sequence (cycle #{:o :x}))


(deftest over-test
  (testing "Returns winner "
    (testing "when there is a winner in board"
      (is (= :o (over? [[:e :x :e]
                        [:o :o :o]
                        [:x :e :x]] (take 2 test-player-sequence))))))
  (testing "Returns true"
    (testing "when there is a draw"
      (is (= true (over? [[:x :o :x]
                          [:x :o :o]
                          [:o :x :x]] (take 0 test-player-sequence))))))
  (testing "Returns false"
    (testing "when there no winner with moves left"
      (is (= false (over? [[:x :e :o]
                           [:e :x :e]
                           [:o :x :o]] (take 3 test-player-sequence)))))
    (testing "when the board is empty"
      (is (= false (over? [[:e :e :e]
                           [:e :e :e]
                           [:e :e :e]] (take 9 test-player-sequence)))))))
