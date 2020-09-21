package Main;

/**
 *
 * @author yixuanhuangyx@gmail.com
 */
import com.sun.javafx.application.LauncherImpl;

import View.ApplicationApp;
import View.ApplicationPreloader;


public class Main {
	public static void main(String[] args) {
		LauncherImpl.launchApplication(ApplicationApp.class, ApplicationPreloader.class, args);
	}
}
