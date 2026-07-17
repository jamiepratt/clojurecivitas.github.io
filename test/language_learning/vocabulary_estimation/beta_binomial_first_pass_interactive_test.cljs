(ns language-learning.vocabulary-estimation.beta-binomial-first-pass-interactive-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [language-learning.vocabulary-estimation.beta-binomial-first-pass-interactive :as proposal-1]))

(deftest seeded-attempt-is-balanced-non-adaptive-and-lossless
  (let [events (mapv proposal-1/scheduled-selection
                     (range proposal-1/seeded-attempt-selection-limit))
        by-stratum (group-by :stratum events)]
    (is (= 32 (count events)))
    (is (= (set (range 1 9)) (set (keys by-stratum))))
    (is (every? #(= 4 (count %)) (vals by-stratum)))
    (is (= [4 4 3 3 2 1 1 0]
           (mapv (fn [stratum]
                   (count (filter #(= :correct (:response %))
                                  (get by-stratum stratum))))
                 (range 1 9))))
    (is (= 32 (count (distinct (map :item-id events)))))
    (is (= #{:correct :wrong :dont-know} (set (map :response events))))
    (is (= proposal-1/seeded-stratum-orders
           (mapv (fn [round]
                   (mapv :stratum
                         (filter #(= round (:round %)) events)))
                 (range 1 5))))
    (is (every? #(not= (vec (range 1 9)) %)
                proposal-1/seeded-stratum-orders)
        "Every round retains a nontrivial seeded presentation shuffle")
    (testing "selection is a fixed function of position, not prior responses"
      (let [scheduled-before (proposal-1/scheduled-selection 17)]
        (reset! proposal-1/state proposal-1/initial-state)
        (doseq [response [:wrong :correct :dont-know :wrong]]
          (proposal-1/record-response! response))
        (is (= scheduled-before
               (proposal-1/scheduled-selection 17)))))))

(deftest three-way-responses-remain-raw-while-proposal-1-updates-binarily
  (reset! proposal-1/state proposal-1/initial-state)
  (doseq [response [:correct :wrong :dont-know]]
    (proposal-1/record-response! response))
  (is (= [:correct :wrong :dont-know] (:responses @proposal-1/state)))
  (is (= {:alpha 2 :beta 3}
         (select-keys @proposal-1/state [:alpha :beta]))))

(deftest posterior-predictive-reset-replays-the-same-seeded-draw
  (let [first-draw (proposal-1/sample-complete-draw!
                    (proposal-1/make-rng proposal-1/sampling-seed))
        replay (proposal-1/sample-complete-draw!
                (proposal-1/make-rng proposal-1/sampling-seed))]
    (is (= first-draw replay))
    (is (= 8 (count (:strata first-draw))))
    (is (<= 18 (:total first-draw) 7986))))

(deftest stopping-explorer-preserves-round-boundary-and-soft-cap-decisions
  (is (false? (:assess?
               (proposal-1/explorer-stopping-check
                {:minimum 32 :target-percent 10 :soft-cap 96
                 :items-tested 36 :half-width 100}))))
  (is (true? (:target?
              (proposal-1/explorer-stopping-check
               {:minimum 32 :target-percent 10 :soft-cap 96
                :items-tested 40 :half-width 750}))))
  (is (true? (:soft-max?
              (proposal-1/explorer-stopping-check
               {:minimum 32 :target-percent 10 :soft-cap 96
                :items-tested 96 :half-width 900})))))
