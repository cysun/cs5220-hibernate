package cs520.hibernate.account;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CD")
public class CDAccount extends Account {

    private static final long serialVersionUID = 1L;

    private Integer term;

    public CDAccount()
    {
    }

    public Integer getTerm()
    {
        return term;
    }

    public void setTerm( Integer term )
    {
        this.term = term;
    }

}
