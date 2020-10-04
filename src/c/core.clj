(ns c.core
  (:require [clj-yaml.core :as y]
            [clojure.term.colors :as c]
            [clojure.string :as s])
  (:gen-class))

(defn replace-variables [value]
  (if-let [match (re-find #"\$\{([^}^{]+)\}" value)]
    (let [pair (s/split (last match) #":")]
      (recur (s/replace value (first match) (last pair))))
    value))

(defn load-bootstrap []
  (y/parse-string (slurp "src/main/resources/bootstrap.yml")))

(defn get-name [bootstrap]
  (get-in bootstrap [:spring :application :name]))

(defn get-config [bootstrap]
  (let [config (get-in bootstrap [:spring :cloud :config])]
    (if (:name config)
      (config)
      (assoc config :name (get-name bootstrap)))))

(defn get-vault [bootstrap]
  (get-in bootstrap [:spring :cloud :vault]))

(defn create-vault-url [vault]
  (format "%s://%s:%s/ui/vault/secrets/secret/show/%s"
          (:scheme vault)
          (:host vault)
          (:port vault)
          (get-in vault [:generic :application-name])))

(defn create-config-url [config]
  (format "%s/%s/%s" (:uri config) (:name config) "default"))

(defn -main [& args]
  (let [bootstrap (load-bootstrap)
        config (get-config bootstrap)
        vault (get-vault bootstrap)]
    (prn (c/yellow "Spring Cloud Config:"))
    (prn (c/yellow "Url:") (-> config create-config-url replace-variables))
    (prn (c/yellow "Spring Cloud Vault:"))
    (prn (c/yellow "Token:") (replace-variables (:token vault)))
    (prn (c/yellow "Url:") (-> vault create-vault-url replace-variables))))
