(ns language-learning.vocabulary-estimation.bayes-theorem-simulations-test
  (:require [clojure.test :refer [deftest is testing]]
            [language-learning.vocabulary-estimation.bayes-theorem-simulations :as bayes]))

(deftest binary-pair-responses-update-a-declared-beta-prior
  (is (= {:alpha 8.0 :beta 4.0}
         (bayes/beta-posterior-parameters
          {:alpha 1.0 :beta 1.0}
          {:recognized 7 :not-recognized 3}))))

(deftest seeded-posterior-prediction-covers-the-finite-pair-pool
  (let [options {:pool-size 100
                 :recognized 7
                 :not-recognized 3
                 :prior-alpha 1.0
                 :prior-beta 1.0
                 :draw-count 4000
                 :seed 620260717}
        first-run (bayes/seeded-finite-pool-predictive options)
        replay (bayes/seeded-finite-pool-predictive options)]
    (testing "the posterior predictive mean includes observed known pairs"
      (is (< (Math/abs (- 67.0 (:mean first-run))) 1.0e-12)))
    (testing "the seeded interval is deterministic and respects pool bounds"
      (is (= first-run replay))
      (is (<= 7 (:lower first-run) (:upper first-run) 97))
      (is (<= (:lower first-run) (:mean first-run) (:upper first-run))))
    (testing "the complete seeded distribution remains inspectable"
      (is (= 4000 (reduce + (vals (:frequencies first-run))))))))
