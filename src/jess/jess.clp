(deftemplate JessACLMessage
    (declare(from-class message.JessACLMessage) ))

(deftemplate sensor (slot id) (slot value) (slot type) (slot zoneId) (slot date) (slot interpretedData))


(defrule electric-consume
    (sensor (value ?s) (type ?t &: (eq ?t "electric")))
    =>
    (printout t "Sensorul electric consuma " ?s)
    (send "Sensorul electric consuma " ?s)
    )

(defrule incomming-msg
    (JessACLMessage (sender ?s))
     (JessACLMessage (content ?content))
    =>
    (printout t "Just received a message from " (?s getLocalName) crlf)
    (printout t "Message content: " ?content  crlf)
    (send "Aggregated data to be parsed")
)