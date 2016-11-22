/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


/**
 *  @author Yaqueline
 * @author Alejandro
 */
public class Lado {
    private Object li;
    private Object ld;

    public Lado(Object li, Object ld) {
        this.li = li;
        this.ld = ld;
    }

    public Object getLi() {
        return li;
    }

    public Object getLd() {
        return ld;
    }

    public void setLi(Object li) {
        this.li = li;
    }

    public void setLd(Object ld) {
        this.ld = ld;
    }
    
    public String toString()
    {
        return "("+(String) li +" , "+ (String) ld+")";
    }
    
    public boolean equals(Lado l)
    {
        if (this.li.equals(l.li) && this.ld.equals(l.ld)) {
            return true;
        }
        else
        {
            return false;
        }
    }
}
