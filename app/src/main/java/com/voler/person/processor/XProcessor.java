package com.voler.person.processor;

import java.util.prefs.AbstractPreferences;
import java.util.prefs.BackingStoreException;

/**
 * XProcessor Created by voler on 2017/5/9.
 * 说明：
 */


public class XProcessor extends AbstractPreferences{
    /**
     * Creates a preference node with the specified parent and the specified
     * name relative to its parent.
     *
     * @param parent the parent of this preference node, or null if this
     *               is the root.
     * @param name   the name of this preference node, relative to its parent,
     *               or <tt>""</tt> if this is the root.
     * @throws IllegalArgumentException if <tt>name</tt> contains a slash
     *                                  (<tt>'/'</tt>),  or <tt>parent</tt> is <tt>null</tt> and
     *                                  name isn't <tt>""</tt>.
     */
    protected XProcessor(AbstractPreferences parent, String name) {
        super(parent, name);
    }

    @Override
    protected void putSpi(String key, String value) {

    }

    @Override
    protected String getSpi(String key) {
        return null;
    }

    @Override
    protected void removeSpi(String key) {

    }

    @Override
    protected void removeNodeSpi() throws BackingStoreException {

    }

    @Override
    protected String[] keysSpi() throws BackingStoreException {
        return new String[0];
    }

    @Override
    protected String[] childrenNamesSpi() throws BackingStoreException {
        return new String[0];
    }

    @Override
    protected AbstractPreferences childSpi(String name) {
        return null;
    }

    @Override
    protected void syncSpi() throws BackingStoreException {

    }

    @Override
    protected void flushSpi() throws BackingStoreException {

    }
}
