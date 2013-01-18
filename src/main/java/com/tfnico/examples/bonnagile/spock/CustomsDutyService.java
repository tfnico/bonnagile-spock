package com.tfnico.examples.bonnagile.spock;

/**
 * Created with IntelliJ IDEA.
 * User: tfnico
 * Date: 1/18/13
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
class CustomsDutyService {

    private App app;

    void setApp(App app) {
        this.app = app;
    }

    public void doTheFoo() {
        app.didIt();
    }
}
