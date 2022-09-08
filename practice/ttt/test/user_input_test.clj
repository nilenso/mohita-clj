(ns user-input-test
  (:require
    [clojure.test :refer :all]
    [failjure.core :as f]
    [ttt.user-input :refer :all]))


(deftest valid-int-test
  (testing "Returns integer"
    (testing "when argument is integer within a string"
      (is (= 1 (valid-int "1")))))
  (testing  "Will not return exception"
    (testing "when argument is not an integer within a string"
      (is (f/failed? (valid-int "abc"))))))


(deftest valid-range-test
  (testing "Return integer"
    (testing "when in range"
      (is (= 5 (valid-range 5 0 7)))))
  (testing "Will not return exception"
    (testing "when argument is out of range"
      (is (f/failed? (valid-range 8 0 7))))))

