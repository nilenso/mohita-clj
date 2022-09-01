(ns console_test
  (:require
    [clojure.test :refer :all]
    [ttt.console :refer :all]))


(def test-empty-board
  [[:e :e :e]
   [:e :e :e]
   [:e :e :e]])


(deftest update-board-test
  (testing "Returns correctly updated board"
    (is (= [[:e :e :e]
            [:e :o :e]
            [:e :e :e]] (update-board test-empty-board [1 1] :o)))))


(deftest move-valid-test
  (testing "Returns true"
    (testing "when position is empty"
      (is (= true (move-valid? test-empty-board [1 1])))))
  (testing "Returns false"
    (testing "when position is occupied"
      (is (= false (move-valid? [[:e :e :e]
                                 [:e :o :e]
                                 [:e :e :e]] [1 1]))))))
