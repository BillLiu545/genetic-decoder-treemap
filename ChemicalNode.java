
public class ChemicalNode implements Comparable<ChemicalNode>
{
    private String type;
    
    public ChemicalNode(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return this.type;
    }
    
    public void setType(String newType)
    {
        this.type = newType;
    }
    
    public int compareTo(ChemicalNode other)
    {
        if (this.type.equals(other.getType()))
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}