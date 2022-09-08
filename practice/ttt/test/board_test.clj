(ns board-test
  (:require
    [clojure.test :refer :all]
    [ttt.board :refer :all]))


(def test-empty-board
  [[:e :e :e]
   [:e :e :e]
   [:e :e :e]])


(deftest empty-coordinate-test
  (testing "Returns true"
    (testing "when position is empty"
      (is (= true (empty-coordinate? test-empty-board [1 1])))))
  (testing "Returns false"
    (testing "when position is not empty"
      (is (= false (empty-coordinate? [[:e :e :e]
                                       [:e :o :e]
                                       [:e :e :e]] [1 1]))))))


(deftest valid-test
  (testing "Returns true"
    (testing "when board is a square matrix and all pieces are valid"
      (is (= true (valid? test-empty-board)))))
  (testing "Return false"
    (testing "when board is not square matrix and all pieces are valid"
      (is (=  false (valid? [[:e :e :e]]))))
    (testing "when board is square matrix and all pieces are not valid"
      (is (=  false (valid? [[:e :e :e]
                             [:e :e :e]
                             [:e :e 1]]))))))


(deftest update-test
  (testing "Returns correctly updated board"
    (testing "when board is valid"
      (is (= [[:e :e :e]
              [:e :o :e]
              [:e :e :e]] (update test-empty-board [1 1] :o))))))


(deftest winner-of-collection-test
  (testing "With all :e elements"
    (is (= nil (winner-of-collection [:e :e :e]))))

  (testing "With all :x elements "
    (is (= :x (winner-of-collection [:x :x :x]))))

  (testing "With 2 :x elements"
    (is (= nil (winner-of-collection [:x :x :o])))))


(deftest winning-game-piece-test

  (testing "Returns winning game piece "
    (testing "when winner is along a row"
      (is  (= :o (winning-game-piece [[:e :x :e]
                                      [:o :o :o]
                                      [:x :e :x]]))))
    (testing "when winner is along column"
      (is (= :x (winning-game-piece [[:x :e :o]
                                     [:x :e :e]
                                     [:x :e :o]]))))
    (testing "when winner is across diagonal"
      (is (= :x (winning-game-piece [[:x :e :e]
                                     [:o :x :e]
                                     [:o :e :x]])))))

  (testing "Returns nil "
    (testing "when there is a draw"
      (is (= nil (winning-game-piece [[:x :e :o]
                                      [:x :x :e]
                                      [:o :x :o]]))))))
