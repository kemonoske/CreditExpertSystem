package com.systems.expert.custom.Navigator;

/**
 * Created by Kage on 2/19/14.
 */
public interface Navigable {

    public void goToPage(String path);

    public void pushTo(String path, String name);

    public void pop();

}
