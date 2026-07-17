(ns language-learning.vocabulary-estimation.pair-frequency-logistic-v2-gate-test
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]
            [language-learning.vocabulary-estimation.pair-frequency-logistic-v2-gate :as gate]))

(defn read-evidence [resource-name]
  (edn/read-string
   (slurp (io/resource
           (str "language_learning/vocabulary_estimation/" resource-name)))))

(deftest precommitted-grid-test
  (is (= 100 (count gate/tuning-rules)))
  (is (= 45 (count gate/supported-cells)))
  (is (= 60 (count gate/stress-cells))))

(deftest tuning-cell-replay-retains-cell-identities-without-rewriting-history
  (let [historical (read-evidence "pair_frequency_logistic_v2_tuning.edn")
        replay (read-evidence "pair_frequency_logistic_v2_tuning_cells.edn")
        cells (:cells replay)
        historical-candidate
        (first (filter #(= gate/diagnostic-rule (:rule %))
                       (:rules historical)))]
    (is (= :tuning-cell-replay (:phase replay)))
    (is (= gate/diagnostic-rule (:rule replay)))
    (is (= 500 (:replicates-per-cell replay)))
    (is (= 45 (count cells)))
    (is (= gate/supported-cells (mapv :cell cells)))
    (is (= (select-keys historical-candidate
                        [:aggregate :minimum-cell-coverage
                         :maximum-cell-mae-ratio])
           (:historical-summary replay)))
    (is (= (apply min (map #(get-in % [:v2 :coverage]) cells))
           (get-in replay [:replay-summary :minimum-cell-coverage])))
    (is (= (apply max (map :mae-ratio cells))
           (get-in replay [:replay-summary :maximum-cell-mae-ratio])))
    (is (not= (get-in replay [:historical-summary :minimum-cell-coverage])
              (get-in replay [:replay-summary :minimum-cell-coverage]))
        "The diagnostic replay must not be relabelled as the historical run")))

(deftest complete-round-stopping-test
  (let [checkpoints
        {32 {:v2 {:lower 3000 :upper 5000}}
         40 {:v2 {:lower 3300 :upper 4700}}}]
    (is (= {:lower 3300 :upper 4700
            :items-tested 40 :stopping-reason :precision-target}
           (gate/stop-at checkpoints
                         {:minimum-items 32
                          :target-half-width-ratio 0.10
                          :soft-maximum-items 40}
                         :v2)))))

(deftest shortest-passing-rule-test
  (let [slow {:rule {:minimum-items 40 :soft-maximum-items 80
                     :target-half-width-ratio 0.075}
              :aggregate {:median-items 64 :mean-items 60.0}
              :passes? true}
        fast {:rule {:minimum-items 32 :soft-maximum-items 64
                     :target-half-width-ratio 0.10}
              :aggregate {:median-items 40 :mean-items 38.0}
              :passes? true}
        failed (assoc fast :passes? false)]
    (is (= fast (gate/choose-rule {:rules [slow failed fast]})))
    (is (nil? (gate/choose-rule {:rules [failed]})))))
