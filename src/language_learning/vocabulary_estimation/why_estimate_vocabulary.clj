^{:kindly/hide-code true
  :kindly/options
  {:html/deps
   [{:js ["https://cdn.jsdelivr.net/npm/scittle@0.7.23/dist/scittle.js"]}]}
  :clay {:hide-info-line true
         :title "Why Estimate Vocabulary?"
         :quarto {:author :jamiep
                  :description "How a learner-facing Polish vocabulary estimate depends on explicit choices from the target pool through the final uncertainty range."
                  :type :post
                  :date "2026-07-17"
                  :image "lexibench_captures/lexibench-2026-07-17-published-scoring-example.jpg"
                  :image-alt "Published LexiBench example of an estimated recognized-Polish-lemma result with a likely range."
                  :category :concepts
                  :tags [:language-learning :vocabulary-estimation :measurement :lexibench]
                  :keywords [:receptive-vocabulary :polish-lemmas :measurement-chain :uncertainty]}}}

(ns language-learning.vocabulary-estimation.why-estimate-vocabulary
  (:require [language-learning.vocabulary-estimation.article-controls :as controls]
            [scicloj.kindly.v4.kind :as kind]))

^:kindly/hide-code
(kind/hiccup
 [:style
  (str
   ":root{--purpose-accent:#1464b5;--purpose-ink:#24313a;--purpose-soft:#e8f2fb;--purpose-warm:#9a4b00;--purpose-warm-soft:#fff0df;--purpose-green:#0f695f;--purpose-green-soft:#e2f4f0;--purpose-muted:#4f5b66}"
   ".quarto-dark{--purpose-accent:#73b7ff;--purpose-ink:#e7eef4;--purpose-soft:#173653;--purpose-warm:#ffc27a;--purpose-warm-soft:#4a2d12;--purpose-green:#64d8c7;--purpose-green-soft:#163d38;--purpose-muted:#b9c7d2}"
   "#title-block-header{padding-top:.75rem}#title-block-header h1{line-height:1.12;overflow-wrap:anywhere}"
   ".purpose-opening{margin:0 0 1.45rem;border-left:5px solid var(--purpose-accent);border-radius:.45rem;padding:clamp(1rem,3vw,1.4rem);background:color-mix(in srgb,var(--bs-body-bg,#fff) 87%,var(--purpose-accent) 13%);font-size:1.08rem;line-height:1.65}.purpose-opening p:last-child{margin-bottom:0}"
   ".purpose-capture{min-width:0;margin:1.35rem 0;border:1px solid var(--bs-border-color,#ced4da);border-radius:.65rem;padding:clamp(.6rem,2vw,1rem);background:var(--bs-body-bg,#fff)}.purpose-capture img{display:block;width:100%;height:auto;border-radius:.35rem}.purpose-capture figcaption{margin:.75rem .15rem .1rem;color:var(--purpose-muted);font-size:.9rem;line-height:1.5}"
   ".purpose-annotation{display:block;margin-top:.35rem;color:var(--bs-body-color,#212529);font-weight:650}"
   ".purpose-question-grid,.purpose-chain{display:grid;grid-template-columns:repeat(auto-fit,minmax(min(100%,13rem),1fr));gap:.75rem;margin:1.2rem 0}"
   ".purpose-card,.purpose-chain-step{min-width:0;border:1px solid var(--bs-border-color,#dee2e6);border-radius:.55rem;padding:.85rem;background:var(--bs-body-bg,#fff);overflow-wrap:anywhere}.purpose-card h3,.purpose-chain-step h3{margin:0 0 .35rem;font-size:1rem;color:var(--purpose-accent)}.purpose-card p:last-child,.purpose-chain-step p:last-child{margin-bottom:0}"
   ".purpose-chain{counter-reset:purpose-step}.purpose-chain-step{counter-increment:purpose-step;border-top:4px solid var(--purpose-accent)}.purpose-chain-step::before{content:counter(purpose-step);display:grid;place-items:center;width:1.6rem;height:1.6rem;margin-bottom:.45rem;border-radius:50%;background:var(--purpose-accent);color:#fff;font-weight:800}"
   ".purpose-callout{margin:1.25rem 0;border:1px solid color-mix(in srgb,var(--purpose-accent) 45%,var(--bs-border-color,#dee2e6));border-left:4px solid var(--purpose-accent);border-radius:.45rem;padding:1rem 1.1rem;background:color-mix(in srgb,var(--bs-body-bg,#fff) 91%,var(--purpose-accent) 9%)}.purpose-callout.warm{border-color:color-mix(in srgb,var(--purpose-warm) 55%,var(--bs-border-color,#dee2e6));border-left-color:var(--purpose-warm);background:color-mix(in srgb,var(--bs-body-bg,#fff) 91%,var(--purpose-warm) 9%)}.purpose-callout.green{border-color:color-mix(in srgb,var(--purpose-green) 55%,var(--bs-border-color,#dee2e6));border-left-color:var(--purpose-green);background:color-mix(in srgb,var(--bs-body-bg,#fff) 91%,var(--purpose-green) 9%)}.purpose-callout strong{display:block;margin-bottom:.3rem}.purpose-callout p:last-child{margin-bottom:0}"
   ".purpose-status-table{width:100%;border-collapse:collapse;margin:1.15rem 0}.purpose-status-table th,.purpose-status-table td{min-width:0;padding:.7rem;border-bottom:1px solid var(--bs-border-color,#dee2e6);text-align:left;vertical-align:top;overflow-wrap:anywhere}.purpose-status-table thead th{border-bottom:2px solid var(--bs-border-color,#adb5bd)}"
   ".purpose-technical{margin:1.2rem 0;border:1px solid var(--bs-border-color,#ced4da);border-radius:.55rem;background:var(--bs-body-bg,#fff)}.purpose-technical summary{padding:.75rem 1rem;color:var(--purpose-accent);font-weight:750;cursor:pointer}.purpose-technical-body{padding:0 1rem 1rem}.purpose-technical-body p:last-child{margin-bottom:0}"
   ".purpose-source-note{font-size:.88rem;color:var(--purpose-muted)}"
   "@media(max-width:575px){.purpose-status-table{display:block;max-width:100%;overflow-x:auto}.purpose-card,.purpose-chain-step{padding:.75rem}}")])

^:kindly/hide-code
(controls/install
 {:article-id :purpose
  :sections [{:id "learner-question" :label "The learner's question"}
             {:id "what-item-hides" :label "What one item hides"}
             {:id "pair-stepping-stone" :label "Why count pairs first"}
             {:id "measurement-chain" :label "The complete measurement chain"}
             {:id "three-scorers" :label "Three scorers, three statuses"}
             {:id "learner-destination" :label "The learner-facing destination"}]
  :technical-sections [{:id "technical-boundaries" :label "Technical boundaries"}
                       {:id "capture-provenance" :label "Capture provenance"}]})

;; BEGIN ALWAYS-VISIBLE
;; A vocabulary result looks simple: a number, perhaps a level, and a range.
;; Yet the number answers a chain of questions that most learners should never
;; have to formulate. What counts as one vocabulary unit? Which Polish words
;; belong in the possible total? What does a correct multiple-choice response
;; reveal? How should a short sample stand in for thousands of unasked items?
;; How certain should the result appear? This series exists because every link
;; in that chain changes what the final number means.
;;
;; The practical destination is easy to state. I want LexiBench to give a
;; learner a useful estimate of **receptive Polish lemmas**: dictionary
;; headwords they are likely to recognise when encountered in meaningful
;; context. The route is harder. A trustworthy estimate needs explicit
;; definitions, versioned data, inspectable items, an inference procedure, and
;; evidence that the whole measurement behaves reasonably for different
;; learners. The current research deliberately starts with a narrower quantity
;; that can be defined and checked more cleanly: receptive knowledge of
;; **lemma–surface-form pairs** in a fixed, versioned pool.
;;
;; Put differently, the destination is **estimated receptive Polish lemmas**,
;; while pair knowledge is the first deliberately limited research target. That
;; separation keeps the learner's purpose visible without pretending the hard
;; linguistic bridge has already been built. It also lets each later article
;; introduce one necessary decision when the reader has a concrete reason to
;; care about it.

^:kindly/hide-code
(kind/hiccup
 [:div.purpose-opening
  [:p "The number is the last step, not the product. The product is a defensible connection between a learner's answers and a clearly named quantity, with uncertainty and limitations visible."]])

;; ## The learner's question {#learner-question}
;;
;; A learner does not arrive asking for an estimand, a sampling design, or a
;; posterior distribution. They ask something like, “How much Polish can I
;; understand?” LexiBench narrows that broad question to passive vocabulary:
;; recognising Polish word meanings in sentence context. The production start
;; screen explains that receptive ability in ordinary language, shows the test
;; format, and asks for a starting level. That level is a routing hint for the
;; first questions, not the final answer and not proof that a learner belongs
;; to a CEFR category.

^:kindly/hide-code
(kind/hiccup
 [:figure.purpose-capture
  [:img
   {:src "lexibench_captures/lexibench-2026-07-17-starting-level.jpg"
    :alt "LexiBench start screen explaining passive vocabulary and offering seven starting-level choices before an 80-item test."}]
  [:figcaption
   [:strong "Production start screen, captured 17 July 2026. "]
   "The start screen defines passive vocabulary, asks for a starting level from pre-A1 through C2, and says the test has 80 sentence-context items."
   [:span.purpose-annotation "The important annotation is conceptual: self-reported level selects where the experience begins; it does not become the vocabulary estimate."]]])

;; Three layers are already visible. First is the **learner goal**: recognise
;; meaning while reading or listening. Second is the **task**: choose the
;; highlighted form’s English meaning inside a Polish sentence. Third is an
;; **operational choice**: use a starting-level answer to avoid beginning in an
;; obviously unhelpful region. None of those layers alone specifies a scorer.
;; The scorer still needs to say which population the questions represent and
;; how evidence from answered items becomes a statement about unasked ones.
;;
;; ## What one item hides {#what-item-hides}
;;
;; The next screen feels equally direct. A sentence contains one highlighted
;; Polish form. The learner selects one English meaning or explicitly says
;; “don't know.” Before anyone answers, however, a complete item has already
;; embodied many decisions: the target lemma, the exact surface form, the
;; intended meaning in this context, the sentence, its translation, the
;; correct option, the distractors, and the version of every component.

^:kindly/hide-code
(kind/hiccup
 [:figure.purpose-capture
  [:img
   {:src "lexibench_captures/lexibench-2026-07-17-unanswered-sentence-item.jpg"
    :alt "Unanswered LexiBench sentence item at zero of eighty, with one highlighted Polish form, five English choices, and a don't-know option."}]
  [:figcaption
   [:strong "Live production item, captured unanswered on 17 July 2026. "]
   "Before any response, the live item shows progress at 0/80, asks for the highlighted Polish form’s meaning in its sentence, offers five translations, and keeps a separate don't-know response."
   [:span.purpose-annotation "No answer was submitted for this capture. A screenshot can show the experience; it cannot establish that the item is unbiased, well calibrated, or equally informative for every learner."]]])

;; A correct response is evidence, not a perfect observation of knowledge. A
;; learner can guess. They can slip despite knowing the word. A weak distractor
;; can make an item too easy; an ambiguous translation can make it unfair. A
;; sentence can disambiguate a useful meaning or accidentally supply the
;; answer. “Don't know” is valuable evidence and should remain distinct from a
;; confidently chosen wrong answer in the stored event, even if a particular
;; proposal later combines both outcomes for one calculation.

^:kindly/hide-code
(kind/hiccup
 [:div.purpose-question-grid
  [:section.purpose-card
   [:h3 "Language unit"]
   [:p "Which lemma and which written or spoken form does the item represent?"]]
  [:section.purpose-card
   [:h3 "Meaning"]
   [:p "Which interpretation does the sentence intend, and is the translation defensible?"]]
  [:section.purpose-card
   [:h3 "Alternatives"]
   [:p "Do distractors reveal recognition without rewarding elimination tricks?"]]
  [:section.purpose-card
   [:h3 "Evidence"]
   [:p "How do correct, wrong, and don't-know responses change uncertainty?"]]])

;; ## Why count pairs first {#pair-stepping-stone}
;;
;; The eventual learner-facing unit is a lemma, but Polish lemmas appear through
;; inflected surface forms. Recognising one common form does not automatically
;; prove recognition of every case, number, gender, tense, person, or participle
;; associated with the headword. Conversely, requiring every theoretically
;; possible form would confuse vocabulary knowledge with exhaustive morphology
;; and would over-weight rare forms. Context and meaning complicate the link
;; further.
;;
;; That is why the first research estimand is deliberately modest: **receptive
;; knowledge of lemma–surface-form pairs in a fixed, versioned pool**. A pair is
;; more precise than “a word.” It lets an item point to a stable lemma identifier
;; and a stable form identifier. A fixed pool supplies a denominator. Versioning
;; means a changed inclusion rule or repaired item creates a new claim rather
;; than silently changing the meaning of an old result.
;;
;; The pair count is a stepping stone, not the promised destination. It helps
;; isolate the sampling and inference problem: given a declared population of
;; pairs and a small set of responses, how many pairs might this learner
;; recognise? Once that layer is credible, later research can ask how evidence
;; across correlated forms supports a latent statement about lemma knowledge.
;; Jumping directly to lemma totals would hide that unresolved bridge inside a
;; seemingly friendly number.

^:kindly/hide-code
(kind/hiccup
 [:div.purpose-callout.warm
  [:strong "A synthetic pool is not the production inventory"]
  [:p "Proposal 1 and Proposal 2 use a synthetic 8,000-pair pool to make model behaviour executable and comparable. It is not the live LexiBench lemma inventory, not a CEFR inventory, and not evidence that Polish has exactly 8,000 relevant pairs."]])

;; ## The complete measurement chain {#measurement-chain}
;;
;; A short test can be useful only when the whole chain is visible enough to
;; challenge. Optimising the final formula while leaving the pool or items
;; vague would merely make an undefined construct faster. The direction of
;; travel is the shortest test that meets broad quality standards: it should
;; measure the intended construct, limit avoidable bias and error, report
;; calibrated uncertainty, and remain robust across relevant learner and item
;; groups. Length is optimised only among candidates that pass those standards.

^:kindly/hide-code
(kind/hiccup
 [:div.purpose-chain
  [:section.purpose-chain-step
   [:h3 "Construct and version the pool"]
   [:p "Define which lemma–form pairs can be counted, their identifiers, inclusion rules, frequencies, and immutable version."]]
  [:section.purpose-chain-step
   [:h3 "Select administered items"]
   [:p "Sample broadly and reproducibly while controlling repeats, exposure, and learner burden."]]
  [:section.purpose-chain-step
   [:h3 "Design each complete item"]
   [:p "Version the context, intended meaning, translation, answer, distractors, and links to the target pair."]]
  [:section.purpose-chain-step
   [:h3 "Pilot and calibrate"]
   [:p "Use representative learner evidence to diagnose ambiguity, bias, difficulty, guessing, slips, and weak distractors."]]
  [:section.purpose-chain-step
   [:h3 "Infer knowledge and uncertainty"]
   [:p "Connect stored responses to the declared pool with a versioned scorer and an honest uncertainty statement."]]
  [:section.purpose-chain-step
   [:h3 "Validate the interpretation"]
   [:p "Check whether results support their intended use across groups, versions, and realistic operating conditions."]]])

;; These stages depend on one another. Pool frequency can help spread a sample,
;; but frequency is not measured learner difficulty. A statistically neat
;; interval does not repair an ambiguous item. Passing software tests proves
;; that code behaves as specified; it does not prove that the specification
;; measures vocabulary. A polished published page proves neither. Model,
;; software, and publication checks therefore remain separate lanes.
;;
;; ## Three scorers, three statuses {#three-scorers}
;;
;; The production interface and the research proposals must not be blended.
;; The **Current LexiBench scorer** estimates recognised Polish lemmas from the
;; live 10,000-lemma inventory and exposes a likely range. It is the scorer
;; behind the current product shown here. This article documents its learner
;; purpose and published interface, not its formula.
;;
;; **Proposal 1**, exact algorithm ID `stratified-beta-binomial-v1`, is the
;; current research implementation target in this public workflow. It divides
;; a synthetic pair pool into broad frequency strata, samples non-adaptively,
;; updates uncertainty from responses, predicts knowledge in untested pairs,
;; and recommends stopping at complete rounds. The worked example estimates
;; 4,334 known pairs. That result belongs to the synthetic fixture, not to a
;; production learner or inventory.
;;
;; **Proposal 2**, exact algorithm ID
;; `continuous-pair-frequency-logistic-v2`, is a preserved experimental
;; checkpoint. It asked whether a continuous frequency curve could improve
;; accuracy and shorten tests. It improved some aggregate measures but failed
;; precommitted worst-group and median-length gates, so it was not promoted.
;; Retaining the candidate and its evidence is part of the method: an honest
;; rejection should remain reproducible instead of disappearing.

^:kindly/hide-code
(kind/hiccup
 [:table.purpose-status-table
  [:caption "What each visible or published scorer means"]
  [:thead
   [:tr
    [:th {:scope "col"} "Name"]
    [:th {:scope "col"} "Quantity"]
    [:th {:scope "col"} "Status"]]]
  [:tbody
   [:tr
    [:th {:scope "row"} "Current LexiBench scorer"]
    [:td "Estimated recognised Polish lemmas"]
    [:td "Deployed in the current product"]]
   [:tr
    [:th {:scope "row"} "Proposal 1 · stratified-beta-binomial-v1"]
    [:td "Known lemma–surface-form pairs in a fixed synthetic pool"]
    [:td "Current research target; not deployed"]]
   [:tr
    [:th {:scope "row"} "Proposal 2 · continuous-pair-frequency-logistic-v2"]
    [:td "Known lemma–surface-form pairs in the same synthetic study"]
    [:td "Not promoted; not deployed"]]]])

;; **Neither proposal is deployed.** Calling Proposal 1 “current” means current
;; within this research programme, not current in LexiBench. Calling Proposal 2
;; “published” means its article, code, and evidence are inspectable, not that
;; its model powers the test. The distinction matters because production
;; screenshots can otherwise make a research formula look operational, while
;; an executable research article can make a synthetic example look like real
;; learner evidence.
;;
;; ## The learner-facing destination {#learner-destination}
;;
;; The production scoring example shows why the longer chain is worth the
;; trouble. A useful result names the unit, gives a centre estimate, and shows a
;; range rather than claiming exact knowledge. It can also explain which parts
;; of the inventory were directly observed and which were inferred. That is far
;; more informative than a bare “words known” number.

^:kindly/hide-code
(kind/hiccup
 [:figure.purpose-capture
  [:img
   {:src "lexibench_captures/lexibench-2026-07-17-published-scoring-example.jpg"
    :alt "Published LexiBench scoring example showing a live and final estimate of about 1,450 recognized Polish lemmas with a likely range of 1,050 to 1,900."}]
  [:figcaption
   [:strong "Published current-product example, captured 17 July 2026. "]
   "The published example reports about 1,450 recognized Polish lemmas, shows 1,050–1,900 as the likely range, and labels the result approximate rather than exact."
   [:span.purpose-annotation "This is a static example on LexiBench's scoring documentation page, not a fabricated completed test and not a result produced by either research proposal."]]])

;; The eventual research question is how to support an estimate of **estimated
;; receptive Polish lemmas** with evidence gathered from particular forms and
;; contexts. This series does not pretend that question is solved. It first
;; teaches the workflow, then the purpose, then the probability ideas needed to
;; reason from a sample. Proposal 1 demonstrates a transparent pair-count
;; model. Proposal 2 records a more ambitious candidate and why its evidence
;; was insufficient for promotion.
;;
;; Several decisions remain explicitly deferred. We have not decided the rule
;; that converts correlated pair evidence into latent lemma knowledge. We have
;; not finished representative item calibration or the detailed sampling rules
;; for how many forms and contexts each lemma needs. We have not earned an
;; adaptive selector: adaptation should follow calibrated items and exposure
;; controls, not treat raw frequency as difficulty. We have not resolved when
;; multiple senses or contexts become separately identifiable. Those are not
;; footnotes to hide; they are the research roadmap.

^:kindly/hide-code
(kind/hiccup
 [:div.purpose-callout.green
  [:strong "The standard for progress"]
  [:p "Compare versioned candidates with clear baselines; distinguish simulations from representative learner evidence; preserve failed checkpoints; and claim only what the evidence supports. “Most accurate possible” is a direction, not a claim that one globally optimal vocabulary test has been found."]])

;; ## Technical boundaries {#technical-boundaries}
;;
;; This orientation adds no new statistical derivation and runs no simulation.
;; Its technical role is to name the interfaces between later articles. The
;; exact research estimand is pair knowledge in a declared pool. Raw response
;; events preserve correct, wrong, and don't-know outcomes. Inputs, selectors,
;; algorithms, random seeds, and outputs are versioned for replay. Proposal 1
;; and Proposal 2 remain historical research objects with their existing
;; contracts and evidence unchanged.
;;
;; ## Capture provenance {#capture-provenance}
;;
;; The three images are dated, read-only captures of the production site. The
;; start and question screens show current interactive states; the score is the
;; site's published static example. No fabricated 80-answer production session
;; was submitted. Source URL, recapture state, capture date, product status,
;; alternative text, prose equivalent, media type, and SHA-256 digest are stored
;; beside the immutable image files in
;; `lexibench-captures-2026-07-17.edn`. A later UI change should produce a new
;; dated capture set, not overwrite this evidence.
;; END ALWAYS-VISIBLE

^:kindly/hide-code
(kind/hiccup
 [:details.purpose-technical
  [:summary "Source and reproducibility links"]
  [:div.purpose-technical-body
   [:p "The reader-facing boundaries above are backed by the versioned implementation target, executable articles, tests, and preserved evidence in the public workflow."]
   [:ul
    [:li [:a {:href "https://github.com/jamiepratt/theory-to-algorithm-workflow/blob/master/docs/language-learning/vocabulary-estimation/current-scoring-algorithm.md"}
          "Proposal 1 scoring contract"]]
    [:li [:a {:href "beta_binomial_first_pass.html"}
          "Proposal 1 executable article"]]
    [:li [:a {:href "pair_frequency_logistic_v2_article.html"}
          "Proposal 2 experiment and non-promotion decision"]]
    [:li [:a {:href "https://lexibench.com/#/current/scoring"}
          "Current LexiBench scoring explanation"]]]
   [:p.purpose-source-note "Technical disclosure preserves provenance without turning this orientation into another derivation or simulation."]]])
