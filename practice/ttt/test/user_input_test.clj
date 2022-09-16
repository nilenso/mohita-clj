(ns user-input-test
  (:require
    [clojure.test :refer :all]
    [failjure.core :as f]
    [ttt.user-input :refer :all]))


(deftest valid-command-test
  (testing "Returns command"
    (testing "if command is valid"
      (is (= "1" (valid-command? "1")))))
  (testing  "Will not return exception"
    (testing "when argument is invalid"
      (is (f/failed? (valid-command? "abc"))))))

