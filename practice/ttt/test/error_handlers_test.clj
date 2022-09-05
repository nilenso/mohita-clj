(ns error-handlers-test
  (:require
    [clojure.test :refer :all]
    [failjure.core :as f]
    [ttt.error-handlers :refer :all]))


(deftest parse-int-test
  (testing "Returns integer"
    (testing "when argument is integer within a string"
      (is (= 1 (parse-int "1")))))
  (testing  "Will not return exception"
    (testing "when argument is not an integer within a string"
      (is (f/failed? (parse-int "abc"))))))


(deftest in-range-test
  (testing "Return integer"
    (testing "when in range"
      (is (= 5 (in-range 5 0 7)))))
  (testing "Will not return exception"
    (testing "when argument is out of range"
      (is (f/failed? (in-range 8 0 7))))))
