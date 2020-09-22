package main;

/**
 *
 * @author yixuanhuangyx@gmail.com
 */
import com.sun.javafx.application.LauncherImpl;

import view.ApplicationApp;
import view.ApplicationPreloader;


public class Main {
	public static void main(String[] args) {
		LauncherImpl.launchApplication(ApplicationApp.class, ApplicationPreloader.class, args);
	}
}
