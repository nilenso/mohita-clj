(ns matrix-operations-test
  (:require
    [clojure.test :refer :all]
    [ttt.matrix-operations :refer :all]))


(def test-square-matrix
  [[1 2 3]
   [4 5 6]
   [7 8 9]])


(def test-1-by-2-matrix
  [[1 2]])


(deftest transpose-matrix-test
  (testing "With 3x3 matrix "
    (is (= [[1 4 7]
            [2 5 8]
            [3 6 9]]
           (transpose-matrix test-square-matrix))))
  (testing "With 1x2 matrix"
    (is (= [[0 2]] (transpose-matrix [[0] [2]])))))


(deftest primary-diag-coordinates-test
  (testing "With 3x3 matrix"
    (is (= '([0 0] [1 1] [2 2]) (primary-diag-coordinates test-square-matrix))))
  (testing "With 1x2 matrix"
    (is (= nil (primary-diag-coordinates test-1-by-2-matrix))))
  (testing "With empty matrix"
    (is (= nil (primary-diag-coordinates [[]])))))


(deftest secondary-diag-coordinates-test
  (testing "With 3x3 matrix"
    (is (= '([0 2] [1 1] [2 0]) (secondary-diag-coordinates test-square-matrix))))
  (testing "With 1x2 matrix"
    (is (= nil (secondary-diag-coordinates test-1-by-2-matrix))))
  (testing "With empty matrix"
    (is (= nil (secondary-diag-coordinates [[]])))))


(deftest get-elements-of-matrix-test
  (testing "with coordinates inside matrix"
    (is (= [1] (get-elements-of-matrix test-square-matrix '([0 0])))))

  (testing "with coordinates that are out of bound"
    (is (= [nil] (get-elements-of-matrix test-square-matrix '([0 7]))))))


(deftest square-matrix-test
  (testing "With 3x3 matrix"
    (is (= true (square-matrix? test-square-matrix))))
  (testing "With 1x2 matrix"
    (is (= false (square-matrix? test-1-by-2-matrix)))))

(deftest matrix-to-string-test
  (testing "With 3x3 matrix"
    (is (= "\n1 2 3\n4 5 6\n7 8 9" (matrix-to-string test-square-matrix) )))
  (testing "With 1x2 matrix"
    (is (= "\n1 2" (matrix-to-string test-1-by-2-matrix)))))