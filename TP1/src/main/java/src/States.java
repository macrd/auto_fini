package src;

import java.util.*;

/**
 * classe States 
 *
 */
public class States<S>
{
    private HashSet<S> SetOfStates;

    /**
     * Constructeur d'objets de classe States
     * vide
     */
    public States()
    {
        this.SetOfStates = new  HashSet<>();
    }


    /**
     * Constructeur d'objets de classe States
     * a partir d'un ensemble d'états de type S
     */
    public States(HashSet<S> Q)
    {
        this.SetOfStates = Q;
    }

    /**
     * accesseur pourSetofStates
     */
    public HashSet<S> getSetOfStates()
    {
        return this.SetOfStates;
    }
    
    /**
     * Ajout d'un état 
     */
    public void addState(S s)
    {
      this.SetOfStates.add(s);
    }
    
    /**
     * Union de deux States 
     */
    public void addAllStates(States set)
    {
     this.SetOfStates.addAll(set.getSetOfStates());
    }
    
    
    /**
     * iterateur pour le type States
     */
    public Iterator<S> iterator() 
    {
     return this.SetOfStates.iterator();
    }
    
    
    /**
     * representation en String d'un ensemble d'états 
     */
    public String toString()
    {
     Iterator<S> AllStates = this.SetOfStates.iterator();
     String Output = "[ ";
     
     while (AllStates.hasNext())
     {  
       S etat = AllStates.next();
       Output = Output + etat.toString()+ " ";     
     }
        
     Output = Output+"]";
        
     return Output; 
    }
    
    
    
}
