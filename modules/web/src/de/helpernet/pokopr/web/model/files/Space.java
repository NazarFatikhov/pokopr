package de.helpernet.pokopr.web.model.files;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;

@MetaClass(name = "pokopr_Space")
public class Space extends StandardEntity {

    private String name;

    private boolean isArchived;

    public Space(String name, boolean isArchived) {

        this.name = name;
        this.isArchived = isArchived;
    }

    @MetaProperty
    public String getName() {

        return name;
    }

    public Space setName(String name) {

        this.name = name;
        return this;
    }

    @MetaProperty
    public Boolean getIsArchived() {

        return isArchived;
    }

    public Space setArchived(boolean archived) {

        isArchived = archived;
        return this;
    }
}
