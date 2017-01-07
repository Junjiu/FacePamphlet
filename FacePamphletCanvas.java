/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		GLabel Message=new GLabel(msg);
		Message.setFont(MESSAGE_FONT);
		add(Message,LEFT_MARGIN+40,TOP_MARGIN+IMAGE_MARGIN+IMAGE_HEIGHT+100);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		int i=1;
		removeAll();
		GLabel label=new GLabel(profile.getName());
		label.setFont(PROFILE_NAME_FONT );
		add(label,LEFT_MARGIN,TOP_MARGIN);
		
		if(profile.getImage()!=null){
			
		
			GImage image=profile.getImage();
			image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image,LEFT_MARGIN,TOP_MARGIN+IMAGE_MARGIN);
			

		}
		else{
			add(new GRect(LEFT_MARGIN,TOP_MARGIN+IMAGE_MARGIN,IMAGE_WIDTH,IMAGE_HEIGHT));// pay attention on the order
			label=new GLabel("No Image");
			label.setFont(PROFILE_IMAGE_FONT);
			add(label,LEFT_MARGIN+IMAGE_WIDTH/4,IMAGE_HEIGHT/2+TOP_MARGIN+IMAGE_MARGIN);
			
		}
		
		if(profile.getStatus()!=null){
			label=new GLabel(profile.getStatus());
			label.setFont(PROFILE_STATUS_FONT );
			add(label,LEFT_MARGIN,TOP_MARGIN+IMAGE_MARGIN+STATUS_MARGIN+IMAGE_WIDTH);
		}
		
		label= new GLabel("Friend");
		label.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(label,LEFT_FRIEND,FRIEND_MARGIN);
		Iterator<String> it=profile.getFriends();//remember!
		while(it.hasNext()){
			label=new GLabel(it.next());
			label.setFont(PROFILE_FRIEND_FONT);
			add(label,LEFT_FRIEND,FRIEND_MARGIN+i*20);
			i++;
			
		}
		
	}
	public void test(){
		add(new GOval(30,30,30,30));
	}
	
}
