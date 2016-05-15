package br.com.gestaoambiental.util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagemUtil {

	private static ResourceBundle bundle = ResourceBundle
			.getBundle("resource/messages_pt_BR");

	public static void errorMsg(String msg) {

		msg = bundle.getString(msg);

		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,
				msg);

		FacesContext fc = FacesContext.getCurrentInstance();
		
		fc.getExternalContext().getFlash().setKeepMessages(true);

		fc.addMessage("erro", fm);

	}

	public static void warnMsg(String msg) {

		msg = bundle.getString(msg);

		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);

		FacesContext fc = FacesContext.getCurrentInstance();
		
		fc.getExternalContext().getFlash().setKeepMessages(true);

		fc.addMessage("warn", fm);

	}

	public static void infoMsg(String msg) {

		msg = bundle.getString(msg);

		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);

		FacesContext fc = FacesContext.getCurrentInstance();
		
		fc.getExternalContext().getFlash().setKeepMessages(true);

		fc.addMessage("info", fm);

	}

}
