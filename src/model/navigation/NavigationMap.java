package model.navigation;

import java.util.List;

public class NavigationMap {
    private List<Harbor> harbors;
    private Harbor currentHarbor;
    
    public NavigationMap(Harbor currentHarbor, List<Harbor> harbors){
        this.currentHarbor = currentHarbor;
        this.harbors = harbors;
    }

    public List<Harbor> getHarbors(){
        return harbors;
    }
    
    public Harbor getCurrentHarbor(){
        return currentHarbor;
    }
}
