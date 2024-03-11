package ExceptionPackage;

import java.util.Comparator;

import DAO.*;

public class PostComparator implements Comparator<Post>{

	public PostComparator() {
	}

	@Override
	public int compare(Post o1, Post o2) {
		return o1.getDatePost().compareTo(o2.getDatePost());
	}

}
