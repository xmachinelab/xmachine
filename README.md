# xmachine
Finite state machine based complex event processing engine

<pre>
  
  Think of a basic state machine..
  
          t1 (c,a)            t2          t3
  Start ----------> s1 (a) --------> s2--------> Final

  * For each state describes "status" of stored "actor" variable. 
  * For each transaction Tx (t1, t2, t3 ..) explains going from one "state" to other "state"
  * Everytime entering on state "action list" runs for the state (a)
  * Everytime while execution of transition "condition list" executed  (c)
  * If condition list is successfull, transition "action list" executed (a)
  * Transitions process via external triggers named "Events" holding some sort "actor" based context
  * Whole system works on basis "indexing" mechanism
  * Every member of machine has an index
  * Many machines can be run at a time with a single event
  * Some transitions can have same event some different
  * "*" event means accepts "ANY" events
  * Conditions and actions can run "Expressionist" expressions, which is integrated to as a library
  * Actions can call each other
  
</pre>
