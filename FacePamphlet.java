/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;
import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		add(new JLabel("Name"),NORTH);
		
		NText=new JTextField(TEXT_FIELD_SIZE);
		NText.addActionListener(this);
		add(NText,NORTH);
		
		add(new JButton("Add"),NORTH);
		addActionListeners();
		
		add(new JButton("Delete"),NORTH);
		add(new JButton("Lookup"),NORTH);
		addActionListeners();
//===============UP IS NORTH==========BELOW IS WEST======================		
		SText=new JTextField(TEXT_FIELD_SIZE);
		SText.setActionCommand("Change Status");
		SText.addActionListener(this);
		add(SText,WEST);
		add(new JButton("Change Status"),WEST);

		
		PText=new JTextField(TEXT_FIELD_SIZE);
		PText.setActionCommand("Change Picture");
		PText.addActionListener(this);
		add(PText,WEST);
		add(new JButton("Change Picture"),WEST);
		
		
		FText=new JTextField(TEXT_FIELD_SIZE);
		FText.setActionCommand("Add Friend");
		FText.addActionListener(this);
		add(FText,WEST);
		add(new JButton("Add Friend"),WEST);
		addActionListeners();
		
		setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
		Canvas=new FacePamphletCanvas();
		add(Canvas);
		Data=new FacePamphletDatabase();
		
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Add"){
			Data.addProfile(NText.getText());
			User=Data.getProfile(NText.getText());
			Canvas.displayProfile(User);
		}
		/* this part is to delete,so we have to find out his friend ,and delete him */
		if(e.getActionCommand()=="Delete"){
			FacePamphletProfile remover=Data.getProfile(NText.getText());
			if(remover!=null){
				Iterator<String> it=remover.getFriends();
				while(it.hasNext()){
					FacePamphletProfile tempmove=Data.getProfile(it.next());
					while(!tempmove.removeFriend(NText.getText())){}
				}
				Canvas.displayProfile(User);
				Data.deleteProfile(NText.getText());
				if(User.getName()==NText.getText()){
					Canvas.removeAll();
				}
			}
			else{
				Canvas.showMessage("there is not this user");
			}
		}
		if(e.getActionCommand()=="Lookup"){
			if(Data.containsProfile(NText.getText())){
				Canvas.displayProfile(Data.getProfile(NText.getText()));
			}
			else{
				Canvas.showMessage("CAN NOT FIND THIS USER");
			}
		}
//=============UPPER THE LINE IS NORTH==========BELOW THE LINE IS WEST===============
		if(e.getActionCommand()=="Change Status"){
			User.setStatus(SText.getText());
			Canvas.displayProfile(User);
		}
		if(e.getActionCommand()=="Change Picture"){
			User.setImage(PText.getText());
			Canvas.displayProfile(User);
		}
		if(e.getActionCommand()=="Add Friend"){
			
			if(Data.getProfile(FText.getText())!=null){
				if(User.addFriend(FText.getText()) ){
				Data.getProfile(FText.getText()).addFriend(User.getName());
				Canvas.showMessage("You have add"+FText.getText()+"to be your friend");
				Canvas.displayProfile(User);
				}
				else{
					Canvas.showMessage("you have this friend");
				}	
			}
			else{
				Canvas.showMessage("Addtion fail");
			}
		}
	}
    private JTextField NText;
    private JTextField SText;
    private JTextField PText;
    private JTextField FText;
    private FacePamphletCanvas Canvas;
    private FacePamphletDatabase Data;
    private FacePamphletProfile User;
}
