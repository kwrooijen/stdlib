(ns kwrooijen.core)

(defn update-when [v pred & args]
  (if (pred v)
    (apply update v args)
    v))

(defn assoc-when [v pred & args]
  (if (pred v)
    (apply assoc v args)
    v))

(defn conj-when [v pred & args]
  (if (pred v)
    (apply conj v args)
    v))

(defn disj-when [v pred & args]
  (if (pred v)
    (apply disj v args)
    v))

(defn apply-when [v pred f]
  (if (pred v)
    (f v)
    v))

(defn drop-nth [n coll]
  (apply concat (update (split-at n coll) 1 rest)))

(defn negate [n]
  (* n -1))
