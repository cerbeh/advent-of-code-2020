(use '[clojure.string :only (join)])
(use '[lumo.util :only (line-seq)])

(def samples (line-seq "day-three-sample.txt"))

(defn loopPosition [landscape pos]
  (let [count (count landscape)]
  (if (true? (>= pos count))
    (- pos count)
    pos)))

(defn treeEncountered [location track]
  (when (= (nth track location) "#") 1))

(defn letsGoSledding [mountain pos travelDistance treesPassed]
  (let [landscape (first mountain)]
  (if (empty? mountain)
    treesPassed
    (recur
      (rest mountain)
      (loopPosition landscape (+ pos travelDistance))
      travelDistance
      (+ treesPassed (treeEncountered pos landscape))))))



(println (letsGoSledding samples 0 3 0) "Part One")
