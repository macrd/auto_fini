package src;

import java.util.*;

/**
 * classe Transitions
 *
 */
public class Transitions<S>
{
    private HashSet<Transition<S>> SetofTransitions; 
    
    /**
     * Constructeur d'objets de classe Transitions initialement vide
     */
    public Transitions()
    {
      this.SetofTransitions = new HashSet<Transition<S>>();  
    }
    
    /**
     * Constructeur d'objets de classe Transitions à partir d'un ensemble de transitions
     */
    public Transitions(HashSet<Transition<S>> T)
    {
      this.SetofTransitions = T;  
    }

    
    /**
     * Ajout d'une transition 
     */
    public void addTransition(Transition<S> t)
    {
      this.SetofTransitions.add(t);   
    }
    
    /**
     * Retourne l'ensemble des transitions représenté  
     */
    public HashSet<Transition<S>> getSetofTransitions()
    {
      return this.SetofTransitions;   
    }
    
    /**
     * Successeurs de l'état s et de la lettre a par les transitions
     */
    States<S> successor(S s,Letter a)
    {
     States<S> Targets = new States<>();
     Iterator<Transition<S>> AllTransitions = this.SetofTransitions.iterator();
     while(AllTransitions.hasNext()){
         Transition<S> state = AllTransitions.next();
         if(state.getSource().toString().equals(s.toString()) &&
                 state.getLabel().toString().equals(a.toString())){
             Targets.addState(state.getTarget());
         }
     }
     return Targets; 
    }
    
 /**
     * successeurs de l'ensemble d'états S et de la lettre a par les 
     * transitions
     */  
    States<S> successors(States<S> set,Letter a)
    {
        States<S> allTransition = new States<>();
        Iterator<S> count = set.iterator();
        while (count.hasNext()){
            allTransition.addAllStates(successor(count.next(),a));
        }
        return allTransition;
    }
    
 }
