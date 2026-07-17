(ns language-learning.vocabulary-estimation.article-controls
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [scicloj.kindly.v4.kind :as kind]))

(def series-manifest
  [{:id :workflow
    :number 1
    :title "Managing brilliant but uneven minds"
    :url "managing_brilliant_but_uneven_minds.html"
    :status :published}
   {:id :purpose
    :number 2
    :title "Why estimate vocabulary?"
    :url "why_estimate_vocabulary.html"
    :status :published}
   {:id :bayes
    :number 3
    :title "Bayes' theorem: from uncertainty to decision"
    :url "bayes_theorem_simulations.html"
    :status :published}
   {:id :proposal-1
    :number 4
    :title "Proposal 1: estimating known pairs"
    :url "beta_binomial_first_pass.html"
    :status :research-target}
   {:id :proposal-2
    :number 5
    :title "Proposal 2: does pair frequency predict responses?"
    :url "pair_frequency_logistic_v2_article.html"
    :status :not-promoted}])

(def series-roadmap
  [{:id :pool-construction
    :title "Build and version the lemma–surface-form pool"
    :status :deferred}
   {:id :lemma-inference
    :title "Infer lemma knowledge from correlated form evidence"
    :status :deferred}
   {:id :response-model
    :title "Model correct, wrong, and don't-know responses separately"
    :status :deferred}
   {:id :calibration-and-adaptation
    :title "Calibrate items before shortening tests adaptively"
    :status :deferred}
   {:id :contexts-and-senses
    :title "Decide when contexts and senses become identifiable"
    :status :deferred}])

(defn series-context
  "Return the current article and its immediate neighbours in canonical order."
  [article-id]
  (let [index (first (keep-indexed #(when (= article-id (:id %2)) %1)
                                   series-manifest))]
    (when (nil? index)
      (throw (ex-info "Unknown vocabulary-series article id."
                      {:article-id article-id})))
    {:previous (get series-manifest (dec index))
     :current (get series-manifest index)
     :next (get series-manifest (inc index))}))

(defn- resource-text [filename]
  (slurp
   (io/resource
    (str "language_learning/vocabulary_estimation/" filename))))

(defn- toolbar []
  [:div.article-explanations-toolbar
   {:role "region" :aria-labelledby "article-explanations-heading"}
   [:p
    [:strong#article-explanations-heading "Reading controls"]
    [:span#article-explanations-description.article-explanations-description
     "Open or close help, code, and equations. Choices for code and equations apply across this article namespace."]
    [:span#article-explanations-status.article-explanations-status
     {:aria-live "polite"}
     "Help is hidden."]]
   [:div#article-help-action.article-reading-action
    [:button#article-explanations-toggle.article-explanations-toggle
     {:type "button"
      :aria-pressed "false"
      :aria-describedby "article-explanations-description article-explanations-status"}
     "Show all help"]]
   [:div#article-code-action.article-reading-action
    [:button#article-code-toggle.article-code-toggle
     {:type "button"
      :aria-pressed "false"
      :aria-describedby "article-explanations-description article-code-status"}
     "Show all code"]
    [:span#article-code-status.article-code-status
     {:aria-live "polite"}
     "Code is hidden."]]
   [:div#article-equations-action.article-reading-action
    [:button#article-equations-toggle.article-equations-toggle
     {:type "button"
      :aria-pressed "true"
      :aria-describedby "article-explanations-description article-equations-status"}
     "Hide all equations"]
    [:span#article-equations-status.article-equations-status
     {:aria-live "polite"}
     "Equations are shown."]]])

(def status-labels
  {:published "published"
   :research-target "current research target"
   :not-promoted "published; not promoted"
   :deferred "deferred"})

(defn- json-escape [text]
  (-> (str text)
      (str/replace "\\" "\\\\")
      (str/replace "\"" "\\\"")
      (str/replace "\n" "\\n")
      (str/replace "\r" "\\r")
      (str/replace "\t" "\\t")
      (str/replace "<" "\\u003c")))

(declare json-value)

(defn- json-object [value]
  (str "{"
       (str/join
        ","
        (map (fn [[key item]]
               (str (json-value (name key)) ":" (json-value item)))
             value))
       "}"))

(defn- json-value [value]
  (cond
    (map? value) (json-object value)
    (sequential? value) (str "[" (str/join "," (map json-value value)) "]")
    (keyword? value) (json-value (name value))
    (string? value) (str "\"" (json-escape value) "\"")
    (number? value) (str value)
    (true? value) "true"
    (false? value) "false"
    (nil? value) "null"
    :else (json-value (str value))))

(defn- article-link [{:keys [url title number]} relationship]
  (when url
    [:a.series-adjacent-link
     (cond-> {:href url}
       relationship (assoc :rel relationship))
     [:span.series-adjacent-direction
      (case relationship
        "prev" "Previous"
        "next" "Next"
        "Article")]
     [:span.series-adjacent-title (str number ". " title)]]))

(defn- roadmap [article-id]
  [:details.series-roadmap
   [:summary "Open the five-article series and future roadmap"]
   [:div.series-roadmap-body
    [:ol.series-opening-list
     (for [{:keys [id number title url status]} series-manifest]
       [:li
        [:a (cond-> {:href url}
              (= id article-id) (assoc :aria-current "page"))
         (str number ". " title)]
        [:span.series-item-status (get status-labels status (name status))]])]
    [:h3 "After the opening five"]
    [:p "These questions remain deliberately unnumbered until their research decisions are ready."]
    [:ul.series-future-list
     (for [{:keys [id title status]} series-roadmap]
       [:li
        title
        [:span.series-item-status (get status-labels status (name status))]])]]])

(defn- series-navigation [article-id]
  (let [{:keys [previous current next]} (series-context article-id)]
    [:nav.series-navigation {:aria-label "Vocabulary-estimation article series"}
     [:div.series-navigation-links
      (or (article-link previous "prev")
          [:span.series-adjacent-placeholder {:aria-hidden "true"}])
      [:span.series-current-position {:aria-current "page"}
       (str "Article " (:number current) " of " (count series-manifest))]
      (or (article-link next "next")
          [:span.series-adjacent-placeholder {:aria-hidden "true"}])]
     (roadmap article-id)]))

(defn- contents-list [sections]
  [:ol.article-contents-list
   (for [{:keys [id label technical]} sections]
     [:li {:class (when technical "article-contents-technical")}
      [:a {:href (str "#" id)
           :data-section-id id}
       label]
      (when technical
        [:span.article-contents-kind "technical"])])])

(defn- active-contents [sections]
  [:div.article-active-contents-shell
   [:button#article-contents-open.article-contents-open
    {:type "button"
     :aria-expanded "false"
     :aria-controls "article-contents-drawer"}
    "Contents"]
   [:nav.article-active-contents
    {:aria-labelledby "article-contents-heading"}
    [:h2#article-contents-heading "In this article"]
    (contents-list sections)]
   [:div#article-contents-backdrop.article-contents-backdrop {:hidden true}]
   [:aside#article-contents-drawer.article-contents-drawer
    {:aria-hidden "true"
     :aria-modal "true"
     :aria-labelledby "article-contents-drawer-heading"
     :role "dialog"
     :hidden true
     :tabindex "-1"}
    [:div.article-contents-drawer-header
     [:h2#article-contents-drawer-heading "In this article"]
     [:button#article-contents-close.article-contents-close
      {:type "button" :aria-label "Close article contents"}
      "Close"]]
    [:nav {:aria-label "Article contents drawer"}
     (contents-list sections)]]])

(defn- configured-install
  [{:keys [article-id sections technical-sections]}]
  (let [all-sections
        (vec (concat (map #(assoc % :technical false) sections)
                     (map #(assoc % :technical true) technical-sections)))
        section-ids (mapv :id all-sections)]
    (series-context article-id)
    (when (or (empty? all-sections)
              (some str/blank? section-ids)
              (not= (count section-ids) (count (distinct section-ids))))
      (throw (ex-info "Article sections require unique, non-blank ids."
                      {:sections all-sections})))
    (kind/hiccup
     [:div.article-controls-installation
      [:style (resource-text "article_controls.css")]
      (series-navigation article-id)
      (active-contents all-sections)
      (toolbar)
      [:script
       {:id "article-controls-config"
        :type "application/json"}
       (json-value
        {:articleId (name article-id)
         :sections (mapv #(select-keys % [:id :label :technical])
                         all-sections)})]
      [:script {:type "application/x-scittle"
                :src "article_controls.cljs"}]])))

(defn install
  "Install article controls. The zero-argument form preserves the migration
  shell; the configuration form adds the canonical series and contents UI."
  ([]
   (kind/hiccup
    [:div.article-controls-installation
     [:style (resource-text "article_controls.css")]
     (toolbar)
     [:script {:type "application/x-scittle"
               :src "article_controls.cljs"}]]))
  ([configuration]
   (configured-install configuration)))
