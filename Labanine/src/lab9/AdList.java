package lab9;

import java.util.HashSet;
import java.util.List;

public class AdList extends ListOfIdentifiables<Ad> {
	private static final long serialVersionUID = 882150501461356499L;
	
	public synchronized Ad addAd(User author, Ad ad){
		ad.setAuthorId(author.getId());
		ad.setAuthor(author);
		ad.setId(getNextId());
		items.add(ad);
		return ad;
	}
	public synchronized void updateAd(Ad ad){
		items.add(ad);
	}
	public synchronized void removeAd(Ad ad){
		items.remove(ad);
	}
	public synchronized HashSet<Ad> getAds(){
		return(HashSet<Ad>)items.clone();
	}
}
