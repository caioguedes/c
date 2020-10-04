(ns c.core-test
  (:require [clojure.test :refer :all]
            [c.core :as core]))

(deftest replace-variables
  (testing "Replace variables with default explicit value"
    (is (= "http://dev.url.com" (core/replace-variables "http://${ENV:dev}.url.com")))
    (is (= "https://dev.url.caom" (core/replace-variables "http${SSL:s}://${ENV:dev}.url.com")))))
