(ns language-learning.vocabulary-estimation.bayes-theorem-simulations-interactive-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [language-learning.vocabulary-estimation.bayes-theorem-simulations-interactive :as bayes]))

(deftest browser-bridge-updates-and-predicts-the-finite-pool
  (is (= {:alpha 8 :beta 4}
         (bayes/beta-posterior-parameters
          {:alpha 1 :beta 1}
          {:recognized 7 :not-recognized 3})))
  (let [summary (bayes/finite-pool-predictive-summary
                 {:pool-size 100
                  :recognized 7
                  :not-recognized 3
                  :prior-alpha 1
                  :prior-beta 1
                  :draw-count 4000
                  :seed 620260717})]
    (is (= 67 (:mean summary)))
    (is (<= 7 (:lower summary) (:mean summary) (:upper summary) 97))
    (is (= 4000 (reduce + (vals (:frequencies summary)))))))

(deftest bridge-reset-replays-identical-prediction
  (bayes/reset-vocabulary-bridge!)
  (bayes/record-pair-response! :recognized)
  (bayes/record-pair-response! :not-recognized)
  (let [first-run (bayes/current-vocabulary-bridge-summary)]
    (bayes/reset-vocabulary-bridge!)
    (bayes/record-pair-response! :recognized)
    (bayes/record-pair-response! :not-recognized)
    (testing "the same responses after reset reproduce every seeded result"
      (is (= first-run (bayes/current-vocabulary-bridge-summary))))))

(deftest bridge-exposes-binary-keyboard-operable-controls-and-a-live-result
  (bayes/reset-vocabulary-bridge!)
  (let [component (bayes/vocabulary-bridge-simulator)
        nodes (filter vector? (tree-seq coll? seq component))
        buttons (filter #(= bayes/control-button (first %)) nodes)
        section (first component)]
    (is (= :section.bp-shell section))
    (is (some #(= "Recognized" (second %)) buttons))
    (is (some #(= "Not recognized" (second %)) buttons))
    (is (some #(= "Reset" (second %)) buttons))
    (is (some #(= "polite" (get-in % [1 :aria-live])) nodes))))
