(use '[clojure.string :only (split)])
(use '[lumo.util :only (line-seq)])

(def samples (line-seq "day-two-sample.txt"))

(defn ruleOne [range letter password]
  (let [allLetterCount (frequencies password)
        restrictionCount (get allLetterCount letter)]
          (and (>= restrictionCount (first range)) (<= restrictionCount (second range)))))

(defn ruleTwo [range letter password]
  (let [x [(= letter (get password (- (first range) 1))) (= letter (get password (- (second range) 1)))]]
    (and (some true? x) (not-every? true? x))))

(def rules {:ruleOne ruleOne :ruleTwo ruleTwo})

(defn passwordIsValid [password validator]
  (let [splitString (split password #" ")
        range (split (first splitString) #"-")
        letter (first (second splitString))
        password (nth splitString 2)]
          (validator range letter password)))

(defn addIfValid [password ruleName]
  (if (true? (passwordIsValid password (get rules (keyword ruleName)))) 1 0))

(defn countValidPasswords [list count rule]
  (if (empty? list)
    count
    (recur (rest list) (+ count (addIfValid (first list) rule)) rule)))

; Part One
(println (countValidPasswords samples 0 "ruleOne") "Part One")
(println (countValidPasswords samples 0 "ruleTwo") "Part Two")
