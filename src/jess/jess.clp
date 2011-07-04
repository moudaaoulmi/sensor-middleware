(deftemplate sensor (slot id) (slot value) (slot type) (slot zoneId) (slot date) (slot interpretedData))


(defrule electric-consume
    ?sensor <- (sensor (value ?s) (zoneId ?zoneId) (type ?t &: (eq ?t "electric")) (interpretedData ?id &: (eq ?id "consum ridicat")))
    =>
    (bind ?message (str-cat "Consum ridicat de energia in zona " ?zoneId ))
    (send ?message)
    (retract ?sensor)
    )

(defrule fire-warning
	  ?sensor1 <- (sensor (value ?s1) (zoneId ?zoneId1) (type ?t1 &: (eq ?t1 "temperature")) (interpretedData ?id1 &: (eq ?id1 "foarte cald")))
      ?sensor2 <- (sensor (type ?t &: (eq ?t "light")) (interpretedData ?id &: (eq ?id "luminozitate ridicata")))
    =>
    (bind ?message (str-cat "Pericol de incendiu in zona " ?zoneId1 ))
    (send ?message)
    (retract ?sensor1)
    (retract ?sensor2)
)