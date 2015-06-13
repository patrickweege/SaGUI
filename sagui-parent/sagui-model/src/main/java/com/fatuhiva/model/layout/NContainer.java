package com.fatuhiva.model.layout;

public class NContainer<MGR extends NIFatuLayoutManager<?>> {

    MGR getLayout() {
        return null;
    }
    
    
    public static void main(String[] args) {
        NContainer<NManager> nContainer = new NContainer<NManager>();
        
        NManager layout = nContainer.getLayout();
        layout.setRule("", new NRule());
        
    }
    
    
}


