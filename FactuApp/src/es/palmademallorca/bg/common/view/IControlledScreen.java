package es.palmademallorca.bg.common.view;

import es.palmademallorca.bg.factuapp.view.ScreensController;

public interface IControlledScreen {

    //This method will allow the injection of the Parent ScreenPane
    public void setScreenParent(ScreensController screenPage);
 }
