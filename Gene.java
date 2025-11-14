
import java.util.*;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.scene.control.*;
import java.util.Map.*;
public class Gene extends TreeMap<Integer, ChemicalNode>
{
    // List of valid chemical bases
    private String[] bases = {"A", "T", "C", "G"};
    
    // add a new chemical node to the gene
    public String addChem(String base)
    {
        ChemicalNode node = new ChemicalNode(base);
        int key = size() + 1;
        put(key, node);
        return "Added additional " + base + " to gene.";
    }
    
    // add chemical when based on user input
    public String add_input()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Chemical Base");
        dialog.setHeaderText("Enter Chemical Base");
        dialog.setContentText("Please enter A, T, C, or G");
        Optional<String> optional = dialog.showAndWait();
        String base = optional.get();
        if (base.equals("A") || base.equals("C") || base.equals("G") || base.equals("T"))
        {
            return addChem(base);
        }
        return "Invalid Chemical Base Input";
    }
    
    // Remove the last chemical node from the gene
    public String removeChem()
    {
        if (isEmpty())
        {
            return "Genetic string is empty";
        }
        ChemicalNode lastNode = pollLastEntry().getValue();
        return "Removed additional " + lastNode.getType() + " from gene.";
    }
    
    // Mutate at certain given position and chemical base
    public String mutate(String base, int pos)
    {
        if (isEmpty())
        {
            return "Genetic string is empty";
        }
        Set set = entrySet();
        Iterator<Entry<Integer,ChemicalNode>> iter = set.iterator();
        String old_base = null;
        while (iter.hasNext())
        {
            Entry<Integer, ChemicalNode> e = iter.next();
            ChemicalNode node = e.getValue();
            old_base = e.getValue().getType();
            int curr_pos = e.getKey();
            if (curr_pos == pos)
            {
                node.setType(base);
                return "Changed from " + old_base + " to " + base + " at position " + pos;
            }
        }
        return "Mutation unsuccessful -  index out of range";
    }
    
    // Mutate chemical when based on user input
    public String mutate_input(int pos)
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Chemical Base");
        dialog.setHeaderText("Enter Chemical Base");
        dialog.setContentText("Please enter A, T, C, or G");
        Optional<String> optional = dialog.showAndWait();
        String base = optional.get();
        if (base.equals("A") || base.equals("C") || base.equals("G") || base.equals("T"))
        {
            return mutate(base, pos);
        }
        return "Invalid Chemical Base Input";
    }
    
    // Mutate at random position and random chemical base
    public String mutate_rand()
    {
        if (isEmpty())
        {
            return "Genetic string is empty";
        }
        Random rand = new Random();
        int rand_pos = rand.nextInt(size());
        int rand_base_ind = rand.nextInt(bases.length);
        String base = bases[rand_base_ind];
        Set<Entry<Integer,ChemicalNode>> set = entrySet();
        Iterator<Entry<Integer,ChemicalNode>> iter = set.iterator();
        String old_base = null;
        while (iter.hasNext())
        {
            Entry<Integer, ChemicalNode> e = iter.next();
            ChemicalNode node = e.getValue();
            old_base = node.getType();
            int curr_pos = e.getKey();
            if (curr_pos == rand_pos)
            {
                node.setType(base);
                return "Changed from " + old_base + " to " + base + " at position " + rand_pos;
            }
        }
        return "Mutation unsuccessful - index out of range";
    }
    
    // Mutate all chemical bases at all positions by random chemical base
    public String mutate_all()
    {
        if (isEmpty())
        {
            return "Genetic string is empty";
        }
        Random rand = new Random();
        String old_gene = fullGene();
        Set<Entry<Integer,ChemicalNode>> set = entrySet();
        Iterator<Entry<Integer,ChemicalNode>> iter = set.iterator();
        while (iter.hasNext())
        {
            Entry<Integer, ChemicalNode> e = iter.next();
            ChemicalNode node = e.getValue();
            int rand_base_ind = rand.nextInt(bases.length);
            String base = bases[rand_base_ind];
            node.setType(base);
        }
        String new_gene = fullGene();
        return "Changed gene from " + old_gene + " to " + new_gene;
    }
    
    // Calculate composition of each chemical base
    public String allChemBases()
    {
        int a = 0, t = 0, c = 0, g = 0;
        Collection<ChemicalNode> vals = values();
        Iterator<ChemicalNode> iter = vals.iterator();
        while (iter.hasNext())
        {
            ChemicalNode next = iter.next();
            if (next.getType().equals("A"))
            {
                a++;
            }
            else if (next.getType().equals("T"))
            {
                t++;
            }
            else if (next.getType().equals("C"))
            {
                c++;
            }
            else
            {
                g++;
            }
        }
        return "A: " + a + "/T: " + t + "/C: " + c + "/G: " + g;
    }
    
    // Retrieve the full gene as a string
    public String fullGene()
    {
        String fullGene = "";
        if (isEmpty())
        {
            return "N/A";
        }
        
        Collection<ChemicalNode> vals = values();
        Iterator<ChemicalNode> iter = vals.iterator();
        while (iter.hasNext())
        {
            ChemicalNode next = iter.next();
            fullGene +=  next.getType();  
        }
        return fullGene;
    }
    
    // Main method to test TreeMap operations
    public static void main(String[] args)
    {
        Gene g = new Gene();
        System.out.println(g.mutate("C", 2));
        System.out.println(g.mutate_rand());
        System.out.println(g.addChem("A"));
        System.out.println(g.addChem("A"));
        System.out.println(g.fullGene());
        System.out.println(g.addChem("A"));
        System.out.println(g.addChem("C"));
        System.out.println(g.addChem("G"));
        System.out.println(g.addChem("T"));
        System.out.println(g.fullGene());
        System.out.println(g.mutate("C", 2));
        System.out.println(g.mutate("C", 10));
        System.out.println(g.fullGene());
        System.out.println(g.mutate_rand());
        System.out.println(g.fullGene());
        System.out.println(g.removeChem());
        System.out.println(g.fullGene());
        System.out.println(g.mutate_all());
        System.out.println(g.fullGene());
        System.out.println(g.allChemBases());
    }
}
