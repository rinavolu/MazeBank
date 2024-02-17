package com.bank.maze.mazebank.Models;

import com.bank.maze.mazebank.Views.ViewFactory;

public class Model {

    public static Model model;

    private final ViewFactory viewFactory;


    private Model() {
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
