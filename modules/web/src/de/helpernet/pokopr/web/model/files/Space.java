package de.helpernet.pokopr.web.model.files;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;

import de.helpernet.pokopr.integration.teamdrive.client.dto.SpaceDto;
import de.helpernet.pokopr.integration.teamdrive.client.dto.SpaceStatus;

@MetaClass(name = "pokopr_Space")
public class Space extends StandardEntity {

    private String name;

    private boolean isArchived;

    public Space(String name, boolean isArchived) {

        this.name = name;
        this.isArchived = isArchived;
    }

    public static Space from(SpaceDto spaceDto) {
        return new Space(spaceDto.getName(), spaceDto.getStatus().equals(SpaceStatus.ACTIVE));
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
