package com.redstoneblocks.java.swing_mvc.core;

/**
 * A base class to designate Models for MVC.
 */
public abstract class Model {
    /**
     * This method should initialize the model with a specified state
     *
     * @param state the object (or null) to be passed to the next screens model
     */
    public abstract void init(Object state);
}
