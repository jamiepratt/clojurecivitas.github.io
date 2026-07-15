(ns
 data-visualization.aog-in-clojure-part1-generated-test
  (:require
   [tablecloth.api :as tc]
   [scicloj.kindly.v4.kind :as kind]
   [clojure.test :refer [deftest is]]))

(def
  v1_l20
  [:style
   ".clay-dataset {\n  max-height:400px;\n  overflow-y: auto;\n}\n.printedClojure {\n  max-height:400px;\n  overflow-y: auto;\n}\n"])

(ns
 data-visualization.aog-in-clojure-part1-generated-test
  (:require
   [tablecloth.api :as tc]
   [tablecloth.column.api :as tcc]
   [scicloj.kindly.v4.kind :as kind]
   [thi.ng.geom.viz.core :as viz]
   [thi.ng.geom.svg.core :as svg]
   [fastmath.ml.regression :as regr]
   [fastmath.stats :as stats]
   [malli.core :as m]
   [malli.error :as me]
   [malli.util :as mu]
   [scicloj.metamorph.ml.rdatasets :as rdatasets]
   [clojure.test :refer [deftest is]]))

(def
  v5_l351
  (let
   [shared
    {:data :penguins}
    layers
    [{:plottype :scatter} {:plottype :line}]]
    (map (fn* [p1__560018#] (merge shared p1__560018#)) layers)))

(def v7_l362 (concat [{:x :a}] [{:y :b}]))

(def v9_l365 (merge {:x :a} {:y :b}))

(def
  v11_l382
  (def penguins (tc/drop-missing (rdatasets/palmerpenguins-penguins))))

(def
  v12_l384
  (kind/pprint
   {:=layers
    [{:=data penguins,
      :=x :bill-length-mm,
      :=y :bill-depth-mm,
      :=plottype :scatter}],
    :=target :geomviz,
    :=width 800,
    :=height 600}))

(def
  v14_l499
  (merge
   {:=x :bill-length, :=color :species}
   {:=y :bill-depth, :=alpha 0.5}))

(def
  v16_l507
  (def
    nested-layer-example
    {:transformation nil,
     :data
     {:bill-length-mm [39.1 39.5 40.3],
      :bill-depth-mm [18.7 17.4 18.0],
      :species [:adelie :adelie :adelie]},
     :positional [:bill-length-mm :bill-depth-mm],
     :named {:color :species},
     :attributes {:alpha 0.5}}))

(def
  v17_l516
  (merge
   {:positional [:x], :named {:color :species}}
   {:positional [:y], :named {:size :body-mass}}))

(def
  v19_l527
  (def
    flat-layer-example
    {:=data
     {:bill-length-mm [39.1 39.5 40.3],
      :bill-depth-mm [18.7 17.4 18.0],
      :species [:adelie :adelie :adelie]},
     :=x :bill-length-mm,
     :=y :bill-depth-mm,
     :=color :species,
     :=alpha 0.5,
     :=plottype :scatter}))

(def v21_l751 (tcc/typeof (penguins :species)))

(def v22_l752 (tcc/typeof (penguins :bill-length-mm)))

(def
  v24_l830
  (def
    registry
    "Malli registry with default schemas and util schemas (for :merge, etc.)"
    (merge (m/default-schemas) (mu/schemas))))

(def
  v26_l836
  (def
    DataType
    "Schema for column data types.\n  \n  - :quantitative - Continuous numeric values\n  - :nominal - Categorical/discrete unordered values\n  - :ordinal - Categorical/discrete ordered values\n  - :temporal - Date/time values"
    [:enum :quantitative :nominal :ordinal :temporal]))

(def
  v27_l845
  (def
    PlotType
    "Schema for plot/mark types."
    [:enum :scatter :line :area :bar :histogram]))

(def
  v28_l849
  (def
    Transformation
    "Schema for statistical transformations."
    [:enum :linear :smooth :density :bin :histogram]))

(def
  v29_l853
  (def
    BinsMethod
    "Schema for histogram binning methods."
    [:or [:enum :sturges :sqrt :rice :freedman-diaconis] pos-int?]))

(def
  v31_l861
  (def
    Dataset
    "Schema for dataset input.\n  \n  Accepts:\n  - Plain Clojure map with keyword keys and sequential values\n  - Vector of maps (row-oriented data)\n  - tech.ml.dataset (tablecloth dataset)"
    [:or
     [:map-of :keyword [:sequential any?]]
     [:sequential map?]
     [:fn #:error{:message "Must be a tablecloth dataset"} tc/dataset?]]))

(def
  v33_l876
  (def
    ColumnReference
    "Schema for referencing a column in the dataset."
    :keyword))

(def
  v34_l880
  (def
    ColumnOrConstant
    "Schema for aesthetics that can be either mapped to a column or set to a constant value.\n  \n  - Keyword → map from column (e.g., :species)\n  - Other value → constant (e.g., \"red\", 0.5)"
    [:or ColumnReference string? number? boolean?]))

(def
  v35_l887
  (def
    PositionalAesthetic
    "Schema for x or y positional aesthetics."
    [:maybe ColumnReference]))

(def
  v36_l891
  (def
    ColorAesthetic
    "Schema for color aesthetic.\n  \n  Can be:\n  - Column reference for mapping\n  - Constant color string\n  - nil (no color mapping)"
    [:maybe ColumnOrConstant]))

(def
  v37_l900
  (def
    SizeAesthetic
    "Schema for size aesthetic."
    [:maybe ColumnOrConstant]))

(def
  v38_l904
  (def
    AlphaAttribute
    "Schema for alpha/opacity attribute (constant only)."
    [:maybe [:double {:min 0.0, :max 1.0}]]))

(def
  v39_l908
  (def
    FacetAesthetic
    "Schema for faceting aesthetics (row, col)."
    [:maybe ColumnReference]))

(def
  v41_l914
  (def
    ScaleTransform
    "Schema for scale transformations."
    [:enum :identity :log :sqrt]))

(def
  v42_l918
  (def
    ScaleDomain
    "Schema for scale domain specification."
    [:or [:tuple number? number?] [:vector any?]]))

(def
  v43_l926
  (def
    ScaleSpec
    "Schema for scale specification."
    [:map
     [:domain {:optional true} ScaleDomain]
     [:transform {:optional true} ScaleTransform]]))

(def
  v45_l934
  (def
    Backend
    "Schema for rendering backend selection."
    [:enum :geomviz :vl :plotly]))

(def
  v47_l940
  (def
    BaseLayer
    "Base layer fields shared across all plot types."
    [:map
     [:=data {:optional true} Dataset]
     [:=color {:optional true} ColorAesthetic]
     [:=size {:optional true} SizeAesthetic]
     [:=row {:optional true} FacetAesthetic]
     [:=col {:optional true} FacetAesthetic]
     [:=alpha {:optional true} AlphaAttribute]
     [:=transformation {:optional true} Transformation]
     [:=bins {:optional true} BinsMethod]
     [:=scale-x {:optional true} ScaleSpec]
     [:=scale-y {:optional true} ScaleSpec]
     [:=scale-color {:optional true} ScaleSpec]]))

(def
  v48_l968
  (def
    Layer
    "Schema for a complete layer specification with plottype-specific requirements.\n  \n  Uses :multi to dispatch on :=plottype and enforce different requirements:\n  - :scatter, :line, :area require both :=x and :=y\n  - :bar, :histogram require :=x (y is optional)\n  - nil (no plottype) allows incomplete layers for composition\n  \n  This replaces the nested conditionals in validate-layer with declarative schemas."
    (m/schema
     [:multi
      {:dispatch :=plottype}
      [:scatter
       [:merge
        BaseLayer
        [:map
         [:=plottype [:enum :scatter]]
         [:=x PositionalAesthetic]
         [:=y PositionalAesthetic]]]]
      [:line
       [:merge
        BaseLayer
        [:map
         [:=plottype [:enum :line]]
         [:=x PositionalAesthetic]
         [:=y PositionalAesthetic]]]]
      [:bar
       [:merge
        BaseLayer
        [:map
         [:=plottype [:enum :bar]]
         [:=x PositionalAesthetic]
         [:=y {:optional true} PositionalAesthetic]]]]
      [:histogram
       [:merge
        BaseLayer
        [:map
         [:=plottype [:enum :histogram]]
         [:=x PositionalAesthetic]
         [:=y {:optional true} PositionalAesthetic]]]]
      [:area
       [:merge
        BaseLayer
        [:map
         [:=plottype [:enum :area]]
         [:=x PositionalAesthetic]
         [:=y PositionalAesthetic]]]]
      [nil
       [:merge
        BaseLayer
        [:map
         [:=plottype {:optional true} [:maybe nil?]]
         [:=x {:optional true} PositionalAesthetic]
         [:=y {:optional true} PositionalAesthetic]]]]]
     {:registry registry})))

(def
  v49_l1035
  (def
    Layers
    "Schema for one or more layers.\n  \n  - Single layer map\n  - Vector of layer maps"
    [:or Layer [:vector Layer]]))

(def
  v50_l1042
  (def
    PlotSpec
    "Schema for a plot specification (complete or partial).\n  \n  A plot spec is a map that can contain:\n  - Layers: Vector of layer maps (optional - allows partial specs)\n  - Plot-level properties: target, width, height\n  - Plot-level scales (optional)\n  \n  All fields are optional to support composition via =* and =+."
    [:map
     [:=layers {:optional true} [:vector Layer]]
     [:=target {:optional true} Backend]
     [:=width {:optional true} pos-int?]
     [:=height {:optional true} pos-int?]
     [:=scale-x {:optional true} ScaleSpec]
     [:=scale-y {:optional true} ScaleSpec]
     [:=scale-color {:optional true} ScaleSpec]]))

(def
  v52_l1077
  (defn
    validate
    "Validate a value against a schema.\n  \n  Returns:\n  - nil if valid\n  - Humanized error map if invalid\n  \n  Example:\n  (validate Layer {:=data {:x [1 2 3]} :=plottype :scatter})\n  ;; => nil (valid)\n  \n  (validate Layer {:=plottype :invalid})\n  ;; => {:=plottype [\"should be one of: :scatter, :line, :area, :bar, :histogram\"]}"
    [schema value]
    (when-not
     (m/validate schema value)
      (me/humanize (m/explain schema value)))))

(def
  v53_l1094
  (defn
    validate!
    "Validate a value against a schema, throwing on error.\n  \n  Throws ex-info with humanized error message if invalid.\n  \n  Example:\n  (validate! Layer my-layer)"
    [schema value]
    (when-let
     [errors (validate schema value)]
      (throw
       (ex-info "Validation failed" {:errors errors, :value value})))))

(def
  v54_l1107
  (defn
    valid?
    "Check if a value is valid according to a schema.\n  \n  Returns boolean.\n  \n  Example:\n  (valid? Layer my-layer)"
    [schema value]
    (m/validate schema value)))

(def
  v56_l1119
  (defn
    validate-layer
    "Validate a layer with context-aware checks.\n  \n  Performs:\n  1. Schema validation (structure + plottype-specific requirements via :multi)\n  2. Data column validation (columns exist) - runtime check\n  \n  Returns nil if valid, error map if invalid."
    [layer]
    (or
     (when-let
      [schema-errors (validate Layer layer)]
       {:type :schema-error,
        :errors schema-errors,
        :message "Layer validation failed"})
     (when-let
      [data (:=data layer)]
       (let
        [column-keys
         (cond
           (tc/dataset? data)
           (set (tc/column-names data))
           (and (vector? data) (map? (first data)))
           (set (keys (first data)))
           (map? data)
           (set (keys data))
           :else
           nil)
         aesthetic-cols
         (filter
          keyword?
          [(:=x layer)
           (:=y layer)
           (when (keyword? (:=color layer)) (:=color layer))
           (when (keyword? (:=size layer)) (:=size layer))
           (:=row layer)
           (:=col layer)])
         missing-cols
         (when column-keys (remove column-keys aesthetic-cols))]
         (when
          (and column-keys (seq missing-cols))
           {:type :missing-columns,
            :missing (vec missing-cols),
            :available (vec (sort column-keys)),
            :message
            (str
             "Columns not found in dataset: "
             (vec missing-cols)
             "\nAvailable columns: "
             (vec (sort column-keys)))})))
     nil)))

(def
  v57_l1177
  (defn
    validate-layer!
    "Validate a layer, throwing on error.\n  \n  Throws ex-info with detailed error information."
    [layer]
    (when-let
     [error (validate-layer layer)]
      (throw (ex-info (:message error "Layer validation failed") error)))))

(def
  v58_l1186
  (defn
    validate-layers
    "Validate one or more layers.\n  \n  Returns nil if all valid, map of errors otherwise."
    [layers]
    (let
     [layer-vec
      (if (vector? layers) layers [layers])
      errors
      (keep-indexed
       (fn
         [idx layer]
         (when-let [error (validate-layer layer)] [idx error]))
       layer-vec)]
      (when
       (seq errors)
        {:type :layers-validation-failed, :errors (into {} errors)}))))

(def
  v59_l1200
  (defn
    validate-layers!
    "Validate one or more layers, throwing on first error."
    [layers]
    (when-let
     [errors (validate-layers layers)]
      (throw (ex-info "Layer validation failed" errors)))))

(def
  v61_l1219
  (defn-
    plot-spec?
    "Check if x is a plot spec (map with :=layers or plot-level keys).\n  \n  Plot specs are maps that have at least one key starting with :=\n  Uses Malli validation as a fallback check for well-formed specs."
    [x]
    (and
     (map? x)
     (some
      (fn* [p1__560019#] (-> p1__560019# name first (= \=)))
      (keys x))
     (valid? PlotSpec x))))

(def
  v63_l1234
  (defmulti
    plot-impl
    "Internal multimethod for plot dispatch."
    (fn [spec opts] (or (:target opts) (:=target spec) :geomviz))))

(def
  v64_l1241
  (defn
    plot
    "Render a plot specification to a visualization.\n\n  Use `plot` as the final step in a threading chain or wrap a composition:\n\n  Args:\n  - spec: Plot spec map with :=layers\n  - target-or-opts: Either:\n    - Keyword - rendering target (:geomviz, :vl, :plotly)\n    - Map with options:\n      - :width - Width in pixels (default 600)\n      - :height - Height in pixels (default 400)\n      - :target - Rendering target (:geomviz, :vl, :plotly)\n\n  The rendering target is determined by:\n  1. target-or-opts if it's a keyword (highest priority)\n  2. :target in opts map (if target-or-opts is a map)\n  3. `:=target` key in spec (set via `target` function)\n  4. :geomviz (static SVG) as default\n\n  Returns:\n  - Kindly-wrapped visualization specification\n\n  Examples:\n  ;; Threading style (recommended):\n  (-> penguins\n      (mapping :bill-length-mm :bill-depth-mm)\n      (scatter)\n      plot)\n  \n  ;; Composition style:\n  (plot (=* (data penguins)\n            (mapping :x :y)\n            (scatter)))\n  \n  ;; With target shorthand:\n  (-> penguins\n      (mapping :x :y)\n      (scatter)\n      (plot :plotly))\n  \n  ;; With full options:\n  (-> penguins\n      (mapping :x :y)\n      (scatter)\n      (plot {:target :vl :width 800 :height 600}))"
    ([spec] (plot-impl spec {}))
    ([spec target-or-opts]
     (if
      (keyword? target-or-opts)
       (plot-impl spec {:target target-or-opts})
       (plot-impl spec target-or-opts)))))

(def
  v65_l1296
  (defn-
    ensure-vec
    "Wrap single items in a vector if not already sequential.\n  \n  This enables auto-wrapping for ergonomic API:\n  - (=* data mapping geom) instead of (=* [data] [mapping] [geom])\n  \n  Args:\n  - x: Either a vector/seq of layers, or a single layer map\n  \n  Returns:\n  - Vector of layers"
    [x]
    (if (sequential? x) x [x])))

(def
  v67_l1312
  (defn
    =*
    "Merge plot specifications (composition).\n  \n  Handles both layer cross-product and plot-level merge:\n  - Layers: Cross-product merge (for [a b], [c d] -> [merge(a,c) merge(a,d) merge(b,c) merge(b,d)])\n  - Plot-level properties: Right-side wins\n  \n  Examples:\n  (=* {:=layers [{:=x :a}]} {:=layers [{:=y :b}]})\n  ;; => {:=layers [{:=x :a :=y :b}]}\n  \n  (=* {:=layers [{:=x :a}]} {:=target :geomviz})\n  ;; => {:=layers [{:=x :a}] :=target :geomviz}"
    ([x] x)
    ([x y]
     (let
      [x-layers
       (get x :=layers [])
       y-layers
       (get y :=layers [])
       x-plot
       (dissoc x :=layers)
       y-plot
       (dissoc y :=layers)
       merged-layers
       (cond
         (and (seq x-layers) (seq y-layers))
         (vec (for [a x-layers b y-layers] (merge a b)))
         (seq x-layers)
         x-layers
         (seq y-layers)
         y-layers
         :else
         [])
       merged-plot
       (merge x-plot y-plot)]
       (cond->
        merged-plot
         (seq merged-layers)
         (assoc :=layers merged-layers))))
    ([x y & more] (reduce =* (=* x y) more))))

(def
  v69_l1367
  (defn-
    valid-layers?
    "Check if x is a valid vector of layers using Malli validation.\n  \n  Note: This specifically checks for a vector of layers, not a single layer."
    [x]
    (valid? [:vector Layer] x)))

(def
  v70_l1374
  (defn
    =+
    "Combine multiple plot specifications for overlay (sum).\n  \n  Concatenates layers and merges plot-level properties (last wins).\n  \n  When used in threading form (-> base (=+ spec1 spec2 ...)), merges each spec\n  with the base's layer properties before concatenating, so layers inherit\n  data/aesthetics from the base. The base layer itself is not included unless\n  it has a plottype.\n  \n  Examples:\n  (=+ {:=layers [{:=plottype :scatter}]} {:=layers [{:=plottype :line}]})\n  ;; => {:=layers [{:=plottype :scatter} {:=plottype :line}]}\n  \n  (-> (mapping penguins :x :y)\n      (=+ (scatter) (linear)))\n  ;; => {:=layers [{:=data penguins :=x :x :=y :y :=plottype :scatter}\n  ;;               {:=data penguins :=x :x :=y :y :=plottype :line :=transformation :linear}]}"
    [& specs]
    (if
     (= (count specs) 1)
      (first specs)
      (let
       [[first-spec & rest-specs]
        specs
        first-layers
        (:=layers first-spec [])
        first-plot-props
        (dissoc first-spec :=layers)
        base-layer
        (when (seq first-layers) (first first-layers))
        base-has-plottype?
        (and base-layer (:=plottype base-layer))
        merged-rest-layers
        (mapcat
         (fn
           [spec]
           (let
            [spec-layers (:=layers spec [])]
             (if
              (and base-layer (seq spec-layers))
               (map
                (fn* [p1__560020#] (merge base-layer p1__560020#))
                spec-layers)
               spec-layers)))
         rest-specs)
        all-layers
        (vec
         (if
          base-has-plottype?
           (concat first-layers merged-rest-layers)
           merged-rest-layers))
        all-plot-props
        (apply
         merge
         first-plot-props
         (map
          (fn* [p1__560021#] (dissoc p1__560021# :=layers))
          rest-specs))]
        (cond->
         all-plot-props
          (seq all-layers)
          (assoc :=layers all-layers))))))

(def
  v72_l1447
  (kind/pprint (=* {:=layers [{:=x :a}]} {:=layers [{:=y :b}]})))

(deftest
  t73_l1451
  (is
   ((fn*
     [p1__560022#]
     (and
      (map? p1__560022#)
      (contains? p1__560022# :=layers)
      (= (count (:=layers p1__560022#)) 1)
      (= (:=x (first (:=layers p1__560022#))) :a)
      (= (:=y (first (:=layers p1__560022#))) :b)))
    v72_l1447)))

(def
  v75_l1459
  (kind/pprint
   (=* {:=layers [{:=x :a} {:=x :b}]} {:=layers [{:=y :c} {:=y :d}]})))

(deftest
  t76_l1463
  (is
   ((fn*
     [p1__560023#]
     (and
      (map? p1__560023#)
      (contains? p1__560023# :=layers)
      (= (count (:=layers p1__560023#)) 4)
      (some
       (fn [layer] (and (= (:=x layer) :a) (= (:=y layer) :c)))
       (:=layers p1__560023#))
      (some
       (fn [layer] (and (= (:=x layer) :a) (= (:=y layer) :d)))
       (:=layers p1__560023#))
      (some
       (fn [layer] (and (= (:=x layer) :b) (= (:=y layer) :c)))
       (:=layers p1__560023#))
      (some
       (fn [layer] (and (= (:=x layer) :b) (= (:=y layer) :d)))
       (:=layers p1__560023#))))
    v75_l1459)))

(def
  v78_l1476
  (kind/pprint
   (=*
    {:=layers [{:=x :a}], :=target :geomviz}
    {:=layers [{:=y :b}], :=width 800})))

(deftest
  t79_l1480
  (is
   ((fn*
     [p1__560024#]
     (and
      (map? p1__560024#)
      (contains? p1__560024# :=layers)
      (= (:=target p1__560024#) :geomviz)
      (= (:=width p1__560024#) 800)
      (= (count (:=layers p1__560024#)) 1)
      (= (:=x (first (:=layers p1__560024#))) :a)
      (= (:=y (first (:=layers p1__560024#))) :b)))
    v78_l1476)))

(def
  v81_l1490
  (kind/pprint
   (=* {:=target :geomviz, :=width 400} {:=target :vl, :=height 300})))

(deftest
  t82_l1494
  (is
   ((fn*
     [p1__560025#]
     (and
      (map? p1__560025#)
      (= (:=target p1__560025#) :vl)
      (= (:=width p1__560025#) 400)
      (= (:=height p1__560025#) 300)))
    v81_l1490)))

(def
  v84_l1503
  (kind/pprint
   (=+
    {:=layers [{:=plottype :scatter, :=alpha 0.5}]}
    {:=layers [{:=plottype :line, :=color :blue}]})))

(deftest
  t85_l1507
  (is
   ((fn*
     [p1__560026#]
     (and
      (map? p1__560026#)
      (contains? p1__560026# :=layers)
      (= (count (:=layers p1__560026#)) 2)
      (= (:=plottype (first (:=layers p1__560026#))) :scatter)
      (= (:=plottype (second (:=layers p1__560026#))) :line)
      (= (:=alpha (first (:=layers p1__560026#))) 0.5)
      (= (:=color (second (:=layers p1__560026#))) :blue)))
    v84_l1503)))

(def
  v87_l1519
  (kind/pprint
   (=+
    {:=layers [{:=data {:x [1 2 3], :y [4 5 6]}, :=x :x, :=y :y}]}
    {:=layers [{:=plottype :scatter}]}
    {:=layers [{:=plottype :line}]})))

(deftest
  t88_l1524
  (is
   ((fn*
     [p1__560027#]
     (and
      (map? p1__560027#)
      (contains? p1__560027# :=layers)
      (= (count (:=layers p1__560027#)) 2)
      (=
       (:=data (first (:=layers p1__560027#)))
       {:x [1 2 3], :y [4 5 6]})
      (=
       (:=data (second (:=layers p1__560027#)))
       {:x [1 2 3], :y [4 5 6]})
      (= (:=x (first (:=layers p1__560027#))) :x)
      (= (:=x (second (:=layers p1__560027#))) :x)
      (= (:=y (first (:=layers p1__560027#))) :y)
      (= (:=y (second (:=layers p1__560027#))) :y)
      (= (:=plottype (first (:=layers p1__560027#))) :scatter)
      (= (:=plottype (second (:=layers p1__560027#))) :line)))
    v87_l1519)))

(def
  v90_l1543
  (kind/pprint
   (=+
    {:=layers [{:=plottype :scatter}], :=target :geomviz, :=width 600}
    {:=layers [{:=plottype :line}], :=height 400})))

(deftest
  t91_l1547
  (is
   ((fn*
     [p1__560028#]
     (and
      (map? p1__560028#)
      (= (:=target p1__560028#) :geomviz)
      (= (:=width p1__560028#) 600)
      (= (:=height p1__560028#) 400)
      (= (count (:=layers p1__560028#)) 2)))
    v90_l1543)))

(def
  v93_l1555
  (defn
    data
    "Attach data to a layer.\n\n  Accepts:\n  - tech.ml.dataset datasets\n  - Maps of vectors: {:x [1 2 3] :y [4 5 6]}\n  - Vector of maps: [{:x 1 :y 4} {:x 2 :y 5}]\n\n  Returns a plot spec map with :=layers containing a layer map with :=data.\n  \n  When called with spec as first arg, merges data into those layers."
    ([dataset] (validate! Dataset dataset) {:=layers [{:=data dataset}]})
    ([spec dataset] (=* spec (data dataset)))))

(def v95_l1573 (data {:x [1 2 3], :y [4 5 6]}))

(def v96_l1575 (data [{:x 1, :y 4} {:x 2, :y 5} {:x 3, :y 6}]))

(def
  v98_l1579
  (defn
    =key
    "Add a = sign to the name of a given keyword."
    [k]
    (->> k name (str "=") keyword)))

(def
  v99_l1584
  (defn
    mapping
    "Define aesthetic mappings from data columns to visual properties.\n  \n  Args:\n  - x, y: Column names (keywords) for positional aesthetics\n  - named: (optional) Map of other aesthetics\n  \n  Returns a plot spec map with :=layers containing a mapping layer.\n  \n  When called with spec-or-data as first arg:\n  - If plot spec (map with :=layers keys): merges mapping into those layers\n  - If data: converts to layer first, then adds mapping"
    ([x y]
     {:=layers
      [(into {} (filter (fn [[_ v]] (some? v)) {:=x x, :=y y}))]})
    ([x y named]
     (if
      (map? named)
       {:=layers
        [(into
          {}
          (filter
           (fn [[_ v]] (some? v))
           (merge {:=x x, :=y y} (update-keys named =key))))]}
       (let
        [spec-or-data
         x
         x-field
         y
         y-field
         named
         spec
         (if (plot-spec? spec-or-data) spec-or-data (data spec-or-data))]
         (=* spec (mapping x-field y-field)))))
    ([first-arg x y named]
     (let
      [spec (if (plot-spec? first-arg) first-arg (data first-arg))]
       (=* spec (mapping x y named))))))

(def v101_l1621 (mapping :bill-length-mm :bill-depth-mm))

(def
  v102_l1623
  (mapping :bill-length-mm :bill-depth-mm {:color :species}))

(def v103_l1625 (mapping :wt :mpg {:color :cyl, :size :hp}))

(def
  v104_l1627
  (defn
    facet
    "Add faceting to a plot specification.\n  \n  Args:\n  - facet-spec: Map with :row and/or :col keys specifying faceting variables\n  \n  Returns layer spec with faceting properties.\n  \n  When called with spec as first arg, merges faceting into that spec.\n  \n  Examples:\n  (facet {:col :species})\n  (facet {:row :sex :col :island})\n  \n  Threading-friendly:\n  (-> penguins (mapping :x :y) (scatter) (facet {:col :species}))"
    ([facet-spec]
     (let
      [facet-keys (update-keys facet-spec =key)]
       {:=layers [facet-keys]}))
    ([spec facet-spec] (=* spec (facet facet-spec)))))

(def
  v105_l1649
  (defn
    scale
    "Specify scale properties for an aesthetic.\n  \n  Args:\n  - aesthetic: Keyword like :x, :y, :color\n  - opts: Map with scale options:\n    - :domain - [min max] for continuous, or vector of categories\n    - :transform - :log, :sqrt, :identity (default)\n  \n  Returns a plot-level property map with scale specification.\n  \n  When called with spec as first arg, merges scale into that spec.\n  \n  Examples:\n  (scale :x {:domain [0 100]})\n  (scale :y {:transform :log})\n  \n  Threading-friendly:\n  (-> penguins (mapping :x :y) (scatter) (scale :x {:domain [30 65]}))"
    ([aesthetic opts]
     (let
      [scale-key (keyword (str "=scale-" (name aesthetic)))]
       {scale-key opts}))
    ([spec aesthetic opts] (=* spec (scale aesthetic opts)))))

(def
  v106_l1674
  (defn
    target
    "Specify the rendering target for plot.\n  \n  Args:\n  - target-kw: One of :geomviz (static SVG), :vl (Vega-Lite), or :plotly (Plotly.js)\n  \n  Returns a plot-level property map (no :=layers).\n  \n  When called with spec as first arg, merges target into that spec.\n  \n  See Multi-Target Rendering section for usage examples."
    ([target-kw] {:=target target-kw})
    ([spec target-kw] (=* spec (target target-kw)))))

(def
  v107_l1690
  (defn
    size
    "Specify width and height for the plot.\n  \n  Args:\n  - width: Plot width in pixels\n  - height: Plot height in pixels\n  \n  Returns a plot-level property map (no :=layers).\n  \n  When called with spec as first arg, merges size into that spec.\n  \n  Examples:\n  (size 800 600)\n  (-> penguins (mapping :x :y) (scatter) (size 800 600))"
    ([width height] {:=width width, :=height height})
    ([spec width height] (=* spec (size width height)))))

(def v109_l1720 (kind/pprint (target :vl)))

(deftest
  t110_l1723
  (is
   ((fn*
     [p1__560029#]
     (and
      (map? p1__560029#)
      (= (:=target p1__560029#) :vl)
      (not (contains? p1__560029# :=layers))))
    v109_l1720)))

(def
  v112_l1729
  (kind/pprint
   (-> {:=layers [{:=data {:x [1 2 3], :y [4 5 6]}}]} (target :plotly))))

(deftest
  t113_l1733
  (is
   ((fn*
     [p1__560030#]
     (and
      (map? p1__560030#)
      (contains? p1__560030# :=layers)
      (= (:=target p1__560030#) :plotly)
      (=
       (:=data (first (:=layers p1__560030#)))
       {:x [1 2 3], :y [4 5 6]})))
    v112_l1729)))

(def v115_l1742 (kind/pprint (scale :x {:domain [0 100]})))

(deftest
  t116_l1745
  (is
   ((fn*
     [p1__560031#]
     (and
      (map? p1__560031#)
      (contains? p1__560031# :=scale-x)
      (= (get-in p1__560031# [:=scale-x :domain]) [0 100])
      (not (contains? p1__560031# :=layers))))
    v115_l1742)))

(def
  v118_l1752
  (kind/pprint
   (=* (scale :x {:domain [0 100]}) (scale :y {:domain [0 50]}))))

(deftest
  t119_l1756
  (is
   ((fn*
     [p1__560032#]
     (and
      (map? p1__560032#)
      (contains? p1__560032# :=scale-x)
      (contains? p1__560032# :=scale-y)
      (= (get-in p1__560032# [:=scale-x :domain]) [0 100])
      (= (get-in p1__560032# [:=scale-y :domain]) [0 50])))
    v118_l1752)))

(def
  v121_l1764
  (kind/pprint
   (-> {:=layers [{:=x :a, :=y :b}]} (scale :x {:domain [30 60]}))))

(deftest
  t122_l1768
  (is
   ((fn*
     [p1__560033#]
     (and
      (map? p1__560033#)
      (contains? p1__560033# :=layers)
      (contains? p1__560033# :=scale-x)
      (= (get-in p1__560033# [:=scale-x :domain]) [30 60])))
    v121_l1764)))

(def v124_l1777 (kind/pprint (size 800 600)))

(deftest
  t125_l1780
  (is
   ((fn*
     [p1__560034#]
     (and
      (map? p1__560034#)
      (= (:=width p1__560034#) 800)
      (= (:=height p1__560034#) 600)
      (not (contains? p1__560034# :=layers))))
    v124_l1777)))

(def
  v127_l1787
  (kind/pprint (-> {:=layers [{:=x :a, :=y :b}]} (size 1000 500))))

(deftest
  t128_l1791
  (is
   ((fn*
     [p1__560035#]
     (and
      (map? p1__560035#)
      (contains? p1__560035# :=layers)
      (= (:=width p1__560035#) 1000)
      (= (:=height p1__560035#) 500)))
    v127_l1787)))

(def
  v130_l1800
  (kind/pprint
   (=* (target :vl) (size 800 600) (scale :x {:domain [0 100]}))))

(deftest
  t131_l1805
  (is
   ((fn*
     [p1__560036#]
     (and
      (map? p1__560036#)
      (= (:=target p1__560036#) :vl)
      (= (:=width p1__560036#) 800)
      (= (:=height p1__560036#) 600)
      (contains? p1__560036# :=scale-x)))
    v130_l1800)))

(def
  v133_l1837
  (defn-
    ensure-dataset
    "Convert data to a tablecloth dataset if it isn't already.\n\n  Accepts:\n  - tech.ml.dataset datasets (passed through)\n  - Maps of vectors: {:x [1 2 3] :y [4 5 6]}\n  - Vector of maps: [{:x 1 :y 4} {:x 2 :y 5}]\n\n  Returns a tablecloth dataset."
    [data]
    (cond
      (tc/dataset? data)
      data
      (map? data)
      (let
       [values (vals data)]
        (when-not
         (every? sequential? values)
          (throw
           (ex-info
            "Map data must have sequential values (vectors or lists)"
            {:data data,
             :invalid-keys
             (filter
              (fn* [p1__560037#] (not (sequential? (get data p1__560037#))))
              (keys data))})))
        (tc/dataset data))
      (and (sequential? data) (every? map? data))
      (tc/dataset data)
      :else
      (throw
       (ex-info
        "Data must be a dataset, map of vectors, or vector of maps"
        {:data data, :type (type data)})))))

(def
  v134_l1866
  (defn-
    validate-column-exists
    "Validate that a column exists in a dataset.\n  \n  Args:\n  - dataset: tech.ml.dataset instance\n  - col-key: Keyword for column name\n  - context: String describing where this column is used (for error messages)\n  \n  Throws informative exception if column doesn't exist.\n  Returns col-key if valid (for threading)."
    [dataset col-key context]
    (when
     col-key
      (let
       [col-names (set (tc/column-names dataset))]
        (when-not
         (contains? col-names col-key)
          (let
           [col-name-str
            (name col-key)
            similar-cols
            (filter
             (fn
               [available-col]
               (let
                [available-str
                 (name available-col)
                 substring-match?
                 (or
                  (.contains available-str col-name-str)
                  (.contains col-name-str available-str))
                 similar-length?
                 (<
                  (Math/abs (- (count available-str) (count col-name-str)))
                  3)]
                 (or substring-match? similar-length?)))
             col-names)
            error-msg
            (str "Column " col-key " not found in dataset")
            suggestion
            (if
             (seq similar-cols)
              (str
               "Did you mean one of: "
               (pr-str (vec (sort similar-cols)))
               "?")
              (str "Available columns: " (pr-str (vec (sort col-names)))))]
            (throw
             (ex-info
              error-msg
              {:column col-key,
               :context context,
               :available-columns (vec (sort col-names)),
               :similar-columns
               (when (seq similar-cols) (vec (sort similar-cols))),
               :suggestion suggestion}))))))
    col-key))

(def
  v135_l1907
  (defn-
    validate-columns
    "Validate multiple columns exist in a dataset.\n  \n  Args:\n  - dataset: tech.ml.dataset instance\n  - col-keys: Collection of column keywords\n  - context: String describing where these columns are used\n  \n  Throws informative exception if any column doesn't exist.\n  Returns col-keys if all valid (for threading)."
    [dataset col-keys context]
    (doseq
     [col-key col-keys]
      (validate-column-exists dataset col-key context))
    col-keys))

(def
  v136_l1922
  (defn-
    validate-layer-columns
    "Validate that all aesthetic mappings in a layer reference existing columns.\n  \n  Checks :=x, :=y, :=color, :=row, :=col, :=group.\n  \n  Args:\n  - layer: Layer map with aesthetic mappings\n  \n  Throws informative exception if any referenced column doesn't exist.\n  Returns layer if valid (for threading)."
    [layer]
    (when-let
     [data (:=data layer)]
      (let
       [dataset
        (ensure-dataset data)
        aesthetics
        [:=x :=y :=color :=row :=col]
        cols
        (keep (fn* [p1__560038#] (get layer p1__560038#)) aesthetics)
        group-cols
        (let
         [g (:=group layer)]
          (cond (keyword? g) [g] (vector? g) g :else []))]
        (doseq
         [col-key (concat cols group-cols)]
          (validate-column-exists
           dataset
           col-key
           (str
            "aesthetic mapping in layer: "
            (pr-str (select-keys layer aesthetics)))))))
    layer))

(def
  v137_l1951
  (defn-
    split-by-facets
    "Split a layer's data by facet variables.\n  \n  Returns: Vector of {:row-label r, :col-label c, :layer layer-with-subset} maps\n  If no faceting, returns vector with single element containing original layer.\n  Labels are nil for dimensions without faceting."
    [layer]
    (let
     [col-var (:=col layer) row-var (:=row layer) data (:=data layer)]
      (if-not
       (or col-var row-var)
        [{:row-label nil, :col-label nil, :layer layer}]
        (let
         [dataset
          (ensure-dataset data)
          col-categories
          (when col-var (sort (distinct (get dataset col-var))))
          row-categories
          (when row-var (sort (distinct (get dataset row-var))))
          combinations
          (cond
            (and row-var col-var)
            (for
             [r row-categories c col-categories]
              {:row-label r, :col-label c})
            col-var
            (for [c col-categories] {:row-label nil, :col-label c})
            row-var
            (for [r row-categories] {:row-label r, :col-label nil}))]
          (mapv
           (fn
             [{:keys [row-label col-label]}]
             (let
              [filtered
               (cond->
                dataset
                 row-var
                 (tc/select-rows (fn [row] (= (get row row-var) row-label)))
                 col-var
                 (tc/select-rows (fn [row] (= (get row col-var) col-label))))
               new-layer
               (assoc layer :=data filtered)]
               {:row-label row-label,
                :col-label col-label,
                :layer new-layer}))
           combinations))))))

(def
  v138_l2006
  (defn-
    has-faceting?
    "Check if any layer has faceting."
    [layers-vec]
    (some
     (fn* [p1__560039#] (or (:=col p1__560039#) (:=row p1__560039#)))
     layers-vec)))

(def
  v139_l2011
  (defn-
    organize-by-facets
    "Organize multiple layers by their facet groups.\n  \n  Returns: Vector of {:row-label r, :col-label c, :layers [layers-for-this-facet]}\n  All layers must have the same facet specification (or no faceting)."
    [layers-vec]
    (if-not
     (has-faceting? layers-vec)
      [{:row-label nil, :col-label nil, :layers layers-vec}]
      (let
       [all-split
        (mapcat split-by-facets layers-vec)
        by-labels
        (group-by (juxt :row-label :col-label) all-split)
        row-labels
        (sort (distinct (map :row-label all-split)))
        col-labels
        (sort (distinct (map :col-label all-split)))
        combinations
        (for [r row-labels c col-labels] [r c])]
        (mapv
         (fn
           [[r c]]
           {:row-label r,
            :col-label c,
            :layers (mapv :layer (get by-labels [r c]))})
         combinations)))))

(def
  v141_l2069
  (defn-
    infer-from-values
    "Simple fallback type inference for plain Clojure data."
    [values]
    (cond
      (every? number? values)
      :continuous
      (some
       (fn*
        [p1__560040#]
        (instance? java.time.temporal.Temporal p1__560040#))
       values)
      :temporal
      :else
      :categorical)))

(def
  v142_l2077
  (defn-
    categorical-type?
    "Check if a column type should be treated as categorical.\n\n  Categorical types create groups for statistical transforms.\n  Continuous and temporal types are visual-only by default."
    [col-type]
    (contains? #{:symbol :string :keyword :boolean :text} col-type)))

(def
  v143_l2085
  (defn-
    get-grouping-columns
    "Determine which columns should be used for grouping statistical transforms.\n\n  Returns a vector of column keywords that create groups:\n  - :=group (explicit, can be keyword or vector)\n  - :=color (if categorical)\n  - :=col (facet column, if categorical)\n  - :=row (facet row, if categorical)\n\n  Logic:\n  1. Explicit :=group always included (supports single keyword or vector)\n  2. Categorical aesthetics (:color, :col, :row) create groups\n  3. Continuous/temporal aesthetics are visual-only, don't create groups\n\n  Returns vector of column keywords, or empty vector if no grouping."
    [layer dataset]
    (let
     [explicit-group
      (:=group layer)
      color-col
      (:=color layer)
      col-facet
      (:=col layer)
      row-facet
      (:=row layer)
      categorical?
      (fn
        [col-key]
        (when
         (and col-key dataset)
          (let
           [col-type
            (try
              (tcc/typeof (get dataset col-key))
              (catch
               Exception
               _
                (infer-from-values (get dataset col-key))))]
            (categorical-type? col-type))))
      grouping-cols
      (cond->
       []
        explicit-group
        (into
         (if (vector? explicit-group) explicit-group [explicit-group]))
        (categorical? color-col)
        (conj color-col)
        (categorical? col-facet)
        (conj col-facet)
        (categorical? row-facet)
        (conj row-facet))]
      (vec (distinct grouping-cols)))))

(def
  v144_l2142
  (defn-
    layer->points
    "Convert layer to point data for rendering.\n  \n  Validates column existence and handles missing data gracefully.\n  \n  Args:\n  - layer: Layer map with :=data and aesthetic mappings\n  \n  Returns: Sequence of point maps with :x, :y (optional), :color (optional), :group (optional)"
    [layer]
    (let
     [data (:=data layer)]
      (when-not
       data
        (throw
         (ex-info "Layer missing :=data" {:layer (dissoc layer :=data)})))
      (let
       [dataset
        (ensure-dataset data)
        _
        (validate-layer-columns layer)
        x-col
        (:=x layer)
        _
        (when-not
         x-col
          (throw
           (ex-info
            "Layer missing :=x mapping"
            {:layer (select-keys layer [:=data :=y :=color])})))
        x-vals
        (vec (get dataset x-col))
        y-col
        (:=y layer)
        y-vals
        (when y-col (vec (get dataset y-col)))
        color-col
        (:=color layer)
        color-vals
        (when color-col (vec (get dataset color-col)))
        grouping-cols
        (get-grouping-columns layer dataset)
        grouping-vals
        (when
         (seq grouping-cols)
          (mapv
           (fn* [p1__560041#] (vec (get dataset p1__560041#)))
           grouping-cols))
        n
        (count x-vals)]
        (when
         (zero? n)
          (throw
           (ex-info
            "Dataset is empty (no rows)"
            {:layer-data-summary
             {:x-column x-col, :y-column y-col, :row-count 0}})))
        (map-indexed
         (fn
           [i _]
           (cond->
            {:x (nth x-vals i)}
             y-vals
             (assoc :y (nth y-vals i))
             color-vals
             (assoc :color (nth color-vals i))
             (seq grouping-cols)
             (assoc
              :group
              (mapv (fn* [p1__560042#] (nth p1__560042# i)) grouping-vals))))
         x-vals)))))

(def
  v146_l2224
  (defmulti
    apply-transform
    "Apply statistical transform to layer points.\n\n  Dispatches on the :=transformation key in the layer.\n\n  Handles grouping: if points contain :group key, applies transform per group.\n\n  Returns structured result based on transformation type:\n  - nil (no transform): {:type :raw :points points}\n  - :linear: {:type :regression :points points :fitted fitted-points} or :grouped map\n  - :histogram: {:type :histogram :points points :bars bar-specs} or :grouped map"
    (fn [layer points] (:=transformation layer))))

(def
  v148_l2238
  (defmethod
    apply-transform
    nil
    [layer points]
    {:type :raw, :points points}))

(def
  v149_l2243
  (defmulti
    transform->domain-points
    "Convert transform result to points for domain computation.\n  \n  Dispatches on the :type key in the transform result."
    (fn [transform-result] (:type transform-result))))

(def
  v151_l2250
  (defmethod
    transform->domain-points
    :raw
    [transform-result]
    (:points transform-result)))

(def
  v153_l2272
  (def
    theme
    "Global theme configuration for plots.\n  \n  Contains:\n  - :colors - Categorical color palette (ggplot2-compatible)\n  - :background - Plot background color\n  - :grid - Grid line color\n  - :default-mark - Default mark/line color\n  - :plot-width - Default plot width\n  - :plot-height - Default plot height\n  - :panel-margin-left - Left margin for plot panel\n  - :panel-margin-right - Right margin for plot panel\n  - :panel-margin-top - Top margin for plot panel\n  - :panel-margin-bottom - Bottom margin for plot panel\n  - :facet-label-offset - Offset for facet labels (top/bottom)\n  - :facet-label-side-offset - Offset for facet labels (left/right)"
    {:panel-margin-bottom 50,
     :panel-margin-top 50,
     :facet-label-offset 30,
     :grid "#FFFFFF",
     :background "#EBEBEB",
     :colors ["#F8766D" "#00BA38" "#619CFF" "#F564E3"],
     :panel-margin-left 50,
     :plot-width 600,
     :panel-margin-right 50,
     :facet-label-side-offset 20,
     :plot-height 400,
     :default-mark "#333333"}))

(def
  v154_l2301
  (defn-
    color-scale
    "ggplot2-like color scale for categorical data."
    [categories]
    (zipmap categories (cycle (:colors theme)))))

(def
  v156_l2320
  (defmulti
    render-layer
    "Render a layer for a specific target.\n  \n  Dispatches on [target plottype-or-transform], where plottype-or-transform\n  is the transformation type if present, otherwise the plottype.\n  \n  Parameters:\n  - target: rendering target (:geomviz, :vl, :plotly)\n  - layer: layer specification map\n  - transform-result: result from apply-transform\n  - alpha: opacity value\n  - context: (optional) context map with plot-level properties like :custom-x-domain, :custom-y-domain"
    (fn
      [target layer transform-result alpha & [context]]
      [target (or (:=transformation layer) (:=plottype layer))])))

(def
  v158_l2337
  (defmethod
    render-layer
    [:geomviz :line]
    [target layer transform-result alpha & [context]]
    (let
     [points
      (:points transform-result)
      color-groups
      (group-by :color points)]
      (if
       (> (count color-groups) 1)
        (let
         [colors (color-scale (keys color-groups))]
          (mapv
           (fn
             [[color group-points]]
             {:values (mapv (fn [p] [(:x p) (:y p)]) group-points),
              :layout viz/svg-line-plot,
              :attribs
              {:stroke (get colors color), :stroke-width 2, :opacity alpha}})
           color-groups))
        [{:values (mapv (fn [p] [(:x p) (:y p)]) points),
          :layout viz/svg-line-plot,
          :attribs
          {:stroke (:default-mark theme),
           :stroke-width 2,
           :opacity alpha}}]))))

(def
  v160_l2360
  (defn-
    infer-domain
    "Infer domain from data values.\n  \n  For delegation: We compute RAW domain (just min/max).\n  thi.ng/geom handles 'nice numbers' and tick placement.\n  \n  Args:\n  - values: Sequence of values (numeric or categorical)\n  \n  Returns: \n  - For numeric: [min max] vector\n  - For categorical: vector of distinct values\n  - For empty: [0 1] as fallback (prevents rendering errors)\n  - For single value: [value-10% value+10%] to provide visual range\n  \n  Edge cases handled:\n  - Empty data returns [0 1]\n  - Single value returns expanded range\n  - All identical values returns expanded range\n  - Mixed types returns categorical domain"
    [values]
    (cond
      (empty? values)
      [0 1]
      (every? number? values)
      (let
       [v-min (apply min values) v-max (apply max values)]
        (if
         (= v-min v-max)
          (let
           [expansion (max 1 (* 0.1 (Math/abs v-min)))]
            [(- v-min expansion) (+ v-max expansion)])
          [v-min v-max]))
      :else
      (vec (distinct values)))))

(def
  v161_l2401
  (defn-
    render-single-panel
    "Render a single plot panel (for use in both faceted and non-faceted plots).\n  \n  Args:\n  - layers: Vector of layers to render in this panel\n  - x-domain, y-domain: Domain for x and y axes [min max]\n  - width, height: Panel dimensions in pixels\n  - x-offset, y-offset: Horizontal and vertical offsets for this panel in pixels\n  \n  Returns: Map with :background, :plot, :hist-rects keys\n  \n  The function handles:\n  - Multi-layer composition\n  - Statistical transforms via render-layer multimethod\n  - Histogram rectangles separately from regular viz data\n  - Proper scaling and axis setup"
    [layers x-domain y-domain width height x-offset y-offset]
    (let
     [x-range
      (clojure.core/- (second x-domain) (first x-domain))
      y-range
      (clojure.core/- (second y-domain) (first y-domain))
      x-major
      (max 1 (clojure.core/* x-range 0.2))
      y-major
      (max 1 (clojure.core/* y-range 0.2))
      panel-left
      (clojure.core/+ (:panel-margin-left theme) x-offset)
      panel-right
      (clojure.core/+
       panel-left
       (clojure.core/-
        width
        (clojure.core/+
         (:panel-margin-left theme)
         (:panel-margin-right theme))))
      panel-top
      (clojure.core/+ (:panel-margin-top theme) y-offset)
      panel-bottom
      (clojure.core/+
       panel-top
       (clojure.core/-
        height
        (clojure.core/+
         (:panel-margin-top theme)
         (:panel-margin-bottom theme))))
      x-axis
      (viz/linear-axis
       {:domain x-domain,
        :range [panel-left panel-right],
        :major x-major,
        :pos panel-bottom})
      y-axis
      (viz/linear-axis
       {:domain y-domain,
        :range [panel-bottom panel-top],
        :major y-major,
        :pos panel-left})
      layer-data
      (mapcat
       (fn
         [layer]
         (let
          [points
           (layer->points layer)
           alpha
           (or (:=alpha layer) 1.0)
           transform-result
           (apply-transform layer points)]
           (render-layer :geomviz layer transform-result alpha nil)))
       layers)
      {viz-data true, rect-data false}
      (group-by
       (fn* [p1__560043#] (not= (:type p1__560043#) :rect))
       layer-data)
      plot-spec
      {:x-axis x-axis,
       :y-axis y-axis,
       :grid {:attribs {:stroke (:grid theme), :stroke-width 1}},
       :data (vec viz-data)}
      bg-rect
      (svg/rect
       [panel-left panel-top]
       (clojure.core/-
        width
        (clojure.core/+
         (:panel-margin-left theme)
         (:panel-margin-right theme)))
       (clojure.core/-
        height
        (clojure.core/+
         (:panel-margin-top theme)
         (:panel-margin-bottom theme)))
       {:fill (:background theme),
        :stroke (:grid theme),
        :stroke-width 1})
      x-scale
      (fn
        [x]
        (clojure.core/+
         panel-left
         (clojure.core/*
          (/
           (clojure.core/- x (first x-domain))
           (clojure.core/- (second x-domain) (first x-domain)))
          (clojure.core/-
           width
           (clojure.core/+
            (:panel-margin-left theme)
            (:panel-margin-right theme))))))
      y-scale
      (fn
        [y]
        (clojure.core/-
         panel-bottom
         (clojure.core/*
          (/
           (clojure.core/- y (first y-domain))
           (clojure.core/- (second y-domain) (first y-domain)))
          (clojure.core/-
           height
           (clojure.core/+
            (:panel-margin-top theme)
            (:panel-margin-bottom theme))))))
      hist-rects
      (mapv
       (fn
         [r]
         (svg/rect
          [(x-scale (:x-min r)) (y-scale (:height r))]
          (clojure.core/- (x-scale (:x-max r)) (x-scale (:x-min r)))
          (clojure.core/- (y-scale 0) (y-scale (:height r)))
          (:attribs r)))
       (or rect-data []))]
      {:background bg-rect,
       :plot (viz/svg-plot2d-cartesian plot-spec),
       :hist-rects hist-rects})))

(def
  v162_l2496
  (defn-
    get-scale-domain
    "Extract custom domain for an aesthetic from plot spec, or return nil if not specified.\n  \n  Args:\n  - spec: Plot specification map (not layers-vec)\n  - aesthetic: Keyword like :x, :y, :color\n  \n  Returns:\n  - Domain vector [min max] or nil if not specified"
    [spec aesthetic]
    (let
     [scale-key (keyword (str "=scale-" (name aesthetic)))]
      (get-in spec [scale-key :domain]))))

(def
  v164_l2523
  (defmethod
    plot-impl
    :geomviz
    [spec opts]
    (let
     [layers-vec
      (get spec :=layers [])
      _
      (validate-layers! layers-vec)
      width
      (or (:=width spec) (:width opts) (:plot-width theme))
      height
      (or (:=height spec) (:height opts) (:plot-height theme))
      facet-groups
      (organize-by-facets layers-vec)
      row-labels
      (distinct (map :row-label facet-groups))
      col-labels
      (distinct (map :col-label facet-groups))
      num-rows
      (count row-labels)
      num-cols
      (count col-labels)
      is-faceted?
      (or (> num-rows 1) (> num-cols 1))
      panel-width
      (/ width num-cols)
      panel-height
      (/ height num-rows)
      custom-x-domain
      (get-scale-domain spec :x)
      custom-y-domain
      (get-scale-domain spec :y)
      all-transformed-points
      (mapcat
       (fn
         [{:keys [layers]}]
         (mapcat
          (fn
            [layer]
            (let
             [points
              (layer->points layer)
              transform-result
              (apply-transform layer points)]
              (transform->domain-points transform-result)))
          layers))
       facet-groups)
      x-vals
      (keep :x all-transformed-points)
      y-vals
      (keep :y all-transformed-points)
      x-domain
      (or custom-x-domain (infer-domain x-vals))
      y-domain
      (or custom-y-domain (infer-domain y-vals))
      valid?
      (and
       (vector? x-domain)
       (vector? y-domain)
       (every? number? x-domain)
       (every? number? y-domain))]
      (if-not
       valid?
        (kind/hiccup
         [:div
          {:style
           {:padding "20px",
            :background-color "#fff3cd",
            :border "1px solid #ffc107",
            :border-radius "4px"}}
          [:h4
           {:style {:margin-top "0", :color "#856404"}}
           "⚠️ Cannot Render Plot"]
          [:p
           "The plot could not be rendered due to one of the following reasons:"]
          [:ul
           [:li "Dataset is empty (no data rows)"]
           [:li
            "Data contains non-numeric values where numbers are expected"]
           [:li "Column mappings reference non-existent columns"]]
          [:p [:strong "Domains computed:"]]
          [:pre
           {:style
            {:background-color "#f8f9fa",
             :padding "10px",
             :border-radius "4px"}}
           (pr-str {:x-domain x-domain, :y-domain y-domain})]
          [:p
           [:em
            "Tip: Use "
            [:code "kind/pprint"]
            " to inspect layer specifications and verify column names."]]])
        (let
         [row-positions
          (zipmap row-labels (range num-rows))
          col-positions
          (zipmap col-labels (range num-cols))
          panels
          (mapv
           (fn
             [{:keys [row-label col-label layers]}]
             (let
              [row-idx
               (get row-positions row-label 0)
               col-idx
               (get col-positions col-label 0)
               x-offset
               (clojure.core/* col-idx panel-width)
               y-offset
               (clojure.core/* row-idx panel-height)
               panel
               (render-single-panel
                layers
                x-domain
                y-domain
                panel-width
                panel-height
                x-offset
                y-offset)]
               (assoc
                panel
                :row-label
                row-label
                :col-label
                col-label
                :row-idx
                row-idx
                :col-idx
                col-idx
                :x-offset
                x-offset
                :y-offset
                y-offset)))
           facet-groups)
          all-backgrounds
          (mapv :background panels)
          all-plots
          (mapv :plot panels)
          all-hist-rects
          (mapcat :hist-rects panels)
          facet-labels
          (when
           is-faceted?
            (concat
             (when
              (> num-cols 1)
               (map
                (fn
                  [col-label]
                  (let
                   [col-idx
                    (get col-positions col-label)
                    label-x
                    (clojure.core/+
                     (clojure.core/* col-idx panel-width)
                     (/ panel-width 2))]
                    (svg/text
                     [label-x (:facet-label-offset theme)]
                     (str col-label)
                     {:text-anchor "middle",
                      :font-family "Arial, sans-serif",
                      :font-size 12,
                      :font-weight "bold"})))
                col-labels))
             (when
              (> num-rows 1)
               (map
                (fn
                  [row-label]
                  (let
                   [row-idx
                    (get row-positions row-label)
                    label-y
                    (clojure.core/+
                     (clojure.core/* row-idx panel-height)
                     (/ panel-height 2))]
                    (svg/text
                     [(:facet-label-side-offset theme) label-y]
                     (str row-label)
                     {:text-anchor "middle",
                      :font-family "Arial, sans-serif",
                      :font-size 12,
                      :font-weight "bold",
                      :transform
                      (str
                       "rotate(-90 "
                       (:facet-label-side-offset theme)
                       " "
                       label-y
                       ")")})))
                row-labels))))
          svg-elem
          (apply
           svg/svg
           {:width width, :height height}
           (concat
            all-backgrounds
            all-plots
            all-hist-rects
            (or facet-labels [])))]
          (kind/html (svg/serialize svg-elem)))))))

(def
  v166_l2666
  (def penguins (tc/drop-missing (rdatasets/palmerpenguins-penguins))))

(def v167_l2668 penguins)

(def v169_l2671 (def mtcars (rdatasets/datasets-mtcars)))

(def v170_l2673 mtcars)

(def v172_l2676 (def iris (rdatasets/datasets-iris)))

(def v173_l2678 iris)

(def
  v175_l2685
  {:bill-length-type (tcc/typeof (penguins :bill-length-mm)),
   :species-type (tcc/typeof (penguins :species)),
   :island-type (tcc/typeof (penguins :island))})

(def
  v177_l2700
  (defn
    scatter
    "Create a scatter plot layer.\n  \n  A scatter plot displays individual data points as marks (circles) positioned\n  according to their x and y values. Useful for showing relationships between\n  two continuous variables or the distribution of discrete points.\n  \n  Args (when provided):\n  - attrs-or-spec: Either a map of attributes {:alpha 0.5} or a plot spec to compose with\n  - attrs: (2-arg form) Map of visual attributes like {:alpha 0.5}\n  \n  Attributes:\n  - :alpha - Opacity (0.0 to 1.0, default 1.0)\n  \n  Returns:\n  - Plot spec map with :=layers containing a scatter layer\n  \n  Forms:\n  \n  1. No args - Returns basic scatter layer:\n     (scatter)\n     ;; => {:=layers [{:=plottype :scatter}]}\n  \n  2. With attributes - Returns scatter layer with custom attributes:\n     (scatter {:alpha 0.5})\n     ;; => {:=layers [{:=plottype :scatter :=alpha 0.5}]}\n  \n  3. Threading form - Composes with existing spec:\n     (-> (data penguins)\n         (mapping :bill-length-mm :bill-depth-mm)\n         (scatter))\n     ;; => {:=layers [{:=data penguins :=x ... :=y ... :=plottype :scatter}]}\n  \n  4. Threading with attributes:\n     (-> (data penguins)\n         (mapping :bill-length-mm :bill-depth-mm)\n         (scatter {:alpha 0.7}))\n  \n  Example - basic scatter plot:\n  (-> penguins\n      (mapping :bill-length-mm :bill-depth-mm)\n      (scatter))\n  \n  Example - semi-transparent points:\n  (-> penguins\n      (mapping :bill-length-mm :bill-depth-mm)\n      (scatter {:alpha 0.5}))\n  \n  Example - colored by species:\n  (-> penguins\n      (mapping :bill-length-mm :bill-depth-mm {:color :species})\n      (scatter))"
    ([] {:=layers [{:=plottype :scatter}]})
    ([attrs-or-spec]
     (if
      (plot-spec? attrs-or-spec)
       (=* attrs-or-spec (scatter))
       (let
        [result
         (merge {:=plottype :scatter} (update-keys attrs-or-spec =key))]
         {:=layers [result]})))
    ([spec attrs] (=* spec (scatter attrs)))))

(def
  v179_l2766
  (defmethod
    render-layer
    [:geomviz :scatter]
    [target layer transform-result alpha & [context]]
    (let
     [points
      (:points transform-result)
      color-groups
      (group-by :color points)]
      (if
       (> (count color-groups) 1)
        (let
         [colors (color-scale (keys color-groups))]
          (mapv
           (fn
             [[color group-points]]
             {:values (mapv (fn [p] [(:x p) (:y p)]) group-points),
              :layout viz/svg-scatter-plot,
              :attribs
              {:fill (get colors color),
               :stroke (get colors color),
               :stroke-width 0.5,
               :opacity alpha}})
           color-groups))
        [{:values (mapv (fn [p] [(:x p) (:y p)]) points),
          :layout viz/svg-scatter-plot,
          :attribs
          {:fill (:default-mark theme),
           :stroke (:default-mark theme),
           :stroke-width 0.5,
           :opacity alpha}}]))))

(def
  v181_l2796
  (kind/pprint
   (=*
    (data penguins)
    (mapping :bill-length-mm :bill-depth-mm)
    (scatter))))

(deftest
  t182_l2801
  (is
   ((fn*
     [p1__560044#]
     (and
      (map? p1__560044#)
      (contains? p1__560044# :=layers)
      (map? (first (:=layers p1__560044#)))
      (contains? (first (:=layers p1__560044#)) :=data)
      (contains? (first (:=layers p1__560044#)) :=x)
      (contains? (first (:=layers p1__560044#)) :=y)
      (contains? (first (:=layers p1__560044#)) :=plottype)
      (= (:=plottype (first (:=layers p1__560044#))) :scatter)))
    v181_l2796)))

(def
  v184_l2811
  (plot
   (=*
    (data penguins)
    (mapping :bill-length-mm :bill-depth-mm)
    (scatter))))

(deftest
  t185_l2816
  (is
   ((fn*
     [p1__560045#]
     (and
      (vector? p1__560045#)
      (string? (first p1__560045#))
      (clojure.string/includes? (first p1__560045#) "<svg")))
    v184_l2811)))

(def
  v187_l2823
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   kind/pprint))

(deftest
  t188_l2828
  (is
   ((fn*
     [p1__560046#]
     (and
      (map? p1__560046#)
      (contains? p1__560046# :=layers)
      (= (:=x (first (:=layers p1__560046#))) :bill-length-mm)
      (= (:=y (first (:=layers p1__560046#))) :bill-depth-mm)
      (= (:=plottype (first (:=layers p1__560046#))) :scatter)))
    v187_l2823)))

(def
  v190_l2835
  (-> penguins (mapping :bill-length-mm :bill-depth-mm) (scatter) plot))

(deftest
  t191_l2840
  (is
   ((fn*
     [p1__560047#]
     (and
      (vector? p1__560047#)
      (string? (first p1__560047#))
      (clojure.string/includes? (first p1__560047#) "<svg")))
    v190_l2835)))

(def
  v193_l2856
  (kind/pprint
   (=*
    (data penguins)
    (mapping :bill-length-mm :bill-depth-mm)
    (scatter))))

(def
  v195_l2871
  (kind/pprint
   (=*
    (data {:x [1 2 3 4 5], :y [2 4 6 8 10]})
    (mapping :x :y)
    (scatter))))

(deftest
  t196_l2877
  (is
   ((fn*
     [p1__560048#]
     (and
      (map? p1__560048#)
      (contains? p1__560048# :=layers)
      (= (:=x (first (:=layers p1__560048#))) :x)
      (= (:=y (first (:=layers p1__560048#))) :y)
      (map? (:=data (first (:=layers p1__560048#))))))
    v195_l2871)))

(def
  v198_l2884
  (plot
   (=*
    (data {:x [1 2 3 4 5], :y [2 4 6 8 10]})
    (mapping :x :y)
    (scatter))))

(deftest
  t199_l2890
  (is
   ((fn*
     [p1__560049#]
     (and
      (vector? p1__560049#)
      (string? (first p1__560049#))
      (clojure.string/includes? (first p1__560049#) "<svg")))
    v198_l2884)))

(def
  v201_l2896
  (kind/pprint
   (=*
    (data
     [{:x 1, :y 2}
      {:x 2, :y 4}
      {:x 3, :y 6}
      {:x 4, :y 8}
      {:x 5, :y 10}])
    (mapping :x :y)
    (scatter))))

(deftest
  t202_l2905
  (is
   ((fn*
     [p1__560050#]
     (and
      (map? p1__560050#)
      (contains? p1__560050# :=layers)
      (= (:=x (first (:=layers p1__560050#))) :x)
      (= (:=y (first (:=layers p1__560050#))) :y)))
    v201_l2896)))

(def
  v204_l2911
  (plot
   (=*
    (data
     [{:x 1, :y 2}
      {:x 2, :y 4}
      {:x 3, :y 6}
      {:x 4, :y 8}
      {:x 5, :y 10}])
    (mapping :x :y)
    (scatter))))

(deftest
  t205_l2920
  (is
   ((fn*
     [p1__560051#]
     (and
      (vector? p1__560051#)
      (string? (first p1__560051#))
      (clojure.string/includes? (first p1__560051#) "<svg")))
    v204_l2911)))

(def
  v207_l2942
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   kind/pprint))

(deftest
  t208_l2947
  (is
   ((fn*
     [p1__560052#]
     (and
      (map? p1__560052#)
      (contains? p1__560052# :=layers)
      (= (:=x (first (:=layers p1__560052#))) :bill-length-mm)
      (= (:=y (first (:=layers p1__560052#))) :bill-depth-mm)
      (= (:=plottype (first (:=layers p1__560052#))) :scatter)))
    v207_l2942)))

(def
  v210_l2954
  (-> penguins (mapping :bill-length-mm :bill-depth-mm) (scatter) plot))

(deftest
  t211_l2959
  (is
   ((fn*
     [p1__560053#]
     (and
      (vector? p1__560053#)
      (string? (first p1__560053#))
      (clojure.string/includes? (first p1__560053#) "<svg")))
    v210_l2954)))

(def
  v213_l2965
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (scatter)
   kind/pprint))

(deftest
  t214_l2970
  (is
   ((fn*
     [p1__560054#]
     (and
      (map? p1__560054#)
      (contains? p1__560054# :=layers)
      (= (:=color (first (:=layers p1__560054#))) :species)
      (= (:=x (first (:=layers p1__560054#))) :bill-length-mm)))
    v213_l2965)))

(def
  v216_l2976
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (scatter)
   plot))

(deftest
  t217_l2981
  (is
   ((fn*
     [p1__560055#]
     (and
      (vector? p1__560055#)
      (string? (first p1__560055#))
      (clojure.string/includes? (first p1__560055#) "<svg")))
    v216_l2976)))

(def
  v219_l2991
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter {:alpha 0.5})
   kind/pprint))

(deftest
  t220_l2996
  (is
   ((fn*
     [p1__560056#]
     (and
      (map? p1__560056#)
      (contains? p1__560056# :=layers)
      (= (:=alpha (first (:=layers p1__560056#))) 0.5)
      (= (:=plottype (first (:=layers p1__560056#))) :scatter)))
    v219_l2991)))

(def
  v222_l3002
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter {:alpha 0.5})
   plot))

(deftest
  t223_l3007
  (is
   ((fn*
     [p1__560057#]
     (and
      (vector? p1__560057#)
      (string? (first p1__560057#))
      (clojure.string/includes? (first p1__560057#) "<svg")))
    v222_l3002)))

(def
  v225_l3013
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (scatter {:alpha 0.7})
   kind/pprint))

(deftest
  t226_l3018
  (is
   ((fn*
     [p1__560058#]
     (and
      (map? p1__560058#)
      (contains? p1__560058# :=layers)
      (= (:=alpha (first (:=layers p1__560058#))) 0.7)
      (= (:=color (first (:=layers p1__560058#))) :species)))
    v225_l3013)))

(def
  v228_l3024
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (scatter {:alpha 0.7})
   plot))

(deftest
  t229_l3029
  (is
   ((fn*
     [p1__560059#]
     (and
      (vector? p1__560059#)
      (string? (first p1__560059#))
      (clojure.string/includes? (first p1__560059#) "<svg")))
    v228_l3024)))

(def
  v231_l3037
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (scale :x {:domain [30 65]})
   (scale :y {:domain [12 23]})
   kind/pprint))

(deftest
  t232_l3044
  (is
   ((fn*
     [p1__560060#]
     (and
      (map? p1__560060#)
      (contains? p1__560060# :=layers)
      (= (get-in p1__560060# [:=scale-x :domain]) [30 65])
      (= (get-in p1__560060# [:=scale-y :domain]) [12 23])))
    v231_l3037)))

(def
  v234_l3050
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (scale :x {:domain [30 65]})
   (scale :y {:domain [12 23]})
   plot))

(deftest
  t235_l3057
  (is
   ((fn*
     [p1__560061#]
     (and
      (vector? p1__560061#)
      (string? (first p1__560061#))
      (clojure.string/includes? (first p1__560061#) "<svg")))
    v234_l3050)))

(def
  v237_l3063
  (->
   {:x [1 2 3 4 5], :y [2 4 6 8 10]}
   (mapping :x :y)
   (scatter)
   kind/pprint))

(deftest
  t238_l3069
  (is
   ((fn*
     [p1__560062#]
     (and
      (map? p1__560062#)
      (contains? p1__560062# :=layers)
      (= (:=x (first (:=layers p1__560062#))) :x)
      (= (:=plottype (first (:=layers p1__560062#))) :scatter)))
    v237_l3063)))

(def
  v240_l3075
  (-> {:x [1 2 3 4 5], :y [2 4 6 8 10]} (mapping :x :y) (scatter) plot))

(deftest
  t241_l3081
  (is
   ((fn*
     [p1__560063#]
     (and
      (vector? p1__560063#)
      (string? (first p1__560063#))
      (clojure.string/includes? (first p1__560063#) "<svg")))
    v240_l3075)))

(def
  v243_l3114
  (defn
    infer-scale-type
    "Infer scale type from values in a layer."
    [layer aesthetic]
    (let
     [data
      (:=data layer)
      dataset
      (ensure-dataset data)
      col-key
      (get layer aesthetic)
      values
      (when col-key (tc/column dataset col-key))]
      (cond
        (nil? values)
        nil
        (every? number? values)
        :continuous
        (some
         (fn*
          [p1__560064#]
          (instance? java.time.temporal.Temporal p1__560064#))
         values)
        :temporal
        :else
        :categorical))))

(def
  v244_l3127
  (let
   [spec
    (=*
     (data penguins)
     (mapping :bill-length-mm :bill-depth-mm {:color :species})
     (scatter))
    layer
    (first (:=layers spec))]
    {:x-type (infer-scale-type layer :=x),
     :y-type (infer-scale-type layer :=y),
     :color-type (infer-scale-type layer :=color)}))

(def
  v246_l3153
  (defn
    linear
    "Add linear regression transformation layer.\n\n  Computes the best-fit line through data points using linear regression.\n  When combined with a categorical color aesthetic, computes separate\n  regression lines for each group automatically.\n\n  The regression is computed using ordinary least squares (OLS) and rendered\n  as a line layer. This is a statistical transform - it derives new data\n  (the fitted line) from the raw data points.\n\n  Args (when provided):\n  - spec-or-data: Plot spec to compose with, or data to create spec from\n\n  Returns:\n  - Plot spec map with :=layers containing a linear regression layer\n\n  Forms:\n\n  1. No args - Returns basic linear regression layer:\n     (linear)\n     ;; => {:=layers [{:=transformation :linear :=plottype :line}]}\n\n  2. Threading with existing spec:\n     (-> (data penguins)\n         (mapping :bill-length-mm :bill-depth-mm)\n         (linear))\n     ;; => Adds regression layer to spec\n\n  3. Composing with data directly:\n     (linear penguins)\n     ;; => {:=layers [{:=data penguins :=transformation :linear :=plottype :line}]}\n\n  Example - simple linear regression:\n  (-> penguins\n      (mapping :bill-length-mm :bill-depth-mm)\n      (linear))\n\n  Example - regression with scatter overlay:\n  (-> penguins\n      (mapping :bill-length-mm :bill-depth-mm)\n      (=+ (scatter {:alpha 0.5})\n          (linear)))\n\n  Example - grouped regression by species:\n  (-> penguins\n      (mapping :bill-length-mm :bill-depth-mm {:color :species})\n      (=+ (scatter {:alpha 0.5})\n          (linear)))\n  ;; => Computes separate regression line for each species"
    ([]
     (let
      [result {:=transformation :linear, :=plottype :line}]
       {:=layers [result]}))
    ([spec-or-data]
     (let
      [spec
       (if (plot-spec? spec-or-data) spec-or-data (data spec-or-data))]
       (=* spec (linear))))))

(def
  v248_l3215
  (defn-
    compute-linear-regression
    "Compute linear regression using fastmath.\n\n  Args:\n  - points: Sequence of point maps with :x and :y keys\n\n  Returns: Vector of 2 points representing the fitted line, or nil if regression fails.\n\n  Edge cases:\n  - Returns nil if fewer than 2 points\n  - Returns nil if all x values are identical (vertical line, undefined slope)\n  - Returns nil if all y values are identical (returns horizontal line at y mean)\n  - Returns nil if regression computation fails"
    [points]
    (when
     (>= (count points) 2)
      (let
       [x-vals
        (mapv :x points)
        y-vals
        (mapv :y points)
        x-min
        (apply min x-vals)
        x-max
        (apply max x-vals)]
        (cond
          (= x-min x-max)
          nil
          (apply = y-vals)
          (let
           [y-val (first y-vals)]
            [{:x x-min, :y y-val} {:x x-max, :y y-val}])
          :else
          (try
            (let
             [xss
              (mapv vector x-vals)
              model
              (regr/lm y-vals xss)
              intercept
              (:intercept model)
              slope
              (first (:beta model))]
              (when
               (and
                (number? intercept)
                (number? slope)
                (not (Double/isNaN intercept))
                (not (Double/isNaN slope))
                (not (Double/isInfinite slope)))
                [{:x x-min,
                  :y (clojure.core/+ intercept (clojure.core/* slope x-min))}
                 {:x x-max,
                  :y
                  (clojure.core/+ intercept (clojure.core/* slope x-max))}]))
            (catch Exception e nil)))))))

(def
  v250_l3283
  (defmethod
    apply-transform
    :linear
    [layer points]
    (when-not
     (seq points)
      (throw
       (ex-info
        "Cannot compute linear regression on empty dataset"
        {:layer-transform (:=transformation layer), :point-count 0})))
    (let
     [has-groups? (some :group points)]
      (if
       has-groups?
        (let
         [grouped
          (group-by :group points)
          group-results
          (into
           {}
           (map
            (fn
              [[group-val group-points]]
              [group-val
               {:fitted (compute-linear-regression group-points),
                :points group-points}])
            grouped))]
          {:type :grouped-regression,
           :points points,
           :groups group-results})
        (let
         [fitted (compute-linear-regression points)]
          {:type :regression,
           :points points,
           :fitted (or fitted points)})))))

(def
  v252_l3312
  (defmethod
    transform->domain-points
    :regression
    [transform-result]
    (:fitted transform-result)))

(def
  v254_l3317
  (defmethod
    transform->domain-points
    :grouped-regression
    [transform-result]
    (mapcat
     (fn [{:keys [fitted]}] fitted)
     (vals (:groups transform-result)))))

(def
  v256_l3324
  (defmethod
    render-layer
    [:geomviz :linear]
    [target layer transform-result alpha & [context]]
    (let
     [transform-type (:type transform-result)]
      (case
       transform-type
        :regression
        (let
         [fitted (:fitted transform-result)]
          [{:values (mapv (fn [p] [(:x p) (:y p)]) fitted),
            :layout viz/svg-line-plot,
            :attribs
            {:stroke (:default-mark theme),
             :stroke-width 2,
             :opacity alpha}}])
        :grouped-regression
        (let
         [groups
          (:groups transform-result)
          colors
          (color-scale (keys groups))]
          (mapv
           (fn
             [[group-val {:keys [fitted]}]]
             (when
              fitted
               {:values (mapv (fn [p] [(:x p) (:y p)]) fitted),
                :layout viz/svg-line-plot,
                :attribs
                {:stroke (get colors group-val),
                 :stroke-width 2,
                 :opacity alpha}}))
           groups))))))

(def
  v258_l3357
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (=+ (scatter {:alpha 0.5}) (linear))
   kind/pprint))

(deftest
  t259_l3363
  (is
   ((fn*
     [p1__560065#]
     (and
      (map? p1__560065#)
      (contains? p1__560065# :=layers)
      (vector? (:=layers p1__560065#))
      (= (count (:=layers p1__560065#)) 2)
      (= (:=plottype (first (:=layers p1__560065#))) :scatter)
      (= (:=transformation (second (:=layers p1__560065#))) :linear)
      (= (:=alpha (first (:=layers p1__560065#))) 0.5)))
    v258_l3357)))

(def
  v261_l3372
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (=+ (scatter {:alpha 0.5}) (linear))
   plot))

(deftest
  t262_l3378
  (is
   ((fn*
     [p1__560066#]
     (and
      (vector? p1__560066#)
      (string? (first p1__560066#))
      (clojure.string/includes? (first p1__560066#) "<svg")))
    v261_l3372)))

(def
  v264_l3388
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (=+ (scatter {:alpha 0.6}) (linear))
   kind/pprint))

(deftest
  t265_l3394
  (is
   ((fn*
     [p1__560067#]
     (and
      (map? p1__560067#)
      (contains? p1__560067# :=layers)
      (= (count (:=layers p1__560067#)) 2)
      (= (:=color (first (:=layers p1__560067#))) :species)
      (= (:=color (second (:=layers p1__560067#))) :species)
      (= (:=transformation (second (:=layers p1__560067#))) :linear)))
    v264_l3388)))

(def
  v267_l3404
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (=+ (scatter {:alpha 0.6}) (linear))
   plot))

(deftest
  t268_l3410
  (is
   ((fn*
     [p1__560068#]
     (and
      (vector? p1__560068#)
      (string? (first p1__560068#))
      (clojure.string/includes? (first p1__560068#) "<svg")))
    v267_l3404)))

(def
  v270_l3431
  (defn
    histogram
    "Add histogram transformation layer.\n\n  Bins continuous data into intervals and counts the number of observations\n  in each bin. The histogram visualizes the distribution of a single variable.\n\n  This is a statistical transform that requires domain computation - it needs\n  to know the data range before determining bin edges. When combined with a\n  categorical aesthetic (e.g., :color), computes separate histograms for each\n  group.\n\n  Args (when provided):\n  - opts-or-spec: Either options map {:bins 20} or plot spec to compose with\n  - opts: (2-arg form) Options map\n\n  Options:\n  - :bins - Binning method (default :sturges):\n    - :sturges - Sturges' formula (good for normal distributions)\n    - :sqrt - Square root of n (simple, works for most cases)\n    - :rice - Rice's rule (for larger datasets)\n    - :freedman-diaconis - Freedman-Diaconis rule (robust to outliers)\n    - Integer - Explicit number of bins (e.g., 20)\n\n  Returns:\n  - Plot spec map with :=layers containing a histogram layer\n\n  Forms:\n\n  1. No args - Returns basic histogram with default binning:\n     (histogram)\n     ;; => {:=layers [{:=transformation :histogram :=plottype :bar :=bins :sturges}]}\n\n  2. With options - Custom bin count or method:\n     (histogram {:bins 20})\n     (histogram {:bins :sqrt})\n\n  3. Threading form:\n     (-> penguins\n         (mapping :bill-length-mm nil)\n         (histogram))\n\n  4. Threading with options:\n     (-> penguins\n         (mapping :bill-length-mm nil)\n         (histogram {:bins 30}))\n\n  Example - basic histogram:\n  (-> penguins\n      (mapping :bill-length-mm nil)\n      (histogram))\n\n  Example - custom bin count:\n  (-> penguins\n      (mapping :bill-length-mm nil)\n      (histogram {:bins 20}))\n\n  Example - grouped histogram by species:\n  (-> penguins\n      (mapping :bill-length-mm nil {:color :species})\n      (histogram))\n  ;; => Separate histogram for each species with shared domain\n\n  Note: Histogram only requires :=x mapping, :=y is computed from bin counts."
    ([]
     {:=layers
      [{:=transformation :histogram, :=plottype :bar, :=bins :sturges}]})
    ([opts-or-spec]
     (if
      (plot-spec? opts-or-spec)
       (=* opts-or-spec (histogram))
       (let
        [result
         (merge
          {:=transformation :histogram, :=plottype :bar, :=bins :sturges}
          (update-keys opts-or-spec =key))]
         {:=layers [result]})))
    ([spec opts] (=* spec (histogram opts)))))

(def
  v272_l3511
  (defn-
    compute-histogram
    "Compute histogram bins using fastmath.stats/histogram.\n\n  Args:\n  - points: Sequence of point maps with :x key\n  - bins-method: Binning method - :sturges, :sqrt, :rice, :freedman-diaconis, or integer count\n\n  Returns: Vector of bar specifications with x-min, x-max, x-center, and height.\n           Returns nil if data is invalid (empty, non-numeric, or all identical values).\n\n  Edge cases:\n  - Returns nil if no points\n  - Returns nil if x values are not all numeric\n  - Returns nil if all x values are identical (single value, can't bin)"
    [points bins-method]
    (when
     (seq points)
      (let
       [x-vals (mapv :x points)]
        (when
         (every? number? x-vals)
          (let
           [x-min (apply min x-vals) x-max (apply max x-vals)]
            (when-not
             (= x-min x-max)
              (try
                (let
                 [hist-result
                  (stats/histogram x-vals (or bins-method :sturges))]
                  (mapv
                   (fn
                     [bin]
                     (let
                      [bin-min (:min bin) bin-max (:max bin)]
                       {:x-min bin-min,
                        :x-max bin-max,
                        :x-center
                        (clojure.core// (clojure.core/+ bin-min bin-max) 2.0),
                        :height (:count bin)}))
                   (:bins-maps hist-result)))
                (catch Exception e nil)))))))))

(def
  v274_l3567
  (defmethod
    apply-transform
    :histogram
    [layer points]
    (when-not
     (seq points)
      (throw
       (ex-info
        "Cannot compute histogram on empty dataset"
        {:layer-transform (:=transformation layer), :point-count 0})))
    (let
     [has-groups? (some :group points)]
      (if
       has-groups?
        (let
         [grouped
          (group-by :group points)
          group-results
          (into
           {}
           (map
            (fn
              [[group-val group-points]]
              [group-val
               {:bars (compute-histogram group-points (:=bins layer)),
                :points group-points}])
            grouped))]
          {:type :grouped-histogram, :points points, :groups group-results})
        (let
         [bins-method
          (:=bins layer)
          bars
          (compute-histogram points bins-method)]
          {:type :histogram, :points points, :bars bars})))))

(def
  v276_l3596
  (defmethod
    transform->domain-points
    :histogram
    [transform-result]
    (mapcat
     (fn
       [bar]
       [{:x (:x-min bar), :y 0} {:x (:x-max bar), :y (:height bar)}])
     (:bars transform-result))))

(def
  v277_l3603
  (defmethod
    transform->domain-points
    :grouped-histogram
    [transform-result]
    (mapcat
     (fn
       [{:keys [bars]}]
       (mapcat
        (fn
          [bar]
          [{:x (:x-min bar), :y 0} {:x (:x-max bar), :y (:height bar)}])
        bars))
     (vals (:groups transform-result)))))

(def
  v279_l3614
  (defmethod
    render-layer
    [:geomviz :histogram]
    [target layer transform-result alpha & [context]]
    (let
     [transform-type (:type transform-result)]
      (case
       transform-type
        :histogram
        (let
         [bars (:bars transform-result)]
          (mapv
           (fn
             [bar]
             {:type :rect,
              :x-min (:x-min bar),
              :x-max (:x-max bar),
              :height (:height bar),
              :attribs
              {:fill (:default-mark theme),
               :stroke (:grid theme),
               :stroke-width 1,
               :opacity alpha}})
           bars))
        :grouped-histogram
        (let
         [groups
          (:groups transform-result)
          colors
          (color-scale (keys groups))]
          (mapcat
           (fn
             [[group-val {:keys [bars]}]]
             (when
              bars
               (mapv
                (fn
                  [bar]
                  {:type :rect,
                   :x-min (:x-min bar),
                   :x-max (:x-max bar),
                   :height (:height bar),
                   :attribs
                   {:fill (get colors group-val),
                    :stroke (:grid theme),
                    :stroke-width 1,
                    :opacity alpha}})
                bars)))
           groups))))))

(def
  v281_l3656
  (-> penguins (mapping :bill-length-mm nil) (histogram) plot))

(def
  v283_l3674
  (->
   penguins
   (mapping :bill-length-mm nil)
   (histogram {:bins 15})
   kind/pprint))

(deftest
  t284_l3679
  (is
   ((fn*
     [p1__560069#]
     (and
      (map? p1__560069#)
      (contains? p1__560069# :=layers)
      (= (:=transformation (first (:=layers p1__560069#))) :histogram)
      (= (:=bins (first (:=layers p1__560069#))) 15)))
    v283_l3674)))

(def
  v286_l3685
  (->
   penguins
   (mapping :bill-length-mm nil)
   (histogram {:bins 15})
   plot))

(deftest
  t287_l3690
  (is
   ((fn*
     [p1__560070#]
     (and
      (vector? p1__560070#)
      (string? (first p1__560070#))
      (clojure.string/includes? (first p1__560070#) "<svg")))
    v286_l3685)))

(def
  v289_l3699
  (->
   penguins
   (mapping :bill-length-mm nil)
   (histogram {:bins :sqrt})
   kind/pprint))

(deftest
  t290_l3704
  (is
   ((fn*
     [p1__560071#]
     (and
      (map? p1__560071#)
      (contains? p1__560071# :=layers)
      (= (:=bins (first (:=layers p1__560071#))) :sqrt)))
    v289_l3699)))

(def
  v292_l3709
  (->
   penguins
   (mapping :bill-length-mm nil)
   (histogram {:bins :sqrt})
   plot))

(deftest
  t293_l3714
  (is
   ((fn*
     [p1__560072#]
     (and
      (vector? p1__560072#)
      (string? (first p1__560072#))
      (clojure.string/includes? (first p1__560072#) "<svg")))
    v292_l3709)))

(def
  v295_l3729
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (=+ (scatter {:alpha 0.5}) (linear))
   kind/pprint))

(deftest
  t296_l3735
  (is
   ((fn*
     [p1__560073#]
     (and
      (map? p1__560073#)
      (contains? p1__560073# :=layers)
      (= (count (:=layers p1__560073#)) 2)
      (= (:=color (first (:=layers p1__560073#))) :species)
      (= (:=transformation (second (:=layers p1__560073#))) :linear)))
    v295_l3729)))

(def
  v298_l3742
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (=+ (scatter {:alpha 0.5}) (linear))
   plot))

(deftest
  t299_l3748
  (is
   ((fn*
     [p1__560074#]
     (and
      (vector? p1__560074#)
      (string? (first p1__560074#))
      (clojure.string/includes? (first p1__560074#) "<svg")))
    v298_l3742)))

(def
  v301_l3763
  (->
   penguins
   (mapping :bill-length-mm nil {:color :species, :alpha 0.7})
   (histogram)
   kind/pprint))

(deftest
  t302_l3768
  (is
   ((fn*
     [p1__560075#]
     (and
      (map? p1__560075#)
      (contains? p1__560075# :=layers)
      (= (:=transformation (first (:=layers p1__560075#))) :histogram)
      (= (:=color (first (:=layers p1__560075#))) :species)
      (= (:=alpha (first (:=layers p1__560075#))) 0.7)))
    v301_l3763)))

(def
  v304_l3775
  (->
   penguins
   (mapping :bill-length-mm nil {:color :species, :alpha 0.7})
   (histogram)
   plot))

(deftest
  t305_l3780
  (is
   ((fn*
     [p1__560076#]
     (and
      (vector? p1__560076#)
      (string? (first p1__560076#))
      (clojure.string/includes? (first p1__560076#) "<svg")))
    v304_l3775)))

(def
  v307_l3799
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :body-mass-g})
   (=+ (scatter {:alpha 0.5}) (linear))
   kind/pprint))

(deftest
  t308_l3805
  (is
   ((fn*
     [p1__560077#]
     (and
      (map? p1__560077#)
      (contains? p1__560077# :=layers)
      (= (count (:=layers p1__560077#)) 2)
      (= (:=color (first (:=layers p1__560077#))) :body-mass-g)
      (= (:=transformation (second (:=layers p1__560077#))) :linear)))
    v307_l3799)))

(def
  v310_l3812
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :body-mass-g})
   (=+ (scatter {:alpha 0.5}) (linear))
   plot))

(deftest
  t311_l3818
  (is
   ((fn*
     [p1__560078#]
     (and
      (vector? p1__560078#)
      (string? (first p1__560078#))
      (clojure.string/includes? (first p1__560078#) "<svg")))
    v310_l3812)))

(def
  v313_l3841
  (->
   mtcars
   (mapping :wt :mpg {:group :cyl})
   (=+ (scatter) (linear))
   kind/pprint))

(deftest
  t314_l3847
  (is
   ((fn*
     [p1__560079#]
     (and
      (map? p1__560079#)
      (contains? p1__560079# :=layers)
      (= (count (:=layers p1__560079#)) 2)
      (= (:=group (first (:=layers p1__560079#))) :cyl)
      (= (:=transformation (second (:=layers p1__560079#))) :linear)))
    v313_l3841)))

(def
  v316_l3854
  (->
   mtcars
   (mapping :wt :mpg {:group :cyl})
   (=+ (scatter) (linear))
   plot))

(deftest
  t317_l3860
  (is
   ((fn*
     [p1__560080#]
     (and
      (vector? p1__560080#)
      (string? (first p1__560080#))
      (clojure.string/includes? (first p1__560080#) "<svg")))
    v316_l3854)))

(def
  v319_l3873
  (->
   penguins
   (mapping
    :bill-length-mm
    :bill-depth-mm
    {:color :sex, :group :species})
   (=+ (scatter {:alpha 0.5}) (linear))
   kind/pprint))

(deftest
  t320_l3879
  (is
   ((fn*
     [p1__560081#]
     (and
      (map? p1__560081#)
      (contains? p1__560081# :=layers)
      (= (:=color (first (:=layers p1__560081#))) :sex)
      (= (:=group (first (:=layers p1__560081#))) :species)
      (= (:=transformation (second (:=layers p1__560081#))) :linear)))
    v319_l3873)))

(def
  v322_l3886
  (->
   penguins
   (mapping
    :bill-length-mm
    :bill-depth-mm
    {:color :sex, :group :species})
   (=+ (scatter {:alpha 0.5}) (linear))
   plot))

(deftest
  t323_l3892
  (is
   ((fn*
     [p1__560082#]
     (and
      (vector? p1__560082#)
      (string? (first p1__560082#))
      (clojure.string/includes? (first p1__560082#) "<svg")))
    v322_l3886)))

(def
  v325_l4013
  (kind/pprint
   (=*
    (data penguins)
    (mapping :bill-length-mm :bill-depth-mm)
    (scatter)
    (facet {:col :species}))))

(deftest
  t326_l4019
  (is
   ((fn*
     [p1__560083#]
     (and
      (map? p1__560083#)
      (contains? p1__560083# :=layers)
      (= (:=col (first (:=layers p1__560083#))) :species)))
    v325_l4013)))

(def
  v328_l4024
  (plot
   (=*
    (data penguins)
    (mapping :bill-length-mm :bill-depth-mm)
    (scatter)
    (facet {:col :species}))))

(deftest
  t329_l4030
  (is
   ((fn*
     [p1__560084#]
     (and
      (vector? p1__560084#)
      (string? (first p1__560084#))
      (clojure.string/includes? (first p1__560084#) "<svg")))
    v328_l4024)))

(def
  v331_l4036
  (->
   penguins
   (mapping :bill-length-mm nil)
   (histogram)
   (facet {:col :species})
   plot))

(def
  v333_l4047
  (kind/pprint
   (=*
    (data penguins)
    (mapping :bill-length-mm :bill-depth-mm)
    (scatter)
    (facet {:row :species}))))

(deftest
  t334_l4053
  (is
   ((fn*
     [p1__560085#]
     (and
      (map? p1__560085#)
      (contains? p1__560085# :=layers)
      (= (:=row (first (:=layers p1__560085#))) :species)))
    v333_l4047)))

(def
  v336_l4058
  (plot
   (=*
    (data penguins)
    (mapping :bill-length-mm :bill-depth-mm)
    (scatter)
    (facet {:row :species}))))

(deftest
  t337_l4064
  (is
   ((fn*
     [p1__560086#]
     (and
      (vector? p1__560086#)
      (string? (first p1__560086#))
      (clojure.string/includes? (first p1__560086#) "<svg")))
    v336_l4058)))

(def
  v339_l4074
  (kind/pprint
   (=*
    (data penguins)
    (mapping :bill-length-mm :bill-depth-mm)
    (scatter)
    (facet {:row :island, :col :sex}))))

(deftest
  t340_l4080
  (is
   ((fn*
     [p1__560087#]
     (and
      (map? p1__560087#)
      (contains? p1__560087# :=layers)
      (= (:=row (first (:=layers p1__560087#))) :island)
      (= (:=col (first (:=layers p1__560087#))) :sex)))
    v339_l4074)))

(def
  v342_l4086
  (plot
   (=*
    (data penguins)
    (mapping :bill-length-mm :bill-depth-mm)
    (scatter)
    (facet {:row :island, :col :sex}))))

(deftest
  t343_l4092
  (is
   ((fn*
     [p1__560088#]
     (and
      (vector? p1__560088#)
      (string? (first p1__560088#))
      (clojure.string/includes? (first p1__560088#) "<svg")))
    v342_l4086)))

(def
  v345_l4111
  (plot
   (=*
    (data penguins)
    (mapping :bill-length-mm :bill-depth-mm {:color :species})
    (=+ (scatter {:alpha 0.5}) (linear))
    (facet {:col :island}))))

(def
  v347_l4122
  (->
   (data penguins)
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (=+ (scatter {:alpha 0.5}) (linear))
   (facet {:col :island})
   kind/pprint))

(deftest
  t348_l4131
  (is
   ((fn*
     [p1__560089#]
     (and
      (map? p1__560089#)
      (contains? p1__560089# :=layers)
      (= (count (:=layers p1__560089#)) 2)
      (= (:=col (first (:=layers p1__560089#))) :island)
      (= (:=color (first (:=layers p1__560089#))) :species)))
    v347_l4122)))

(def
  v350_l4138
  (->
   (data penguins)
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (=+ (scatter {:alpha 0.5}) (linear))
   (facet {:col :island})
   plot))

(deftest
  t351_l4147
  (is
   ((fn*
     [p1__560090#]
     (and
      (vector? p1__560090#)
      (string? (first p1__560090#))
      (clojure.string/includes? (first p1__560090#) "<svg")))
    v350_l4138)))

(def
  v353_l4169
  (kind/pprint
   (=*
    (data mtcars)
    (mapping :wt :mpg)
    (scatter)
    (scale :y {:domain [0 40]}))))

(deftest
  t354_l4175
  (is
   ((fn*
     [p1__560091#]
     (and
      (map? p1__560091#)
      (contains? p1__560091# :=layers)
      (= (get-in p1__560091# [:=scale-y :domain]) [0 40])))
    v353_l4169)))

(def
  v356_l4180
  (plot
   (=*
    (data mtcars)
    (mapping :wt :mpg)
    (scatter)
    (scale :y {:domain [0 40]}))))

(deftest
  t357_l4186
  (is
   ((fn*
     [p1__560092#]
     (and
      (vector? p1__560092#)
      (string? (first p1__560092#))
      (clojure.string/includes? (first p1__560092#) "<svg")))
    v356_l4180)))

(def
  v359_l4197
  (plot
   (=*
    (data penguins)
    (mapping :bill-length-mm :bill-depth-mm)
    (scatter)
    (scale :x {:domain [30 65]})
    (scale :y {:domain [10 25]}))))

(def
  v361_l4218
  (defn-
    layer->vl-data
    "Convert layer data to Vega-Lite data format."
    [layer]
    (let
     [data
      (:=data layer)
      dataset
      (ensure-dataset data)
      x-col
      (:=x layer)
      y-col
      (:=y layer)
      color-col
      (:=color layer)
      row-col
      (:=row layer)
      col-col
      (:=col layer)
      rows
      (tc/rows dataset :as-maps)]
      (mapv
       (fn
         [row]
         (cond->
          {}
           x-col
           (assoc (keyword (name x-col)) (get row x-col))
           y-col
           (assoc (keyword (name y-col)) (get row y-col))
           color-col
           (assoc (keyword (name color-col)) (get row color-col))
           row-col
           (assoc (keyword (name row-col)) (get row row-col))
           col-col
           (assoc (keyword (name col-col)) (get row col-col))))
       rows))))

(def
  v362_l4238
  (defn-
    layer->vl-encoding
    "Create Vega-Lite encoding for a layer."
    [layer context]
    (let
     [x-col
      (:=x layer)
      y-col
      (:=y layer)
      color-col
      (:=color layer)
      alpha
      (:=alpha layer)
      custom-x-domain
      (:custom-x-domain context)
      custom-y-domain
      (:custom-y-domain context)
      tooltip-fields
      (cond->
       []
        x-col
        (conj {:field (name x-col), :type "quantitative"})
        y-col
        (conj {:field (name y-col), :type "quantitative"})
        color-col
        (conj {:field (name color-col), :type "nominal"}))]
      (cond->
       {}
        x-col
        (assoc
         :x
         (cond->
          {:field (name x-col), :type "quantitative"}
           true
           (assoc
            :scale
            (merge
             {:zero false}
             (when custom-x-domain {:domain custom-x-domain})))))
        y-col
        (assoc
         :y
         (cond->
          {:field (name y-col), :type "quantitative"}
           true
           (assoc
            :scale
            (merge
             {:zero false}
             (when custom-y-domain {:domain custom-y-domain})))))
        color-col
        (assoc
         :color
         {:field (name color-col),
          :type "nominal",
          :scale {:range (:colors theme)}})
        alpha
        (assoc :opacity {:value alpha})
        (seq tooltip-fields)
        (assoc :tooltip tooltip-fields)))))

(def
  v364_l4267
  (defmethod
    render-layer
    [:vl :scatter]
    [target layer transform-result alpha & [context]]
    (let
     [vl-data (layer->vl-data layer)]
      [{:mark "circle",
        :data {:values vl-data},
        :encoding (layer->vl-encoding layer context)}])))

(def
  v365_l4274
  (defmethod
    render-layer
    [:vl :linear]
    [target layer transform-result alpha & [context]]
    (let
     [transform-type (:type transform-result)]
      (case
       transform-type
        :regression
        (let
         [fitted
          (:fitted transform-result)
          fitted-data
          (mapv
           (fn
             [p]
             {(keyword (name (:=x layer))) (:x p),
              (keyword (name (:=y layer))) (:y p)})
           fitted)]
          [{:mark "line",
            :data {:values fitted-data},
            :encoding (layer->vl-encoding layer context)}])
        :grouped-regression
        (let
         [groups
          (:groups transform-result)
          grouping-cols
          (get-grouping-columns layer (ensure-dataset (:=data layer)))]
          (mapv
           (fn
             [[group-val {:keys [fitted]}]]
             (when
              fitted
               (let
                [group-map
                 (zipmap
                  (map
                   (fn* [p1__560093#] (keyword (name p1__560093#)))
                   grouping-cols)
                  (if (vector? group-val) group-val [group-val]))
                 group-fitted-data
                 (mapv
                  (fn
                    [p]
                    (merge
                     {(keyword (name (:=x layer))) (:x p),
                      (keyword (name (:=y layer))) (:y p)}
                     group-map))
                  fitted)]
                 {:mark "line",
                  :data {:values group-fitted-data},
                  :encoding (layer->vl-encoding layer context)})))
           groups))))))

(def
  v366_l4308
  (defmethod
    render-layer
    [:vl :histogram]
    [target layer transform-result alpha & [context]]
    (let
     [transform-type (:type transform-result)]
      (case
       transform-type
        :histogram
        (let
         [bars
          (:bars transform-result)
          bar-data
          (mapv
           (fn
             [bar]
             {:bin-start (:x-min bar),
              :bin-end (:x-max bar),
              :count (:height bar)})
           bars)]
          [{:mark "bar",
            :data {:values bar-data},
            :encoding
            {:x
             {:field "bin-start",
              :type "quantitative",
              :bin
              {:binned true,
               :step (- (:x-max (first bars)) (:x-min (first bars)))},
              :axis {:title (name (:=x layer))}},
             :x2 {:field "bin-end"},
             :y {:field "count", :type "quantitative"},
             :tooltip
             [{:field "bin-start", :type "quantitative", :title "Min"}
              {:field "bin-end", :type "quantitative", :title "Max"}
              {:field "count", :type "quantitative", :title "Count"}]}}])
        :grouped-histogram
        (let
         [groups
          (:groups transform-result)
          grouping-cols
          (get-grouping-columns layer (ensure-dataset (:=data layer)))]
          (mapcat
           (fn
             [[group-val {:keys [bars]}]]
             (when
              bars
               (let
                [group-map
                 (zipmap
                  (map
                   (fn* [p1__560094#] (keyword (name p1__560094#)))
                   grouping-cols)
                  (if (vector? group-val) group-val [group-val]))
                 bar-data
                 (mapv
                  (fn
                    [bar]
                    (merge
                     {:bin-start (:x-min bar),
                      :bin-end (:x-max bar),
                      :count (:height bar)}
                     group-map))
                  bars)
                 tooltip-fields
                 (concat
                  [{:field "bin-start", :type "quantitative", :title "Min"}
                   {:field "bin-end", :type "quantitative", :title "Max"}
                   {:field "count", :type "quantitative", :title "Count"}]
                  (map
                   (fn*
                    [p1__560095#]
                    (hash-map :field (name p1__560095#) :type "nominal"))
                   grouping-cols))]
                 [{:mark "bar",
                   :data {:values bar-data},
                   :encoding
                   (merge
                    {:x
                     {:field "bin-start",
                      :type "quantitative",
                      :bin
                      {:binned true,
                       :step (- (:x-max (first bars)) (:x-min (first bars)))},
                      :axis {:title (name (:=x layer))}},
                     :x2 {:field "bin-end"},
                     :y {:field "count", :type "quantitative"},
                     :tooltip tooltip-fields}
                    (when
                     (seq grouping-cols)
                      {:color
                       {:field (name (first grouping-cols)),
                        :type "nominal"}}))}])))
           groups))))))

(def
  v367_l4368
  (defmethod
    plot-impl
    :vl
    [spec opts]
    (let
     [layers-vec
      (get spec :=layers [])
      _
      (validate-layers! layers-vec)
      width
      (or (:=width spec) (:width opts) 600)
      height
      (or (:=height spec) (:height opts) 400)
      is-faceted?
      (has-faceting? layers-vec)
      row-var
      (when is-faceted? (some :=row layers-vec))
      col-var
      (when is-faceted? (some :=col layers-vec))
      custom-x-domain
      (get-scale-domain spec :x)
      custom-y-domain
      (get-scale-domain spec :y)
      context
      {:custom-x-domain custom-x-domain,
       :custom-y-domain custom-y-domain}
      vl-layers
      (mapcat
       (fn
         [layer]
         (let
          [points
           (layer->points layer)
           alpha
           (or (:=alpha layer) 1.0)
           transform-result
           (apply-transform layer points)]
           (render-layer :vl layer transform-result alpha context)))
       layers-vec)
      vl-layers
      (remove nil? (flatten vl-layers))
      ggplot2-config
      {:view {:stroke "transparent"},
       :background (:background theme),
       :axis
       {:gridColor (:grid theme),
        :domainColor (:grid theme),
        :tickColor (:grid theme)},
       :mark {:color (:default-mark theme)}}
      spec
      (cond
        is-faceted?
        (let
         [layers-without-data
          (mapv (fn* [p1__560096#] (dissoc p1__560096# :data)) vl-layers)
          all-data
          (mapcat layer->vl-data layers-vec)
          first-layer
          (first layers-vec)
          dataset
          (ensure-dataset (:=data first-layer))
          num-cols
          (if col-var (count (distinct (tc/column dataset col-var))) 1)
          num-rows
          (if row-var (count (distinct (tc/column dataset row-var))) 1)]
          (cond->
           {:$schema "https://vega.github.io/schema/vega-lite/v5.json",
            :data {:values all-data},
            :width (int (/ width num-cols)),
            :height (int (/ height num-rows)),
            :config ggplot2-config,
            :spec {:layer layers-without-data}}
            col-var
            (assoc
             :facet
             {:column {:field (name col-var), :type "nominal"}})
            row-var
            (assoc-in
             [:facet :row]
             {:field (name row-var), :type "nominal"})))
        (> (count vl-layers) 1)
        {:$schema "https://vega.github.io/schema/vega-lite/v5.json",
         :width width,
         :height height,
         :config ggplot2-config,
         :layer vl-layers}
        :else
        (merge
         {:$schema "https://vega.github.io/schema/vega-lite/v5.json",
          :width width,
          :height height,
          :config ggplot2-config}
         (first vl-layers)))]
      (kind/vega-lite spec))))

(def
  v369_l4459
  (defmethod
    render-layer
    [:plotly :scatter]
    [target layer transform-result alpha & [context]]
    (let
     [points (:points transform-result) color-col (:=color layer)]
      (if
       color-col
        (let
         [color-groups (group-by :color points)]
          (map-indexed
           (fn
             [idx [color-val group-points]]
             {:type "scatter",
              :mode "markers",
              :x (mapv :x group-points),
              :y (mapv :y group-points),
              :name (str color-val),
              :marker
              {:color (get (:colors theme) idx (:default-mark theme)),
               :size 8}})
           color-groups))
        [{:type "scatter",
          :mode "markers",
          :x (mapv :x points),
          :y (mapv :y points),
          :marker {:color (:default-mark theme), :size 8},
          :showlegend false}]))))

(def
  v370_l4484
  (defmethod
    render-layer
    [:plotly :linear]
    [target layer transform-result alpha & [context]]
    (let
     [transform-type (:type transform-result)]
      (case
       transform-type
        :regression
        (let
         [fitted (:fitted transform-result)]
          [{:type "scatter",
            :mode "lines",
            :x (mapv :x fitted),
            :y (mapv :y fitted),
            :line {:color (:default-mark theme), :width 2},
            :showlegend false}])
        :grouped-regression
        (let
         [groups (:groups transform-result)]
          (map-indexed
           (fn
             [idx [group-val {:keys [fitted]}]]
             (when
              fitted
               {:type "scatter",
                :mode "lines",
                :x (mapv :x fitted),
                :y (mapv :y fitted),
                :name (str group-val " (fit)"),
                :line
                {:color (get (:colors theme) idx (:default-mark theme)),
                 :width 2},
                :showlegend false}))
           groups))))))

(def
  v371_l4514
  (defmethod
    render-layer
    [:plotly :histogram]
    [target layer transform-result alpha & [context]]
    (let
     [transform-type (:type transform-result)]
      (case
       transform-type
        :histogram
        (let
         [bars (:bars transform-result)]
          [{:type "bar",
            :x (mapv (fn [b] (/ (+ (:x-min b) (:x-max b)) 2)) bars),
            :y (mapv :height bars),
            :width (mapv (fn [b] (- (:x-max b) (:x-min b))) bars),
            :marker
            {:color (:default-mark theme),
             :line {:color (:grid theme), :width 1}},
            :showlegend false}])
        :grouped-histogram
        (let
         [groups (:groups transform-result)]
          (map-indexed
           (fn
             [idx [group-val {:keys [bars]}]]
             (when
              bars
               {:type "bar",
                :x (mapv (fn [b] (/ (+ (:x-min b) (:x-max b)) 2)) bars),
                :y (mapv :height bars),
                :width (mapv (fn [b] (- (:x-max b) (:x-min b))) bars),
                :name (str group-val),
                :marker
                {:color (get (:colors theme) idx (:default-mark theme)),
                 :line {:color (:grid theme), :width 1}}}))
           groups))))))

(def
  v372_l4544
  (defmethod
    plot-impl
    :plotly
    [spec opts]
    (let
     [layers-vec
      (get spec :=layers [])
      _
      (validate-layers! layers-vec)
      width
      (or (:=width spec) (:width opts) 600)
      height
      (or (:=height spec) (:height opts) 400)
      is-faceted?
      (has-faceting? layers-vec)
      row-var
      (when is-faceted? (some :=row layers-vec))
      col-var
      (when is-faceted? (some :=col layers-vec))
      custom-x-domain
      (get-scale-domain spec :x)
      custom-y-domain
      (get-scale-domain spec :y)
      context
      {:custom-x-domain custom-x-domain,
       :custom-y-domain custom-y-domain}
      plotly-traces
      (mapcat
       (fn
         [layer]
         (let
          [points
           (layer->points layer)
           alpha
           (or (:=alpha layer) 1.0)
           transform-result
           (apply-transform layer points)]
           (render-layer :plotly layer transform-result alpha context)))
       layers-vec)
      plotly-traces
      (remove nil? plotly-traces)
      first-layer
      (first layers-vec)
      x-name
      (when-let [x-col (:=x first-layer)] (name x-col))
      y-name
      (when-let [y-col (:=y first-layer)] (name y-col))
      layout
      {:width width,
       :height height,
       :plot_bgcolor (:background theme),
       :paper_bgcolor (:background theme),
       :xaxis
       {:gridcolor (:grid theme),
        :title (or x-name "x"),
        :range custom-x-domain},
       :yaxis
       {:gridcolor (:grid theme),
        :title (or y-name "y"),
        :range custom-y-domain},
       :margin {:l 60, :r 30, :t 30, :b 60}}
      spec
      (if
       is-faceted?
        (let
         [data
          (:=data first-layer)
          dataset
          (ensure-dataset data)
          row-vals
          (when row-var (sort (distinct (tc/column dataset row-var))))
          col-vals
          (when col-var (sort (distinct (tc/column dataset col-var))))
          num-rows
          (if row-var (count row-vals) 1)
          num-cols
          (if col-var (count col-vals) 1)
          faceted-traces
          (for
           [[row-idx row-val]
            (map-indexed vector (or row-vals [nil]))
            [col-idx col-val]
            (map-indexed vector (or col-vals [nil]))]
            (let
             [filtered-data
              (cond->
               dataset
                row-var
                (tc/select-rows
                 (fn* [p1__560097#] (= (get p1__560097# row-var) row-val)))
                col-var
                (tc/select-rows
                 (fn* [p1__560098#] (= (get p1__560098# col-var) col-val))))
              facet-layer
              (assoc first-layer :=data filtered-data)
              facet-points
              (layer->points facet-layer)
              transform-result
              (apply-transform facet-layer facet-points)
              subplot-idx
              (+ (* row-idx num-cols) col-idx 1)
              xaxis-ref
              (if (= subplot-idx 1) "x" (str "x" subplot-idx))
              yaxis-ref
              (if (= subplot-idx 1) "y" (str "y" subplot-idx))]
              (case
               (:type transform-result)
                :raw
                {:type "scatter",
                 :mode "markers",
                 :x (mapv :x facet-points),
                 :y (mapv :y facet-points),
                 :xaxis xaxis-ref,
                 :yaxis yaxis-ref,
                 :marker {:color (:default-mark theme), :size 6},
                 :showlegend false}
                :histogram
                (let
                 [bars (:bars transform-result)]
                  {:type "bar",
                   :x (mapv (fn [b] (/ (+ (:x-min b) (:x-max b)) 2)) bars),
                   :y (mapv :height bars),
                   :width (mapv (fn [b] (- (:x-max b) (:x-min b))) bars),
                   :xaxis xaxis-ref,
                   :yaxis yaxis-ref,
                   :marker
                   {:color (:default-mark theme),
                    :line {:color (:grid theme), :width 1}},
                   :showlegend false})
                :grouped-histogram
                (let
                 [groups
                  (:groups transform-result)
                  bars
                  (:bars (second (first groups)))]
                  {:type "bar",
                   :x (mapv (fn [b] (/ (+ (:x-min b) (:x-max b)) 2)) bars),
                   :y (mapv :height bars),
                   :width (mapv (fn [b] (- (:x-max b) (:x-min b))) bars),
                   :xaxis xaxis-ref,
                   :yaxis yaxis-ref,
                   :marker
                   {:color (:default-mark theme),
                    :line {:color (:grid theme), :width 1}},
                   :showlegend false})
                nil)))
          subplot-layout
          (merge
           layout
           {:grid
            {:rows num-rows, :columns num-cols, :pattern "independent"},
            :annotations
            (concat
             (when
              col-var
               (for
                [[idx val] (map-indexed vector col-vals)]
                 {:text (str val),
                  :xref "paper",
                  :yref "paper",
                  :x (/ (+ idx 0.5) num-cols),
                  :y 1.0,
                  :xanchor "center",
                  :yanchor "bottom",
                  :showarrow false}))
             (when
              row-var
               (for
                [[idx val] (map-indexed vector row-vals)]
                 {:text (str val),
                  :xref "paper",
                  :yref "paper",
                  :x -0.05,
                  :y (- 1.0 (/ (+ idx 0.5) num-rows)),
                  :xanchor "right",
                  :yanchor "middle",
                  :showarrow false})))})]
          {:data (remove nil? faceted-traces), :layout subplot-layout})
        {:data plotly-traces, :layout layout})]
      (kind/plotly spec))))

(def
  v374_l4703
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (target :vl)
   kind/pprint))

(deftest
  t375_l4709
  (is
   ((fn*
     [p1__560099#]
     (and
      (map? p1__560099#)
      (contains? p1__560099# :=layers)
      (= (:=target p1__560099#) :vl)
      (= (:=x (first (:=layers p1__560099#))) :bill-length-mm)
      (= (:=y (first (:=layers p1__560099#))) :bill-depth-mm)))
    v374_l4703)))

(def
  v377_l4716
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (target :vl)
   plot))

(deftest
  t378_l4722
  (is
   ((fn*
     [p1__560100#]
     (and
      (map? p1__560100#)
      (contains? p1__560100# :data)
      (contains? p1__560100# :mark)
      (contains? p1__560100# :encoding)))
    v377_l4716)))

(def
  v380_l4738
  (->
   mtcars
   (mapping :wt :mpg)
   (=+ (scatter) (linear))
   (target :vl)
   kind/pprint))

(deftest
  t381_l4745
  (is
   ((fn*
     [p1__560101#]
     (and
      (map? p1__560101#)
      (contains? p1__560101# :=layers)
      (= (count (:=layers p1__560101#)) 2)
      (= (:=target p1__560101#) :vl)))
    v380_l4738)))

(def
  v383_l4751
  (->
   mtcars
   (mapping :wt :mpg)
   (=+ (scatter) (linear))
   (target :vl)
   plot))

(deftest
  t384_l4758
  (is
   ((fn*
     [p1__560102#]
     (and
      (map? p1__560102#)
      (contains? p1__560102# :layer)
      (= (count (:layer p1__560102#)) 2)))
    v383_l4751)))

(def
  v386_l4772
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (=+ (scatter) (linear))
   (target :vl)
   kind/pprint))

(deftest
  t387_l4779
  (is
   ((fn*
     [p1__560103#]
     (and
      (map? p1__560103#)
      (contains? p1__560103# :=layers)
      (= (:=color (first (:=layers p1__560103#))) :species)
      (= (:=target p1__560103#) :vl)))
    v386_l4772)))

(def
  v389_l4785
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (=+ (scatter) (linear))
   (target :vl)
   plot))

(deftest
  t390_l4792
  (is
   ((fn*
     [p1__560104#]
     (and
      (map? p1__560104#)
      (contains? p1__560104# :layer)
      (>= (count (:layer p1__560104#)) 2)))
    v389_l4785)))

(def
  v392_l4805
  (->
   penguins
   (mapping :bill-length-mm nil)
   (histogram)
   (target :vl)
   plot))

(def
  v394_l4821
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (facet {:col :species})
   (target :vl)
   kind/pprint))

(deftest
  t395_l4828
  (is
   ((fn*
     [p1__560105#]
     (and
      (map? p1__560105#)
      (contains? p1__560105# :=layers)
      (= (:=col (first (:=layers p1__560105#))) :species)
      (= (:=target p1__560105#) :vl)))
    v394_l4821)))

(def
  v397_l4834
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (facet {:col :species})
   (target :vl)
   plot))

(deftest
  t398_l4841
  (is
   ((fn*
     [p1__560106#]
     (and
      (map? p1__560106#)
      (contains? p1__560106# :facet)
      (contains? p1__560106# :spec)))
    v397_l4834)))

(def
  v400_l4857
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (facet {:row :island, :col :sex})
   (target :vl)
   (size 800 600)
   kind/pprint))

(deftest
  t401_l4865
  (is
   ((fn*
     [p1__560107#]
     (and
      (map? p1__560107#)
      (contains? p1__560107# :=layers)
      (= (:=target p1__560107#) :vl)
      (= (:=row (first (:=layers p1__560107#))) :island)
      (= (:=col (first (:=layers p1__560107#))) :sex)
      (= (:=width p1__560107#) 800)
      (= (:=height p1__560107#) 600)))
    v400_l4857)))

(def
  v403_l4874
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (facet {:row :island, :col :sex})
   (target :vl)
   (size 800 600)
   plot))

(deftest
  t404_l4882
  (is
   ((fn*
     [p1__560108#]
     (and
      (map? p1__560108#)
      (contains? p1__560108# :spec)
      (contains? p1__560108# :facet)
      (= (-> p1__560108# :facet :row :field) "island")
      (= (-> p1__560108# :facet :column :field) "sex")))
    v403_l4874)))

(def
  v406_l4898
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (scale :x {:domain [30 65]})
   (scale :y {:domain [10 25]})
   (target :vl)
   kind/pprint))

(deftest
  t407_l4906
  (is
   ((fn*
     [p1__560109#]
     (and
      (map? p1__560109#)
      (contains? p1__560109# :=layers)
      (= (get-in p1__560109# [:=scale-x :domain]) [30 65])
      (= (:=target p1__560109#) :vl)))
    v406_l4898)))

(def
  v409_l4912
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (scale :x {:domain [30 65]})
   (scale :y {:domain [10 25]})
   (target :vl)
   plot))

(deftest
  t410_l4920
  (is
   ((fn*
     [p1__560110#]
     (and (map? p1__560110#) (contains? p1__560110# :encoding)))
    v409_l4912)))

(def
  v412_l4957
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (target :plotly)
   kind/pprint))

(deftest
  t413_l4963
  (is
   ((fn*
     [p1__560111#]
     (and
      (map? p1__560111#)
      (contains? p1__560111# :=layers)
      (= (:=target p1__560111#) :plotly)
      (= (:=x (first (:=layers p1__560111#))) :bill-length-mm)
      (= (:=y (first (:=layers p1__560111#))) :bill-depth-mm)))
    v412_l4957)))

(def
  v415_l4970
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (target :plotly)
   plot))

(deftest
  t416_l4976
  (is
   ((fn*
     [p1__560112#]
     (and
      (map? p1__560112#)
      (contains? p1__560112# :data)
      (contains? p1__560112# :layout)
      (sequential? (:data p1__560112#))))
    v415_l4970)))

(def
  v418_l4991
  (->
   mtcars
   (mapping :wt :mpg)
   (=+ (scatter) (linear))
   (target :plotly)
   kind/pprint))

(deftest
  t419_l4998
  (is
   ((fn*
     [p1__560113#]
     (and
      (map? p1__560113#)
      (contains? p1__560113# :=layers)
      (= (count (:=layers p1__560113#)) 2)
      (= (:=target p1__560113#) :plotly)))
    v418_l4991)))

(def
  v421_l5004
  (->
   mtcars
   (mapping :wt :mpg)
   (=+ (scatter) (linear))
   (target :plotly)
   plot))

(deftest
  t422_l5011
  (is
   ((fn*
     [p1__560114#]
     (and
      (map? p1__560114#)
      (contains? p1__560114# :data)
      (>= (count (:data p1__560114#)) 2)))
    v421_l5004)))

(def
  v424_l5025
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (=+ (scatter) (linear))
   (target :plotly)
   kind/pprint))

(deftest
  t425_l5032
  (is
   ((fn*
     [p1__560115#]
     (and
      (map? p1__560115#)
      (contains? p1__560115# :=layers)
      (= (:=color (first (:=layers p1__560115#))) :species)
      (= (:=target p1__560115#) :plotly)))
    v424_l5025)))

(def
  v427_l5038
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm {:color :species})
   (=+ (scatter) (linear))
   (target :plotly)
   plot))

(deftest
  t428_l5045
  (is
   ((fn*
     [p1__560116#]
     (and
      (map? p1__560116#)
      (contains? p1__560116# :data)
      (>= (count (:data p1__560116#)) 2)))
    v427_l5038)))

(def
  v430_l5059
  (->
   penguins
   (mapping :bill-length-mm nil)
   (histogram)
   (target :plotly)
   (size 500 400)
   plot))

(def
  v432_l5075
  (->
   penguins
   (mapping :bill-length-mm nil)
   (histogram {:bins 12})
   (facet {:col :species})
   (target :plotly)
   (size 900 350)
   plot))

(def
  v434_l5094
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (facet {:col :species})
   (target :plotly)
   (size 800 400)
   kind/pprint))

(deftest
  t435_l5102
  (is
   ((fn*
     [p1__560117#]
     (and
      (map? p1__560117#)
      (contains? p1__560117# :=layers)
      (= (:=target p1__560117#) :plotly)
      (= (:=col (first (:=layers p1__560117#))) :species)
      (= (:=width p1__560117#) 800)
      (= (:=height p1__560117#) 400)))
    v434_l5094)))

(def
  v437_l5110
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (facet {:col :species})
   (target :plotly)
   (size 800 400)
   plot))

(deftest
  t438_l5118
  (is
   ((fn*
     [p1__560118#]
     (and
      (map? p1__560118#)
      (contains? p1__560118# :data)
      (contains? p1__560118# :layout)
      (sequential? (:data p1__560118#))
      (> (count (:data p1__560118#)) 0)))
    v437_l5110)))

(def
  v440_l5135
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (scale :x {:domain [30 65]})
   (scale :y {:domain [10 25]})
   (target :plotly)
   kind/pprint))

(deftest
  t441_l5143
  (is
   ((fn*
     [p1__560119#]
     (and
      (map? p1__560119#)
      (contains? p1__560119# :=layers)
      (= (get-in p1__560119# [:=scale-x :domain]) [30 65])
      (= (:=target p1__560119#) :plotly)))
    v440_l5135)))

(def
  v443_l5149
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (scale :x {:domain [30 65]})
   (scale :y {:domain [10 25]})
   (target :plotly)
   plot))

(deftest
  t444_l5157
  (is
   ((fn*
     [p1__560120#]
     (and
      (map? p1__560120#)
      (contains? p1__560120# :layout)
      (get-in p1__560120# [:layout :xaxis :range])))
    v443_l5149)))

(def
  v446_l5175
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (facet {:row :island, :col :sex})
   (target :vl)
   (size 800 600)
   kind/pprint))

(deftest
  t447_l5183
  (is
   ((fn*
     [p1__560121#]
     (and
      (map? p1__560121#)
      (= (:=width p1__560121#) 800)
      (= (:=height p1__560121#) 600)
      (= (:=target p1__560121#) :vl)))
    v446_l5175)))

(def
  v449_l5189
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (facet {:row :island, :col :sex})
   (target :vl)
   (size 800 600)
   plot))

(deftest
  t450_l5197
  (is
   ((fn*
     [p1__560122#]
     (and
      (map? p1__560122#)
      (contains? p1__560122# :facet)
      (contains? p1__560122# :spec)))
    v449_l5189)))

(def
  v452_l5209
  (->
   penguins
   (mapping :bill-length-mm :bill-depth-mm)
   (scatter)
   (facet {:row :island, :col :sex})
   (target :vl)
   (plot {:width 800, :height 600})))

(def
  v454_l5233
  (validate
   Layer
   {:=data {:x [1 2 3], :y [4 5 6]},
    :=x :x,
    :=y :y,
    :=plottype :scatter,
    :=alpha 0.7}))

(def
  v456_l5243
  (validate
   Layer
   {:=data {:x [1 2 3]}, :=plottype :scatter, :=alpha 1.5}))

(def
  v458_l5251
  (validate Layer {:=data {:x [1 2 3]}, :=plottype :invalid-type}))

(def
  v460_l5258
  (validate-layer
   {:=data {:x [1 2 3], :y [4 5 6]}, :=x :x, :=plottype :scatter}))

(def
  v462_l5267
  (validate-layer
   {:=data {:x [1 2 3]}, :=x :x, :=y :y, :=plottype :scatter}))

(def
  v464_l5276
  (valid? Layer {:=data {:x [1 2 3]}, :=plottype :scatter}))

(def v465_l5278 (valid? Layer {:=plottype :invalid}))

(def v467_l5281 (validate Layer {:=plottype :invalid, :=alpha 2.0}))
