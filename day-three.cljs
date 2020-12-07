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

(defn letsGoSledding [mountain currentPosition travelDistance treesPassed]
  (let [landscape (first mountain)]
  (if (< (count mountain) (:down travelDistance))
    treesPassed
    (recur
      (subvec mountain (:down travelDistance))
      (loopPosition landscape (+ currentPosition (:right travelDistance)))
      travelDistance
      (+ treesPassed (treeEncountered currentPosition landscape))))))

(defn countTreesOnSlope [directions]
  (letsGoSledding samples 0 directions 0))

; Part One
(println (countTreesOnSlope {:right 3 :down 1}) "Part One")
; Part Two
(println
  (*
    (countTreesOnSlope {:right 1 :down 1})
    (countTreesOnSlope {:right 3 :down 1})
    (countTreesOnSlope {:right 5 :down 1})
    (countTreesOnSlope {:right 7 :down 1})
    (countTreesOnSlope {:right 1 :down 2})) "Part Two")
