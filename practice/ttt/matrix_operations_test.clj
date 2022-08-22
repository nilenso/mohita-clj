(ns ttt.matrix-operations-test
  (:require
    [clojure.test :refer :all]
    [ttt.matrix-operations :refer :all]))


(def test-matrix
  [[1 2 3]
   [4 5 6]
   [7 8 9]])


(deftest transpose-matrix-test
  (testing "With 3x3 matrix "
    (is (= [[1 4 7]
            [2 5 8]
            [3 6 9]]
           (transpose-matrix test-matrix))))
  (testing "With 2x1 matrix"
    (is (=  [[0 2]] (transpose-matrix [[0] [2]]))))

  (testing "With 1d seq"
    (is (= [1 2 3] (transpose-matrix [1 2 3])))))


(deftest primary-diag-coordinates-test
  (testing "With 3x3 matrix"
    (is (= '([0 0] [1 1] [2 2]) (primary-diag-coordinates test-matrix)))))


(deftest secondary-diag-coordinates-test
  (testing "With 3x3 matrix"
    (is (= '([0 2] [1 1] [2 0]) (secondary-diag-coordinates test-matrix)))))


(deftest get-elements-of-matrix-test
  (testing "with coordinates inside matrix"
    (is (= [1] (get-elements-of-matrix test-matrix '([0 0])))))

  (testing "with coordinates that are out of bound"
    (is (= [nil] (get-elements-of-matrix test-matrix '([0 7]))))))


(run-all-tests #"clojure.test.example")
