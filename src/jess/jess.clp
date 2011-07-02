(deftemplate JessACLMessage
    (declare(from-class message.JessACLMessage) ))

(deftemplate bogdan (slot id) (slot content))

(defrule incomming-bogdan
    (bogdan (id ?id))
    =>
    (printout t "Just received a message from " ?id  crlf))


(defrule incomming-msg
    (JessACLMessage (sender ?s))
     (JessACLMessage (content ?content))
    =>
    (printout t "Just received a message from " (?s getLocalName) crlf)
    (printout t "Just received a message from " ?content  crlf)
)