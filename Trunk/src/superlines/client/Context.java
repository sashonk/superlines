/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package superlines.client;

import superlines.core.Authentication;
import superlines.core.SuperlinesContext;
import superlines.core.User;

/**
 *
 * @author Sashonk
 */
public class Context {
    private static Context instance;
    
    public static Context get(){
        if(instance == null){
            instance = new Context();
        }
        
        return instance;
    }
    
    public void setAuth(final Authentication value){
        m_auth = value;
    }
    
    public Authentication getAuth(){
        return m_auth;
    }
    
    public SuperlinesContext getSuperlinesContext(){
        return m_ctx;
    }
    
    public void setSuperlinesContext(final SuperlinesContext value){
        m_ctx = value;
    }
    
    public void setUser(final User value){
        m_user= value;
    }
    
    public User getUser(){
        return m_user;
    }
    
    public boolean isOffline(){
        return m_offline;
    }
    
    public void setOffline(boolean value){
        m_offline = value;
    }
    
    private Authentication m_auth;
    private SuperlinesContext m_ctx;
    private User m_user;
    private boolean m_offline;
}
