package sy.comparator;

import java.util.Comparator;

import sy.model.demo.Tmenu;

/**
 * 菜单排序
 * 
 * @author 孙宇
 * 
 */
public class MenuComparator implements Comparator<Tmenu> {

	public int compare(Tmenu o1, Tmenu o2) {
		int i1 = o1.getCseq() != null ? o1.getCseq().intValue() : -1;
		int i2 = o2.getCseq() != null ? o2.getCseq().intValue() : -1;
		return i1 - i2;
	}
}
