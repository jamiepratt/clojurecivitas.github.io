(ns language-learning.vocabulary-estimation.article-controls-test
  (:require [cljs.test :refer [deftest is testing]]
            [language-learning.vocabulary-estimation.article-controls :as controls]))

(deftest stored-preference-test
  (testing "missing storage uses the declared fallback"
    (is (true? (controls/stored-preference nil true)))
    (is (false? (controls/stored-preference nil false))))
  (testing "only the persisted string true opens a disclosure"
    (is (true? (controls/stored-preference "true" false)))
    (is (false? (controls/stored-preference "false" true)))
    (is (false? (controls/stored-preference "invalid" true)))))

(deftest disclosure-state-transition-test
  (is (false? (controls/next-disclosure-open? [true true] false)))
  (is (true? (controls/next-disclosure-open? [true false] false)))
  (is (true? (controls/next-disclosure-open? [] false)))
  (is (false? (controls/next-disclosure-open? [] true))))

(deftest available-controls-test
  (is (= {:toolbar false :help false :code false :equations false}
         (controls/available-controls
          {:explanation-count 0 :code-count 0 :equation-count 0})))
  (is (= {:toolbar true :help true :code false :equations true}
         (controls/available-controls
          {:explanation-count 2 :code-count 0 :equation-count 1}))))

(deftest namespace-loads-without-a-dom-test
  (is (false? (controls/dom-available?))))

(deftest drawer-state-and-focus-contract-test
  (is (= {:open? true :focus :drawer-close}
         (controls/drawer-transition {:open? false} :open)))
  (is (= {:open? false :focus :trigger}
         (controls/drawer-transition {:open? true} :close)))
  (is (= {:open? false :focus :trigger}
         (controls/drawer-transition {:open? true} :escape)))
  (is (= {:open? false :focus nil}
         (controls/drawer-transition {:open? false} :escape))))

(deftest active-section-follows-hash-then-scroll-position-test
  (let [sections [{:id "question" :top -180}
                  {:id "chain" :top 48}
                  {:id "limits" :top 260}]]
    (is (= "limits" (controls/active-section-id sections "#limits" 96)))
    (is (= "chain" (controls/active-section-id sections "" 96)))
    (is (= "chain" (controls/active-section-id sections "#missing" 96)))
    (is (= "question"
           (controls/active-section-id
            [{:id "question" :top 250}
             {:id "chain" :top 520}]
            "" 96)))))

(deftest anchor-navigation-leaves-history-to-the-native-anchor-test
  (is (= {:hash "#measurement-chain"
          :history :native
          :scroll :smooth
          :focus "measurement-chain"}
         (controls/anchor-navigation "measurement-chain" false)))
  (is (= :instant
         (:scroll (controls/anchor-navigation "measurement-chain" true)))))

(deftest section-id-is-derived-from-rendered-link-href-test
  (is (= "measurement-chain"
         (controls/section-id-from-href "#measurement-chain")))
  (is (= "measurement-chain"
         (controls/section-id-from-href
          "http://localhost/article.html#measurement-chain")))
  (is (nil? (controls/section-id-from-href "http://localhost/article.html")))
  (is (nil? (controls/section-id-from-href "#"))))

(deftest global-key-handling-ignores-editable-contexts-test
  (doseq [context [{:tag-name "input"}
                   {:tag-name "TEXTAREA"}
                   {:tag-name "select"}
                   {:content-editable? true}
                   {:role "textbox"}]]
    (is (false? (controls/global-key-handled? "Escape" context true))))
  (is (true? (controls/global-key-handled?
              "Escape" {:tag-name "DIV"} true)))
  (is (true? (controls/global-key-handled?
              "Escape" {:tag-name "BUTTON"} true)))
  (is (false? (controls/global-key-handled?
               "ArrowDown" {:tag-name "DIV"} true)))
  (is (false? (controls/global-key-handled?
               "Escape" {:tag-name "DIV"} false))))
