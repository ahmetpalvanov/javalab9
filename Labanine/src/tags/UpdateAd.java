package tags;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import lab9.Ad;
import lab9.AdList;
import lab9.AdListHelper;
import lab9.User;

public class UpdateAd extends SimpleTagSupport {
	private Ad ad;
	public void setAd(Ad ad){
		this.ad=ad;
	}
	public void doTag() throws JspException, IOException{
		String errorMessage=null;
		AdList adList=(AdList) getJspContext().getAttribute("ads",PageContext.APPLICATION_SCOPE);
		User currentUser=(User) getJspContext().getAttribute("authUser",PageContext.SESSION_SCOPE);
		if(ad.getSubject()==null||ad.getSubject().equals("")){
			errorMessage="The title cannot be empty!";
		/*} else {
			if(currentUser==null||(ad.getId()>0&&ad.getAuthorId()!=currentUser.getId())){
				errorMessage="Access denied!";
			}*/
		}
		if(errorMessage==null){
			ad.setLastModifiedDate(Calendar.getInstance().getTimeInMillis());
			
			if(ad.getId()==0){
				adList.addAd(currentUser, ad);
			}
			else {
				adList.updateAd(ad);
			}
			AdListHelper.saveAdList(adList);
		}
		getJspContext().setAttribute("ads", adList);
		getJspContext().setAttribute("errorMessage",errorMessage,PageContext.SESSION_SCOPE);
			
	}
}
