(ns c.core-test
  (:require [clojure.test :refer :all]
            [c.core :as core]))

(deftest replace-variables
  (testing "Replace variables with default explicit value"
    (is (= "http://dev.url.com" (core/replace-variables "http://${ENV:dev}.url.com")))
    (is (= "https://dev.url.com" (core/replace-variables "http${SSL:s}://${ENV:dev}.url.com")))))

(deftest create-vault-url
  (testing "Build vault url using from config map"
    (let [config {:scheme "http"
                  :host "example.com"
                  :port "8200"
                  :generic {:application-name "my-secret-path"}}]
      (is (= "http://example.com:8200/ui/vault/secrets/secret/show/my-secret-path"
             (core/create-vault-url config))))))

(deftest create-config-url
  (testing "Build cloud config url from config map"
    (let [config {:uri "http://example.com" :name "my-config"}]
      (is (= "http://example.com/my-config/default"
             (core/create-config-url config))))))
