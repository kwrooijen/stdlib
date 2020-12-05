(ns kwrooijen.file
  (:require
   [clojure.java.io :as io]
   [kwrooijen.core :refer [apply-when negate]]))

(defn file? [f]
  (if (string? f)
    (.isFile (io/file f))
    (.isFile f)))

(defn directory? [f]
  (if (string? f)
    (.isDirectory (io/file f))
    (.isDirectory f)))

(defn get-name [file]
  (.getName file))

(defn get-absolute-name [file]
  (.getAbsolutePath file))

(defn files [directory]
  (->> directory
       (clojure.java.io/file)
       (file-seq)
       (mapv get-name)))

(defn tree
  ([dirpath]
   (->> dirpath
        (io/file)
        (file-seq)
        (keep (fn [file]
                (when (file? file)
                  (get-absolute-name file))))
        (vec)))
  ([dirpath pattern]
   (->> dirpath
        (io/file)
        (file-seq)
        (keep (fn [file]
                (let [name (get-absolute-name file)]
                  (when (and (file? file)
                             (re-matches pattern name))
                    name))))
        (vec))))

(defn file-hash [file]
  (-> file
      (slurp)
      (hash)
      (apply-when neg? negate)))

(defn jar [jar-file]
  (if (string? jar-file)
    (java.util.jar.JarFile. jar-file)
    jar-file))

(defn jar-files [jar-file]
  (->> (jar jar-file)
       (.entries)
       (enumeration-seq)
       (mapv get-name)))

(defn jar-file [jar-file file]
  (->> file
       (.getJarEntry (jar jar-file))
       (.getInputStream (jar jar-file))))
