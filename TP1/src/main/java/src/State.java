package src;

/**
 * classe State
 *
 */
public class State
{
    private String name;

    /**
     * Constructeur d'objets de classe State
     */
    public State(String name)
    {
        this.name = name;
    }

    /**
     * accesseur 
     */
    public String getName()
    {
        return this.name;
    }
    
    public String toString()
    {
        return this.name;
    }
}
