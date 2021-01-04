package mjmw.projects.depthoffieldcalculator.model;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import mjmw.projects.depthoffieldcalculator.R;

public class LensManager implements Iterable<Lens> {

    private List<Lens> lenses = new ArrayList<>();

    private static LensManager instance;

    private LensManager(){
    }
    
    public static LensManager getInstance() {
        if(instance == null) {
            instance = new LensManager();
            instance.add(new Lens("Canon", 1.8, 50));
            instance.add(new Lens("Tamron", 2.8, 90));
            instance.add(new Lens("Sigma", 2.8, 200));
            instance.add(new Lens("Nikon", 4, 200));
        }
        return instance;
    }

    //Adding object lens to the array
    public void add(Lens lens) {
        lenses.add(lens);
    }

    public Lens getLens(int i) {
        return lenses.get(i);
    }

    @Override
    public Iterator<Lens> iterator() {
        return lenses.iterator();
    }
}
