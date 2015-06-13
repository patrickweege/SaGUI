package test.com.fatuhiva.model.pages.masterDetail;

import java.util.ArrayList;
import java.util.List;


public class Project {
    
    private Integer id;
    private String name;
    private List<ProjectMember> members;

    public Project() {
        this.members = new ArrayList<ProjectMember>();
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
    
    public List<ProjectMember> getMembers() {
        return members;
    }
    
    public void setMembers(List<ProjectMember> members) {
        this.members = members;
    }
    
    
    

}
