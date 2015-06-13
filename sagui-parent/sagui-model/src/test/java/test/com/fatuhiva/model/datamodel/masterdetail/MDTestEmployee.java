package test.com.fatuhiva.model.datamodel.masterdetail;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author patrick.weege
 * POJO Used for Master Detail Table Model Test
 *
 */
public class MDTestEmployee {
    
    private Integer id;
    private String name;
    private List<MDTestEmployee> subordinates;
    
    
    public MDTestEmployee() {
        this.subordinates = new ArrayList<MDTestEmployee>();
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<MDTestEmployee> getSubordinates() {
        return subordinates;
    }
    
    public void setSubordinates(List<MDTestEmployee> subordinates) {
        this.subordinates = subordinates;
    }
    
}
