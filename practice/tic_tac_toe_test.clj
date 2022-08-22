(ns tic-tac-toe-test
  (:require
    [clojure.test :refer :all]
    [tic-tac-toe :as ttt]))


(deftest winner-of-collection-test
  (testing "With all :e elements"
    (is (= nil (ttt/winner-of-collection [:e :e :e]))))

  (testing "With all :x elements "
    (is (= :x (ttt/winner-of-collection [:x :x :x]))))

  (testing "With 2 :x elements"
    (is (= nil (ttt/winner-of-collection [:x :x :o])))))


(deftest transpose-matrix-test
  (testing "With 3x3 matrix "
    (is (= [[1 4 7]
            [2 5 8]
            [3 6 9]]
           (ttt/transpose-matrix [[1 2 3]
                                  [4 5 6]
                                  [7 8 9]]))))
  (testing "With 2x1 matrix"
    (is (=  [[0 2]] (ttt/transpose-matrix [[0] [2]]))))

  (testing "With 1d seq"
    (is (= [1 2 3] (ttt/transpose-matrix [1 2 3])))))


(deftest primary-diag-coordinates-test
  (testing "With 3x3 matrix"
    (is (= '([0 0] [1 1] [2 2]) (ttt/primary-diag-coordinates [[1 4 7]
                                                               [2 5 8]
                                                               [3 6 9]]))))
  (testing "With 2x3 matrix"
    (is (= nil (ttt/primary-diag-coordinates [[1 4 7]
                                              [2 5 8]])))))


(deftest secondary-diag-coordinates-test
  (testing "With 3x3 matrix"
    (is (= '([0 2] [1 1] [2 0]) (ttt/secondary-diag-coordinates [[1 4 7]
                                                                 [2 5 8]
                                                                 [3 6 9]]))))
  (testing "With 2x3 matrix"
    (is (= nil (ttt/secondary-diag-coordinates [[1 4 7]
                                                [2 5 8]])))))


(deftest get-elements-of-matrix
  (testing "with coordinates inside matrix"
    (is (= 1 (ttt/get-elements-of-matrix [[1 4 7]
                                          [2 5 8]
                                          [3 6 9]] '([0 0])))))

  (testing "with coordinates that are out of bound"
    (is (= nil (ttt/get-elements-of-matrix [[1 4 7]
                                            [2 5 8]
                                            [3 6 9]] '([0 7])))))
  (testing "with 1d array with no elements"
    (is (= nil (ttt/get-elements-of-matrix [] '([0 0]))))))


(deftest is-valid-collection?-test
  (testing "Validity of a collection in board"
    (testing "with valid game pieces"
      (is (= true (ttt/is-valid-collection? [:x :e :o]))))
    (testing "with invalid game pieces"
      (is (= false (ttt/is-valid-collection? [:o 1 :o]))))))


(deftest is-board-valid?-test
  (testing "Validity of a board"
    (testing "with valid game pieces"
      (is (= true (ttt/is-board-valid? [[:x :e :o]
                                        [:x :x :e]
                                        [:o :x :o]]))))
    (testing "with invalid game pieces"
      (is (= false (ttt/is-board-valid? [[:x :e :o]
                                         [:x :x :e]
                                         [:o 1 :o]]))))
    (testing "with invalid board size"
      (is (= false (ttt/is-board-valid? []))))))


(deftest winner-of-board-test

  (testing "Returns winning game piece "
    (testing "when winner is along a row"
      (is ((= :o (ttt/winner-of-board [[:e :x :e]
                                       [:o :o :o]
                                       [:x :e :x]])))))
    (testing "when winner is along column"
      (is (= :x (ttt/winner-of-board [[:x :e :o]
                                      [:x :e :e]
                                      [:x :e :o]]))))
    (testing "when winner is across diagonal"
      (is (= :x (ttt/winner-of-board [[:x :e :e]
                                      [:o :x :e]
                                      [:o :e :x]])))))

  (testing "Returns no winner when there is a draw"
    (is (= "No Winner" (ttt/winner-of-board [[:x :e :o]
                                             [:x :x :e]
                                             [:o :x :o]]))))
  (testing "Returns invalid board when game pieces aren't valid"
    (is (= "Invalid Board" (ttt/winner-of-board [[:x :e :o]
                                                 [:x :x :e]
                                                 [:o 1 :o]])))))


(run-all-tests #"clojure.test.example")
