package test.com.fatuhiva.model.datamodel.masterdetail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

public class TestUnmodifiableCollection {
	
	public static void main(String[] args) {
		
		List<String> l = new ArrayList<String>();
		l.add("A");
		
		List<String> roList = ListUtils.unmodifiableList(l);
		
		l.add("B");
		
		System.out.println(roList);
		
		
		
		
	}

}
