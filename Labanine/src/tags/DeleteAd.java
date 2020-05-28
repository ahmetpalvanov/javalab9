package tags;

import java.util.LinkedList;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import lab9.Ad;
import lab9.AdList;
import lab9.AdListHelper;
import lab9.User;

public class DeleteAd extends SimpleTagSupport {
	private Ad ad;
	public void setAd(Ad ad){
		this.ad=ad;
	}
	public void doTag(){
		String errorMessage=null;
		AdList adList=(AdList)getJspContext().getAttribute("ads",PageContext.APPLICATION_SCOPE);
		User currentUser=(User) getJspContext().getAttribute("authUser",PageContext.SESSION_SCOPE);
		if(currentUser==null||(ad.getId()>0&&ad.getAuthorId()!=currentUser.getId())){
			errorMessage="You havent required rights";
		}
		if(errorMessage==null){
			adList.removeAd(ad);
			AdListHelper.saveAdList(adList);
		}
		getJspContext().setAttribute("errorMessage", errorMessage,PageContext.SESSION_SCOPE);
	}
}
