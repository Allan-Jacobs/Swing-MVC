package com.redstoneblocks.java.swing_mvc.core;

/**
 * A base class to designate Models for MVC.
 */
public abstract class Model<T> {
    /**
     * This method should initialize the model with a specified state
     *
     * @param state the object (or null) to be passed to the next screen's model
     */
    public abstract void init(T state);
}
