package src;

import java.util.*;

/**
 * Automate
 *
 */

public class Automate {
    public static void main (String[] args) {
     // création de lettres 
     Letter letter1 = new Letter("a"); 
     Letter letter2 = new Letter("b");
     
     // création d'états 
     State state1 = new State("q_1"); 
     State state2 = new State("q_2");
     State state3 = new State("q_3");
     
     // Création d'un objet de type States 
     States<State> Q = new States<State>();  
     Q.addState(state1);
     Q.addState(state2);
     Q.addState(state3);
     
     System.out.println(Q.toString());
     
     
     //création des transitions 
     Transition t1 = new Transition(state1,letter1,state1); 
     Transition t2 = new Transition(state1,letter1,state2);
     Transition t3 = new Transition(state1,letter2,state3);
     Transition t4 = new Transition(state3,letter2,state3);
     Transition t5 = new Transition(state3,letter1,state2);
    
     //création de l'ensemble des  transitions
     Transitions<State> Delta = new Transitions<State>();
     Delta.addTransition(t1);
     Delta.addTransition(t2);
     Delta.addTransition(t3);
     Delta.addTransition(t4);
     Delta.addTransition(t5);
    
    //affiche les successeurs par a de l'état q_1
    States E1 = Delta.successor(state1,letter1);
    System.out.println(E1.toString());
    
    //affiche les successeurs par a de l'état q_2
    States E2 = Delta.successor(state2,letter1);
    System.out.println(E2.toString());
    
    //affiche les successeurs par a de l'état q_3
    States E3 = Delta.successor(state3,letter1);
    System.out.println(E3.toString());
    
    //affiche les successeurs par b de {q_1, q_2}
    HashSet<State> H = new HashSet<State>();
    H.add(state1);
    H.add(state2);
    States<State> S1 = new States<State>(H);
    States E4 = Delta.successors(S1,letter2); 
    System.out.println(S1.toString());
    System.out.println(E4.toString());
     
    }
}
