package com.apptech.first.client.views;

import java.util.List;
import java.util.logging.Logger;


import com.apptech.first.client.cordova.plugins.UserSettingPlugin;
import com.apptech.first.shared.model.UserModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.BasicCell;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;

public class LoginViewImpl extends Composite implements LoginView{

	private Logger logger = Logger.getLogger("MSLApp.LoginViewImpl");
	private static LoginViewImplUiBinder uiBinder = GWT.create(LoginViewImplUiBinder.class);
	
	interface LoginViewImplUiBinder extends UiBinder<Widget, LoginViewImpl> {
	}

	private LoginView.Presenter presenter;
	private Storage stockStore;
	@UiField
	ScrollPanel listOfUsersScrollPanel;
	
	@UiField(provided=true)
	CellList<UserModel> listOfUsers;
	
	@UiField
	MTextBox serverUrlTextBox;
	
	public LoginViewImpl() {
		
		listOfUsers = new CellList<UserModel>(new BasicCell<UserModel>(){
			@Override
			public String getDisplayString(UserModel model) {
				return model.getName();
			}
			@Override
			public boolean canBeSelected(UserModel model) {
				return true;
			}
		});
		
		// Init the server url text box
		serverUrlTextBox = new MTextBox();
		initWidget(uiBinder.createAndBindUi(this));
		
		serverUrlTextBox.setVisible(true); // hide the server url text box when initialize
		
		// Init the text box content and handler
		stockStore = Storage.getLocalStorageIfSupported();
		String serverUrl = null;
		if (stockStore != null)
		{
			// Init the text box with the server url inside localstorage
			serverUrl = stockStore.getItem(UserSettingPlugin.APP_URL);
			logger.info("LoginViewImpl() serverUrl = " + serverUrl);
			// Set the default value if it is first time startup
			if (serverUrl == null || serverUrl.equals(""))
			{
				serverUrl = UserSettingPlugin.DefaultServerUrl;
				stockStore.setItem(UserSettingPlugin.APP_URL, serverUrl);
				logger.info("LoginViewImpl() Invalid serverUrl, set default url = " + serverUrl);
			}
			// Init the text box with the stored url
			serverUrlTextBox.setText(serverUrl);
		}
		
		// Register the handler to save the server url in localstorage
		serverUrlTextBox.addKeyUpHandler(new KeyUpHandler(){
			@Override
			public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				serverUrlTextBox.getElement().blur();
			}
			String serverUrl = serverUrlTextBox.getText();
			logger.info("serverUrlTextBox.KeyUpHandler.onKeyUp() Update serverUrl = " + serverUrl);
			// Save the server url in local storage
			stockStore.setItem(UserSettingPlugin.APP_URL, serverUrl);
		}});
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void populate(List<UserModel> list) {
		listOfUsers.render(list);
		listOfUsersScrollPanel.refresh();
		serverUrlTextBox.setVisible(false); // hide the url text box while user list display
	}
	
	@UiHandler("listOfUsers")
	void onUserSelected(CellSelectedEvent  e){
		if(presenter != null){
			presenter.onUserSelected(e.getIndex());
		}
	}
}
