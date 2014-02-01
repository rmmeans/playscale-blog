package controllers;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.commons.codec.digest.DigestUtils;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result index(Long var) throws NoSuchAlgorithmException, NoSuchProviderException {
    	try {
    		Thread.sleep(1200);
    	} catch (Exception e) {
    		//interrupted.
    	}
    	
    	String salt = Play.application().configuration().getString("application.secret", "NOSALT");
    	String digest = DigestUtils.sha512Hex(salt.concat(var.toString()));
    	
    	return ok(views.html.index.render(var, digest));
    }
}
