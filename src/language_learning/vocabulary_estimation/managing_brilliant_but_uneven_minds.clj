^{:kindly/hide-code true
  :kindly/options
  {:html/deps
   [{:js ["https://cdn.jsdelivr.net/npm/scittle@0.7.23/dist/scittle.js"]}]}
  :clay {:hide-info-line true
         :title "Managing Brilliant but Uneven Minds: My Theory-to-Algorithm Workflow"
         :quarto {:author :jamiep
                  :description "A personal account of managing fast, uneven AI agents through explicit decisions, executable work, independent validation, and retained human responsibility."
                  :type :post
                  :date "2026-07-15"
                  :image "managing_brilliant_but_uneven_minds_preview.png"
                  :image-alt "Theory-to-algorithm research cycle above separate model, software, and publication validation cards."
                  :category :concepts
                  :tags [:ai :research-workflow :language-learning :clojure]
                  :keywords [:coding-agents :theory-to-algorithm :executable-research :model-validation]}}}

(ns language-learning.vocabulary-estimation.managing-brilliant-but-uneven-minds
  (:require [language-learning.vocabulary-estimation.article-controls :as controls]
            [language-learning.vocabulary-estimation.math-explanations :as math]
            [scicloj.kindly.v4.kind :as kind]))

^:kindly/hide-code
(kind/hiccup
 [:style
  (str
   ":root{--mw-accent:#1464b5;--mw-warm:#9a4b00;--mw-success:#0f695f;--mw-muted:#4f5b66}"
   ".quarto-dark{--mw-accent:#73b7ff;--mw-warm:#ffc27a;--mw-success:#64d8c7;--mw-muted:#b9c7d2}"
   "#title-block-header{padding-top:.75rem}#title-block-header h1{line-height:1.15;overflow-wrap:anywhere}"
   ".mw-opening{border-left:5px solid var(--mw-accent);border-radius:.45rem;padding:clamp(1rem,3vw,1.4rem);margin:0 0 1.5rem;background:color-mix(in srgb,var(--bs-body-bg,#fff) 87%,var(--mw-accent) 13%);font-size:1.08rem;line-height:1.65}.mw-opening p:last-child{margin-bottom:0}"
   ".mw-grid,.mw-definition-grid{display:grid;grid-template-columns:repeat(auto-fit,minmax(min(100%,15rem),1fr));gap:.85rem;margin:1.25rem 0}"
   ".mw-card,.mw-definition{min-width:0;border:1px solid var(--bs-border-color,#dee2e6);border-radius:.55rem;padding:1rem;background:var(--bs-body-bg,#fff);color:var(--bs-body-color,#212529);overflow-wrap:anywhere}.mw-card h3{font-size:1rem;margin:0 0 .45rem;color:var(--mw-accent)}.mw-card p:last-child,.mw-card ul:last-child{margin-bottom:0}"
   ".mw-definition dt{font-weight:800;color:var(--mw-accent)}.mw-definition dd{margin:.25rem 0 0}"
   ".mw-callout{border:1px solid color-mix(in srgb,var(--mw-accent) 45%,var(--bs-border-color,#dee2e6));border-left:4px solid var(--mw-accent);background:color-mix(in srgb,var(--bs-body-bg,#fff) 91%,var(--mw-accent) 9%);padding:1rem 1.15rem;margin:1.35rem 0;border-radius:.4rem}.mw-callout.warm{border-color:color-mix(in srgb,var(--mw-warm) 55%,var(--bs-border-color,#dee2e6));border-left-color:var(--mw-warm);background:color-mix(in srgb,var(--bs-body-bg,#fff) 91%,var(--mw-warm) 9%)}.mw-callout.success{border-color:color-mix(in srgb,var(--mw-success) 55%,var(--bs-border-color,#dee2e6));border-left-color:var(--mw-success);background:color-mix(in srgb,var(--bs-body-bg,#fff) 91%,var(--mw-success) 9%)}.mw-callout strong{display:block;margin-bottom:.3rem}.mw-callout p:last-child{margin-bottom:0}"
   ".mw-table-wrap{max-width:100%;overflow-x:auto;margin:1.25rem 0}.mw-table{width:100%;border-collapse:collapse}.mw-table th,.mw-table td{padding:.65rem .75rem;border-bottom:1px solid var(--bs-border-color,#dee2e6);text-align:left;vertical-align:top;min-width:8rem}.mw-table thead th{border-bottom:2px solid var(--bs-border-color,#adb5bd)}.mw-table code{overflow-wrap:anywhere;white-space:normal}"
   ".mw-code{max-width:100%;overflow-x:auto;border:1px solid var(--bs-border-color,#dee2e6);border-radius:.45rem;padding:.75rem;background:color-mix(in srgb,var(--bs-body-bg,#fff) 94%,var(--mw-accent) 6%)}"
   ".mw-cycle-caption,.mw-note{font-size:.9rem;color:var(--mw-muted)}.mw-cycle-caption{text-align:center}"
   "@media(max-width:575px){.mw-table th,.mw-table td{padding:.5rem}.mw-card{padding:.8rem}}")])

^:kindly/hide-code
(controls/install
 {:article-id :workflow
  :sections [{:id "management-job" :label "The management job"}
             {:id "changed-cost-curve" :label "A changed cost curve"}
             {:id "decisions-before-delegation" :label "Decide before delegating"}
             {:id "theory-to-algorithm-cycle" :label "The theory-to-algorithm cycle"}
             {:id "independent-validation" :label "Independent validation"}
             {:id "repository-boundary" :label "Repository boundary"}
             {:id "human-ownership" :label "What the human still owns"}
             {:id "practical-influences" :label "Practical influences"}]
  :technical-sections [{:id "sources" :label "Sources and further reading"}]})

^:kindly/hide-code
(kind/hiccup
 [:div.mw-opening
  [:p "I am genuinely excited. I now have brilliant but profoundly uneven minds at my command: a natural-language interface to the digital world and a vast body of online knowledge. I can call on ten agents concurrently and watch them work at superhuman speed on some narrow tasks. Their range and stamina are extraordinary. Their judgment, context, and accountability are not. My new craft is learning to manage those strengths and weaknesses so that the result is good code and good knowledge work—not AI slop."]])

^:kindly/hide-code
(kind/hiccup
 [:ol.article-chapter-map
  [:li [:strong "Decide"] [:br] "Name the outcome, authority, and acceptable risk."]
  [:li [:strong "Delegate"] [:br] "Give agents bounded, inspectable work."]
  [:li [:strong "Execute"] [:br] "Turn claims into code, examples, and evidence."]
  [:li [:strong "Validate"] [:br] "Keep workflow, model, software, and publication checks distinct."]
  [:li [:strong "Preserve"] [:br] "Version assumptions, failures, decisions, and rollback points."]
  [:li [:strong "Own"] [:br] "Retain human responsibility for purpose and consequences."]])

;; ## The management job {#management-job}
;;
;; Calling these systems “minds” is deliberately provocative, but it is not a
;; claim that they are people. It describes the experience of delegating a
;; messy intellectual task, receiving a surprising interpretation, challenging
;; it, and watching another attempt arrive in seconds. Natural language is the
;; interface, but useful agents are not confined to chat. They can search, read
;; a repository, run programs, inspect a browser, edit an article, and compare
;; the result with an explicit test.
;;
;; “Superhuman” also needs a boundary. An agent can transform text, search a
;; codebase, or try variations far faster than I can. It does not thereby gain
;; a superhuman understanding of my users, the consequences of a wrong
;; decision, or the scientific status of a claim. Ten agents can make ten fast,
;; plausible mistakes. Parallelism multiplies a management system; it does not
;; create one.

^:kindly/hide-code
(kind/hiccup
 [:div.mw-grid
  [:section.mw-card
   [:h3 "What the agents are unusually good at"]
   [:ul
    [:li "Rapid search and synthesis across code, documents, and tools."]
    [:li "Turning a clear specification into several concrete alternatives."]
    [:li "Writing, running, and revising code in short feedback loops."]
    [:li "Replaying routine checks without fatigue."]]]
  [:section.mw-card
   [:h3 "What remains dangerously uneven"]
   [:ul
    [:li "Filling a gap with a confident, plausible invention."]
    [:li "Optimising the task it heard instead of the outcome I meant."]
    [:li "Treating a recent source as established authority."]
    [:li "Announcing completion before the whole result is checked."]]]
  [:section.mw-card
   [:h3 "What the human manager must own"]
   [:ul
    [:li "Purpose, users, definitions, priorities, and acceptable risk."]
    [:li "Which sources and evidence deserve authority."]
    [:li "Permission boundaries and irreversible decisions."]
    [:li "Independent review and the final claim that work is good enough."]]]])

;; The “junior developer” analogy is useful only if it changes my behaviour. I
;; do not hand a junior colleague one vague sentence, merge whatever appears,
;; and blame the colleague. I explain the outcome, let questions expose
;; ambiguity, divide work into inspectable slices, provide tools, and require
;; tests and review. Agents need an even more explicit version of that
;; management because they are extraordinarily productive, do not accumulate
;; responsibility, and can repeat the same mistake in a fresh, fluent form.
;;
;; Established engineering practices therefore matter more, not less. Clear
;; requirements, version control, small changes, executable examples,
;; test-first regression work, peer review, least privilege, and rollback turn
;; fallible effort into dependable output. An LLM can help perform nearly every
;; step. It cannot be the reason that a step is trusted.
;;
;; ## A changed cost curve {#changed-cost-curve}
;;
;; I have not yet read Clayton M. Christensen's
;; [*The Innovator's Dilemma*](https://store.hbr.org/product/the-innovator-s-dilemma-with-a-new-foreword-when-new-technologies-cause-great-firms-to-fail/10706).
;; What follows is a question prompted by publisher summaries, Christensen's
;; historical influence, and later scholarship—not my account of an unread
;; book. The idea I find useful is that sensible organisations can optimise for
;; the qualities their established customers value and still miss an initially
;; weaker offering built around a different cost, convenience, or value curve.
;;
;; Applying that idea to agents is my inference, not a claim that Christensen
;; predicted coding agents. These systems remain worse than skilled humans on
;; many traditional measures of professional work, while radically changing
;; the speed, price, accessibility, and availability of intellectual labour.
;; The question is not whether an agent can reproduce an expert's entire old
;; job unaided. It is which units of work and organisational habits change when
;; competent output becomes abundant, conversational, and uneven—and when
;; specification, judgment, validation, and responsibility become the scarce
;; resources.
;;
;; The book's influence is not in doubt: Harvard Business Review Press records
;; its bestseller reception, and Harvard Business School records its 1997
;; Global Business Book Award and Christensen's role in putting disruptive
;; innovation into managerial vocabulary. Influence is not settled science.
;; Later research has refined the theory, disputed parts of it, and documented
;; its misuse. I want a framework that sharpens questions I can test, not a
;; prestigious label that excuses me from evidence.
;;
;; ## Decide before delegating {#decisions-before-delegation}
;;
;; The expensive part of agent management is often deciding what the work
;; should mean. Before implementation I must name the user outcome, translate
;; an informal expectation into an explicit target, choose which sources have
;; authority, state exclusions, and decide what evidence could prove the idea
;; inadequate. If those decisions stay tacit, an agent will make them for me.
;; Its answer may be elegant and internally consistent while solving the wrong
;; problem.
;;
;; My LexiBench vocabulary-estimation work is the concrete case study. The
;; product purpose, learner-facing quantity, complete measurement chain, current
;; research targets, and deferred questions now live in
;; [Article 2, “Why estimate vocabulary?”](why_estimate_vocabulary.html).
;; Article 1 owns how I organise the work; Article 2 owns why the product exists
;; and what it is trying to measure.

^:kindly/hide-code
(kind/hiccup
 [:div.mw-callout.warm
  [:strong "Delegation starts after the important nouns are defined"]
  [:p "A fluent implementation can hide disagreement about the user, target quantity, population, evidence, or acceptable error. I write those decisions down, give them versions, and make later changes visible."]])

;; LLMs are fabulous research tools here. They can map unfamiliar terminology,
;; propose searches, compare papers, challenge an assumption, explain a
;; derivation several ways, and translate a model contract into code. They are
;; discovery and synthesis engines, not scientific authorities. A fluent
;; summary sends me to the primary source; it does not replace it.
;;
;; ## The theory-to-algorithm cycle {#theory-to-algorithm-cycle}
;;
;; The unit of progress is not “ask an AI for the answer.” It is one versioned,
;; explainable refinement. I begin with the smallest defensible model, then
;; change one important assumption at a time. Theory becomes an explicit model
;; contract; the contract becomes executable code; code produces replayable
;; evidence; evidence decides whether a candidate earns promotion. The old
;; model remains runnable. A failed candidate remains evidence instead of
;; disappearing into a rewritten success story.

^:kindly/hide-code
(kind/mermaid
 "flowchart LR
    U[User expectation<br/>as a hypothesis] --> E[Explicit target<br/>and exclusions]
    E --> T[Relevant theory<br/>and assumptions]
    T --> B[Executable<br/>baseline]
    B --> C[One versioned<br/>candidate]
    C --> G[Precommitted<br/>validation gate]
    G --> D{All required<br/>checks pass?}
    D -->|yes| P[Promote with<br/>replay + rollback]
    D -->|no| N[Do not promote;<br/>preserve evidence]
    P --> R[Publish reasoning,<br/>code, evidence, decision]
    N --> R
    R --> L[User interpretation<br/>and feedback]
    L --> U")

^:kindly/hide-code
(kind/hiccup
 [:p.mw-cycle-caption
  "Agents can contribute throughout the cycle. Human responsibility for the target, authority, gate, and decision does not move with the work."])

;; Agents can work on separate, bounded slices, but a coordinator must preserve
;; dependencies and integrate in order. A protocol should exist before the
;; result it will judge. Generated evidence should identify its inputs,
;; algorithm, seed, runtime, and output. A surprising result should send the
;; work back around the cycle, not trigger quiet threshold changes.
;;
;; ## Independent validation {#independent-validation}
;;
;; “Validated” is too vague to be useful. I keep four questions separate:

^:kindly/hide-code
(kind/hiccup
 [:dl.mw-definition-grid
  [:div.mw-definition
   [:dt "Workflow validation"]
   [:dd "Do repository roles, paths, commands, ownership, and publication order resolve from the versioned profile?"]]
  [:div.mw-definition
   [:dt "Model validation"]
   [:dd "Does the candidate earn promotion for the declared target under frozen scenarios and thresholds?"]]
  [:div.mw-definition
   [:dt "Software validation"]
   [:dd "Does the implementation behave as specified, replay deterministically, and agree across supported runtimes?"]]
  [:div.mw-definition
   [:dt "Publication validation"]
   [:dd "Can a reader inspect the claim through working pages, controls, links, labels, layouts, and themes?"]]])

;; Passing one lane never substitutes for another. This distinction has already
;; mattered in the case study. The first research scorer estimates independent
;; knowing rates across eight frequency strata. A second candidate replaced
;; those steps with a continuous pair-frequency curve. It improved aggregate
;; coverage and mean absolute error in its held-out simulations, yet failed
;; precommitted worst-cell coverage, worst-cell error, and test-length checks.
;; The code worked. The article rendered. The model did not earn promotion, so
;; the first version remained the research target.
;;
;; That negative result is the workflow doing its job. Without a frozen gate,
;; a highly productive agent—or an enthusiastic human—could select attractive
;; aggregate numbers, retune after seeing the result, and publish a polished
;; success story. The antidote to AI slop is not merely better prose. It is an
;; evidence structure that makes inconvenient results hard to erase.
;;
;; ## Repository boundary {#repository-boundary}
;;
;; I have published the complete worked example in the
;; [theory-to-algorithm workflow repository](https://github.com/jamiepratt/theory-to-algorithm-workflow).
;; Its identity is deliberately dual: a reusable method plus one complete,
;; inspectable vocabulary-estimation case study. The repository boundary makes
;; authority explicit rather than allowing two histories to claim the same
;; artifacts.

^:kindly/hide-code
(kind/hiccup
 [:div.mw-table-wrap
  [:table.mw-table
   [:caption "Ownership and authority in the public example"]
   [:thead
    [:tr
     [:th {:scope "col"} "Area"]
     [:th {:scope "col"} "Owns"]
     [:th {:scope "col"} "Management purpose"]]]
   [:tbody
    [:tr
     [:th {:scope "row"} "Workflow repository"]
     [:td "Method, decisions, model contract, versioned profile, and reusable skills."]
     [:td "Tell a fresh agent what is authoritative and how this project is organised."]]
    [:tr
     [:th {:scope "row"} "Publication submodule"]
     [:td "Executable articles, model code, fixtures, evidence, tests, and publication inputs."]
     [:td "Pin the exact source and evidence described by the parent workflow history."]]
    [:tr
     [:th {:scope "row"} "GitHub issues and pull requests"]
     [:td "Future work, dependency order, review, and publication checkpoints."]
     [:td "Keep unfinished intentions out of docs that claim to describe shipped reality."]]]]])

;; The publication repository must be committed and published before the parent
;; repository records its submodule pointer. That order is mundane but
;; important: a reader should never receive a pointer to work that does not
;; exist. Clojure, Clay, Quarto, Civitas, and Git submodules implement this case
;; study; none is a requirement of the reusable method.

^:kindly/hide-code
(math/code-detail
 "code-clone-workflow"
 "Cloning and validating the complete workflow"
 [:div
  [:p "Clone both histories, then validate the versioned repository profile before using its commands."]
  [:pre.mw-code
   [:code "git clone --recurse-submodules https://github.com/jamiepratt/theory-to-algorithm-workflow.git\ncd theory-to-algorithm-workflow\npython3 .agents/scripts/research_workflow.py validate"]]])

;; ## What the human still owns {#human-ownership}
;;
;; My agents can propose requirements, draft a protocol, implement code, run a
;; simulation, inspect a page, and criticise one another. I still own the reason
;; for doing the work, the people affected, the definitions that shape the
;; answer, the authority assigned to sources, the permissions granted to tools,
;; and the decision to publish or deploy. I also own the possibility that my
;; framing is wrong.
;;
;; Retained responsibility does not mean manually repeating every agent action.
;; It means arranging the work so that consequential decisions are explicit and
;; evidence reaches me in a reviewable form. I use isolated workspaces, narrow
;; scopes, tests that observe public behaviour, immutable evidence, visual
;; review, staged publication, and rollback. I ask another agent to review when
;; independence helps, but I do not confuse agent disagreement or agreement
;; with human judgment.

^:kindly/hide-code
(kind/hiccup
 [:div.mw-callout.success
  [:strong "My acceptance test"]
  [:p "The standard is not whether an agent produced something astonishingly quickly. It is whether humans and agents together produced something worth trusting—and whether I can explain why."]])

;; ## Two practical influences {#practical-influences}
;;
;; [Matt Pocock](https://www.mattpocock.com/)'s
;; [workflow walkthrough](https://www.youtube.com/watch?v=-QFHIoCo-Ko) treats
;; agent output as engineering work: interrogate a vague request, write down the
;; destination, cut work into vertical slices that create feedback, and use
;; established testing and review practices. The agent is a tremendously
;; productive junior developer, not an oracle or the person accountable for the
;; system.
;;
;; [Kun Chen's agentic-engineering walkthrough](https://www.youtube.com/watch?v=iQyg-KypKAA)
;; takes the captain-and-crew analogy seriously: invest human attention at the
;; beginning in deciding what should be built, delegate implementation across
;; isolated agents, and invest again at the end in validation. His emphasis on
;; visual artefacts is valuable when agents generate information faster than a
;; human can review walls of text.
;;
;; I am not reproducing either workflow ritual for ritual. The durable lesson is
;; to design the organisation around the agents' actual strengths and
;; weaknesses. Requirements, context, tools, isolation, feedback, validation,
;; versioned evidence, and rollback are the management system.
;;
;; We have suddenly gained a strange abundance of fast intellectual labour. The
;; temptation is to demand more output. The interesting work is to build better
;; judgment around it: decide what deserves to exist, clarify what it should
;; mean, construct evidence that can contradict us, and preserve the reasoning
;; so another person can inspect it. Every hidden assumption that becomes
;; visible is progress. Every candidate that fails honestly is progress too.
;;
;; ## Sources and further reading {#sources}
;;
;; - Clayton M. Christensen,
;;   [*The Innovator's Dilemma*](https://store.hbr.org/product/the-innovator-s-dilemma-with-a-new-foreword-when-new-technologies-cause-great-firms-to-fail/10706),
;;   Harvard Business Review Press, for the publisher's summary and reception claims.
;; - [Harvard Business School's account of Christensen's work and influence](https://www.hbs.edu/news/releases/clayton-christensen-obituary),
;;   including the book's New York Times bestseller status and 1997 Global Business Book Award.
;; - Si and Chen,
;;   [“A literature review of disruptive innovation: What it is, how it works and where it goes”](https://www.sciencedirect.com/science/article/pii/S0923474820300163),
;;   for the theory's influence, refinements, disputes, and frequent misuse.
;; - [The public theory-to-algorithm workflow](https://github.com/jamiepratt/theory-to-algorithm-workflow),
;;   including its method, scoring contract, complete case study, and adaptation guide.
;; - [Why estimate vocabulary?](why_estimate_vocabulary.html),
;;   [Bayes' theorem from uncertainty to decision](bayes_theorem_simulations.html),
;;   [the stratified Beta–binomial first pass](beta_binomial_first_pass.html), and
;;   [the continuous-frequency candidate and non-promotion decision](pair_frequency_logistic_v2_article.html).
