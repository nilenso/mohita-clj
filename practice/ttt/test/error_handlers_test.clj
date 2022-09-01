(ns error_handlers_test
  (:require
    [clojure.test :refer :all]
    [failjure.core :as f]
    [ttt.error-handlers :refer :all]))


(deftest parse-int-test
  (testing "Returns integer"
    (testing "when argument is integer within a string"
      (is (= 1 (parse-int "1")))))
  (testing  "Does not return exception"
    (testing "when argument is not an integer within a string"
      (is (f/failed? (parse-int "abc"))))))
